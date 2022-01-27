package gui;

import game.Directions;
import game.Game;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.DepthTest;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class App extends Application {

    //TODO: init()

    Scene scene;
    GridPane grid;

    boolean isX = true;
    int highlightedGridX = 1;
    int highlightedGridY = 1;

    int newHighlightedColumn = 1;
    int newHighlightedRow = 1;

    Label playerX;
    Label playerO;

    Game game;

    Table table;
    int counter = 0;

//    private void addToTable(){
//
//    }

    static long sec, milisec, totalMilisec;

    public static void convertTime(){
        sec = TimeUnit.MILLISECONDS.toSeconds(totalMilisec);
        milisec = totalMilisec - (sec * 1000);
        System.out.println(sec + ":" + milisec / 100);
        totalMilisec-=100;
    }

    private void draw(Button btn){
        if(isX){
//            Text text = new Text("X");
            btn.setText("X");
            btn.setStyle("-fx-text-fill: #6600ff; -fx-font-size: 24px");
            playerO.setStyle("-fx-font-size: 22px; -fx-background-color: yellow");
            playerX.setStyle("-fx-font-size: 22px; -fx-background-color: none");
        }
        else {
            btn.setText("O");
            btn.setStyle("-fx-text-fill: #04ff00; -fx-font-size: 24px");
            playerX.setStyle("-fx-font-size: 22px; -fx-background-color: yellow");
            playerO.setStyle("-fx-font-size: 22px; -fx-background-color: none");
        }
//        btn.setFont(Font.font(23));


    }

    private void changeHighlight(){
        if (!(newHighlightedRow == highlightedGridX && newHighlightedColumn == highlightedGridY)){
            GridPane toHighlight = null;
            GridPane highlighted = null;
            ObservableList<Node> childrens = grid.getChildren();

            for (Node node: childrens){
                if(GridPane.getRowIndex(node) == newHighlightedRow && GridPane.getColumnIndex(node) == newHighlightedColumn) {
                    toHighlight = (GridPane) node;
                    break;
                }
            }

            for (Node node: childrens){
                if(GridPane.getRowIndex(node) == highlightedGridX && GridPane.getColumnIndex(node) == highlightedGridY) {
                    highlighted = (GridPane) node;
                    break;
                }
            }


            ObservableList<Node> childrens2;
            if (toHighlight != null){
                childrens2 = toHighlight.getChildren();

                for (Node node: childrens2){
                    node.setDisable(false);
                }
            }

            ObservableList<Node> childrens3;
            if (highlighted != null){
                childrens3 = highlighted.getChildren();

                for (Node node: childrens3){
                    node.setDisable(true);
                }
            }

            highlightedGridX = newHighlightedRow;
            highlightedGridY = newHighlightedColumn;

        }

    }

    public void makeDisable(int x, int y, char c){
        GridPane gridPane = null;
        ObservableList<Node> childrens = grid.getChildren();

        for (Node node: childrens){
            if(GridPane.getRowIndex(node) == x && GridPane.getColumnIndex(node) == y) {
                gridPane = (GridPane) node;
                break;
            }
        }

        Text text = new Text(String.valueOf(c));

        if (c == 'X') {
            gridPane.setStyle("-fx-background-color: #af7aff");
            //TODO: nie dziala zmiana na niebieski :(((
//            text.setStyle("-fx-text-fill: blue");
        } else {
            gridPane.setStyle("-fx-background-color: #84faa2");
            text.setStyle("-fx-text-fill: gray");

        }
        text.setStyle("-fx-font-weight: bolder");
        text.setFont(Font.font(40));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text, 1, 1);
    }

    private void createBigBoard(){
        game = new Game(this);
        grid = new GridPane();


        for (int blockColumn = 0; blockColumn < 3 ; blockColumn++) {
            for (int blockRow = 0; blockRow < 3; blockRow++) {


                GridPane box = new GridPane();
                box.setStyle("-fx-background-color: darkgray; -fx-background-insets: 0, 2; -fx-padding: 3;");
//                box.setStyle("-fx-background-color: black, -fx-control-inner-background; -fx-background-insets: 0, 2; -fx-padding: 2;");


                for (int column = 0; column < 3; column++) {
                    for (int row = 0 ; row < 3; row++) {

                        Button smallBox = new Button();
                        smallBox.setLayoutX(row);
                        smallBox.setLayoutY(column);



                        if (blockColumn == highlightedGridY && blockRow == highlightedGridX){
                            smallBox.setDisable(false);
                        }
                        else {
                            smallBox.setDisable(true);
                        }
                        smallBox.setStyle("-fx-pref-width: 50px; -fx-pref-height: 50px");

                        GridPane.setConstraints(smallBox, column, row);
                        box.getChildren().add(smallBox);

                        smallBox.setOnMouseClicked(event -> {
                            totalMilisec = 6000;
                            Random random = new Random();
                            if(smallBox.getText() != "X" && smallBox.getText() != "O"){
                                newHighlightedRow = GridPane.getRowIndex(smallBox);
                                newHighlightedColumn = GridPane.getColumnIndex(smallBox);


                                // TODO: jesli allOccupied ale to jest tez wsm przypadek gdy SB wygrane przez X lub O
                                // np !game.won
                                // TODO: spr czy wszystkie small nie sa occupied (bo tutaj spr w 1 konkretnym small,
                                // a chodzi zeby wychwycic przypadek konca gry jako remis)
                                // np. && !game.all()

                                if (!game.isAllOccupied()){
//                                    System.out.println("elo if");
//                                    System.out.println(newHighlightedRow+ " " + newHighlightedColumn );
//                                    System.out.println(game.isAllOccupiedInSmallBoard(newHighlightedRow, newHighlightedColumn));
                                    while(game.isAllOccupiedInSmallBoard(newHighlightedRow, newHighlightedColumn) || game.isWon(newHighlightedRow, newHighlightedColumn)){
                                        System.out.println("elo while");
                                        newHighlightedRow = random.nextInt(2);
                                        newHighlightedColumn = random.nextInt(2);
                                    }
                                }
                                else {
                                    //TODO: przemyslec czy wsm nie inna metoda do remisu
                                    game.gameOver();
                                }


                                // obecnie jest blokada - ze nie moge isc w sr jak jest cale pelne
                                // TODO: dowolny ruch dla drugiego gracza
//                                else{

//                                }
                                counter++;
                                char x = isX ? 'X' : 'O';

                                table.addToTable(String.valueOf(counter), String.valueOf(x), String.valueOf(Directions.values()[3 * highlightedGridX + highlightedGridY]),
                                        String.valueOf(Directions.values()[3 * newHighlightedRow + newHighlightedColumn]));
                                game.nextMove(newHighlightedRow, newHighlightedColumn, highlightedGridX, highlightedGridY, isX);
                                changeHighlight();
                                draw(smallBox);
                                isX = !isX;
                            }

                        });

                    }
                }

                GridPane.setConstraints(box, blockColumn, blockRow);
                grid.getChildren().add(box);

            }
        }

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        totalMilisec = 6000;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Sekunda!");
                convertTime();
                if(totalMilisec <= 0){
                    draw(new Button());
                    isX = !isX;
                    totalMilisec = 6000;
                }

            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 100);



        createBigBoard();
        Label turn = new Label(" Now, it's your turn : ");
        turn.setStyle("-fx-font-weight: 700; -fx-font-size: 30px");
        turn.setAlignment(Pos.CENTER);
        playerX = new Label("  Player X  ");
        playerX.setStyle("-fx-font-size: 22px; -fx-background-color: yellow");
        playerO = new Label("  Player O  ");
        playerO.setStyle(" -fx-font-size: 22px");

        table = new Table();

        HBox players = new HBox(playerX, playerO);
        VBox playersBox = new VBox(turn, players, grid);
        playersBox.setSpacing(10);
        playersBox.setPadding(new Insets(10, 0, 0, 10));
        players.setAlignment(Pos.BASELINE_CENTER);
        HBox allElements = new HBox(playersBox, table.getVbox());
        scene = new Scene(allElements, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();




    }
}

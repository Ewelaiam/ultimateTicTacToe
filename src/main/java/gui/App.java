package gui;

import game.Directions;
import game.Game;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class App extends Application {

    //TODO: init()

    Button startBtn;
    VBox vboxScene1;
    Scene scene1;

    Label title;
    Label subtitle;
    CheckBox checkBox;
    Label turn;

    HBox players;
    VBox playersBox;

    VBox rightColumn;
    HBox allElements;

    Scene scene;
    GridPane grid;

    Label playerX;
    Label playerO;

    Game game;

    Table table;
    int counter = 0;

    Label timerText;
    Text timeToEnd;

    Spinner<Integer> spinner;

    boolean isX = true;
    int highlightedGridX = 1;
    int highlightedGridY = 1;

    int newHighlightedColumn = 1;
    int newHighlightedRow = 1;

    int clickedX;
    int clickedY;
    boolean isClicked = false;

    private long sec, milisec, totalMilisec;

    Stage stage;


    private void createScene1(){
        title = new Label("Ultimate Tic Tac Toe");
        title.setStyle("-fx-font-weight: bolder; -fx-font-size: 40px");
        title.setPadding(new Insets(0, 0,20,0));

        subtitle = new Label("Set time for move (seconds): ");
        subtitle.setStyle("-fx-font-size: 20px");

        checkBox = new CheckBox();

        spinner = new Spinner<>(1,60,10);
        spinner.setVisible(false);
        spinner.setStyle("-fx-font-size: 15px");


        EventHandler<ActionEvent> event = e -> {
            if (checkBox.isSelected()) {
                spinner.setVisible(true);
            } else
                spinner.setVisible(false);
        };

        checkBox.setOnAction(event);

        startBtn = new Button("Start");
        startBtn.setStyle("-fx-font-size: 20px");
        vboxScene1 = new VBox(title, subtitle, checkBox, spinner, startBtn);
        vboxScene1.setAlignment(Pos.CENTER);

        scene1 = new Scene(vboxScene1, 600, 600);
    }

    public void createEndScene(char c){
        Text text = new Text("Koniec gry");
        text.setStyle("-fx-font-size: 40px");
        Text text1 = new Text("Wygrana gracza: " + c);
        text1.setStyle("-fx-font-size: 20px");
        VBox endScene = new VBox(text, text1);
        Scene scene3 = new Scene(endScene, 300, 200);

        stage.setScene(scene3);
    }

    private void createPlayers(){
        turn = new Label(" Now, it's your turn : ");
        turn.setStyle("-fx-font-weight: 700; -fx-font-size: 30px");
        turn.setAlignment(Pos.CENTER);

        playerX = new Label("  Player X  ");
        playerX.setStyle("-fx-font-size: 22px; -fx-background-color: yellow");
        playerO = new Label("  Player O  ");
        playerO.setStyle(" -fx-font-size: 22px");

        players = new HBox(playerX, playerO);
        playersBox = new VBox(turn, players, grid);
        playersBox.setSpacing(10);
        playersBox.setPadding(new Insets(10, 0, 0, 10));
        players.setAlignment(Pos.BASELINE_CENTER);

    }

    private void putElementInOrder(){
        rightColumn = new VBox(table.getVbox(), timerText, timeToEnd);
        allElements = new HBox(playersBox, rightColumn);
    }


    private void setTimer(){
        timerText = new Label("Timer: ");
        timerText.setStyle("-fx-font-weight: 700; -fx-font-size: 30px");
        timerText.setPadding(new Insets(20, 0, 0, 20));
        timeToEnd = new Text();
        timeToEnd.setStyle("-fx-font-weight: 700; -fx-font-size: 30px");


        totalMilisec = spinner.getValue() * 1000;
        System.out.println(spinner.getValue());
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                convertTime();
                if(totalMilisec <= 0){
                    draw(new Button());
                    isX = !isX;
                    totalMilisec = spinner.getValue() * 1000;

                }

            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 100);

    }

    public void convertTime(){
        sec = TimeUnit.MILLISECONDS.toSeconds(totalMilisec);
        milisec = totalMilisec - (sec * 1000);

        timeToEnd.setText("   " + sec + ":" + milisec / 100);
        totalMilisec-=100;
    }

    private void draw(Button btn){
        if(isX){
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


    }

    private void changeHighlight(){
        if (!(newHighlightedRow == highlightedGridX && newHighlightedColumn == highlightedGridY)){
            GridPane toHighlight = null;
            GridPane highlighted = null;
            ObservableList<Node> children = grid.getChildren();

            for (Node node: children){
                if(GridPane.getRowIndex(node) == newHighlightedRow && GridPane.getColumnIndex(node) == newHighlightedColumn) {
                    toHighlight = (GridPane) node;
                    break;
                }
            }

            for (Node node: children){
                if(GridPane.getRowIndex(node) == highlightedGridX && GridPane.getColumnIndex(node) == highlightedGridY) {
                    highlighted = (GridPane) node;
                    break;
                }
            }

            ObservableList<Node> children2;
            if (toHighlight != null){
                children2 = toHighlight.getChildren();

                for (Node node: children2){
                    node.setDisable(false);
                }
            }

            ObservableList<Node> children3;
            if (highlighted != null){
                children3 = highlighted.getChildren();

                for (Node node: children3){
                    node.setDisable(true);
                }
            }

            highlightedGridX = newHighlightedRow;
            highlightedGridY = newHighlightedColumn;

        }

    }


    public void makeDisable(int x, int y, char c){
        GridPane gridPane = null;
        ObservableList<Node> children = grid.getChildren();

        for (Node node: children){
            if(GridPane.getRowIndex(node) == x && GridPane.getColumnIndex(node) == y) {
                gridPane = (GridPane) node;
                break;
            }
        }

        Text text = new Text(String.valueOf(c));

        if (c == 'X') {
            gridPane.setStyle("-fx-background-color: #af7aff");

        } else {
            gridPane.setStyle("-fx-background-color: #84faa2");
        }

        text.setStyle("-fx-font-weight: bolder");
        text.setFont(Font.font(40));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text, 1, 1);
    }

    private void createScene2(Stage primaryStage){
        game = new Game(this);
        grid = new GridPane();


        for (int blockColumn = 0; blockColumn < 3 ; blockColumn++) {
            for (int blockRow = 0; blockRow < 3; blockRow++) {

                GridPane box = new GridPane();
                box.setStyle("-fx-background-color: darkgray; -fx-background-insets: 0, 2; -fx-padding: 3;");

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
                            if(smallBox.getText() != "X" && smallBox.getText() != "O") {

                                totalMilisec = spinner.getValue() * 1000;
                                Random random = new Random();

                                newHighlightedRow = GridPane.getRowIndex(smallBox);
                                newHighlightedColumn = GridPane.getColumnIndex(smallBox);


                                // TODO: jesli allOccupied ale to jest tez wsm przypadek gdy SB wygrane przez X lub O
                                // np !game.won

                                if (!game.isAllOccupied()) {
                                    // obecnie jest blokada - ze nie moge isc w sr jak jest cale pelne
                                    // TODO: dowolny ruch dla drugiego gracza :(
                                    clickedX = newHighlightedRow;
                                    clickedY = newHighlightedColumn;

                                    while (game.isAllOccupiedInSmallBoard(newHighlightedRow, newHighlightedColumn) || game.isWon(newHighlightedRow, newHighlightedColumn)) {
                                        isClicked = true;
                                        newHighlightedRow = random.nextInt(2);
                                        newHighlightedColumn = random.nextInt(2);
                                    }
                                    System.out.println(highlightedGridX + " " + highlightedGridY);
                                } else {
                                    char c = isX ? 'X' : '0';
                                    createEndScene(c);


//                                    EventHandler<ActionEvent> event2 = new
//                                            EventHandler<ActionEvent>() {
//                                                public void handle(ActionEvent e)
//                                                {
//                                                    // set alert type
//                                                    a.setAlertType(Alert.AlertType.INFORMATION);
//
//                                                    // show the dialog
//                                                    a.show();
//                                                }
//                                            };
//                                    b2.setOnAction(event2);

//                                    game.gameOver();
                                }

                                counter++;
                                char x = isX ? 'X' : 'O';

                                if(isClicked){
                                    table.addToTable(String.valueOf(counter), String.valueOf(x), String.valueOf(Directions.values()[3 * highlightedGridX + highlightedGridY]),
                                            String.valueOf(Directions.values()[3 * clickedX + clickedY]));
                                    game.nextMove(clickedX, clickedY, highlightedGridX, highlightedGridY, isX);
                                    isClicked = false;
                                }
                                else {
                                    table.addToTable(String.valueOf(counter), String.valueOf(x), String.valueOf(Directions.values()[3 * highlightedGridX + highlightedGridY]),
                                            String.valueOf(Directions.values()[3 * newHighlightedRow + newHighlightedColumn]));
                                    game.nextMove(newHighlightedRow, newHighlightedColumn, highlightedGridX, highlightedGridY, isX);
                                }

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
        stage = primaryStage;

        createScene1();

        startBtn.setOnMouseClicked(e -> {
            createScene2(primaryStage);
            createPlayers();

            table = new Table();

            setTimer();

            putElementInOrder();

            scene = new Scene(allElements, 1200, 600);
            primaryStage.setScene(scene);
        });


        primaryStage.setScene(scene1);
        primaryStage.show();


    }
}

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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class App extends Application {

    Button startBtn;
    Label playerX, playerO, subtitle, title, turn;

    HBox allElements, players;
    VBox playersBox, rightColumn, vboxScene1;

    Scene scene, scene1;
    GridPane grid;

    CheckBox checkBox;

    Game game;

    Table table;

    Spinner<Integer> spinner;

    int counter = 0;
    int highlightedGridX = 1;
    int highlightedGridY = 1;

    int newHighlightedColumn = 1;
    int newHighlightedRow = 1;


    boolean isX = true;

    boolean isMagic = false;
    boolean[] isOccupied = {false, false, false, false, false, false, false, false, false};


    Stage stage;

    MoveTimer moveTimer;

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

    private void createStartScene(){
        title = new Label("Ultimate Tic Tac Toe");
        title.setStyle("-fx-font-weight: bolder; -fx-font-size: 40px");
        title.setPadding(new Insets(0, 0,20,0));

        subtitle = new Label("Set time for move (seconds): ");
        subtitle.setStyle("-fx-font-size: 20px");

        checkBox = new CheckBox();

        spinner = new Spinner<>(1,60,10);
        spinner.setVisible(false);
        spinner.setStyle("-fx-font-size: 15px");


        EventHandler<ActionEvent> event = e -> spinner.setVisible(checkBox.isSelected());

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


    protected void changeX(){
        isX = !isX;
    }

    private void putElementInOrder(){
        rightColumn = new VBox(table.getVbox(), moveTimer.getTimerText(), moveTimer.getTimeToEnd());
        allElements = new HBox(playersBox, rightColumn);
    }


    protected void draw(Button btn){
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

        ObservableList<Node> children2;
        if (gridPane != null){
            children2 = gridPane.getChildren();

            for (Node node: children2){
                node.setDisable(true);
            }
        }


        Text text = new Text(String.valueOf(c));

        if(gridPane != null){
            if (c == 'X') {
                gridPane.setStyle("-fx-background-color: #af7aff");

            } else {
                gridPane.setStyle("-fx-background-color: #84faa2");
            }
        }


        text.setStyle("-fx-font-weight: bolder");
        text.setFont(Font.font(40));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text, 1, 1);
    }

    public void enableOrDisableSmallBoard(int x, int y, boolean b) {
        GridPane gridPane = null;
        ObservableList<Node> children = grid.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) == x && GridPane.getColumnIndex(node) == y) {
                gridPane = (GridPane) node;
                break;
            }
        }

        ObservableList<Node> children2;
        if (gridPane != null){
            children2 = gridPane.getChildren();

            for (Node node: children2){
                if(b){
                    node.setDisable(false);
                }
                else {
                    node.setDisable(true);
                }
            }
        }
    }


    private void createMainScene(){
        game = new Game(this);
        grid = new GridPane();

        for (int bigColumn = 0; bigColumn < 3 ; bigColumn++) {
            for (int bigRow = 0; bigRow < 3; bigRow++) {

                GridPane box = new GridPane();
                box.setStyle("-fx-background-color: darkgray; -fx-background-insets: 0, 2; -fx-padding: 3;");

                for (int column = 0; column < 3; column++) {
                    for (int row = 0 ; row < 3; row++) {

                        Button smallBox = new Button();
                        smallBox.setLayoutX(row);
                        smallBox.setLayoutY(column);

                        smallBox.setDisable(bigColumn != highlightedGridY || bigRow != highlightedGridX);
                        smallBox.setStyle("-fx-pref-width: 50px; -fx-pref-height: 50px");

                        GridPane.setConstraints(smallBox, column, row);
                        box.getChildren().add(smallBox);

                        smallBox.setOnMouseClicked(event -> {
                            if(!smallBox.getText().equals("X") && !smallBox.getText().equals("O")) {


                                moveTimer.timeToMove();

                                newHighlightedRow = GridPane.getRowIndex(smallBox);
                                newHighlightedColumn = GridPane.getColumnIndex(smallBox);


                                if(isMagic){
                                    highlightedGridX = GridPane.getRowIndex(box);
                                    highlightedGridY = GridPane.getColumnIndex(box);
                                    for (int i = 0; i < 3; i++){
                                        for(int j = 0; j < 3; j++){
                                            if(isOccupied[3 * i + j] && !(i == newHighlightedRow && j == newHighlightedColumn)){
                                                enableOrDisableSmallBoard(i,j,false);
                                            }
                                            isOccupied[3 * i + j] = false;
                                        }
                                    }
                                    isMagic = false;
                                }
                                if (!game.isAllOccupied()) {


                                    if(game.isAllOccupiedInSmallBoard(newHighlightedRow, newHighlightedColumn) || game.isWon(newHighlightedRow, newHighlightedColumn)){
                                        isMagic = true;
                                        for (int i = 0; i < 3; i++){
                                            for (int j = 0; j < 3; j++){
                                                if(!(game.isAllOccupiedInSmallBoard(i, j) || game.isWon(i, j))){
                                                    enableOrDisableSmallBoard(i, j, true);
                                                    isOccupied[3 * i + j] = true;
                                                }
                                            }
                                        }
                                    }

                                } else {
                                    char c = isX ? 'X' : '0';
                                    createEndScene(c);
                                }

                                counter++;
                                char x = isX ? 'X' : 'O';

                                table.addToTable(String.valueOf(counter), String.valueOf(x), String.valueOf(Directions.values()[3 * highlightedGridX + highlightedGridY]),
                                        String.valueOf(Directions.values()[3 * newHighlightedRow + newHighlightedColumn]));
                                game.nextMove(newHighlightedRow, newHighlightedColumn, highlightedGridX, highlightedGridY, isX);

                                if(!isMagic){
                                    changeHighlight();
                                    highlightedGridX = newHighlightedRow;
                                    highlightedGridY = newHighlightedColumn;
                                }

                                draw(smallBox);
                                changeX();
                            }
                        });

                    }
                }

                GridPane.setConstraints(box, bigColumn, bigRow);
                grid.getChildren().add(box);
                }
            }

        }


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        createStartScene();
        moveTimer = new MoveTimer(this, spinner);


        startBtn.setOnMouseClicked(e -> {
            createMainScene();
            createPlayers();

            table = new Table();

            moveTimer.setTimer();

            putElementInOrder();

            scene = new Scene(allElements, 1200, 600);
            primaryStage.setScene(scene);
        });


        primaryStage.setScene(scene1);
        primaryStage.show();

    }
}

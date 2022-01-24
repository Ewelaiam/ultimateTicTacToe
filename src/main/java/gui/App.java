package gui;

import game.Game;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    //TODO: init()

    Scene scene;
    GridPane grid;

    boolean isX = true;
    int highlightedGridX = 1;
    int highlightedGridY = 1;

    int newHighlightedColumn = 1;
    int newHighlightedRow = 1;

    Game game;

    private void draw(Button btn){
        if(isX){
            btn.setText("X");
        }
        else {
            btn.setText("O");

        }
        btn.setFont(Font.font(23));


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


    private void createBigBoard(){
        game = new Game();
        grid = new GridPane();


        for (int blockColumn = 0; blockColumn < 3 ; blockColumn++) {
            for (int blockRow = 0; blockRow < 3; blockRow++) {


                GridPane box = new GridPane();
                box.setStyle("-fx-background-color: black, -fx-control-inner-background; -fx-background-insets: 0, 2; -fx-padding: 2;");


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
                            if(smallBox.getText() != "X" && smallBox.getText() != "O"){
                                newHighlightedRow = GridPane.getRowIndex(smallBox);
                                newHighlightedColumn = GridPane.getColumnIndex(smallBox);
                                game.nextMove(newHighlightedRow, newHighlightedColumn, highlightedGridX, highlightedGridY, isX);

                                if(!game.isAllOccupied(newHighlightedRow, newHighlightedColumn)){
                                    changeHighlight();
                                    draw(smallBox);
                                    isX = !isX;
                                }

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
        createBigBoard();
        scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}

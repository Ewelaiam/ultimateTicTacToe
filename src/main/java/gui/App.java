package gui;

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
import javafx.stage.Stage;

public class App extends Application {

    Scene scene;
    GridPane grid;

    boolean isX = true;
    int highlightedGridX = 1;
    int highlightedGridY = 1;

    int newHighlightedColumn = 1;
    int newHighlightedRow = 1;

    private void draw(Button btn){
        if(isX){
            btn.setText("X");
        }
        else {
            btn.setText("O");
        }
    }

    private void changeHighlight(){
        if (!(newHighlightedRow == highlightedGridX && newHighlightedColumn == highlightedGridY)){
            GridPane toHighlight = null;
            GridPane highlighted = null;
            ObservableList<Node> childrens = grid.getChildren();
//        System.out.println(childrens);

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

//        System.out.println(result);

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






//        result.setStyle("-fx-background-color: red");
//        if (result != null){
//            System.out.println("elo");
//            result.setDisable(true);
//        }


//        for (int i = 0; i < 3; i++){
//            for (int j = 0; j < 3; j++){


//                grid.getChildren(r,h).get(i,j).setDisable(true);
//                grid.getChildren().get(r + h + 2 * h)
//            }
//        }
    }


    private void createBigBoard(){
        grid = new GridPane();
//        grid.setPrefSize(600,600);



        for (int blockColumn = 0; blockColumn < 3 ; blockColumn++) {
            for (int blockRow = 0; blockRow < 3; blockRow++) {


                GridPane box = new GridPane();
                box.setStyle("-fx-background-color: black, -fx-control-inner-background; -fx-background-insets: 0, 2; -fx-padding: 2;");

//                box.setLayoutX(blockRow);
//                box.setLayoutY(blockColumn);
                for (int column = 0; column < 3; column++) {
                    for (int row = 0 ; row < 3; row++) {
//                        TextField textField = new TextField(" ");
//                        textField.setStyle("-fx-pref-width: 2em;");
//                        GridPane.setConstraints(textField, column, row);
//                        box.getChildren().add(textField);
                        Button smallBox = new Button();
//                        System.out.println(smallBox.getLayoutX() + " " + smallBox.getLayoutY());
                        smallBox.setLayoutX(row);
                        smallBox.setLayoutY(column);



//                        System.out.println(row + " " + column + " " + smallBox.getLayoutX() + " " + smallBox.getLayoutY() + " ");
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
//                            System.out.println(smallBox.getLayoutX() + " " + smallBox.getLayoutY());
//                            System.out.println(box.getLayoutX() + " " + box.getLayoutY());
//                            System.out.println(GridPane.getRowIndex(smallBox)+ " " + GridPane.getColumnIndex(smallBox));
//                            System.out.println(smallBox.getScaleX() + " " + smallBox.getScaleY());
                            newHighlightedRow = GridPane.getRowIndex(smallBox);
                            newHighlightedColumn = GridPane.getColumnIndex(smallBox);

//                            System.out.println(event.getX() + " " + event.getY() + " " + event.getSceneX() + " " + event.getSceneY()
//                            + " " + event.getScreenX() + " " + event.getScreenY());
//                            System.out.println(event.getX() + " " + event.getY());
//                            newHighlightedRow = (int) event.getX();
//                            newHighlightedColumn = (int) event.getY();
//                            System.out.println(newHighlightedRow);
//                            System.out.println(newHighlightedColumn);

                            changeHighlight();
//                            changeHighlight(newHighlightedRow, newHighlightedColumn);
//                            highlightedGridX = row;
//                            highlightedGridY = column;
                            draw(smallBox);
                            isX = !isX;
                        });
//                        System.out.println(row + " " + column + " " + smallBox.getLayoutX() + " " + smallBox.getLayoutY() + " ");


//                        smallBox.setText(String.valueOf(blockColumn) + " " + String.valueOf(blockRow));

                    }
                }

                GridPane.setConstraints(box, blockColumn, blockRow);
                grid.getChildren().add(box);

            }
        }
    }

//    private void createBigBoard(){
//        grid = new GridPane();
//        grid.setPrefSize(600, 600);
//        for(int i = 0; i < 9; i++){
//            for (int j = 0; j < 9; j++){
//                Tile bigTile = new Tile(i,j);
//                bigTile.setTranslateX(j * 50);
//                bigTile.setTranslateY(i * 50);
//
//                //createSmallBoard();
//
//
//                grid.getChildren().add(bigTile);
//            }
//        }
//
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        createBigBoard();
        scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}

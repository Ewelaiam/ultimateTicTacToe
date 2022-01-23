package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
    public Tile(int i, int j){
        Rectangle singleTile;

        //singleTile = b ?  new Rectangle(100,100) : new Rectangle(200,200);

        singleTile = new Rectangle(50,50);


        singleTile.setFill(null);
        singleTile.setStroke(Color.BLACK);
//
//        if (i == 2 || i == 5){
//            singleTile.setStyle("-fx-background-color: RED");
//        }
//        else{
//            singleTile.setStyle("-fx-border-width: 10px");
//        }
//
        setAlignment(Pos.CENTER);
        getChildren().addAll(singleTile);
    }
}

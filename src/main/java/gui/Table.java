package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Table {

    private TableView<PlayerMove> table = new TableView<PlayerMove>();
    final VBox vbox = new VBox();

    private TableColumn<PlayerMove, String> turn;
    private TableColumn<PlayerMove, String> player;
    private TableColumn<PlayerMove, String> bigField;
    private TableColumn<PlayerMove, String> smallField;



    public Table(){
        Label title = new Label("Players moves: ");
        title.setFont(new Font("Arial", 20));

        table.setEditable(true);

        turn = new TableColumn("Turn");
        player = new TableColumn("Player");
        bigField = new TableColumn("Square");
        smallField = new TableColumn("Field");

        turn.setMinWidth(100);
        turn.setCellValueFactory(new PropertyValueFactory<PlayerMove, String>("turn"));
        player.setMinWidth(100);
        player.setCellValueFactory(new PropertyValueFactory<PlayerMove, String>("player"));
        bigField.setMinWidth(100);
        bigField.setCellValueFactory(new PropertyValueFactory<PlayerMove, String>("bigField"));
        smallField.setMinWidth(100);
        smallField.setCellValueFactory(new PropertyValueFactory<PlayerMove, String>("smallField"));

        table.getColumns().addAll(turn, player, bigField, smallField);

        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(title, table);


//        ((Group) scene.getRoot()).getChildren().addAll(vbox);

    }

    public VBox getVbox() {
        return vbox;
    }
//
//    public TableView<PlayerMove> getTable() {
//        return table;
//    }

    public void addToTable(String turnValue, String playerValue, String bigFieldValue, String smallFieldValue){
        table.getItems().add(new PlayerMove(turnValue, playerValue, bigFieldValue, smallFieldValue));
//        data.add(new PlayerMove(turnValue, playerValue, fieldValue));
//        System.out.println(data);

//        table.setItems(data);
//        table.getColumns().addAll(turn, player, field);
//
//        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
//        hb.setSpacing(3);
//
//        final VBox vbox = new VBox();
//        vbox.setSpacing(5);
//        vbox.setPadding(new Insets(10, 0, 0, 10));
//        vbox.getChildren().addAll(label, table, hb);
//
//        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        //table.getItems().add(new PlayerMove(turnValue, playerValue, fieldValue));

    }
}

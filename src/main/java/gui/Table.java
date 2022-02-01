package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


public class Table {

    private TableView<PlayerMove> table = new TableView<PlayerMove>();
    final VBox vbox = new VBox();

    private final TableColumn<PlayerMove, String> turn;
    private final TableColumn<PlayerMove, String> player;
    private final TableColumn<PlayerMove, String> bigField;
    private final TableColumn<PlayerMove, String> smallField;



    public Table(){
        Label title = new Label("Players moves: ");
        title.setStyle("-fx-font-weight: 700; -fx-font-size: 30px; -fx-padding: 0 0 45px 0");


        table.setEditable(true);

        turn = new TableColumn("Turn");
        player = new TableColumn("Player");
        bigField = new TableColumn("Square");
        smallField = new TableColumn("Field");

        turn.setMinWidth(100);
        turn.setCellValueFactory(new PropertyValueFactory<>("turn"));
        player.setMinWidth(100);
        player.setCellValueFactory(new PropertyValueFactory<>("player"));
        bigField.setMinWidth(200);
        bigField.setCellValueFactory(new PropertyValueFactory<>("bigField"));
        smallField.setMinWidth(200);
        smallField.setCellValueFactory(new PropertyValueFactory<>("smallField"));

        table.getColumns().addAll(turn, player, bigField, smallField);

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 0, 0, 20));
        vbox.getChildren().addAll(title, table);


    }

    public VBox getVbox() {
        return vbox;
    }


    public void addToTable(String turnValue, String playerValue, String bigFieldValue, String smallFieldValue){
        table.getItems().add(new PlayerMove(turnValue, playerValue, bigFieldValue, smallFieldValue));

    }
}

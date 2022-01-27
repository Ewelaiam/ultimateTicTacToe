package gui;

import javafx.beans.property.SimpleStringProperty;

public class PlayerMove {
    private final SimpleStringProperty turn;
    private final SimpleStringProperty player;
    private final SimpleStringProperty bigField;
    private final SimpleStringProperty smallField;

    PlayerMove(String turn, String player, String bigField, String smallField) {
        this.turn = new SimpleStringProperty(turn);
        this.player = new SimpleStringProperty(player);
        this.bigField = new SimpleStringProperty(bigField);
        this.smallField = new SimpleStringProperty(smallField);
    }

    public String getPlayer() {
        return player.get();
    }

    public void setPlayer(String fName) {
        player.set(fName);
    }

    public String getTurn() {
        return turn.get();
    }

    public void setTurn(String fName) {
        turn.set(fName);
    }

    public String getSmallField() {
        return smallField.get();
    }

    public void setSmallField(String fName) {
        smallField.set(fName);
    }


    public String getBigField() {
        return bigField.get();
    }

    public void setBigField(String fName) {
        bigField.set(fName);
    }
}


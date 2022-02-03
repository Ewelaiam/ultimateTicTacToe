package game;

import gui.App;

public class Game {
    private final BigBoard bigBoard;
    private final App app;

    public Game(App app){
        bigBoard = new BigBoard(this);
        this.app = app;
    }

    public void nextMove(int x, int y, int prev_x, int prev_y, boolean isX, boolean isMagic){
        bigBoard.actualize(x,y, prev_x, prev_y, isX, isMagic);
    }

    public boolean isAllOccupiedInSmallBoard(int x, int y){
        return bigBoard.isAllOccupiedInSmallBoard(x, y);
    }

    public boolean isAllOccupied(){
        return bigBoard.isAllOccupied();
    }

    public void gameOver(char c){
        app.createEndScene(c);
    }

    public boolean isWon(int x, int y){
        return bigBoard.isWon(x, y);
    }

    public void makeDisable(int x, int y, char c){
        app.makeDisable(x,y, c);
    }

    public boolean isF(int cordX, int cordY, int x, int y){
        return bigBoard.isF(cordX, cordY, x, y);
    }

}

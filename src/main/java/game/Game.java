package game;

import gui.App;

public class Game {
    private BigBoard bigBoard;
    private boolean endOfGame;
    private App app;

    public Game(App app){
        bigBoard = new BigBoard(this);
        endOfGame = false;
        this.app = app;
    }

    //TODO: public?
    public void nextMove(int x, int y, int prev_x, int prev_y, boolean isX){
        bigBoard.actualize(x,y, prev_x, prev_y, isX);
    }

    public boolean isAllOccupiedInSmallBoard(int x, int y){
        return bigBoard.isAllOccupiedInSmallBoard(x, y);
    }

    public boolean isAllOccupied(){
        return bigBoard.isAllOccupied();
    }

    public void gameOver(){
        endOfGame = true;

        //TODO: cos ala exit(0); czy nowe okno z wynikami?? albo app.endGame i tam cos
    }

    public boolean isWon(int x, int y){
        return bigBoard.isWon(x, y);
    }

    public void makeDisable(int x, int y, char c){
        app.makeDisable(x,y, c);
    }

}

package game;

public class Game {
    private BigBoard bigBoard;

    public Game(){
        bigBoard = new BigBoard();
    }

    //TODO: public?
    public void nextMove(int x, int y, int prev_x, int prev_y, boolean isX){
        bigBoard.actualize(x,y, prev_x, prev_y, isX);
    }
}

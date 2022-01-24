package game;

public class Game {
    private BigBoard bigBoard;
    private boolean endOfGame;

    public Game(){
        bigBoard = new BigBoard(this);
        endOfGame = false;
    }

    //TODO: public?
    public void nextMove(int x, int y, int prev_x, int prev_y, boolean isX){
        bigBoard.actualize(x,y, prev_x, prev_y, isX);
    }

    public boolean isAllOccupied(int x, int y){
        return bigBoard.isAllOccupied(x, y);
    }

    protected void gameOver(){
        endOfGame = true;
        //TODO: cos ala exit(0); czy nowe okno z wynikami??
    }

}

package game;

public class BigBoard {
    private final Game game;
    private final SmallBoard[] bigBoardWithSmallBoards;
    private final SmallBoard bigBoardValue;

    public BigBoard(Game game){
        this.game = game;
        bigBoardValue = new SmallBoard();
        bigBoardWithSmallBoards = new SmallBoard[9];
        for (int i = 0; i < 9; i++){
            bigBoardWithSmallBoards[i] = new SmallBoard();
        }
    }

    protected void actualize(int x, int y, int prev_x, int prev_y, boolean isX){
        char c = isX ? 'X' : '0';


        if (bigBoardWithSmallBoards[3 * prev_x + prev_y].changeFieldValue(x, y, isX)){
            game.makeDisable(prev_x, prev_y, c);
            if (bigBoardValue.changeFieldValue(prev_x, prev_y, isX)){
                game.gameOver(c);
            }
        }
    }

    protected boolean isAllOccupiedInSmallBoard(int x, int y){
        return !bigBoardWithSmallBoards[3 * x + y].isPossibleMove();
    }

    protected boolean isAllOccupied(){
        return !bigBoardValue.isPossibleMove();
    }

    protected boolean isWon(int x, int y){
        return bigBoardValue.getSmallBoardValue(3 * x + y) == 'O' || bigBoardValue.getSmallBoardValue(3 * x + y) == 'X';
    }
}

package game;

public class BigBoard {
    private Game game;
    //private List<char[]> bigBoardValue;
    private SmallBoard[] bigBoardWithSmallBoards;
    private SmallBoard bigBoardValue;

    public BigBoard(Game game){
        this.game = game;
        //bigBoardValue = new ArrayList<>();
        // TODO: przemyśleć czy da sie bez settera
        bigBoardValue = new SmallBoard();
        bigBoardWithSmallBoards = new SmallBoard[9];
        for (int i = 0; i < 9; i++){
            //bigBoardValue.add(new SmallBoard());
            bigBoardWithSmallBoards[i] = new SmallBoard();
        }
    }

    protected void actualize(int x, int y, int prev_x, int prev_y, boolean isX){
//        System.out.println("3 * prev_x + prev_y " + (3 * prev_x + prev_y));
        char c = bigBoardWithSmallBoards[3 * prev_x + prev_y].changeFieldValue(x, y, isX);
        System.out.println("c " + c);

        if (c != 'f'){
            //TODO: wyslij do GUI info, zeby zmienic smallBoard prev na nieaktywna z napisem wygranego symbolu
            game.makeDisable(prev_x, prev_y, c);
            char res = bigBoardValue.setSmallBoardValue(prev_x, prev_y, c);
            if (res != 'f'){
                game.gameOver();
            }
        }
    }

    public boolean isAllOccupiedInSmallBoard(int x, int y){
        return !bigBoardWithSmallBoards[3 * x + y].isPossibleMove();
    }

    public boolean isAllOccupied(){
        return !bigBoardValue.isPossibleMove();
    }

    public boolean isWon(int x, int y){
        return bigBoardValue.getSmallBoardValue(3 * x + y) == 'O' || bigBoardValue.getSmallBoardValue(3 * x + y) == 'X';
    }
}

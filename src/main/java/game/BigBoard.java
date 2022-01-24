package game;

import java.util.ArrayList;
import java.util.List;

public class BigBoard {
    //private List<char[]> bigBoardValue;
    private SmallBoard[] bigBoardValue;

    public BigBoard(){
        //bigBoardValue = new ArrayList<>();
        bigBoardValue = new SmallBoard[9];
        for (int i = 0; i < 9; i++){
            //bigBoardValue.add(new SmallBoard());
            bigBoardValue[i] = new SmallBoard();
        }
    }

    protected void actualize(int x, int y, int prev_x, int prev_y, boolean isX){
        char c = bigBoardValue[3 * prev_x + prev_y].changeFieldValue(x, y, isX);
        if (c != 'f'){
            //TODO: wyslij do GUI info, zeby zmienic smallBoard prev na nieaktywna z napisem wygranego symbolu
        }


    }
}

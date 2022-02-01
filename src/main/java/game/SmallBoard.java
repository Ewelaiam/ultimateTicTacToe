package game;

public class SmallBoard {
    //TODO: tablica statyczna czy dynamiczna
    private char[] smallBoardValue;
    // TODO: moze zamiast 3 * x + y stworzyc tutaj int pos i zainicjowac w changeField..
    private int actualPosition;

    public SmallBoard(){
        smallBoardValue = new char[]{'f','f','f','f','f','f','f','f','f'};
    }


    public char getSmallBoardValue(int idx){
        return smallBoardValue[idx];
    }


    protected boolean changeFieldValue(int x, int y, boolean isX){
        actualPosition = 3 * x + y;
        smallBoardValue[actualPosition] = isX ? 'X' : 'O';
        System.out.println(smallBoardValue);
        return find3InLine(x, y);

    }

    private boolean find3InLine(int x, int y){
        if (((x == 0 || x == 2) && (y == 0 || y == 2)) || (x == 1 && y == 1)){
            if(diagonal()){
                return true;
            }
        }

        if(horizontal()){
            return true;
        }
        else return vertical();

    }



    private boolean diagonal(){
        switch (actualPosition){
            case 0,8 -> {
                return smallBoardValue[0] != 'f' && smallBoardValue[0] == smallBoardValue[4] && smallBoardValue[4] == smallBoardValue[8];
            }

            case 2,6 -> {
                return smallBoardValue[2] != 'f' && smallBoardValue[2] == smallBoardValue[4] && smallBoardValue[4] == smallBoardValue[6];
            }

            case 4 -> {
                return ((smallBoardValue[0] != 'f' && smallBoardValue[0] == smallBoardValue[4] && smallBoardValue[4] == smallBoardValue[8])
                        || (smallBoardValue[2] != 'f' && smallBoardValue[2] == smallBoardValue[4] && smallBoardValue[4] == smallBoardValue[6]));
            }
            default -> {
                return false;
            }
        }

    }

    private boolean horizontal(){
        switch (actualPosition){
            case 0, 1, 2 -> {
                return smallBoardValue[0] != 'f' && smallBoardValue[0] == smallBoardValue[1] && smallBoardValue[1] == smallBoardValue[2];
            }
            case 3,4,5 -> {
                return smallBoardValue[3] != 'f' && smallBoardValue[3] == smallBoardValue[4] && smallBoardValue[4] == smallBoardValue[5];
            }

            case 6,7,8 -> {
                return smallBoardValue[6] != 'f' && smallBoardValue[6] == smallBoardValue[7] && smallBoardValue[7] == smallBoardValue[8];
            }
            default -> {
                return false;
            }

        }

    }

    private boolean vertical(){
        switch (actualPosition){
            case 0,3,6 -> {
                return smallBoardValue[0] != 'f' && smallBoardValue[0] == smallBoardValue[3] && smallBoardValue[3] == smallBoardValue[6];
            }

            case 1,4,7 -> {
                return smallBoardValue[1] != 'f' && smallBoardValue[1] == smallBoardValue[4] && smallBoardValue[4] == smallBoardValue[7];
            }

            case 2,5,8 -> {
                return smallBoardValue[2] != 'f' && smallBoardValue[2] == smallBoardValue[5] && smallBoardValue[5] == smallBoardValue[8];
            }

            default -> {
                return false;
            }
        }

    }

    protected boolean isPossibleMove(){
        for (int i = 0; i < 9; i++){
            if(smallBoardValue[i] == 'f'){
//                System.out.println("i" + i);
//                System.out.println(smallBoardValue);
                return true;
            }
        }
        return false;
    }


}

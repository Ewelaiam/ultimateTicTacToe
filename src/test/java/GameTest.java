import game.Game;
import gui.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void isAllOccupiedInSmallBoardTest(){
        App app = new App();
        Game game = new Game(app);

        Assertions.assertFalse(game.isAllOccupiedInSmallBoard(0,0));
        Assertions.assertFalse(game.isAllOccupiedInSmallBoard(2,0));
        Assertions.assertFalse(game.isAllOccupiedInSmallBoard(0,1));

        try{
            game.nextMove(0,0,0,0,true);
            game.nextMove(1,1,0,0,true);
            game.nextMove(2,2,0,0,true);
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void isAllOccupied(){
        App app = new App();
        Game game = new Game(app);

        Assertions.assertFalse(game.isAllOccupied());
        game.nextMove(0,0,0,0, true);
        game.nextMove(0,0,1,0, true);
        game.nextMove(1,1,2,0, false);
        game.nextMove(0,0,0,0, false);
        game.nextMove(1,0,2,0, true);
        game.nextMove(0,2,0,0, true);
        Assertions.assertFalse(game.isAllOccupied());

    }

    @Test
    public void isWonTest(){
        App app = new App();
        Game game = new Game(app);

        Assertions.assertFalse(game.isWon(0,0));
        Assertions.assertFalse(game.isWon(2,0));
        Assertions.assertFalse(game.isWon(0,1));

        try{
            game.nextMove(0,0,0,0,true);
            game.nextMove(1,1,0,0,true);
            game.nextMove(2,2,0,0,true);
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
}

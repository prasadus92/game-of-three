package takeaway.gameofthree.api.component;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import takeaway.gameofthree.api.exception.GameAlreadyFinishedException;
import takeaway.gameofthree.api.exception.InvalidNumberMoveException;
import takeaway.gameofthree.api.exception.InvalidTurnMoveException;
import takeaway.gameofthree.api.model.Move;
import takeaway.gameofthree.api.model.Player;

import java.io.IOException;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public class GameManagerTest {

    GameManagerImpl gameManager;

    @Before
    public void initTest() {
        gameManager = new GameManagerImpl();
    }

    @Test
    public void creatingNewGames() {
        GamePlayerRegistration game = gameManager.registerGame();
        Assert.assertEquals(Player.PLAYER_1, game.getPlayer());

        GamePlayerRegistration newGame = gameManager.registerGame();
        Assert.assertEquals(newGame.getGame().getId(), game.getGame().getId());
        Assert.assertEquals(Player.PLAYER_2, newGame.getPlayer());

        GamePlayerRegistration game2 = gameManager.registerGame();
        Assert.assertEquals(Player.PLAYER_1, game2.getPlayer());

        GamePlayerRegistration newGame2 = gameManager.registerGame();
        Assert.assertEquals(newGame2.getGame().getId(), game2.getGame().getId());
        Assert.assertEquals(Player.PLAYER_2, newGame2.getPlayer());
    }

    @Test(expected = InvalidTurnMoveException.class)
    public void nextMoveTurnInvalid()
            throws InvalidTurnMoveException, InvalidNumberMoveException, GameAlreadyFinishedException, IOException {
        GamePlayerRegistration game = gameManager.registerGame();
        Assert.assertEquals(Player.PLAYER_1, game.getGame().getNextMovePlayer());

        GamePlayerRegistration game2 = gameManager.registerGame();
        Assert.assertEquals(Player.PLAYER_1, game2.getGame().getNextMovePlayer());

        gameManager.nextMove(game2.getGame().getId(), game2.getPlayer().getPlayerId(), 1000);
    }

    @Test(expected = InvalidTurnMoveException.class)
    public void nextMoveWithoutPlayer2()
            throws InvalidTurnMoveException, InvalidNumberMoveException, GameAlreadyFinishedException, IOException {
        GamePlayerRegistration game = gameManager.registerGame();
        Assert.assertEquals(Player.PLAYER_1, game.getGame().getNextMovePlayer());

        gameManager.nextMove(game.getGame().getId(), game.getPlayer().getPlayerId(), 1000);
        Assert.assertEquals(Player.PLAYER_2, game.getGame().getNextMovePlayer());
        gameManager.nextMove(game.getGame().getId(), game.getPlayer().getPlayerId(), 333);
    }

    @Test(expected = InvalidNumberMoveException.class)
    public void nextMoveNumberInvalid()
            throws InvalidTurnMoveException, InvalidNumberMoveException, GameAlreadyFinishedException, IOException {
        GamePlayerRegistration game = gameManager.registerGame();
        Assert.assertEquals(Player.PLAYER_1, game.getGame().getNextMovePlayer());

        GamePlayerRegistration game2 = gameManager.registerGame();
        Assert.assertEquals(Player.PLAYER_1, game.getGame().getNextMovePlayer());

        gameManager.nextMove(game.getGame().getId(), game.getPlayer().getPlayerId(), 1000);
        Assert.assertEquals(Player.PLAYER_2, game.getGame().getNextMovePlayer());
        gameManager.nextMove(game2.getGame().getId(), game2.getPlayer().getPlayerId(), 1000);
    }

    @Test(expected = GameAlreadyFinishedException.class)
    public void allGameUntilFinished()
            throws InvalidTurnMoveException, InvalidNumberMoveException, GameAlreadyFinishedException, IOException {
        GamePlayerRegistration game1 = gameManager.registerGame();
        Assert.assertEquals(Player.PLAYER_1, game1.getGame().getNextMovePlayer());

        GamePlayerRegistration game2 = gameManager.registerGame();
        Assert.assertEquals(Player.PLAYER_1, game2.getGame().getNextMovePlayer());

        String gameId = game1.getGame().getId();
        String player1Id = game1.getPlayer().getPlayerId();
        String player2Id = game2.getPlayer().getPlayerId();

        Move move = gameManager.nextMove(gameId, player1Id, 1000);
        Assert.assertEquals(new Move(player1Id, 1000, 333, -1, false), move);
        Assert.assertEquals(Player.PLAYER_2, game1.getGame().getNextMovePlayer());

        move = gameManager.nextMove(gameId, player2Id, 333);
        Assert.assertEquals(new Move(player2Id, 333, 111, 0, false), move);
        Assert.assertEquals(Player.PLAYER_1, game1.getGame().getNextMovePlayer());

        move = gameManager.nextMove(gameId, player1Id, 111);
        Assert.assertEquals(new Move(player1Id, 111, 37, 0, false), move);
        Assert.assertEquals(Player.PLAYER_2, game1.getGame().getNextMovePlayer());

        move = gameManager.nextMove(gameId, player2Id, 37);
        Assert.assertEquals(new Move(player2Id, 37, 12, -1, false), move);
        Assert.assertEquals(Player.PLAYER_1, game1.getGame().getNextMovePlayer());

        move = gameManager.nextMove(gameId, player1Id, 12);
        Assert.assertEquals(new Move(player1Id, 12, 4, 0, false), move);
        Assert.assertEquals(Player.PLAYER_2, game1.getGame().getNextMovePlayer());

        move = gameManager.nextMove(gameId, player2Id, 4);
        Assert.assertEquals(new Move(player2Id, 4, 1, -1, true), move);
        Assert.assertEquals(Player.PLAYER_1, game1.getGame().getNextMovePlayer());

        Assert.assertTrue(move.isGameFinished());
        move = gameManager.nextMove(gameId, player1Id, 1);
    }
}

package takeaway.gameofthree.api.model;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public class Game {

    private String id;
    private SseEmitter player1;
    private SseEmitter player2;
    private Player nextMovePlayer;
    private Move lastMove;

    public Game() {
    }

    public Game(String id) {
        this.id = id;
        this.setPlayer1(new SseEmitter(-1L));
        this.setPlayer2(new SseEmitter(-1L));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SseEmitter getPlayer1() {
        return player1;
    }

    public void setPlayer1(SseEmitter player1) {
        this.player1 = player1;
    }

    public SseEmitter getPlayer2() {
        return player2;
    }

    public void setPlayer2(SseEmitter player2) {
        this.player2 = player2;
    }

    public Player getNextMovePlayer() {
        return nextMovePlayer;
    }

    public void setNextMovePlayer(Player nextMovePlayer) {
        this.nextMovePlayer = nextMovePlayer;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
    }
}

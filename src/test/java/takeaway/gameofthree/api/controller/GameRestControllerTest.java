package takeaway.gameofthree.api.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import takeaway.gameofthree.api.IntegrationTest;
import takeaway.gameofthree.api.dto.ErrorDTO;
import takeaway.gameofthree.api.dto.GameDTO;
import takeaway.gameofthree.api.model.Move;
import takeaway.gameofthree.api.util.ErrorCode;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public class GameRestControllerTest extends IntegrationTest {

    public static final String REGISTER_PLAYER_URL = "http://localhost:8080/game/player";

    public static final String GAME_MOVE_URL = "http://localhost:8080/game";

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void startGameAndMoveAndMoveTurnInvalid() {

        // register player1
        ResponseEntity<GameDTO> playerResponse = restTemplate.postForEntity(REGISTER_PLAYER_URL, null, GameDTO.class);

        Assert.assertEquals(HttpStatus.OK, playerResponse.getStatusCode());
        GameDTO gameDTO = playerResponse.getBody();

        // register player2
        ResponseEntity<GameDTO> playerResponse2 = restTemplate.postForEntity(REGISTER_PLAYER_URL, null, GameDTO.class);

        Assert.assertEquals(HttpStatus.OK, playerResponse2.getStatusCode());
        Assert.assertEquals(gameDTO.getGameId(), playerResponse2.getBody().getGameId());

        // move player1
        Move move = new Move("P1", 10000, 3333, -1, false);
        ResponseEntity<Move> moveResponse = restTemplate.postForEntity(
                GAME_MOVE_URL + "/" + gameDTO.getGameId() + "/" + gameDTO.getPlayerId() + "/" + "10000", null,
                Move.class);

        Assert.assertEquals(HttpStatus.OK, moveResponse.getStatusCode());
        Assert.assertEquals(move, moveResponse.getBody());

        // move 2 invalid for player1
        ResponseEntity<ErrorDTO> moveResponseInvalid = restTemplate.postForEntity(
                GAME_MOVE_URL + "/" + gameDTO.getGameId() + "/" + gameDTO.getPlayerId() + "/" + "10000", null,
                ErrorDTO.class);

        ErrorDTO errorDTO = new ErrorDTO(ErrorCode.INVALID_TURN_MOVE);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, moveResponseInvalid.getStatusCode());
        Assert.assertEquals(errorDTO, moveResponseInvalid.getBody());

    }

    @Test
    public void startGameAndMoveAndMoveNumberInvalid() {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setGameId("G1");
        gameDTO.setPlayerId("P1");

        // register player1
        ResponseEntity<GameDTO> playerResponse = restTemplate.postForEntity(REGISTER_PLAYER_URL, null, GameDTO.class);

        Assert.assertEquals(HttpStatus.OK, playerResponse.getStatusCode());
        Assert.assertEquals(gameDTO, playerResponse.getBody());

        // move
        Move move = new Move("P1", 10000, 3333, -1, false);
        ResponseEntity<Move> moveResponse = restTemplate.postForEntity(
                GAME_MOVE_URL + "/" + gameDTO.getGameId() + "/" + gameDTO.getPlayerId() + "/" + "10000", null,
                Move.class);

        Assert.assertEquals(HttpStatus.OK, moveResponse.getStatusCode());
        Assert.assertEquals(move, moveResponse.getBody());

        // register player2
        ResponseEntity<GameDTO> playerResponse2 = restTemplate.postForEntity(REGISTER_PLAYER_URL, null, GameDTO.class);
        Assert.assertEquals(HttpStatus.OK, playerResponse2.getStatusCode());

        // move invalid with player2 but number invalid
        ResponseEntity<ErrorDTO> moveResponseInvalid2 = restTemplate.postForEntity(
                GAME_MOVE_URL + "/" + gameDTO.getGameId() + "/" + "P2" + "/" + "10000", null, ErrorDTO.class);

        ErrorDTO errorDTO = new ErrorDTO(ErrorCode.INVALID_NUMBER_MOVE);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, moveResponseInvalid2.getStatusCode());
        Assert.assertEquals(errorDTO, moveResponseInvalid2.getBody());
    }
}
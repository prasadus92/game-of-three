package takeaway.gameofthree.player.service;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
@Service
public class GameServiceImpl implements GameService {

    public static final String SEND_NUMBER_SERVICE_URL = "http://localhost:8080/game";

    public String sendNumber(String gameId, String playerId, String number) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.exchange(SEND_NUMBER_SERVICE_URL + "/" + gameId + "/" + playerId + "/" + number,
                    HttpMethod.POST, null, Object.class);
            return null;
        } catch (HttpStatusCodeException e) {
            String errorPayload = e.getResponseBodyAsString();
            return errorPayload;
        }
    }
}

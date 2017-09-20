package takeaway.gameofthree.api;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import takeaway.gameofthree.GameOfThreeApplication;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public class IntegrationTest {

    private static final ConfigurableApplicationContext APPLICATION_CONTEXT;

    static {
        APPLICATION_CONTEXT = SpringApplication.run(GameOfThreeApplication.class);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                APPLICATION_CONTEXT.close();
            }
        });
    }
}

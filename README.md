The game is implemented with Spring Boot and the communication between the two players will happen through the APIs. Each player will be simply a browser.

To run the project:

- Download the repo
- Build the project by running: mvn clean install
- Start the game using: mvn spring-boot:run OR java -jar target/gameofthree-0.0.1-SNAPSHOT.jar

To play the game:

- Navigate to the URL "http://localhost:8080/playGame" from the browser.
- If you like the player to play automatically, check the "Automatic Game" checkbox.
- Click "Start Game".
- The API will assign a Game ID and a Player ID for the player, based on the games started by other players. If there is a game without a player, it will assign the player to this game. Otherwise, it will create a new game for this player.
- Repeat the above steps to start on behalf of the second player.
- Even if you've marked both players to be automatic, the first player (identified by "P1") should input the first number.
The game is implemented with Spring (to create a REST API) and server sent events to the communication between the two players through the API. Each player is a browser. Both, the API and the player's application are in the same project but they should be in separate projects.

To run the project follow the steps:

- Download repo
- Build the project by running: mvn clean install
- Start the game using: mvn spring-boot:run or java -jar target/gameofthree-0.0.1-SNAPSHOT.jar

To play the game follow the steps:

- Navigate to the URL "http://localhost:8080/playGame" from browser.
- If you like the player to play automatically, check the "Automatic game" checkbox.
- Click "Start game".
- The API will assign a Game ID and a Player ID for the player, based on the games started by other players. If there is a game without a player, it will assign the player to this game. Otherwise, it will create a new game for this player.
- Repeat the above steps to start on behalf of the second player.
- Even if you've marked for both players to be automatic, the first player (identified by "P1") should input the first number.
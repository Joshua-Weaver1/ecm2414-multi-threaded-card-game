# ECM2414 Multi-Threaded Card Game Continuous Assessment
For this coursework we were handed the task of creating a mutli-threaded card simulation game for 1 to n players, where there are n decks of cards numbered from 1 to n. Each player will hold a hand of 4 cards. Both these hands and the decks will be drawn from a pack which contains 8n cards. Each card has a face value of a non-negative integer. At the start of the game, each player will be distributed four cards in a round-robin fashion, from the top of the pack, starting by giving one card to player_1, then one card to player_2, etc. After the hands have been distributed, the decks will then be filled from the remaining cards in the pack, again in a round-robin fashion.

To win the game, a player needs four cards of the same value in their hand. If a player is given four cards which are all the same value at the start of the game, the player thread (that has a winning hand) should immediately notify teh other threads and exit. If the game is not won immediately, then the game progresses as follows: each player picks a card from the top of the deck to their left, and discards one to the bottom of the deck to their right. This process continues until the first player declares that they have four cards of the same value, at which point the game ends.

# How to run the JAR file
1. Use either command line or terminal to navigate to the location you downloaded the cards.jar
2. Enter the code into command line or terminal: 
  ``
  java -jar cards.jar
  ``
3. Run the code
4. Enter the number of desired players when asked (e.g. '4')
5. Enter the name and full path of the pack file when asked (e.g. 'C:/example')

The game will execute and will display the winner of the game in the console. You can also find the player and deck output files in the 'logs' folder.

# How to run the source code
1. Use either command line or terminal to navigate of the root folder of the project
2. Enter the code into command line or terminal: 
  ``
  cd ecm2414-multi-threaded-card-game/src
  ``
3. Run the command
  ``
  java CardGame
  ``
 4. Enter the number of desired players when asked (e.g. '4')
 5. Enter the name and full path of the pack file when asked (e.g. 'C:/example')

# How to run the test suite 
Enter the code into command line or terminal: 
1. Changes direcory
  ``
  cd ecm2414-multi-threaded-card-game/src
  ``
2. Compiles the source code to byte code
  ``
  javac -cp .;../lib/junit-4.12.jar CardGameTestSuite.java
  ``
3. Runs the tests with JUnit
``
  java -cp .;../lib/junit-4.12.jar;../lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore CardGameTestSuite
  ``

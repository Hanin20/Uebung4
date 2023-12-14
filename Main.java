/**
 * Tic Tac Toe
 *
 * @author Hanin Aljalab: 3009857
 */


import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        //
        Scanner scanner = new Scanner(System.in);

        // Create players
        HumanPlayer humanPlayer = new HumanPlayer('O', scanner);
        ComputerPlayer computerPlayer = new ComputerPlayer('X');

        // Create game
        Game game = new Game(humanPlayer, computerPlayer);

        // Play the game
        game.playGame();
    }
}

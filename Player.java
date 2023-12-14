import java.util.Random;
import java.util.Scanner;


// Represents a player in the game
class Player {
    protected char symbol;
    protected int winCount;
    protected int loseCount;

    // Constructor to set the player's symbol
    public Player(char symbol) {
        this.symbol = symbol;
        this.winCount = 0;
        this.loseCount = 0;
    }

    //get-Method
    public char getSymbol() {
        return symbol;
    }

    // Increment win count
    public void incrementWinCount() {
        winCount++;
    }

    // Increment lose count
    public void incrementLoseCount() {
        loseCount++;
    }

    // Abstract method for making a move
    public void makeMove(Board board) {
        // To be implemented by subclasses
    }
}

// Represents a human player, extends the Player class
class HumanPlayer extends Player {
    private Scanner scanner;

    // Constructor to set the symbol and initialize a scanner for input
    public HumanPlayer(char symbol, Scanner scanner) {
        super(symbol);
        this.scanner = scanner;
    }

    @Override
    public void makeMove(Board board) {
        System.out.println("Spieler, geben Sie Ihre Position ein (1-9): ");
        int input = scanner.nextInt();

        // Convert input to row and column
        int row = (input - 1) / 3;
        int col = (input - 1) % 3;

        // Check whether the position is valid and the field is not occupied
        if (input >= 1 && input <= 9 && board.getBoard()[row][col] == ' ') {
            board.getBoard()[row][col] = symbol;
        } else {
            System.out.println("Ungültige Position. Bitte erneut versuchen.");
            makeMove(board); // Recursive call for new input
        }
    }
}
// ...

// Represents a computer player, extends the Player class
// ...

// Represents a computer player, extends the Player class
// ...

// Represents a computer player, extends the Player class
class ComputerPlayer extends Player {
    private Random random;

    // Constructor
    public ComputerPlayer(char symbol) {
        super(symbol);
        this.random = new Random();
    }

    @Override
    public void makeMove(Board board) {
        int[] bestMove = getBestMove(board, 10000); // Anzahl der Simulationen (kann angepasst werden)
        int row = bestMove[0];
        int col = bestMove[1];

        if (board.getBoard()[row][col] == ' ') {
            board.getBoard()[row][col] = symbol;
        } else {
            // Handle the case where the chosen move is not valid
            // This could happen if there are no available moves with positive win rates
            System.out.println("Error: Invalid move. Choosing the first available empty space.");
            makeRandomMove(board);
        }
    }

    private int[] getBestMove(Board board, int simulations) {
        int[] bestMove = new int[]{-1, -1};
        double bestWinRate = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[i][j] == ' ') {
                    double winRate = monteCarlo(board, i, j, simulations);

                    if (winRate > bestWinRate) {
                        bestWinRate = winRate;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        System.out.printf("Best Move: (%d, %d) with Win Rate = %.2f%n", bestMove[0] + 1, bestMove[1] + 1, bestWinRate);

        return bestMove;
    }

    private double monteCarlo(Board board, int row, int col, int simulations) {
        int winCount = 0;

        for (int k = 0; k < simulations; k++) {
            Board copyBoard = copyBoard(board);
            copyBoard.getBoard()[row][col] = symbol;

            if (simulateGame(copyBoard)) {
                winCount++;
            }
        }

        return (double) winCount / simulations;
    }
    private void makeRandomMove(Board board) {
        // Fallback-Methode, falls keine gültigen Züge gefunden werden
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board.getBoard()[row][col] != ' ');

        board.getBoard()[row][col] = symbol;
    }





    private boolean simulateGame(Board board) {
        // Check for a win in rows
        for (int i = 0; i < 3; i++) {
            if (board.getBoard()[i][0] == symbol &&
                    board.getBoard()[i][1] == symbol &&
                    board.getBoard()[i][2] == symbol) {
                return true; // Computer wins
            }
        }

        // Check for a win in columns
        for (int j = 0; j < 3; j++) {
            if (board.getBoard()[0][j] == symbol &&
                    board.getBoard()[1][j] == symbol &&
                    board.getBoard()[2][j] == symbol) {
                return true; // Computer wins
            }
        }

        // Check for a win in diagonals
        if (board.getBoard()[0][0] == symbol &&
                board.getBoard()[1][1] == symbol &&
                board.getBoard()[2][2] == symbol) {
            return true; // Computer wins
        }

        if (board.getBoard()[0][2] == symbol &&
                board.getBoard()[1][1] == symbol &&
                board.getBoard()[2][0] == symbol) {
            return true; // Computer wins
        }

        return false; // No win
    }

    private Board copyBoard(Board original) {
        Board copy = new Board();
        char[][] originalBoard = original.getBoard();
        char[][] copyBoard = copy.getBoard();

        for (int i = 0; i < originalBoard.length; i++) {
            for (int j = 0; j < originalBoard[i].length; j++) {
                copyBoard[i][j] = originalBoard[i][j];
            }
        }

        return copy;
    }
}




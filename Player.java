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
        int[] bestMove = monteCarlo(board, this.symbol, 10000); // Anzahl der Simulationen (kann angepasst werden)
        board.getBoard()[bestMove[0]][bestMove[1]] = symbol;
    }

    private int[] monteCarlo(Board board, char playerSymbol, int simulations) {
        int[] bestMove = new int[]{-1, -1};
        double bestWinRate = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[i][j] == ' ') {
                    int winCount = 0;
                    int loseCount = 0;

                    for (int k = 0; k < simulations; k++) {
                        Board copyBoard = copyBoard(board);
                        copyBoard.getBoard()[i][j] = playerSymbol;

                        if (simulateGame(copyBoard, playerSymbol)) {
                            winCount++;
                        } else {
                            loseCount++;
                        }
                    }

                    double winRate = (double) winCount / (winCount + loseCount);

                    if (winRate > bestWinRate) {
                        bestWinRate = winRate;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
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

    private boolean simulateGame(Board board, char playerSymbol) {
        // Implementieren Sie hier die Logik zum Simulieren eines Spiels bis zum Ende
        // Rückgabe true, wenn der Spieler mit playerSymbol gewinnt, sonst false
        // Beachten Sie, dass Sie den Monte Carlo Algorithmus anpassen können, um den besten Zug für den Gegner zu finden.
        // Sie können die Simulation rekursiv für beide Spieler durchführen.
        return false; // Dummy-Rückgabe, bitte anpassen
    }
}

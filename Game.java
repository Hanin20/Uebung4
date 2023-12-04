import java.util.Scanner;

class Game {
    private Board board;
    private Player player1;
    private Player player2;

    // Constructor to initialize the game with two players
    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
    }

    //method to play the game
    public void playGame() {
        int currentPlayer = 1;
        boolean win = false;

        Scanner scanner = new Scanner(System.in);

        while (!win && !isBoardFull()) {
            boardDisplay();
            // Determine which player's turn it is
            if (currentPlayer == 1) {
                player1.makeMove(board);
            } else {
                player2.makeMove(board);
            }
            // Check for a win
            win = isWin();

            // Switch to the other player if no win
            if (!win) {
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            }
        }

        // Display the final board
        boardDisplay();

        // Display the result of the game
        if (win) {
            System.out.println("Spieler " + currentPlayer + " gewinnt!");
        } else {
            System.out.println("Unentschieden!");
        }

        scanner.close();
    }

    // Display the current state of the board
    public void boardDisplay() {
        char[][] currentBoard = board.getBoard();
        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[i].length; j++) {
                System.out.print(" [" + currentBoard[i][j] + "] ");
            }
            System.out.println();
        }
    }

    // Check if there is a win on the current board
    public boolean isWin() {
        char[][] currentBoard = board.getBoard();

        // Check rows and columns for a win
        for (int i = 0; i < 3; i++) {
            if (currentBoard[i][0] == currentBoard[i][1] && currentBoard[i][1] == currentBoard[i][2] && currentBoard[i][0] != ' ') {
                return true;
            }

            if (currentBoard[0][i] == currentBoard[1][i] && currentBoard[1][i] == currentBoard[2][i] && currentBoard[0][i] != ' ') {
                return true;
            }
        }
        // Check diagonals for a win
        if ((currentBoard[0][0] == currentBoard[1][1] && currentBoard[1][1] == currentBoard[2][2] && currentBoard[0][0] != ' ') ||
                (currentBoard[0][2] == currentBoard[1][1] && currentBoard[1][1] == currentBoard[2][0] && currentBoard[0][2] != ' ')) {
            return true;
        }

        return false;
    }


    // Check if the board is full
    public boolean isBoardFull() {
        char[][] currentBoard = board.getBoard();

        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[i].length; j++) {
                if (currentBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
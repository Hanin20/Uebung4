public class Board {
    private char[][] board;

    // Constructor to initialize an empty 3x3 board
    public Board() {
        board = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }

    // Getters and Setters
    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
}



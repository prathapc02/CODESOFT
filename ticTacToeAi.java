import java.util.*;

public class ticTacToeAi {
    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };
    static char human = 'O';
    static char ai = 'X';
    static char currentPlayer = human;

    // Main driver function
    public static void main(String[] args) {
        while (true) {
            printBoard();
            if (currentPlayer == human) {
                humanMove();
                currentPlayer = ai;
            } else {
                aiMove();
                currentPlayer = human;
            }
            if (isGameOver()) {
                printBoard();
                break;
            }
        }
    }

    
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }


    public static boolean isGameOver() {
        if (hasWon(ai)) {
            printBoard();
            System.out.println("AI wins!");
            return true;
        } else if (hasWon(human)) {
            printBoard();
            System.out.println("Human wins!");
            return true;
        } else if (isBoardFull()) {
            printBoard();
            System.out.println("It's a draw!");
            return true;
        }
        return false;
    }

    
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    
    public static boolean hasWon(char player) {
        
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

   
    public static void humanMove() {
        Scanner scanner = new Scanner(System.in);
        int move;
        while (true) {
            System.out.println("Enter your move (1-9): ");
            move = scanner.nextInt();
            if (move >= 1 && move <= 9 && board[(move - 1) / 3][(move - 1) % 3] == ' ') {
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        board[(move - 1) / 3][(move - 1) % 3] = human;
    }

    
    public static void aiMove() {
        int bestMove = findBestMove();
        board[(bestMove) / 3][(bestMove) % 3] = ai;
        System.out.println("AI moves at position " + (bestMove + 1));
    }

    
    public static int findBestMove() {
        int bestVal = Integer.MIN_VALUE;
        int bestMove = -1;

        
        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int col = i % 3;
            if (board[row][col] == ' ') {
                board[row][col] = ai;
                int moveVal = minimax(false);
                board[row][col] = ' ';
                if (moveVal > bestVal) {
                    bestMove = i;
                    bestVal = moveVal;
                }
            }
        }
        return bestMove;
    }

    
    public static int minimax(boolean isMaximizingPlayer) {
        if (hasWon(ai)) {
            return 1;
        }
        if (hasWon(human)) {
            return -1;
        }
        if (isBoardFull()) {
            return 0;
        }

        if (isMaximizingPlayer) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                int row = i / 3;
                int col = i % 3;
                if (board[row][col] == ' ') {
                    board[row][col] = ai;
                    best = Math.max(best, minimax(false));
                    board[row][col] = ' ';
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                int row = i / 3;
                int col = i % 3;
                if (board[row][col] == ' ') {
                    board[row][col] = human;
                    best = Math.min(best, minimax(true));
                    board[row][col] = ' ';
                }
            }
            return best;
        }
    }
}


package onlycs.tictactoe;

import java.util.Scanner;

public class Main {
	static final int SIZE = 3;

	public static final class Player {
		protected int data;

		public static final Player X() {
			return new Player(0);
		}

		public static final Player O() {
			return new Player(1);
		}

		public static final Player EMPTY() {
			return new Player(-1);
		}

		private Player(int v) {
			data = v;
		}

		public boolean equals(Player other) {
			return other.data == data;
		}

		public Player other() {
			return new Player(data == 0 ? 1 : 0);
		}

		public Player clone() {
			return new Player(data);
		}
	}

	static Player[][] board = new Player[SIZE][SIZE];
	static char[][] board_disp = new char[SIZE][SIZE];
	static Player currentPlayer = Player.X();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		initializeBoard();
		System.out.println("Tic tac toe");
		printBoard();

		while (!isGameOver()) {
			if (currentPlayer.equals(Player.X())) {
				playerMove(sc);
			} else {
				aiMove();
				printBoard();
			}
			currentPlayer = currentPlayer.other();
		}

		if (hasPlayerWon(Player.X())) {
			System.out.println("Player X won!");
		} else if (hasPlayerWon(Player.O())) {
			System.out.println("Player O won!");
		} else {
			System.out.println("It's a tie!");
		}

		printBoard();
	}

	private static void initializeBoard() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = Player.EMPTY();
			}
		}
	}

	private static void generateDisplayBoard() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				Player p = board[i][j];

				if (p.equals(Player.EMPTY())) {
					board_disp[i][j] = '-';
				} else if (p.equals(Player.O())) {
					board_disp[i][j] = 'O';
				} else {
					board_disp[i][j] = 'X';
				}
			}
		}
	}

	private static void printBoard() {
		generateDisplayBoard();
		System.out.println("  a b c");
		for (int i = 0; i < SIZE; i++) {
			System.out.print((i + 1) + " ");
			for (int j = 0; j < SIZE; j++) {
				System.out.print(board_disp[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static boolean isGameOver() {
		return hasPlayerWon(Player.X()) || hasPlayerWon(Player.O()) || isBoardFull();
	}

	private static boolean isBoardFull() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == Player.EMPTY()) {
					return false;
				}
			}
		}

		return true;
	}

	private static boolean hasPlayerWon(Player player) {
		// check rows and columns
		for (int i = 0; i < SIZE; i++) {
			if (board[i][0].equals(player) && board[i][1].equals(player)
					&& board[i][2].equals(player)) {
				return true;
			}

			if (board[0][i].equals(player) && board[1][i].equals(player)
					&& board[2][i].equals(player)) {
				return true;
			}
		}

		// check diagonals
		if (board[0][0].equals(player) && board[1][1].equals(player)
				&& board[2][2].equals(player)) {
			return true;
		}

		if (board[0][2].equals(player) && board[1][1].equals(player)
				&& board[2][0].equals(player)) {
			return true;
		}

		return false;
	}

	private static void playerMove(Scanner sc) {
		System.out.print("Player " + currentPlayer + " move (example: a2) (q to quit): ");

		String move = sc.next();

		if (move.charAt(0) == 'q') {
			System.out.print("Ok bye!");
			System.exit(0);
		}

		int row = move.charAt(1) - '1';
		int col = move.charAt(0) - 'a';

		if (isValidMove(row, col)) {
			board[row][col] = currentPlayer.clone();
		} else {
			System.out.println("Invalid move, try again.");
			playerMove(sc);
		}
	}

	private static boolean isValidMove(int row, int col) {
		if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
			return false;
		}
		return board[row][col] == Player.EMPTY();
	}

	private static void aiMove() {
		int[] bestMove = findBestMove();
		int row = bestMove[0];
		int col = bestMove[1];
		board[row][col] = currentPlayer.clone();
	}

	private static int[] findBestMove() {
		int[] bestMove = new int[] {-1, -1};
		int bestScore = Integer.MIN_VALUE;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == Player.EMPTY()) {
					board[i][j] = currentPlayer;
					int score = minimax(board, 0, false);
					board[i][j] = Player.EMPTY();
					if (score > bestScore) {
						bestScore = score;
						bestMove[0] = i;
						bestMove[1] = j;
					}
				}
			}
		}
		return bestMove;
	}

	private static int minimax(Player[][] board, int currentDepth, boolean isAiTurn) {
		if (hasPlayerWon(Player.X())) {
			return -10 + currentDepth;
		}
		if (hasPlayerWon(Player.O())) {
			return 10 - currentDepth;
		}
		if (isBoardFull()) {
			return 0;
		}
		int bestScore;
		if (isAiTurn) {
			bestScore = Integer.MIN_VALUE;
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (board[i][j] == Player.EMPTY()) {
						board[i][j] = Player.O();
						bestScore = Math.max(bestScore, minimax(board, currentDepth + 1, false));
						board[i][j] = Player.EMPTY();
					}
				}
			}
			return bestScore;
		} else {
			bestScore = Integer.MAX_VALUE;
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (board[i][j] == Player.EMPTY()) {
						board[i][j] = Player.X();
						bestScore = Math.min(bestScore, minimax(board, currentDepth + 1, true));
						board[i][j] = Player.EMPTY();
					}
				}
			}
			return bestScore;
		}
	}
}

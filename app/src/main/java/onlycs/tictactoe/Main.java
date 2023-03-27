package onlycs.tictactoe;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		final int SIZE = 3;
		final char PLAYER_X = 'X';
		final char PLAYER_O = 'O';

		char[][] board = new char[SIZE][SIZE];
		char currentPlayer = PLAYER_X;

		initializeBoard(board, SIZE);
		printBoard(board, SIZE);

		while (!isGameOver(board, PLAYER_X, PLAYER_O, SIZE)) {
			if (currentPlayer == PLAYER_X) {
				playerMove(sc, board, currentPlayer, SIZE);
			} else {
				aiMove(board, currentPlayer, SIZE);
				printBoard(board, SIZE);
			}
			currentPlayer = currentPlayer == PLAYER_X ? PLAYER_O : PLAYER_X;
		}

		if (hasPlayerWon(board, PLAYER_X, SIZE)) {
			System.out.println("Player X won!");
		} else if (hasPlayerWon(board, PLAYER_O, SIZE)) {
			System.out.println("Player O won!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	private static void initializeBoard(char[][] board, int SIZE) {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = '-';
			}
		}
	}

	private static void printBoard(char[][] board, int SIZE) {
		System.out.println(" a b c");
		for (int i = 0; i < SIZE; i++) {
			System.out.print((i + 1) + " ");
			for (int j = 0; j < SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static boolean isGameOver(char[][] board, char PLAYER_X, char PLAYER_O, int SIZE) {
		return hasPlayerWon(board, PLAYER_X, SIZE) || hasPlayerWon(board, PLAYER_O, SIZE)
				|| isBoardFull(board, SIZE);
	}

	private static boolean isBoardFull(char[][] board, int SIZE) {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}

		return true;
	}

	private static boolean hasPlayerWon(char[][] board, char player, int SIZE) {
		// check rows and columns
		for (int i = 0; i < SIZE; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}

			if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
				return true;
			}
		}

		// check diagonals
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}

		if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
			return true;
		}

		return false;
	}

	private static void playerMove(Scanner sc, char[][] board, char currentPlayer, int SIZE) {
		System.out.println("Player " + currentPlayer + " move (e.g. a2):");

		String move = sc.next();

		int row = move.charAt(1) - '1';
		int col = move.charAt(0) - 'a';

		if (isValidMove(board, row, col, SIZE)) {
			board[row][col] = currentPlayer;
		} else {
			System.out.println("Invalid move, try again.");
			playerMove(sc, board, currentPlayer, SIZE);
		}
	}

	private static boolean isValidMove(char[][] board, int row, int col, int SIZE) {
		if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
			return false;
		}
		return board[row][col] == '-';
	}

	private static void aiMove(char[][] board, char currentPlayer, int SIZE) {
		int[] bestMove = findBestMove(board, currentPlayer, 'O', SIZE);
		int row = bestMove[0];
		int col = bestMove[1];
		board[row][col] = currentPlayer;
	}

	private static int[] findBestMove(char[][] board, char currentPlayer, char PLAYER_O, int SIZE) {
		int[] bestMove = new int[] {-1, -1};
		int bestScore = Integer.MIN_VALUE;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == '-') {
					board[i][j] = currentPlayer;
					int score = minimax(board, 0, false, 'X', PLAYER_O, SIZE);
					board[i][j] = '-';
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

	private static int minimax(char[][] board, int currentDepth, boolean isAiTurn, char PLAYER_X,
			char PLAYER_O, int SIZE) {
		if (hasPlayerWon(board, PLAYER_X, SIZE)) {
			return -10 + currentDepth;
		}
		if (hasPlayerWon(board, PLAYER_O, SIZE)) {
			return 10 - currentDepth;
		}
		if (isBoardFull(board, SIZE)) {
			return 0;
		}
		int bestScore;
		if (isAiTurn) {
			bestScore = Integer.MIN_VALUE;
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (board[i][j] == '-') {
						board[i][j] = PLAYER_O;
						bestScore = Math.max(bestScore,
								minimax(board, currentDepth + 1, false, PLAYER_X, PLAYER_O, SIZE));
						board[i][j] = '-';
					}
				}
			}
			return bestScore;
		} else {
			bestScore = Integer.MAX_VALUE;
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (board[i][j] == '-') {
						board[i][j] = PLAYER_X;
						bestScore = Math.min(bestScore,
								minimax(board, currentDepth + 1, true, PLAYER_X, PLAYER_O, SIZE));
						board[i][j] = '-';
					}
				}
			}
			return bestScore;
		}
	}
}

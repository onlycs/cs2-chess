package onlycs.chess.types;

import onlycs.chess.rustish.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import onlycs.chess.pieces.*;

public class Board {
	Square[][] board = new Square[8][8];
	Color turn = Color.White;

	private Board(Square[][] board) {
		this.board = board;
	}

	public static Result<Board> create() {
		Square[][] board = new Square[8][8];

		for (int ranki = 0; ranki < 8; ranki++) {
			for (int filei = 0; filei < 8; filei++) {
				int rank = 8 - ranki;
				int file = filei + 1;

				Result<Position> positionResult = Position.create(file, rank);

				if (positionResult.isErr()) {
					return Result.Err(positionResult.err().unwrap());
				}

				board[ranki][filei] = new Square(positionResult.unwrap());
			}
		}

		for (Square[] sqrank : board) {
			for (Square sq : sqrank) {
				if (sq.position.rank == 2) {
					sq.setPiece(new Pawn(Color.White, sq.position));
				}

				if (sq.position.rank == 7) {
					sq.setPiece(new Pawn(Color.Black, sq.position));
				}

				if (sq.position.rank == 1) {
					if (sq.position.file == 1 || sq.position.file == 8) {
						sq.setPiece(new Rook(Color.White, sq.position));
					}

					if (sq.position.file == 2 || sq.position.file == 7) {
						sq.setPiece(new Knight(Color.White, sq.position));
					}

					if (sq.position.file == 3 || sq.position.file == 6) {
						sq.setPiece(new Bishop(Color.White, sq.position));
					}

					if (sq.position.file == 4) {
						sq.setPiece(new Queen(Color.White, sq.position));
					}

					if (sq.position.file == 5) {
						sq.setPiece(new King(Color.White, sq.position));
					}
				}

				if (sq.position.rank == 8) {
					if (sq.position.file == 1 || sq.position.file == 8) {
						sq.setPiece(new Rook(Color.Black, sq.position));
					}

					if (sq.position.file == 2 || sq.position.file == 7) {
						sq.setPiece(new Knight(Color.Black, sq.position));
					}

					if (sq.position.file == 3 || sq.position.file == 6) {
						sq.setPiece(new Bishop(Color.Black, sq.position));
					}

					if (sq.position.file == 4) {
						sq.setPiece(new Queen(Color.Black, sq.position));
					}

					if (sq.position.file == 5) {
						sq.setPiece(new King(Color.Black, sq.position));
					}
				}
			}
		}

		return Result.Ok(new Board(board));
	}

	public Square at(Position position) {
		return board[8 - position.rank][position.file - 1];
	}

	public Square[] file(char file) {
		Square[] fileSquares = new Square[8];

		for (int i = 0; i < 8; i++) {
			fileSquares[i] = board[i][file - 'a'];
		}

		return Arrays.stream(fileSquares).sorted((a, b) -> {
			return a.position.rank - b.position.rank;
		}).toArray(Square[]::new);
	}

	public Square[] rank(int rank) {
		return board[8 - rank];
	}

	public void display() {
		// github copilot did this
		System.out.println("    a   b   c   d   e   f   g   h ");
		System.out.println("  ┌───┬───┬───┬───┬───┬───┬───┬───┐");
		System.out.println("8 │ " + board[0][0].toChar() + " │ " + board[0][1].toChar() + " │ "
				+ board[0][2].toChar() + " │ " + board[0][3].toChar() + " │ " + board[0][4].toChar()
				+ " │ " + board[0][5].toChar() + " │ " + board[0][6].toChar() + " │ "
				+ board[0][7].toChar() + " │ 8");
		System.out.println("  ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println("7 │ " + board[1][0].toChar() + " │ " + board[1][1].toChar() + " │ "
				+ board[1][2].toChar() + " │ " + board[1][3].toChar() + " │ " + board[1][4].toChar()
				+ " │ " + board[1][5].toChar() + " │ " + board[1][6].toChar() + " │ "
				+ board[1][7].toChar() + " │ 7");
		System.out.println("  ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println("6 │ " + board[2][0].toChar() + " │ " + board[2][1].toChar() + " │ "
				+ board[2][2].toChar() + " │ " + board[2][3].toChar() + " │ " + board[2][4].toChar()
				+ " │ " + board[2][5].toChar() + " │ " + board[2][6].toChar() + " │ "
				+ board[2][7].toChar() + " │ 6");
		System.out.println("  ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println("5 │ " + board[3][0].toChar() + " │ " + board[3][1].toChar() + " │ "
				+ board[3][2].toChar() + " │ " + board[3][3].toChar() + " │ " + board[3][4].toChar()
				+ " │ " + board[3][5].toChar() + " │ " + board[3][6].toChar() + " │ "
				+ board[3][7].toChar() + " │ 5");
		System.out.println("  ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println("4 │ " + board[4][0].toChar() + " │ " + board[4][1].toChar() + " │ "
				+ board[4][2].toChar() + " │ " + board[4][3].toChar() + " │ " + board[4][4].toChar()
				+ " │ " + board[4][5].toChar() + " │ " + board[4][6].toChar() + " │ "
				+ board[4][7].toChar() + " │ 4");
		System.out.println("  ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println("3 │ " + board[5][0].toChar() + " │ " + board[5][1].toChar() + " │ "
				+ board[5][2].toChar() + " │ " + board[5][3].toChar() + " │ " + board[5][4].toChar()
				+ " │ " + board[5][5].toChar() + " │ " + board[5][6].toChar() + " │ "
				+ board[5][7].toChar() + " │ 3");
		System.out.println("  ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println("2 │ " + board[6][0].toChar() + " │ " + board[6][1].toChar() + " │ "
				+ board[6][2].toChar() + " │ " + board[6][3].toChar() + " │ " + board[6][4].toChar()
				+ " │ " + board[6][5].toChar() + " │ " + board[6][6].toChar() + " │ "
				+ board[6][7].toChar() + " │ 2");
		System.out.println("  ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println("1 │ " + board[7][0].toChar() + " │ " + board[7][1].toChar() + " │ "
				+ board[7][2].toChar() + " │ " + board[7][3].toChar() + " │ " + board[7][4].toChar()
				+ " │ " + board[7][5].toChar() + " │ " + board[7][6].toChar() + " │ "
				+ board[7][7].toChar() + " │ 1");
		System.out.println("  └───┴───┴───┴───┴───┴───┴───┴───┘");
		System.out.println("    a   b   c   d   e   f   g   h ");
	}

	public void userMove(Scanner sc) {
		display();

		System.out.print("Which peice do you want to move (e2)? ");

		String fromstr = sc.nextLine();

		Position from = Position.parseFEN(fromstr).unwrap();

		// clear the screen
		// using both standard ANSI escape sequences and the bluej escape sequence (\f)
		System.out.print("\033[H\033[2J\f");
		System.out.flush();

		display();

		System.out.print("Where do you want to move it to (e4)? ");

		String tostr = sc.nextLine();

		// clear the screen
		// using both standard ANSI escape sequences and the bluej escape sequence (\f)
		System.out.print("\033[H\033[2J\f");
		System.out.flush();

		Position to = Position.parseFEN(tostr).unwrap();

		ArrayList<Move> moves = getMoves();

		moves = moves.stream().filter(m -> m.from.equals(from) && m.to.equals(to))
				.collect(Collectors.toCollection(ArrayList::new));

		if (moves.size() == 0) {
			System.out.println("Invalid move" + moves.size());
			return;
		}

		Move move = moves.get(0);

		move(move);
	}

	public void move(Move move) {
		Position from = move.from;
		Position to = move.to;

		Square fromSq = at(from);
		Square toSq = at(to);

		if (fromSq.getPiece().isNone()) {
			throw new IllegalArgumentException("No piece at " + from);
		}

		Piece frompc = fromSq.getPiece().unwrap();

		if (toSq.getPiece().isSome() && toSq.getPiece().unwrap().getColor() == frompc.getColor()) {
			throw new IllegalArgumentException(
					"Can't move to " + to + " because there is a piece of the same color there");
		}

		frompc.setPosition(to);

		toSq.setPiece(frompc);
		fromSq.removePiece();

		turn = turn.getOpposite();
	}

	public ArrayList<Move> getMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();

		for (int ranki = 0; ranki < 8; ranki++) {
			for (int filei = 0; filei < 8; filei++) {
				Square sq = board[ranki][filei];

				if (sq.getPiece().isSome()) {
					Piece pc = sq.getPiece().unwrap();

					if (pc.getColor() == turn) {
						moves.addAll(pc.getMoves(this));
					}
				}
			}
		}

		return moves;
	}

	public Board clone() {
		Square[][] boardClone = new Square[8][8];

		for (int ranki = 0; ranki < 8; ranki++) {
			for (int filei = 0; filei < 8; filei++) {
				boardClone[ranki][filei] = board[ranki][filei].clone();
			}
		}

		return new Board(boardClone);
	}
}

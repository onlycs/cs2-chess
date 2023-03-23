package onlycs.chess.types;

import onlycs.chess.rustish.Result;

/**
 * Store for the UI
 */
public class Position {
	public int rank;
	public int file;

	private Position(int file, int rank) {
		this.rank = rank;
		this.file = file;
	}

	public static Result<Position> create(int file, int rank) {
		if (rank < 1 || rank > 8 || file < 1 || file > 8) {
			return Result.Err(new Error("Invalid position"));
		}
		return Result.Ok(new Position(file, rank));
	}

	public static Result<Position> parseFEN(String fen) {
		if (fen.length() != 2) {
			return Result.Err(new Error("Invalid FEN"));
		}

		// convert e4 to 5, 4
		int file = fen.charAt(0) - 'a' + 1;
		int rank = fen.charAt(1) - '0';

		return Position.create(file, rank);
	}

	public Position clone() {
		return new Position(file, rank);
	}

	public Result<Position> up(int ct) {
		return Position.create(file, rank + ct);
	}

	public Result<Position> up() {
		return Position.create(file, rank + 1);
	}

	public Result<Position> down(int ct) {
		return Position.create(file, rank - ct);
	}

	public Result<Position> down() {
		return Position.create(file, rank - 1);
	}

	public Result<Position> left(int ct) {
		return Position.create(file - ct, rank);
	}

	public Result<Position> left() {
		return Position.create(file - 1, rank);
	}

	public Result<Position> right(int ct) {
		return Position.create(file + ct, rank);
	}

	public Result<Position> right() {
		return Position.create(file + 1, rank);
	}

	public boolean equals(Position other) {
		return rank == other.rank && file == other.file;
	}
}

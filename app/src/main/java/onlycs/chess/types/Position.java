package onlycs.chess.types;

import onlycs.chess.rustish.Result;

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

	public static Result<Position> create(char file, int rank) {
		if (rank < 1 || rank > 8) {
			return Result.Err(new Error("Invalid position"));
		}

		int filei = file - 'a' + 1;

		if (filei < 1 || filei > 8) {
			return Result.Err(new Error("Invalid position"));
		}

		return Result.Ok(new Position(filei, rank));
	}

	public Position clone() {
		return new Position(rank, file);
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
}

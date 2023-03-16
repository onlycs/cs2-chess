package com.onlycs.chess.types;

import com.onlycs.chess.rust.Result;

public class Position {
	public int x;
	public int y;

	private Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Result<Position, Error> create(int x, int y) {
		if (x < 1 || x > 8 || y < 1 || y > 8) {
			return Result.Err(new Error("Invalid position"));
		}
		return Result.Ok(new Position(x, y));
	}
}

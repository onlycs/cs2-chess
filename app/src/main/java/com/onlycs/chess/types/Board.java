package com.onlycs.chess.types;

import com.onlycs.chess.rust.Result;

public class Board {
	Square[][] board = new Square[8][8];

	private Board(Square[][] board) {
		this.board = board;
	}

	public Result<Board, Error> create() {
		Square[][] board = new Square[8][8];
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				Result<Position, Error> position = Position.create(i, j);

				if (position.isErr()) {
					return Result.Err(position.unwrapErr());
				}

				board[i - 1][j - 1] = new Square(position.unwrap());
			}
		}

		return Result.Ok(new Board(board));
	}

	public Square at(Position position) {
		return board[8 - position.y][position.x - 1];
	}
}

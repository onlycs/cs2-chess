package com.onlycs.chess.types;

import com.onlycs.chess.pieces.Piece;
import com.onlycs.chess.rust.Option;

public class Square {
	Option<Piece> piece;
	Position position;

	public Square(Position position) {
		this.piece = Option.None();
		this.position = position;
	}

	public Option<Piece> getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = Option.Some(piece);
	}

	public void removePiece() {
		this.piece = Option.None();
	}

	public boolean isEmpty() {
		return piece.isNone();
	}
}

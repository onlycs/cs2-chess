package onlycs.chess.types;

import onlycs.chess.pieces.Piece;
import onlycs.chess.rustish.Option;

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

	public char toChar() {
		if (isEmpty()) {
			return ' ';
		} else {
			return piece.unwrap().toChar();
		}
	}

	public Square clone() {
		Square square = new Square(position);
		if (piece.isSome()) {
			square.setPiece(piece.unwrap().clone());
		}
		return square;
	}
}

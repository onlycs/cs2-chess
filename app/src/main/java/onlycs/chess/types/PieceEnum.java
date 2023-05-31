package onlycs.chess.types;

public enum PieceEnum {
	Pawn, Knight, Bishop, Rook, Queen, King;

	public char toChar(Color color) {
		switch (this) {
			case Pawn:
				return color == Color.White ? 'P' : 'p';
			case Knight:
				return color == Color.White ? 'N' : 'n';
			case Bishop:
				return color == Color.White ? 'B' : 'b';
			case Rook:
				return color == Color.White ? 'R' : 'r';
			case Queen:
				return color == Color.White ? 'Q' : 'q';
			case King:
				return color == Color.White ? 'K' : 'k';
		}

		throw new RuntimeException("Unreachable");
	}

	public char fen(Color color) {
		switch (this) {
			case Pawn:
				return color == Color.White ? 'P' : 'p';
			case Knight:
				return color == Color.White ? 'N' : 'n';
			case Bishop:
				return color == Color.White ? 'B' : 'b';
			case Rook:
				return color == Color.White ? 'R' : 'r';
			case Queen:
				return color == Color.White ? 'Q' : 'q';
			case King:
				return color == Color.White ? 'K' : 'k';
		}

		throw new RuntimeException("Unreachable");
	}
}

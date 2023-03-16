package com.onlycs.chess.types;

public enum PieceEnum {
	Pawn, Knight, Bishop, Rook, Queen, King;

	public char toChar(Color color) {
		switch (this) {
			case Pawn:
				return color == Color.White ? (char) 0x2659 : (char) 0x265F;
			case Knight:
				return color == Color.White ? (char) 0x2658 : (char) 0x265E;
			case Bishop:
				return color == Color.White ? (char) 0x2657 : (char) 0x265D;
			case Rook:
				return color == Color.White ? (char) 0x2656 : (char) 0x265C;
			case Queen:
				return color == Color.White ? (char) 0x2655 : (char) 0x265B;
			case King:
				return color == Color.White ? (char) 0x2654 : (char) 0x265A;
		}

		return ' ';
	}
}

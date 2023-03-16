package com.onlycs.chess.pieces;

import java.util.ArrayList;
import com.onlycs.chess.types.Color;
import com.onlycs.chess.types.Move;
import com.onlycs.chess.types.PieceEnum;
import com.onlycs.chess.types.Position;

public abstract class Piece {
	public abstract Color getColor();

	public abstract PieceEnum getType();

	public char toChar() {
		return getType().toChar(getColor());
	}

	public abstract Piece clone();

	public abstract void setPosition(Position position);

	public abstract Position getPosition();

	public abstract ArrayList<Move> getMoves();
}

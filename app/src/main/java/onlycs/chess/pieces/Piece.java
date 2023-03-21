package onlycs.chess.pieces;

import java.util.ArrayList;
import onlycs.chess.types.Board;
import onlycs.chess.types.Color;
import onlycs.chess.types.Move;
import onlycs.chess.types.PieceEnum;
import onlycs.chess.types.Position;

public abstract class Piece {
	public abstract Color getColor();

	public abstract PieceEnum getType();

	public char toChar() {
		return getType().toChar(getColor());
	}

	public abstract Piece clone();

	public abstract void setPosition(Position position);

	public abstract Position getPosition();

	public abstract ArrayList<Move> getMoves(Board board);
}

package com.jagadish.chess.domain;

import java.util.List;
import java.util.Stack;

public abstract class Piece {

	Position currentPosition;
	Stack moveHistory;
	boolean isValid;

	public Piece() {
	}

	boolean isValid() {
		return isValid;
	}

	Position getCurrentPosition() {
		return currentPosition;
	}

	Stack getMoveHistory() {
		return moveHistory;
	}

	Position getLastMove() {
		return (Position) moveHistory.peek();
	}

	boolean applyMove(Position p) {
		if (isValid && isValidMove(p)) {
			moveHistory.push(currentPosition);
			currentPosition = p;
			return true;
		} else {
			return false;
		}
	}

	abstract List<Position> getValidMoves();

	abstract boolean isValidMove(Position position);
}

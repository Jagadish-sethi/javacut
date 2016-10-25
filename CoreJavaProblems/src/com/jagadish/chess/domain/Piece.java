package com.jagadish.chess.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public abstract class Piece implements Display{

	protected Position currentPosition;
	protected Stack<Position> moveHistory;
	protected boolean isAvailable;
	protected Team team;
	
	public Piece(boolean isAvailable,Position position, Team team) {
		this.currentPosition = position; 
		this.currentPosition.setPiece(this);
		this.isAvailable = isAvailable;
		this.team = team;
		
		moveHistory = new Stack<Position>();
	}

	boolean isAvailable() {
		return isAvailable;
	}
	
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
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
		if (isAvailable && isValidMove(p)) {
			moveHistory.push(currentPosition);
			
			p.getPiece().setAvailable(false);
			p.setPiece(this);
			currentPosition = p;
			return true;
		} else {
			return false;
		}
	}

	//abstract List<Position> getValidMoves();

	boolean isValidMove(Position position) {
		if (position.getX() == currentPosition.getX()
				&& position.getY() == currentPosition.getY())
			return false; // cannot move nothing
		if (position.getX() < 0 || position.getX() > 7 || position.getY() < 0
				|| position.getY() > 7)
			return false;
		if (position.getPiece().team == currentPosition.getPiece().team)
			return false;
		return true;
	}
	
	@Override
	public void display() {
		if (isAvailable) {
			team.display();
		}
	}
	
}

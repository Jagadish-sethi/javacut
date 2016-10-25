package com.jagadish.chess.domain;

public class Rook extends Piece{

	public Rook(boolean isAvailable,Position position, Team team) {
		super(isAvailable, position, team);
	}
	
	@Override
	boolean isValidMove(Position position) {
		if (super.isValidMove(position)) {
			if (currentPosition.getX() == position.getX()
					|| currentPosition.getY() == position.getY()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void display() {
		super.display();
		System.out.print("RO|");
		
	}
}
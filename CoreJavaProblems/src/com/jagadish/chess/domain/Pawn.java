package com.jagadish.chess.domain;

public class Pawn extends Piece {
	
	int delta;
	public Pawn(boolean isAvailable,Position position, Team team) {
		super(isAvailable, position, team);
		this.delta = (this.currentPosition.getY()==1)?1:-1;
	}
	
	@Override
	boolean isValidMove(Position position) {
		// TODO Auto-generated method stub
		if(super.isValidMove(position)){
			if (currentPosition.getX() == position.getX() && currentPosition.getY() + delta == position.getY()){
	            return true;
	        }
		}
		return false;
	}
	
	@Override
	public void display() {
		super.display();
		System.out.print("PA|");
		
	}

}
package com.jagadish.chess.domain;

public class Knight extends Piece{

    public Knight(boolean isAvailable,Position position, Team team) {
		super(isAvailable, position, team);
	}

    @Override
    public boolean isValidMove(Position position) {
		// TODO Auto-generated method stub
		if (super.isValidMove(position)) {
			if ((Math.abs(currentPosition.getX() - position.getX()) == 2 && Math
					.abs(currentPosition.getY() - position.getY()) == 1)
					|| (Math.abs(currentPosition.getX() - position.getX()) == 1 && Math
							.abs(currentPosition.getY() - position.getY()) == 2)) {
				return true;
			}
		}
		return false;
	}
    
    public void display() {
		super.display();
		System.out.print("KN|");
		
	}

}
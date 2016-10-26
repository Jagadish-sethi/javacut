package com.jagadish.chess.domain;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

	public King(boolean isAvailable,Position position, Team team) {
		super(isAvailable, position, team);
	}
	
	@Override
	boolean isValidMove(Position position) {
		// TODO Auto-generated method stub
		if(super.isValidMove(position)){
			if ((currentPosition.getX() == position.getX() && Math
					.abs(currentPosition.getY() - position.getY()) == 1)
					|| (currentPosition.getY() == position.getY() && Math
							.abs(currentPosition.getX() - position.getX()) == 1)
					|| (Math.abs(currentPosition.getX() - position.getX()) == 1 && Math
							.abs(currentPosition.getY() - position.getY()) == 1)){
	            return true;
	        }
		}
		return false;
	}

	@Override
	public void display() {
		super.display();
		System.out.print("KI|");
	}
}

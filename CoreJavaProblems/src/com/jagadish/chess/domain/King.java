package com.jagadish.chess.domain;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

	@Override
	List<Position> getValidMoves() {
		// TODO Auto-generated method stub
		List<Position> list = new ArrayList<Position>();
		Position pos1 = new Position(currentPosition.getX()+1,currentPosition.getY()+1);
		if(isValidMove(pos1)) list.add(pos1);
		Position pos2 = new Position(currentPosition.getX(),currentPosition.getY()+1);
		if(isValidMove(pos1)) list.add(pos2);
		Position pos3 = new Position(currentPosition.getX()+1,currentPosition.getY());
		if(isValidMove(pos1)) list.add(pos3);
		Position pos4 = new Position(currentPosition.getX()-1,currentPosition.getY()-1);
		if(isValidMove(pos1)) list.add(pos4);
		Position pos5 = new Position(currentPosition.getX(),currentPosition.getY()-1);
		if(isValidMove(pos1)) list.add(pos5);
		Position pos6 = new Position(currentPosition.getX()-1,currentPosition.getY());
		if(isValidMove(pos1)) list.add(pos6);
		Position pos7 = new Position(currentPosition.getX()-1,currentPosition.getY()+1);
		if(isValidMove(pos1)) list.add(pos7);
		Position pos8 = new Position(currentPosition.getX()+1,currentPosition.getY()-1);
		if(isValidMove(pos1)) list.add(pos8);
		
		return list;
	}

	@Override
	boolean isValidMove(Position position) {
		// TODO Auto-generated method stub
		if(position.getX()>=0 && position.getY()>=0 && position.getX()<8 && position.getY()<8){
			if()
		}
		return false;
	}

}

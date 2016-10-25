package com.jagadish.chess.domain;

public class Position implements Display{
	
	private int x;
	private int y;
	private Piece piece;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		this.piece = null;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public void display() {
		if(this.piece!=null){
		this.piece.display();
		}else{
			if((x+y)%2==0){
			System.out.print("|    |");
			}else{
				System.out.print("|++++|");
			}
		}
	}
}

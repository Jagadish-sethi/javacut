package com.jagadish.chess.domain;

import java.io.BufferedReader;

import com.jagadish.main.Main;

public class ChessGame {

	 private Board board;
	 private Player white;
	 private Player black;
	 
	 public ChessGame() {
		this.board = new Board();
		this.white = new Player(Team.WHITE, board);
		this.black = new Player(Team.BLACK, board);
	}
	 
	public static void main(String[] args) {
		ChessGame cg = new ChessGame();
		int option = -1;
		int counter = 0;
		while(option !=2){
		cg.display();
		if(counter%2==0){
			System.out.println("WHITE player turn");
		}else{
			System.out.println("BLACK player turn");
		}
		System.out.println("1. To enter next move");
		System.out.println("2. To quit");
		option = Main.readInteger("Enter operation number ?");
		if(option==1){
			Piece piece = null;
			while(piece==null){
			System.out.println("Please enter valid from position");
			int fx = Main.readInteger("From X ?");
			int fy = Main.readInteger("From Y ?");
			piece = cg.board.getPosition(fx, fy).getPiece();
			}
			
			boolean isValid = false;
			while(!isValid){
				System.out.println("Please enter valid from position");
				int tx = Main.readInteger("To X ?");
				int ty = Main.readInteger("To Y ?");
				Position pos = cg.board.getPosition(tx, ty);
				isValid = piece.applyMove(pos);
			}
			
			counter++;	
		}
		
		}
	}
	
	public void display() {
		board.display();
	}
}

package com.jagadish.chess.main;

import com.jagadish.chess.domain.Board;
import com.jagadish.chess.domain.Player;
import com.jagadish.chess.domain.Team;

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
		cg.display();
	}
	
	public void display() {
		board.display();
	}
}

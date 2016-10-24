package com.jagadish.chess.domain;

public class Board {
	
	int[][] board;
	
	Board() {
		board = new int[8][8];
		board[7][7]=1;
	}

}

package com.jagadish.chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    
	public Team team;
	public Board board;

    private List<Piece> pieces = new ArrayList<>();

    public Player(Team team,Board board ) {
        super();
        this.team = team;
        this.board = board;
        initializePieces();
    }

    public List<Piece> getPieces() {
        return pieces;
    }


    public void initializePieces(){
        if(this.team == Team.WHITE){
            for(int i=0; i<8; i++){ // draw pawns
                pieces.add(new Pawn(true,board.getPosition(i,1),Team.WHITE));
            }
            pieces.add(new Rook(true,board.getPosition(0, 0), Team.WHITE));
            pieces.add(new Rook(true,board.getPosition(7, 0), Team.WHITE));
            pieces.add(new Bishop(true,board.getPosition(2, 0), Team.WHITE));
            pieces.add(new Bishop(true,board.getPosition(5, 0), Team.WHITE));
            pieces.add(new Knight(true,board.getPosition(1, 0), Team.WHITE));
            pieces.add(new Knight(true,board.getPosition(6, 0), Team.WHITE));
            pieces.add(new Queen(true,board.getPosition(3, 0), Team.WHITE));
            pieces.add(new King(true,board.getPosition(4, 0), Team.WHITE));
        }
        else{
            for(int i=0; i<8; i++){ 
                pieces.add(new Pawn(true,board.getPosition(i,6),Team.BLACK));
            }
            pieces.add(new Rook(true,board.getPosition(0, 7),Team.BLACK));
            pieces.add(new Rook(true,board.getPosition(7, 7),Team.BLACK));
            pieces.add(new Bishop(true,board.getPosition(2, 7),Team.BLACK));
            pieces.add(new Bishop(true,board.getPosition(5, 7),Team.BLACK));
            pieces.add(new Knight(true,board.getPosition(1, 7),Team.BLACK));
            pieces.add(new Knight(true,board.getPosition(6, 7),Team.BLACK));
            pieces.add(new Queen(true,board.getPosition(3, 7),Team.BLACK));
            pieces.add(new King(true,board.getPosition(4, 7),Team.BLACK));
        }

    }
}
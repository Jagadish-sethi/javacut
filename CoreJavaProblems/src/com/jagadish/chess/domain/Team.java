package com.jagadish.chess.domain;

public enum Team implements Display {
	WHITE(1),BLACK(2);
	int value;
	private Team(int value) {
		this.value = value;
	}
	
	public void display() {
		if (value == 1) {
			System.out.print("|W-");
		} else {
			System.out.print("|B-");
		}
	}

}

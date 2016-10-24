package com.jagadish.chess.domain;

public enum Team {
	WHITE(1),BLACK(2);
	int value;
	private Team(int value) {
		this.value = value;
	}
}

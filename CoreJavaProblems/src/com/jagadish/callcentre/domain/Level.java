package com.jagadish.callcentre.domain;

public enum Level {
	FRESHER(1),TECHNICAL_LEAD(2),PRODUCT_MANAGER(3);
	private int position;
	Level(int position) {
		this.position = position;
	}
}

enum CallStatus {
	Opened(1),Hold(2),InProgress(3),Resolved(4),UnResolved(5),Invalid(6),Closed(7);
	private int status;
	private CallStatus(int status) {
		this.status = status;
	}
}
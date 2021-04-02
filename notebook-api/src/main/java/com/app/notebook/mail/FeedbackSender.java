package com.app.notebook.mail;

public interface FeedbackSender {
	void sendFeedback(String from, String name, String feedback);
}

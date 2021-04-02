package com.app.notebook.controller;

import javax.validation.ValidationException;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.notebook.mail.FeedbackSender;
import com.app.notebook.viewmodel.FeedbackViewModel;

@RestController
@RequestMapping("/api/v1/feedback")
@CrossOrigin
public class FeedbackController {
	private FeedbackSender feedbackSender;

	public FeedbackController(FeedbackSender feedbackSender) {
		this.feedbackSender = feedbackSender;
	}

	@PostMapping
	public void sendFeedback(@RequestBody FeedbackViewModel feedbackViewModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidationException("Feedback has errors; Can not send feedback;");
		}

		this.feedbackSender.sendFeedback(feedbackViewModel.getEmail(), 
				feedbackViewModel.getName(),
				feedbackViewModel.getFeedback());
	}
}

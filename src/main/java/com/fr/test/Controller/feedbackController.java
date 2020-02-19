package com.fr.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.fr.test.mail.FeedbackSender;
import com.fr.test.model.feedbackViewModel;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin("*")
public class feedbackController {
	@Autowired
	private FeedbackSender feedbackSender;

	public feedbackController(FeedbackSender feedbackSender) {
		this.feedbackSender = feedbackSender;
	}

	@PostMapping("/mail")
	public void sendFeedback(@RequestBody feedbackViewModel feedbackViewModel, BindingResult bindingResult) {
		System.out.println(feedbackViewModel);
		System.out.println(bindingResult);
		System.out.println("email is using vlaue");
		System.out.println(feedbackViewModel.getEmail());
		if (bindingResult.hasErrors()) {
			throw new ValidationException("Feedback has errors; Can not send feedback;");
		}

		this.feedbackSender.sendFeedback(feedbackViewModel.getEmail(), feedbackViewModel.getName(),
				feedbackViewModel.getFeedback());
	}
}

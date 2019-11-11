package com.fr.adaming.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.fr.adaming.mail.FeedbackSender;
import javax.validation.ValidationException;
import com.fr.adaming.dto.feedbackViewModel;
/*
Requests can be tested using the built in HTTP Rest Client. Use the
examples found in 'noteit.http' file.
 */

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin("*")
public class feedbackController {
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

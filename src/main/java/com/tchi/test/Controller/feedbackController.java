package com.tchi.test.Controller;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
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
	}	@PostMapping
	public void sendemail(@RequestBody Email email) {
		System.out.println(email);		 
		System.out.println("email is using vlaue");
		System.out.println(email.getTo());
	 
//		    private String to;
//		    private String subject;
//		    private String content;    
		this.feedbackSender.sendFeedback(email.getTo(), email.getSubject(),email.getContent());
	}
  
}

package service;

import javax.servlet.http.HttpServletRequest;

import domain.Submission;

public interface SubmissionService {
	

	boolean updateSubmission(Submission submission);
}

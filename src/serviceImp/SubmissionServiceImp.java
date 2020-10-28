package serviceImp;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Submission;
import service.SubmissionService;
import shared.UnitOfWork;
import util.JsonToObject;

public class SubmissionServiceImp implements SubmissionService{
	private JsonToObject jo = new JsonToObject();

	@Override
	public boolean updateSubmission(HttpServletRequest request) {
		UnitOfWork.newCurrent();
		Submission submission = new Submission();
		JSONObject submissionJsonObject = jo.ReqJsonToObject(request);
		submission = JSON.toJavaObject(submissionJsonObject, Submission.class);

		// update the submission
		UnitOfWork.getCurrent().registerDirty(submission);

		return UnitOfWork.getCurrent().commit();
	}
	
	

}

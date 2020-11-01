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
	private UnitOfWork current;
	
	public SubmissionServiceImp(UnitOfWork current) {
		this.current = current;
		current.setCurrent(current.getCurrent());
		this.current = current;
	}

	@Override
	public boolean updateSubmission(Submission submission) {
	//	UnitOfWork.newCurrent();

		// update the submission
		current.registerDirty(submission);

		return current.commit();
	}
	
	

}

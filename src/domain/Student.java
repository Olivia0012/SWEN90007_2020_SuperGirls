package domain;

import java.util.ArrayList;
import java.util.List;

import enumeration.Role;
import mapper.QuestionMapper;
import mapper.SubmissionMapper;

public class Student extends User{
	
	private List<Submission> submissions = new ArrayList<Submission>();
	

	public Student(User user,List<Submission> submissions) {
		super(user.getId(), user.getUserName(), user.getPassWord(), Role.STUDENT);
		this.setSubmissions(submissions);
	}


	public Student() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the submissions
	 */
	public List<Submission> getSubmissions() {
		if(submissions == null) {
			load();
		}
		return submissions;
	}


	/**
	 * @param submissions the submissions to set
	 */
	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}


	void load() {
		try {
			SubmissionMapper submissionMapper = new SubmissionMapper();
			List<Submission> submissionList = submissionMapper.findByStudentId(Id);
			submissions = submissionList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}





}

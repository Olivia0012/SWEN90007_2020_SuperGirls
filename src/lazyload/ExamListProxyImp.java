package lazyload;

import java.util.List;

import domain.Exam;
import enumeration.Role;

public class ExamListProxyImp implements ExamList{
	private ExamList examList;
	
	@Override
	public List<Exam> getExamList(int subjectId, Role role) {
		if(examList == null) {
			examList = new ExamListImp();
		}
		
		return examList.getExamList(subjectId, role);
	}
	

}

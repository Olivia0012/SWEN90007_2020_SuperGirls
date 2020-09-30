package lazyload;

import java.util.List;

import domain.Exam;
import enumeration.Role;
import mapper.ExamMapper;

public class ExamListImp implements ExamList {
	private List<Exam> examList;
	private ExamMapper examMapper = new ExamMapper();

	@Override
	public List<Exam> getExamList(int subjectId, Role role) {
		return getExams(subjectId,role);
	}
	
	private List<Exam> getExams(int subjectId, Role role){
		examList = examMapper.FindAllExamsBySubjectId(subjectId, role);
	
		return examList;
		
	}

}

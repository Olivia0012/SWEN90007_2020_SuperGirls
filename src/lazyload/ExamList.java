package lazyload;

import java.util.List;

import domain.Exam;
import enumeration.Role;

public interface ExamList {
	public List<Exam> getExamList(int subjectId, Role role);

}

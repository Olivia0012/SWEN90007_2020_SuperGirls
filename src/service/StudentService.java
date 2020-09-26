package service;

public interface StudentService {
	public String findAllStudentsBySubjectId(int subjectId);
	
	public String findAllStudentsByExamId(int examId);

	String findAllStudentsAndSubmissions(int subjectId, int examId);
	

}

package domain;

import java.util.List;

public class InstructorListProxyImp implements InstructorList{
	private InstructorList instructorList;

	@Override
	public List<User> getInstructorList(int subjectId) {
		if(instructorList == null) {
			instructorList = new InstructorListImp();
		}
		return instructorList.getInstructorList(subjectId);
	}

}

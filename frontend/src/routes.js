import React from 'react';
import DashboardLayout from 'src/layouts/DashboardLayout';
import SubjectListView from 'src/views/subject/SubjectListView';
import StudentListView from 'src/views/student/UserListView';
import Exam from 'src/views/exam/ExamView';
import LoginView from 'src/views/auth/LoginView';
import EditExam from 'src/views/exam/EditExamView';
import MarkExamView from 'src/views/exam/MarkExamView';
import MainLayout from 'src/layouts/MainLayout';
import TakeExam from './views/exam/TakeExam';
import DashboardAdmin from './layouts/DashboardAdmin';
import SubjectManagement from './views/admin/SubjectManagement/SubjectManagement';
import UserManagement from './views/admin/UserManagement/UserManagement';

const routes = [
  {
    path: 'oea',
    element: <DashboardLayout />,
    children: [
      { path: 'subjects', element: <SubjectListView /> },
      { path: 'students/subject=:subject&exam=:exam', element: <StudentListView /> },
      { path: 'exam?user=:user', element: <EditExam /> },
      { path: 'exam/id=:id', element: <Exam />},
      { path: 'students/submission=:submission', element: <MarkExamView />},
      { path: 'takeExam/submission=:submission', element: <TakeExam />},
      { path: 'viewResult/examId=:examId', element: <MarkExamView />},
    ]
  },
	{
		path: 'admin',
		element: <DashboardAdmin />,
		children: [ { path: 'subjects', element: <SubjectManagement /> }, 
		{ path: 'user', element: <UserManagement /> } ]
	},
  {
    path: '/',
    element: <MainLayout />,
    children: [
      { path: '', element: <LoginView /> },
    ]
  }
];

export default routes;

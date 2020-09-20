import React from 'react';
import DashboardLayout from 'src/layouts/DashboardLayout';
import SubjectListView from 'src/views/subject/SubjectListView';
import StudentListView from 'src/views/student/UserListView';
import Exam from 'src/views/exam/ExamView';

const routes = [
  {
    path: '/',
    element: <DashboardLayout />,
    children: [
      { path: 'subjects', element: <SubjectListView /> },
      { path: 'students', element: <StudentListView /> },
    //  { path: 'exams', element: <Exam /> },
      { path: `exam/id=:id`, element: <Exam />},
    ]
  }
];

export default routes;

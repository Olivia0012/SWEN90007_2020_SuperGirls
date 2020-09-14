import React from 'react';
import DashboardLayout from 'src/layouts/DashboardLayout';
import CustomerListView from 'src/views/student/UserListView';
import Exam from 'src/views/exam/ExamView';

const routes = [
  {
    path: '/',
    element: <DashboardLayout />,
    children: [
      { path: 'subjects', element: <CustomerListView /> },
      { path: 'exams', element: <Exam /> }
    ]
  }
];

export default routes;

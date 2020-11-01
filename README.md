# SWEN90007_2020_SuperGirls

<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-3-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->
![GitHub repo size](https://img.shields.io/github/repo-size/Olivia0012/SWEN90007_2020_SuperGirls)
![GitHub](https://img.shields.io/github/license/Olivia0012/SWEN90007_2020_SuperGirls)

## Introduction
```
This repository is used for managing deliverables of SWEN90007. 
All deliverables will be uploaded and managed here.

```

## Contributors
<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://github.com/Olivia0012"><img src="https://avatars0.githubusercontent.com/u/55537942?v=4" width="100px;" alt=""/><br /><sub><b>Lu Wang</b></sub></a><br /><a href="https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/commits?author=Olivia0012" title="Code"></a></td>
    <td align="center"><a href="https://github.com/Susan-Yao"><img src="https://avatars1.githubusercontent.com/u/57033153?v=4" width="100px;" alt=""/><br /><sub><b>Jianjing Yao</b></sub></a><br /><a href="https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/commits?author=Susan-Yao" title="Code"></a></td>
    <td align="center"><a href="https://github.com/lucyliu13"><img src="https://avatars2.githubusercontent.com/u/64895984?v=4" width="100px;" alt=""/><br /><sub><b>Xueling Liu</b></sub></a><br /><a href="https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/commits?author=lucyliu13" title="Code"></a></td>
  </tr>
  <tr>
    <td align="center">1054195</td>
    <td align="center">1049113</td>
    <td align="center">1095044</td>
  </tr>
  <tr>
    <td align="center">lu.wang4@student.unimelb.edu.au</td>
    <td align="center">jianjingy@student.unimelb.edu.au</td>
    <td align="center">xuelingl1@student.unimelb.edu.au</td>
  </tr>
</table>

<!-- markdownlint-enable -->
<!-- prettier-ignore-end -->
<!-- ALL-CONTRIBUTORS-LIST:END -->

## Description
### Online-Exam-Application: https://frontend-react-test-01.herokuapp.com/

### Patterns:

1. Domain Logic

    [Domain model](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/tree/master/src/domain)

    [Service Interface](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/tree/master/src/service) ; [Service Implementation](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/tree/master/src/serviceImp)

2. Data-source layer

   [Data mapper](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/tree/master/src/mapper)

3. Object-to-relational behavioural design

    [Unit of Work](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/tree/master/src/shared)

    Lazy load: question list in the Exam and answer list in the Submission.

    Identity Field: all tables

4. Presentation layer:
  
   Implemented with React: [frontend](http://35.174.205.208:3000/)

5. Concurrency:

    Pessimistic lock: 
      [ExclusiveWriteLock](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/blob/master/src/mapper/ExclusiveWriteLockManager.java)
      [editingExamLockController](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/blob/master/src/servlet/LockEditExamController.java)
      [editingMarkLockController](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/blob/master/src/servlet/LockMarkExamController.java)

6. Security:

      [authentication](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/blob/master/src/util/SSOLogin.java)
      
      Authorization: checking the user role in every controller before handling the request, for example,  [admin request controller for viewing and add subjects](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/blob/master/src/servlet/AdminSubjectsController.java)

      Secure Pipe:
      [AESCrypto](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/blob/master/src/util/AESCrypto.java) 


## Environment:
```
Backend: JavaEE - Java SE 14.0.2; Tomcat v9.0; 
Frontend: React v16.13.1; Node 11.0.0; npm: 6.14.8
Database: PostgreSql
```


## Repository Structure
```
.
├── LICENSE
├── Procfile
├── README.md
├── WebContent
│   ├── META-INF
│   │   └── MANIFEST.MF
│   ├── WEB-INF
│   │   ├── lib
│   │   │   ├── fastjson-1.1.6.jar
│   │   │   └── postgresql-42.2.14.jar
│   │   └── web.xml
│   └── test.jsp
├── build
│   └── classes
│       ├── database
│       │   └── DatabaseConnection.class
│       ├── domain
│       │   ├── Admin.class
│       │   ├── Answer.class
│       │   ├── DomainObject.class
│       │   ├── Exam.class
│       │   ├── Instructor.class
│       │   ├── Question.class
│       │   ├── Student.class
│       │   ├── Subject.class
│       │   ├── Submission.class
│       │   └── User.class
│       ├── enumeration
│       │   ├── ExamStatus.class
│       │   ├── QuestionType.class
│       │   └── Role.class
│       ├── mapper
│       │   ├── AnswerMapper.class
│       │   ├── DataMapper.class
│       │   ├── ExamMapper.class
│       │   ├── QuestionMapper.class
│       │   ├── SubjectMapper.class
│       │   ├── SubmissionMapper.class
│       │   └── UserMapper.class
│       ├── service
│       │   ├── CreateNewExam.class
│       │   ├── CreateNewExamImp.class
│       │   ├── ViewExam.class
│       │   └── ViewExamImp.class
│       ├── servlet
│       │   ├── ExamController.class
│       │   └── HelloServlet.class
│       └── shared
│           ├── IdentityMap.class
│           ├── LazyLoad.class
│           └── UnitOfWork.class
├── docs
│   ├── Part2_Figures
│   │   ├── Figure\ 1\ Domain\ Model.png
│   │   ├── Figure\ 2\ �\200\2344+1�\200\235\ Framework.png
│   │   ├── Figure\ 3\ UML\ Class\ Diagram\ Relationships.png
│   │   ├── Figure\ 4\ Class\ Diagram.png
│   │   ├── Figure\ 5\ Sequence\ Diagram\ of\ Instructor.png
│   │   ├── Figure\ 6\ Sequence\ Diagram\ of\ Student.png
│   │   ├── Figure\ 7\ Component\ Diagram.png
│   │   ├── Figure\ 8-1\ Deployment\ Diagram.png
│   │   ├── Figure\ 8-2\ Deployment\ Diagram.png
│   │   └── Figure\ 9\ Use\ Case\ Diagram.png
│   ├── architecture
│   │   └── SWEN90007_CIS-TMPLT-ARCH-1.docx
│   ├── meetings
│   │   ├── README.md
│   │   ├── Week\ 2\ Minutes_Aug\ 10_Team\ Super\ Girls.pdf
│   │   ├── Week\ 3\ Minutes_Aug\ 17_Team\ Super\ Girls.pdf
│   │   ├── Week\ 4\ Minutes_Aug\ 24_Team\ Super\ Girls.pdf
│   │   ├── Week\ 5\ Minutes_Aug\ 31_Team\ Super\ Girls.pdf
│   │   ├── Week\ 6\ Minutes_Sep\ 07_Team\ Super\ Girls.pdf
│   │   ├── Week\ 7\ Minutes_Sep\ 14_Team\ Super\ Girls.pdf
│   │   ├── Week\ 8\ Minutes_Sep\ 21_Team\ Super\ Girls.pdf
│   │   └── Week\ 9\ Minutes_Sep\ 28_Team\ Super\ Girls.pdf
│   ├── part1
│   │   ├── README.md
│   │   ├── SWEN90007_2020_Part1_SuperGirls.pdf
│   │   ├── [01.00-D05]SWEN90007_2020_Part1_SuperGirls.doc
│   │   └── [01.00-D06]SWEN90007_2020_Part1_SuperGirls(1).doc
│   └── part2
│       └── SWEN90007_2020_Part2_SuperGirls.pdf
├── frontend
│   ├── LICENSE
│   ├── LICENSE.md
│   ├── README.md
│   ├── jsconfig.json
│   ├── nginx.conf
│   ├── package-lock.json
│   ├── package.json
│   ├── public
│   │   ├── _redirects
│   │   ├── favicon.ico
│   │   ├── index.html
│   │   ├── manifest.json
│   │   └── static
│   │       ├── images
│   │       │   ├── auth.jpeg
│   │       │   ├── avatars
│   │       │   │   ├── avatar_1.png
│   │       │   │   ├── avatar_10.png
│   │       │   │   ├── avatar_11.png
│   │       │   │   ├── avatar_2.png
│   │       │   │   ├── avatar_3.png
│   │       │   │   ├── avatar_4.png
│   │       │   │   ├── avatar_5.png
│   │       │   │   ├── avatar_6.png
│   │       │   │   ├── avatar_7.png
│   │       │   │   ├── avatar_8.png
│   │       │   │   └── avatar_9.png
│   │       │   ├── not_found.png
│   │       │   ├── products
│   │       │   │   ├── product_1.png
│   │       │   │   ├── product_2.png
│   │       │   │   ├── product_3.png
│   │       │   │   ├── product_4.png
│   │       │   │   ├── product_5.png
│   │       │   │   └── product_6.png
│   │       │   ├── undraw_page_not_found_su7k.svg
│   │       │   └── undraw_resume_folder_2_arse.svg
│   │       └── logo.svg
│   ├── server.js
│   ├── setupProxy.js
│   ├── src
│   │   ├── App.js
│   │   ├── api
│   │   │   ├── examAPI.js
│   │   │   └── instructorAPI.js
│   │   ├── components
│   │   │   ├── GlobalStyles.js
│   │   │   ├── Logo.js
│   │   │   └── Page.js
│   │   ├── icons
│   │   │   ├── Facebook.js
│   │   │   └── Google.js
│   │   ├── index.js
│   │   ├── layouts
│   │   │   ├── DashboardAdmin
│   │   │   │   ├── NavBar
│   │   │   │   │   ├── NavItem.js
│   │   │   │   │   └── index.js
│   │   │   │   ├── TopBar.js
│   │   │   │   └── index.js
│   │   │   ├── DashboardLayout
│   │   │   │   ├── NavBar
│   │   │   │   │   ├── NavItem.js
│   │   │   │   │   └── index.js
│   │   │   │   ├── TopBar.js
│   │   │   │   └── index.js
│   │   │   └── MainLayout
│   │   │       ├── TopBar.js
│   │   │       └── index.js
│   │   ├── mixins
│   │   │   └── chartjs.js
│   │   ├── routes.js
│   │   ├── serviceWorker.js
│   │   ├── setupProxy.js
│   │   ├── theme
│   │   │   ├── index.js
│   │   │   ├── shadows.js
│   │   │   └── typography.js
│   │   ├── util
│   │   │   └── GetJson.java
│   │   ├── utils
│   │   │   ├── getInitials.js
│   │   │   ├── loading.js
│   │   │   └── setAuthToken.js
│   │   └── views
│   │       ├── admin
│   │       │   ├── SubjectManagement
│   │       │   │   ├── Results.js
│   │       │   │   ├── SubjectManagement.js
│   │       │   │   ├── Toolbar.js
│   │       │   │   └── UserTransferList.js
│   │       │   └── UserManagement
│   │       │       ├── Results.js
│   │       │       ├── Toolbar.js
│   │       │       └── UserManagement.js
│   │       ├── auth
│   │       │   ├── LoginView.js
│   │       │   └── RegisterView.js
│   │       ├── exam
│   │       │   ├── EditExamView
│   │       │   │   ├── ExamBasicInfo.js
│   │       │   │   ├── NewQuestion.js
│   │       │   │   └── index.js
│   │       │   ├── ExamView
│   │       │   │   ├── ExamBasicInfo.js
│   │       │   │   ├── NewQuestion.js
│   │       │   │   ├── QuestionCard.js
│   │       │   │   └── index.js
│   │       │   ├── MarkExamView
│   │       │   │   ├── QuestionCard.js
│   │       │   │   ├── SubmissionInfo.js
│   │       │   │   └── index.js
│   │       │   └── TakeExam
│   │       │       ├── ExamInfo.js
│   │       │       ├── QuestionCard.js
│   │       │       └── index.js
│   │       ├── student
│   │       │   └── UserListView
│   │       │       ├── Results.js
│   │       │       ├── Toolbar.js
│   │       │       ├── data.js
│   │       │       └── index.js
│   │       └── subject
│   │           └── SubjectListView
│   │               ├── Results.js
│   │               ├── ResultsforStudent.js
│   │               ├── Toolbar.js
│   │               ├── data.json
│   │               └── index.js
│   ├── tree.text
│   └── yarn.lock
├── pom.xml
├── src
│   ├── database
│   │   ├── ConnectionPool.java
│   │   ├── DBMaintain.java
│   │   ├── DatabaseConnection.java
│   │   └── QueryExecutor.java
│   ├── domain
│   │   ├── Answer.java
│   │   ├── DomainObject.java
│   │   ├── Exam.java
│   │   ├── Instructor.java
│   │   ├── InstructorList.java
│   │   ├── InstructorListImp.java
│   │   ├── InstructorListProxyImp.java
│   │   ├── Question.java
│   │   ├── Relationship.java
│   │   ├── Student.java
│   │   ├── Subject.java
│   │   ├── Submission.java
│   │   └── User.java
│   ├── enumeration
│   │   ├── ExamStatus.java
│   │   ├── QuestionType.java
│   │   └── Role.java
│   ├── mapper
│   │   ├── AnswerMapper.java
│   │   ├── DataMapper.java
│   │   ├── ExamMapper.java
│   │   ├── ExclusiveWriteLockManager.java
│   │   ├── LockManager.java
│   │   ├── QuestionMapper.java
│   │   ├── RelationshipMapper.java
│   │   ├── SubjectMapper.java
│   │   ├── SubmissionMapper.java
│   │   └── UserMapper.java
│   ├── service
│   │   ├── ExamService.java
│   │   ├── StudentService.java
│   │   ├── SubjectService.java
│   │   ├── SubmissionService.java
│   │   └── UserService.java
│   ├── serviceImp
│   │   ├── ExamServiceImp.java
│   │   ├── StudentServiceImp.java
│   │   ├── SubjectServiceImp.java
│   │   ├── SubmissionServiceImp.java
│   │   └── UserServiceImp.java
│   ├── servlet
│   │   ├── AddExamController.java
│   │   ├── AdminSubjectsController.java
│   │   ├── AdminUserInSubjectController.java
│   │   ├── AdminUsersController.java
│   │   ├── DeleteExamController.java
│   │   ├── DeleteQuestionController.java
│   │   ├── EditExamController.java
│   │   ├── ExamController.java
│   │   ├── InstructorEditMarkController.java
│   │   ├── LockEditExamController.java
│   │   ├── LockMarkExamController.java
│   │   ├── LoginController.java
│   │   ├── LogoutController.java
│   │   ├── MarkExamController.java
│   │   ├── StudentController.java
│   │   ├── SubjectController.java
│   │   └── TakeExamController.java
│   ├── shared
│   │   └── UnitOfWork.java
│   └── util
│       ├── AESCrypto.java
│       ├── JsonToObject.java
│       ├── ResponseHeader.java
│       └── SSOLogin.java
├── system.properties
├── target
│   ├── classes
│   │   ├── database
│   │   │   ├── ConnectionPool.class
│   │   │   ├── DBMaintain.class
│   │   │   ├── DatabaseConnection.class
│   │   │   └── QueryExecutor.class
│   │   ├── domain
│   │   │   ├── Answer.class
│   │   │   ├── DomainObject.class
│   │   │   ├── Exam.class
│   │   │   ├── Instructor.class
│   │   │   ├── InstructorList.class
│   │   │   ├── InstructorListImp.class
│   │   │   ├── InstructorListProxyImp.class
│   │   │   ├── Question.class
│   │   │   ├── Relationship.class
│   │   │   ├── Student.class
│   │   │   ├── Subject.class
│   │   │   ├── Submission.class
│   │   │   └── User.class
│   │   ├── enumeration
│   │   │   ├── ExamStatus.class
│   │   │   ├── QuestionType.class
│   │   │   └── Role.class
│   │   ├── mapper
│   │   │   ├── AnswerMapper.class
│   │   │   ├── DataMapper.class
│   │   │   ├── ExamMapper.class
│   │   │   ├── ExclusiveWriteLockManager.class
│   │   │   ├── LockManager.class
│   │   │   ├── QuestionMapper.class
│   │   │   ├── RelationshipMapper.class
│   │   │   ├── SubjectMapper.class
│   │   │   ├── SubmissionMapper.class
│   │   │   └── UserMapper.class
│   │   ├── service
│   │   │   ├── ExamService.class
│   │   │   ├── StudentService.class
│   │   │   ├── SubjectService.class
│   │   │   ├── SubmissionService.class
│   │   │   └── UserService.class
│   │   ├── serviceImp
│   │   │   ├── ExamServiceImp.class
│   │   │   ├── StudentServiceImp.class
│   │   │   ├── SubjectServiceImp.class
│   │   │   ├── SubmissionServiceImp.class
│   │   │   └── UserServiceImp.class
│   │   ├── servlet
│   │   │   ├── AddExamController.class
│   │   │   ├── AdminSubjectsController.class
│   │   │   ├── AdminUserInSubjectController.class
│   │   │   ├── AdminUsersController.class
│   │   │   ├── DeleteExamController.class
│   │   │   ├── DeleteQuestionController.class
│   │   │   ├── EditExamController.class
│   │   │   ├── ExamController.class
│   │   │   ├── InstructorEditMarkController.class
│   │   │   ├── LockEditExamController.class
│   │   │   ├── LockMarkExamController.class
│   │   │   ├── LoginController.class
│   │   │   ├── LogoutController.class
│   │   │   ├── MarkExamController.class
│   │   │   ├── StudentController.class
│   │   │   ├── SubjectController.class
│   │   │   └── TakeExamController.class
│   │   ├── shared
│   │   │   └── UnitOfWork.class
│   │   └── util
│   │       ├── AESCrypto.class
│   │       ├── JsonToObject.class
│   │       ├── ResponseHeader.class
│   │       └── SSOLogin.class
│   ├── m2e-wtp
│   │   └── web-resources
│   │       └── META-INF
│   │           ├── MANIFEST.MF
│   │           └── maven
│   │               └── SWEN90007_2020_SuperGirls
│   │                   └── SWEN90007_2020_SuperGirls
│   │                       ├── pom.properties
│   │                       └── pom.xml
│   └── test-classes
├── test
│   └── SWEN90007-CIS-SW-TSTD.doc
└── tree.text

82 directories, 292 files
.
├── LICENSE
├── Procfile
├── README.md
├── WebContent
│   ├── META-INF
│   │   └── MANIFEST.MF
│   ├── WEB-INF
│   │   ├── lib
│   │   │   ├── fastjson-1.1.6.jar
│   │   │   └── postgresql-42.2.14.jar
│   │   └── web.xml
│   └── test.jsp
├── build
│   └── classes
│       ├── database
│       │   └── DatabaseConnection.class
│       ├── domain
│       │   ├── Admin.class
│       │   ├── Answer.class
│       │   ├── DomainObject.class
│       │   ├── Exam.class
│       │   ├── Instructor.class
│       │   ├── Question.class
│       │   ├── Student.class
│       │   ├── Subject.class
│       │   ├── Submission.class
│       │   └── User.class
│       ├── enumeration
│       │   ├── ExamStatus.class
│       │   ├── QuestionType.class
│       │   └── Role.class
│       ├── mapper
│       │   ├── AnswerMapper.class
│       │   ├── DataMapper.class
│       │   ├── ExamMapper.class
│       │   ├── QuestionMapper.class
│       │   ├── SubjectMapper.class
│       │   ├── SubmissionMapper.class
│       │   └── UserMapper.class
│       ├── service
│       │   ├── CreateNewExam.class
│       │   ├── CreateNewExamImp.class
│       │   ├── ViewExam.class
│       │   └── ViewExamImp.class
│       ├── servlet
│       │   ├── ExamController.class
│       │   └── HelloServlet.class
│       └── shared
│           ├── IdentityMap.class
│           ├── LazyLoad.class
│           └── UnitOfWork.class
├── docs
│   ├── Part2_Figures
│   │   ├── Figure\ 1\ Domain\ Model.png
│   │   ├── Figure\ 2\ �\200\2344+1�\200\235\ Framework.png
│   │   ├── Figure\ 3\ UML\ Class\ Diagram\ Relationships.png
│   │   ├── Figure\ 4\ Class\ Diagram.png
│   │   ├── Figure\ 5\ Sequence\ Diagram\ of\ Instructor.png
│   │   ├── Figure\ 6\ Sequence\ Diagram\ of\ Student.png
│   │   ├── Figure\ 7\ Component\ Diagram.png
│   │   ├── Figure\ 8-1\ Deployment\ Diagram.png
│   │   ├── Figure\ 8-2\ Deployment\ Diagram.png
│   │   └── Figure\ 9\ Use\ Case\ Diagram.png
│   ├── architecture
│   │   └── SWEN90007_CIS-TMPLT-ARCH-1.docx
│   ├── meetings
│   │   ├── README.md
│   │   ├── Week\ 2\ Minutes_Aug\ 10_Team\ Super\ Girls.pdf
│   │   ├── Week\ 3\ Minutes_Aug\ 17_Team\ Super\ Girls.pdf
│   │   ├── Week\ 4\ Minutes_Aug\ 24_Team\ Super\ Girls.pdf
│   │   ├── Week\ 5\ Minutes_Aug\ 31_Team\ Super\ Girls.pdf
│   │   ├── Week\ 6\ Minutes_Sep\ 07_Team\ Super\ Girls.pdf
│   │   ├── Week\ 7\ Minutes_Sep\ 14_Team\ Super\ Girls.pdf
│   │   ├── Week\ 8\ Minutes_Sep\ 21_Team\ Super\ Girls.pdf
│   │   └── Week\ 9\ Minutes_Sep\ 28_Team\ Super\ Girls.pdf
│   ├── part1
│   │   ├── README.md
│   │   ├── SWEN90007_2020_Part1_SuperGirls.pdf
│   │   ├── [01.00-D05]SWEN90007_2020_Part1_SuperGirls.doc
│   │   └── [01.00-D06]SWEN90007_2020_Part1_SuperGirls(1).doc
│   └── part2
│       └── SWEN90007_2020_Part2_SuperGirls.pdf
├── frontend
│   ├── LICENSE
│   ├── LICENSE.md
│   ├── README.md
│   ├── jsconfig.json
│   ├── nginx.conf
│   ├── package-lock.json
│   ├── package.json
│   ├── public
│   │   ├── _redirects
│   │   ├── favicon.ico
│   │   ├── index.html
│   │   ├── manifest.json
│   │   └── static
│   │       ├── images
│   │       │   ├── auth.jpeg
│   │       │   ├── avatars
│   │       │   │   ├── avatar_1.png
│   │       │   │   ├── avatar_10.png
│   │       │   │   ├── avatar_11.png
│   │       │   │   ├── avatar_2.png
│   │       │   │   ├── avatar_3.png
│   │       │   │   ├── avatar_4.png
│   │       │   │   ├── avatar_5.png
│   │       │   │   ├── avatar_6.png
│   │       │   │   ├── avatar_7.png
│   │       │   │   ├── avatar_8.png
│   │       │   │   └── avatar_9.png
│   │       │   ├── not_found.png
│   │       │   ├── products
│   │       │   │   ├── product_1.png
│   │       │   │   ├── product_2.png
│   │       │   │   ├── product_3.png
│   │       │   │   ├── product_4.png
│   │       │   │   ├── product_5.png
│   │       │   │   └── product_6.png
│   │       │   ├── undraw_page_not_found_su7k.svg
│   │       │   └── undraw_resume_folder_2_arse.svg
│   │       └── logo.svg
│   ├── server.js
│   ├── setupProxy.js
│   ├── src
│   │   ├── App.js
│   │   ├── api
│   │   │   ├── examAPI.js
│   │   │   └── instructorAPI.js
│   │   ├── components
│   │   │   ├── GlobalStyles.js
│   │   │   ├── Logo.js
│   │   │   └── Page.js
│   │   ├── icons
│   │   │   ├── Facebook.js
│   │   │   └── Google.js
│   │   ├── index.js
│   │   ├── layouts
│   │   │   ├── DashboardAdmin
│   │   │   │   ├── NavBar
│   │   │   │   │   ├── NavItem.js
│   │   │   │   │   └── index.js
│   │   │   │   ├── TopBar.js
│   │   │   │   └── index.js
│   │   │   ├── DashboardLayout
│   │   │   │   ├── NavBar
│   │   │   │   │   ├── NavItem.js
│   │   │   │   │   └── index.js
│   │   │   │   ├── TopBar.js
│   │   │   │   └── index.js
│   │   │   └── MainLayout
│   │   │       ├── TopBar.js
│   │   │       └── index.js
│   │   ├── mixins
│   │   │   └── chartjs.js
│   │   ├── routes.js
│   │   ├── serviceWorker.js
│   │   ├── setupProxy.js
│   │   ├── theme
│   │   │   ├── index.js
│   │   │   ├── shadows.js
│   │   │   └── typography.js
│   │   ├── util
│   │   │   └── GetJson.java
│   │   ├── utils
│   │   │   ├── getInitials.js
│   │   │   ├── loading.js
│   │   │   └── setAuthToken.js
│   │   └── views
│   │       ├── admin
│   │       │   ├── SubjectManagement
│   │       │   │   ├── Results.js
│   │       │   │   ├── SubjectManagement.js
│   │       │   │   ├── Toolbar.js
│   │       │   │   └── UserTransferList.js
│   │       │   └── UserManagement
│   │       │       ├── Results.js
│   │       │       ├── Toolbar.js
│   │       │       └── UserManagement.js
│   │       ├── auth
│   │       │   ├── LoginView.js
│   │       │   └── RegisterView.js
│   │       ├── exam
│   │       │   ├── EditExamView
│   │       │   │   ├── ExamBasicInfo.js
│   │       │   │   ├── NewQuestion.js
│   │       │   │   └── index.js
│   │       │   ├── ExamView
│   │       │   │   ├── ExamBasicInfo.js
│   │       │   │   ├── NewQuestion.js
│   │       │   │   ├── QuestionCard.js
│   │       │   │   └── index.js
│   │       │   ├── MarkExamView
│   │       │   │   ├── QuestionCard.js
│   │       │   │   ├── SubmissionInfo.js
│   │       │   │   └── index.js
│   │       │   └── TakeExam
│   │       │       ├── ExamInfo.js
│   │       │       ├── QuestionCard.js
│   │       │       └── index.js
│   │       ├── student
│   │       │   └── UserListView
│   │       │       ├── Results.js
│   │       │       ├── Toolbar.js
│   │       │       ├── data.js
│   │       │       └── index.js
│   │       └── subject
│   │           └── SubjectListView
│   │               ├── Results.js
│   │               ├── ResultsforStudent.js
│   │               ├── Toolbar.js
│   │               ├── data.json
│   │               └── index.js
│   ├── tree.text
│   └── yarn.lock
├── pom.xml
├── src
│   ├── database
│   │   ├── ConnectionPool.java
│   │   ├── DBMaintain.java
│   │   ├── DatabaseConnection.java
│   │   └── QueryExecutor.java
│   ├── domain
│   │   ├── Answer.java
│   │   ├── DomainObject.java
│   │   ├── Exam.java
│   │   ├── Instructor.java
│   │   ├── InstructorList.java
│   │   ├── InstructorListImp.java
│   │   ├── InstructorListProxyImp.java
│   │   ├── Question.java
│   │   ├── Relationship.java
│   │   ├── Student.java
│   │   ├── Subject.java
│   │   ├── Submission.java
│   │   └── User.java
│   ├── enumeration
│   │   ├── ExamStatus.java
│   │   ├── QuestionType.java
│   │   └── Role.java
│   ├── mapper
│   │   ├── AnswerMapper.java
│   │   ├── DataMapper.java
│   │   ├── ExamMapper.java
│   │   ├── ExclusiveWriteLockManager.java
│   │   ├── LockManager.java
│   │   ├── QuestionMapper.java
│   │   ├── RelationshipMapper.java
│   │   ├── SubjectMapper.java
│   │   ├── SubmissionMapper.java
│   │   └── UserMapper.java
│   ├── service
│   │   ├── ExamService.java
│   │   ├── StudentService.java
│   │   ├── SubjectService.java
│   │   ├── SubmissionService.java
│   │   └── UserService.java
│   ├── serviceImp
│   │   ├── ExamServiceImp.java
│   │   ├── StudentServiceImp.java
│   │   ├── SubjectServiceImp.java
│   │   ├── SubmissionServiceImp.java
│   │   └── UserServiceImp.java
│   ├── servlet
│   │   ├── AddExamController.java
│   │   ├── AdminSubjectsController.java
│   │   ├── AdminUserInSubjectController.java
│   │   ├── AdminUsersController.java
│   │   ├── DeleteExamController.java
│   │   ├── DeleteQuestionController.java
│   │   ├── EditExamController.java
│   │   ├── ExamController.java
│   │   ├── InstructorEditMarkController.java
│   │   ├── LockEditExamController.java
│   │   ├── LockMarkExamController.java
│   │   ├── LoginController.java
│   │   ├── LogoutController.java
│   │   ├── MarkExamController.java
│   │   ├── StudentController.java
│   │   ├── SubjectController.java
│   │   └── TakeExamController.java
│   ├── shared
│   │   └── UnitOfWork.java
│   └── util
│       ├── AESCrypto.java
│       ├── JsonToObject.java
│       ├── ResponseHeader.java
│       └── SSOLogin.java
├── system.properties
├── target
│   ├── classes
│   │   ├── database
│   │   │   ├── ConnectionPool.class
│   │   │   ├── DBMaintain.class
│   │   │   ├── DatabaseConnection.class
│   │   │   └── QueryExecutor.class
│   │   ├── domain
│   │   │   ├── Answer.class
│   │   │   ├── DomainObject.class
│   │   │   ├── Exam.class
│   │   │   ├── Instructor.class
│   │   │   ├── InstructorList.class
│   │   │   ├── InstructorListImp.class
│   │   │   ├── InstructorListProxyImp.class
│   │   │   ├── Question.class
│   │   │   ├── Relationship.class
│   │   │   ├── Student.class
│   │   │   ├── Subject.class
│   │   │   ├── Submission.class
│   │   │   └── User.class
│   │   ├── enumeration
│   │   │   ├── ExamStatus.class
│   │   │   ├── QuestionType.class
│   │   │   └── Role.class
│   │   ├── mapper
│   │   │   ├── AnswerMapper.class
│   │   │   ├── DataMapper.class
│   │   │   ├── ExamMapper.class
│   │   │   ├── ExclusiveWriteLockManager.class
│   │   │   ├── LockManager.class
│   │   │   ├── QuestionMapper.class
│   │   │   ├── RelationshipMapper.class
│   │   │   ├── SubjectMapper.class
│   │   │   ├── SubmissionMapper.class
│   │   │   └── UserMapper.class
│   │   ├── service
│   │   │   ├── ExamService.class
│   │   │   ├── StudentService.class
│   │   │   ├── SubjectService.class
│   │   │   ├── SubmissionService.class
│   │   │   └── UserService.class
│   │   ├── serviceImp
│   │   │   ├── ExamServiceImp.class
│   │   │   ├── StudentServiceImp.class
│   │   │   ├── SubjectServiceImp.class
│   │   │   ├── SubmissionServiceImp.class
│   │   │   └── UserServiceImp.class
│   │   ├── servlet
│   │   │   ├── AddExamController.class
│   │   │   ├── AdminSubjectsController.class
│   │   │   ├── AdminUserInSubjectController.class
│   │   │   ├── AdminUsersController.class
│   │   │   ├── DeleteExamController.class
│   │   │   ├── DeleteQuestionController.class
│   │   │   ├── EditExamController.class
│   │   │   ├── ExamController.class
│   │   │   ├── InstructorEditMarkController.class
│   │   │   ├── LockEditExamController.class
│   │   │   ├── LockMarkExamController.class
│   │   │   ├── LoginController.class
│   │   │   ├── LogoutController.class
│   │   │   ├── MarkExamController.class
│   │   │   ├── StudentController.class
│   │   │   ├── SubjectController.class
│   │   │   └── TakeExamController.class
│   │   ├── shared
│   │   │   └── UnitOfWork.class
│   │   └── util
│   │       ├── AESCrypto.class
│   │       ├── JsonToObject.class
│   │       ├── ResponseHeader.class
│   │       └── SSOLogin.class
│   ├── m2e-wtp
│   │   └── web-resources
│   │       └── META-INF
│   │           ├── MANIFEST.MF
│   │           └── maven
│   │               └── SWEN90007_2020_SuperGirls
│   │                   └── SWEN90007_2020_SuperGirls
│   │                       ├── pom.properties
│   │                       └── pom.xml
│   └── test-classes
├── test
│   └── SWEN90007-CIS-SW-TSTD.doc
└── tree.text

```

## Git Workflow

### 1. Development set up
#### 　Clone the repository
    git clone git@github.com:Olivia0012/SWEN90007_2020_SuperGirls.git
### 2. Branch naming
#### 　Create a new branch
    git branch <new-branch> 
#### 　Change the current working branch to the new branch
    git checkout <new-branch> 
#### 　Push the new branch 
    git push origin <new-branch> 
#### 　Branch naming standard 
    <end>/<tag>/<description>
     · <application> can be one of backend or frontend.
     · <tag> element matches the tags for the commit standards.
     · <description> should be limited to a small number of works.
    Examples:　frontend/feature/student-login
               backend/test/instructor-logout
               backend/fix/view-exams 
### 3. Commit standards 
<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><b>Tag</b></td>
    <td align="center"><b>Description</b></td>
  </tr>
  <tr>
    <td align="center">feature</td>
    <td align="center">A new feature</td>
  </tr>
   <tr>
    <td align="center">fix</td>
    <td align="center">Fix a bug</td>
  </tr>  
  <tr>
    <td align="center">test</td>
    <td align="center">Unit testing/ integration testing</td>
  </tr>
</table>

<!-- markdownlint-enable -->
<!-- prettier-ignore-end -->
<!-- ALL-CONTRIBUTORS-LIST:END -->
### 4. Merge changes
    · Create a pull request 
    · Do code review with team members 
    · Merge the change 


# Deliverables: 

## Deadlines
```
    Part 1 : 23/08/2020
    Part 2 : 04/10/2020
    Part 3 : 01/11/2020
```
## Details

### 1. Part 1

- [X] A report, in pdf format, including
    - [X] team’s name, team members’ names, student ids, unimelb usernames and emails. 
    - [X]  A use case diagram
    - [X]  A detailed description (see Appendix B for a sample template) of each use case. Each use case must contain: the list of actors associated with the use case, a high-level description of the use case, the typical course of events, and the main alternative courses of action.
    - [X]  A link to your private Git repository. Before the submission deadline, all the teaching team must have read and write access to your repository. A copy of this stage’s report should already be stored in your repository. 
    - [X]  A Git release tag:
        - [X] create a release tag in your Git repository for this deliverable in the following format: ``SWEN90007_2020_Part1_<team name>``
        - [X] The created release tag must be added to your report.

### 2. Part 2
- [X] The application coded and deployed. 
    - [X] The source code of your application must be committed to your Git repository. 
    - [X] The application must be deployed in Heroku.
- [X] You must create a tag in your Git repository for this deliverable in the following format: 
SWEN90007_2020_Part2_<team name>. The tag must be created before the submission 
deadline as this will be used to assess your deliverable (no exceptions).
- [X]  A Software Architecture Design (SAD) report with the following items:
    - [X] The domain model of your application. 
    - [X] The class diagram of your application. 
    - [X] A description of all patterns used. The description must be contextualized, this means 
it must include details of how the pattern was implemented as part of your particular 
application design. A sequence diagram illustrating the use of the pattern in your 
application must be included as part of your description. 
    - [X] Design rationale for unit of work and lazy load: an explanation on where the patterns 
were used and why. 
    - [X] A link to your Heroku deployed app, including a populated database with a range of 
realistic data samples/information that are necessary for the teaching team to test 
your application. You also need to provide additional instructions on how to use the 
existing data that you created in your system, for example: the administrator 
username and password. We do not expect to create any test data from scratch.
Again, this is your project. Make sure you test your deliverable before your submission
and that you have real/appropriate data in the deployed system. Meaningless data 
such as abc, 123, blah blah, subject ASDFG and so on will compromise the assessment 
of your project (and your final marks). See Appendix D for more details. 
  - [X] The Git release tag.

    
### 3. Part 3

- [X] The application coded and deployed. The source code of your application must be committed to your Git repository. The application must be deployed in Heroku.
- [X] You must create a tag in your Git repository for this deliverable in the following format:       SWEN90007_2020_Part3_<team name>. The tag must be created before the submission deadline as this will be used to assess your deliverable (no exceptions).
- [X] A Software Architecture Design (SAD) report with the following items:
    - [X] The updated class diagram of your application, with any updates made to it highlighted.
    - [X] A description of all patterns used. The description must be contextualized, this means it must include details of how the pattern was implemented as part of your particular application design. A sequence diagram illustrating the use of the pattern in your application must also be included as part of your description.
   - [X] An explanation and justification supporting your choice of concurrency pattern(s).
   - [X] A link to your Heroku deployed App including data/information that we need in order to be able to test your application.
- [X] The Git release tag.


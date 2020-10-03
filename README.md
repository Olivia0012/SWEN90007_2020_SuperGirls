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
### Online-Exam-Application: http://35.174.205.208:3000/

### Patterns:

1. Domain Logic

    [Domain model](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/tree/master/src/domain)

    [Service Interface](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/tree/master/src/service) ; [Service Implementation](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/tree/master/src/serviceImp)

2. Data-source layer

   [Data mapper](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/tree/master/src/mapper)

3. Object-to-relational behavioural design

    [Unit of Work](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/blob/master/src/shared/UnitOfWork.java)

    [Identity Map](https://github.com/Olivia0012/SWEN90007_2020_SuperGirls/blob/master/src/shared/IdentityMap.java)

    Lazy load: question list in the Exam and answer list in the Submission.

    Identity Field: all tables

4. Presentation layer:
  
   Implemented with React: [frontend](http://35.174.205.208:3000/)



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
├── docs
│   ├── Part2_Figures
│   ├── architecture
│   │   └── SWEN90007_CIS-TMPLT-ARCH-1.docx
│   ├── meetings
│   │   ├── README.md
│   │   ├── Week\ 2\ Minutes_Aug\ 10_Team\ Super\ Girls.pdf
│   │   ├── Week\ 3\ Minutes_Aug\ 17_Team\ Super\ Girls.pdf
│   │   ├── Week\ 4\ Minutes_Aug\ 24_Team\ Super\ Girls.pdf
│   │   └── Week\ 5\ Minutes_Aug\ 31_Team\ Super\ Girls.pdf
│   └── part1
│       ├── README.md
│       ├── SWEN90007_2020_Part1_SuperGirls.pdf
│       ├── [01.00-D05]SWEN90007_2020_Part1_SuperGirls.doc
│       └── [01.00-D06]SWEN90007_2020_Part1_SuperGirls(1).doc
├── frontend
│   ├── LICENSE.md
│   ├── README.md
│   ├── jsconfig.json
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
│   ├── src
│   │   ├── App.js
│   │   ├── api
│   │   │   └── examAPI.js
│   │   ├── components
│   │   │   ├── GlobalStyles.js
│   │   │   ├── Logo.js
│   │   │   └── Page.js
│   │   ├── icons
│   │   │   ├── Facebook.js
│   │   │   └── Google.js
│   │   ├── index.js
│   │   ├── layouts
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
│   │   ├── theme
│   │   │   ├── index.js
│   │   │   ├── shadows.js
│   │   │   └── typography.js
│   │   ├── utils
│   │   │   └── getInitials.js
│   │   └── views
│   │       ├── exam
│   │       │   ├── EditExamView
│   │       │   │   ├── BlogDetailComponent.js
│   │       │   │   ├── ExamBasicInfo.js
│   │       │   │   ├── Password.js
│   │       │   │   ├── QuestionCard.js
│   │       │   │   └── index.js
│   │       │   └── ExamView
│   │       │       ├── BlogDetailComponent.js
│   │       │       ├── ExamBasicInfo.js
│   │       │       ├── NewQuestion.js
│   │       │       ├── Password.js
│   │       │       ├── QuestionCard.js
│   │       │       └── index.js
│   │       └── student
│   │           └── UserListView
│   │               ├── Results.js
│   │               ├── Toolbar.js
│   │               ├── data.js
│   │               └── index.js
│   └── yarn.lock
├── src
│   ├── database
│   │   └── DatabaseConnection.java
│   ├── domain
│   │   ├── Admin.java
│   │   ├── Answer.java
│   │   ├── DomainObject.java
│   │   ├── Exam.java
│   │   ├── Instructor.java
│   │   ├── Question.java
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
│   │   ├── QuestionMapper.java
│   │   ├── SubjectMapper.java
│   │   ├── SubmissionMapper.java
│   │   └── UserMapper.java
│   ├── service
│   │   ├── CreateNewExam.java
│   │   ├── CreateNewExamImp.java
│   │   ├── ViewExam.java
│   │   └── ViewExamImp.java
│   ├── servlet
│   │   ├── ExamController.java
│   │   └── HelloServlet.java
│   └── shared
│       ├── IdentityMap.java
│       ├── LazyLoad.java
│       └── UnitOfWork.java
├── target
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
```
```

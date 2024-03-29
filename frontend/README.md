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
│       ├── servlet
│       │   └── HelloServlet.class
│       └── shared
│           ├── IdentityMap.class
│           ├── LazyLoad.class
│           └── UnitOfWork.class
├── docs
│   ├── architecture
│   │   └── SWEN90007_CIS-TMPLT-ARCH-1.docx
│   ├── meetings
│   │   ├── README.md
│   │   ├── Week\ 2\ Minutes_Aug\ 10_Team\ Super\ Girls.pdf
│   │   ├── Week\ 3\ Minutes_Aug\ 17_Team\ Super\ Girls.pdf
│   │   └── Week\ 4\ Minutes_Aug\ 24_Team\ Super\ Girls.pdf
│   └── part1
│       ├── README.md
│       ├── SWEN90007_2020_Part1_SuperGirls.pdf
│       ├── [01.00-D05]SWEN90007_2020_Part1_SuperGirls.doc
│       └── [01.00-D06]SWEN90007_2020_Part1_SuperGirls(1).doc
├── lib
│   └── postgresql-42.2.14.jar
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
│   ├── servlet
│   │   └── HelloServlet.java
│   └── shared
│       ├── IdentityMap.java
│       ├── LazyLoad.java
│       └── UnitOfWork.java
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
- [ ] The application coded and deployed. 
    - [ ] The source code of your application must be committed to your Git repository. 
    - [ ] The application must be deployed in Heroku.
- [ ] You must create a tag in your Git repository for this deliverable in the following format: 
SWEN90007_2020_Part2_<team name>. The tag must be created before the submission 
deadline as this will be used to assess your deliverable (no exceptions).
- [ ]  A Software Architecture Design (SAD) report with the following items:
    - [ ] The domain model of your application. 
    - [ ] The class diagram of your application. 
    - [ ] A description of all patterns used. The description must be contextualized, this means 
it must include details of how the pattern was implemented as part of your particular 
application design. A sequence diagram illustrating the use of the pattern in your 
application must be included as part of your description. 
    - [ ] Design rationale for unit of work and lazy load: an explanation on where the patterns 
were used and why. 
    - [ ] A link to your Heroku deployed app, including a populated database with a range of 
realistic data samples/information that are necessary for the teaching team to test 
your application. You also need to provide additional instructions on how to use the 
existing data that you created in your system, for example: the administrator 
username and password. We do not expect to create any test data from scratch.
Again, this is your project. Make sure you test your deliverable before your submission
and that you have real/appropriate data in the deployed system. Meaningless data 
such as abc, 123, blah blah, subject ASDFG and so on will compromise the assessment 
of your project (and your final marks). See Appendix D for more details. 
  - [ ] The Git release tag.

    
### 3. Part 3
```
```

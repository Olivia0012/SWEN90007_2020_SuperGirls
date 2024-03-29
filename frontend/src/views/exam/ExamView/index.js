import React, { createContext, useEffect } from 'react';
import { Box, Button, Container, makeStyles, Card, CardHeader, Grid, Collapse } from '@material-ui/core';
import Page from 'src/components/Page';
import ExamBasicInfo from './ExamBasicInfo';
import NewQuestion from './NewQuestion';
import QuestionCard from './QuestionCard';
import { getExams, setExamStatus, deleteExam, editExam, lockEditExam } from '../../../api/examAPI';
import routes from 'src/routes';
import { useRoutes } from 'react-router-dom';
import Loading from '../../../utils/loading';
import { async } from 'q';

const useStyles = makeStyles((theme) => ({
	root: {
		backgroundColor: theme.palette.background.dark,
		minHeight: '100%',
		paddingBottom: theme.spacing(3),
		paddingTop: theme.spacing(3)
	}
}));

const blogInfo = {
	eact: {
		post: 'Learn useContext Hooks',
		author: 'Adhithi Ravichandran'
	},
	GraphQL: {
		post: 'Learn GraphQL Mutations',
		author: 'Adhithi Ravichandran'
	}
};

export const CountContext = createContext();
export const ExamBasicContent = createContext();
export const QuestionContent = createContext();
export const Editable = createContext();
export const NewQuestionCon = createContext();

const Exam = () => {
	const classes = useStyles();
	const [ isLoading, setLoading ] = React.useState(false);
	const [ newQuestion, addNew ] = React.useState([]);
	const [ data, setData ] = React.useState({});
	const [ examBasicInfo, setExamBasicInfo ] = React.useState({});
	const [ editable, setEditable ] = React.useState(false);
	const routeResult = useRoutes(routes);
	const examId = routeResult.props.value.params.id;
	let questions = [];
	const [ questionList, setQuestionList ] = React.useState([]);
	const [ newQ, setNewQ ] = React.useState({
		questionType: '',
		questionMark: '',
		id: -1,
		choices: [],
		examId: examId,
		questionDescription: '',
		questionNum: 0
	});

	const [ temp, setTemp ] = React.useState([]);

	const handleAdd = (index, question) => {
		console.log(question);
		question.examId = examId;
		//	newQuestion[index] = question;
		console.log(newQuestion);
		const curData = [ ...newQuestion ];
		newQuestion[index] = question;
		console.log(newQuestion);
		setTemp(curData);
		//	newQuestion = curData;
		//	questionList.push(curData);
	};

	console.log(temp);
	const handleQuestion = (index, question) => {
		const curData = [ ...questionList ];
		curData[index] = question;
		setQuestionList(curData);
		console.log(curData);
		console.log(data);
	};

	console.log(data);

	const handleTitle = (title) => {
		data.title = title;
	};

	const handleEdit = async() => {
		await lockEditExam(examId,true).then((response) => {
			console.log(response);
			const result = response.data;
			if (response.data.valid == false) {
				alert('Please login to continue.');
				window.location.replace("/");
			}else{
				if(response.data.acquirelock == "true")
					setEditable((pre) => !pre);
				else{
					alert(response.data.acquirelock);
					window.location.reload();
				}
			}
				
			})
			.catch((error) => {
				setLoading(false);
				alert('Error from processDataAsycn() with async( When promise gets rejected ): ' + error);
				window.location.replace("../");
			});
	};


	const handleReleaseLock = async() => {
		await lockEditExam(examId,false).then((response) => {
			console.log(response);
			const result = response.data;
			if (response.data.valid == false) {
				alert('Please login to continue.');
				window.location.replace("/");
			}else{
				if(response.data.acquirelock == "true")
					setEditable((pre) => !pre);
				else{
					alert(response.data.acquirelock);
				}
			}
				
			})
			.catch((error) => {
				setLoading(false);
				alert('Error from processDataAsycn() with async( When promise gets rejected ): ' + error);
				window.location.replace("../");
			});
	};


	useEffect(() => {
		const fetchData = async () => {
			setLoading(true);
			await getExams(examId).then((response) => {
				console.log(response);
				const result = response.data;
				if (response.data == false) {
					alert('Please login to continue.');
					window.location.href = '../';
				}
				console.log(result);
				setData(result);
				const a = result;
				console.log(result);
				setQuestionList(result.questionList);
				var readyData = {
					subjectCode: a.subject.subjectCode,
					subjectTitle: a.subject.title,
					examTitle: a.title,
					creator: a.creator.userName,
					createTime: a.createdTime,
					updateTime: a.updateTime,
					status: a.status
				};
				setExamBasicInfo(readyData);
				setLoading(false);
			});
		};
		fetchData();
	}, []);

	const handleEditExam = async (e) => {
		e.preventDefault();
		setLoading(true);
		//	console.log(data);
		//	console.log(newQuestion);
		const c = questionList.concat(newQuestion);
		data.questionList = c;
		//	console.log(data);

		await editExam(data)
		.then((response) => {
			console.log(response);
			const result = response.data;
			if (response.data == false) {
				alert('Please login to continue.');
				window.location.replace("/");
			}else{
				setLoading(false);
				alert('Edited Successfully!');
				window.location.href = './id=' + examId;
			}
				
			})
			.catch((error) => {
				setLoading(false);
				alert('Error from processDataAsycn() with async( When promise gets rejected ): ' + error);
			});
	};

	const handleDeleteExam = async () => {
		setLoading(true);
		await deleteExam(data.id)
			.then((a) => {
				setLoading(false);
				alert('Deleted Successfully!');
				window.location.href = '../subjects';
			})
			.catch((error) => {
				setLoading(false);
				alert('Error from processDataAsycn() with async( When promise gets rejected ): ' + error);
			});
	};

	const handlePublish = async () => {
		setLoading(true);
		await setExamStatus(data.id)
			.then(() => {
				setLoading(false);
				alert('Successfully!');
				window.location.href = './id=' + examId;
			})
			.catch((error) => {
				setLoading(false);
				alert('Error from processDataAsycn() with async( When promise gets rejected ): ' + error);
			});
	};

	console.log(data);

	return (
		<Page className={classes.root} title="Exam">
			{!isLoading ? (
				<Container maxWidth="lg">
					<Grid container direction="row" justify="flex-end" alignItems="center" spacing={2}>
						<Grid item xs={9}>
							<Button color="secondary" x onClick={handleDeleteExam}>
								Delete
							</Button>
						</Grid>
						<Grid item xs={1}>
							{data.status === 'CREATED' && !editable ? (
								<Button color="primary" variant="contained" fullWidth onClick={handleEdit}>
									Edit
								</Button>
							) : (
								<div />
							)}
						</Grid>
						<Grid item xs={2}>
							{data.status === 'RELEASED' ? (
								<div />
							) : (
								<Button color="primary" variant="contained" fullWidth onClick={handlePublish}>
									{data.status === 'CLOSED' ? (
										'release'
									) : data.status === 'PUBLISHED' ? (
										'close'
									) : (
										'Publish'
									)}
								</Button>
							)}
						</Grid>
					</Grid>
					<Box p={1} />

					<Editable.Provider value={editable}>
						<ExamBasicInfo handleTitle={handleTitle} examBasicInfo={examBasicInfo} />
						{data.questionList ? (
							data.questionList.map((nq, index) => {
								console.log(nq);
								console.log(questionList[index]);
								if (nq)
									return (
										<div key={index}>
											<Box p={1} />
											<QuestionCard value={index} question={nq} handleQuestion={handleQuestion} />
										</div>
									);
							})
						) : (
							<Box p={1}>
								<Card>
									<CardHeader title={<div>There is no question in this exam.</div>} />
								</Card>
							</Box>
						)}
						<Box p={1} />
						<Collapse in={editable}>
							{newQuestion.map((q, index) => (
								<div>
									<NewQuestion qIndex={index} question={q} handleAdd={handleAdd} />
									<Box p={1} />
								</div>
							))}
						</Collapse>
					</Editable.Provider>
					<Collapse in={editable}>
						<Box my={2}>
							<Button
								variant="outlined"
								color="primary"
								fullWidth
								size="large"
								onClick={() => {
									//setCount(count + 1);
									addNew([ ...newQuestion, newQ ]);
								}}
							>
								Add New Question
							</Button>
						</Box>
						<Box p={2}>
							<Grid container spacing={5} justify="center">
								<Grid item xs={3} lg={3}>
									<Button color="secondary" variant="contained" onClick={handleReleaseLock} fullWidth>
										Cancel
									</Button>
								</Grid>
								<Grid item xs={3} lg={3}>
									<Button color="primary" variant="contained" fullWidth onClick={handleEditExam}>
										Save
									</Button>
								</Grid>
							</Grid>
						</Box>
					</Collapse>
				</Container>
			) : (
				<Loading isLoading={isLoading} />
			)}
		</Page>
	);
};

export default Exam;

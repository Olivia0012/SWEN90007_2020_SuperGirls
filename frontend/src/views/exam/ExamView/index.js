import React, { createContext, useEffect } from 'react';
import { Box, Button, Container, makeStyles, Card, CardHeader, Grid, Collapse } from '@material-ui/core';
import Page from 'src/components/Page';
import ExamBasicInfo from './ExamBasicInfo';
import NewQuestion from './NewQuestion';
import QuestionCard from './QuestionCard';
import { getExams, addNewQuestion, deleteExam } from '../../../api/examAPI';
import routes from 'src/routes';
import { useRoutes } from 'react-router-dom';
import Loading from '../../../utils/loading';

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
	const [ count, setCount ] = React.useState(1);
	const [ newQuestion, addNew ] = React.useState([]);
	const [ data, setData ] = React.useState({});
	const [ examBasicInfo, setExamBasicInfo ] = React.useState('');
	const [ editable, setEditable ] = React.useState(false);
	const routeResult = useRoutes(routes);
	const examId = routeResult.props.value.params.id;
	let questions = [];

	const handleEdit = () => {
		setEditable((pre) => !pre);
	};

	useEffect(() => {
		const fetchData = async () => {
			setLoading(true);
			const result = await getExams(examId);
			console.log(result);
			setData(result.data);
			var readyData = {
				subjectCode: result.data.subject.subjectCode,
				subjectTitle: result.data.subject.title,
				examTitle: result.data.title,
				creator: result.data.creator.userName,
				createTime: result.data.createdTime,
				updateTime: result.data.updateTime,
				status: result.data.status
			};
			setExamBasicInfo(readyData);
			setLoading(false);
		};
		fetchData();
	}, []);

	const handleAddNewQuestion = async () => {
		//	setNewquestion({})
		setLoading(true);
		const newQ = {
			questionDescription: newquestion.questionDescription,
			questionType: newquestion.questionType,
			choices: [ newquestion.choice1, newquestion.choice2, newquestion.choice3, newquestion.choice4 ],
			id: -1,
			examId: data.id,
			questionMark: newquestion.questionMark,
			questionNum: 0
		};
		questions.push(newQ);
		//	const readyData = data;
		//	readyData.quesitonList = null;
		//	readyData.quesitonList = questions;

		const readyData = {
			createdTime: data.createdTime,
			creator: data.creator,
			id: data.id,
			locked: data.locked,
			questionList: questions,
			status: data.status,
			subject: data.subject,
			title: data.title,
			updatedTime: data.updateTime
		};

		console.log('readyData:' + readyData);
		console.log(data.questionList);

		await addNewQuestion(readyData)
			.then((a) => {
				setLoading(false);
				window.location.href = './id=' + data.id;
			})
			.catch((error) => {
				setLoading(false);
				alert('Error from processDataAsycn() with async( When promise gets rejected ): ' + error);
			});
		console.log('add new ');
	};

	const [ newquestion, setNewquestion ] = React.useState({
		questionDescription: '',
		questionType: '',
		choice1: '',
		choice2: '',
		choice3: '',
		choice4: '',
		id: null,
		questionMark: 0,
		questionNum: 0
	});

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

	// Add new quesiton
	const handleSubmitNewQuestion = async () => {
		/*		if (openGreen) {
			window.location.href = './dashboard/survey';
		}
		//
		var readyData = JSON.stringify({
			surveyId: product.surveyId,
			surveyTitle: values.title,
			surveyIntroduction: values.descrpition,
			surveyVersion: product.surveyVersion
    });
    handleUploadImg();
		const feedBack = await editSurvey(readyData)
			.then((data) => {
				setOpenGreen(true);
			})
			.catch((error) => {
				setOpen(true);
				setError(error + '');
				//			alert('Error from processDataAsycn() with async( When promise gets rejected ): ' + error);
			});
		return feedBack;*/
	};

	console.log(data);

	return (
		<Page className={classes.root} title="Exam">
			{!isLoading ? (
				<Container maxWidth="lg">
					<Grid container direction="row" xs={12} justify="flex-end" alignItems="center" spacing={2}>
						<Button color="primary" variant="contained" onClick={handleEdit}>
							Edit
						</Button>
						<Box p={1} />
						<Button color="primary" variant="contained" onClick={handleDeleteExam}>
							Delete
						</Button>
					</Grid>
					<Box p={1} />

					<Editable.Provider value={editable}>
						<ExamBasicContent.Provider value={examBasicInfo}>
							<ExamBasicInfo />
						</ExamBasicContent.Provider>
						{data.questionList ? (
							data.questionList.map((nq) => {
								console.log(nq);
								questions.push(nq);
								return (
									<div key={nq}>
										<Box p={1} />
										<QuestionContent.Provider value={nq}>
											<QuestionCard />
										</QuestionContent.Provider>
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
							{newQuestion.map((q) => (
								<div>
									<NewQuestionCon.Provider value={{ newquestion, setNewquestion }}>
										<NewQuestion />
									</NewQuestionCon.Provider>
									<Box p={2}>
										<Grid container spacing={5} justify="center">
											<Grid item xs={3} lg={3}>
												<Button color="secondary" variant="contained" fullWidth>
													Cancel
												</Button>
											</Grid>
											<Grid item xs={3} lg={3}>
												<Button
													color="primary"
													variant="contained"
													fullWidth
													onClick={handleAddNewQuestion}
												>
													Save
												</Button>
											</Grid>
										</Grid>
									</Box>
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
									setCount(count + 1);
									addNew([ ...newQuestion, count ]);
								}}
							>
								Add New Question
							</Button>
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

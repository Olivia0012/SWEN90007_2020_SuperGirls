import React, { createContext, useEffect, useParams } from 'react';
import { Box, Button, Container, makeStyles, Card, CardHeader, Grid, Collapse } from '@material-ui/core';
import Page from 'src/components/Page';
import ExamBasicInfo from './ExamBasicInfo';
import NewQuestion from './NewQuestion';
import QuestionCard from './NewQuestion';
import { getExams, addNewQuestion } from '../../../api/examAPI';
import routes from 'src/routes';
import { useRoutes } from 'react-router-dom';

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

const EditExam = () => {
	const classes = useStyles();
	const [ count, setCount ] = React.useState(0);

	const [ data, setData ] = React.useState({});
	const [ examBasicInfo, setExamBasicInfo ] = React.useState('');
	const [ editable, setEditable ] = React.useState(true);
	const routeResult = useRoutes(routes);
	const examId = routeResult.props.value.params.id;
	let questions = [];
	console.log(routeResult);

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

	console.log(newquestion);
	const [ newQuestion, addNew ] = React.useState([]);
	if (newquestion.questionDescription !== '' && newquestion.questionMark !== 0 && newquestion.questionType !== '') {
		setCount(count + 1);
		addNew([ ...newQuestion, { count, newquestion } ]);
		setNewquestion({
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
	}

	

	useEffect(() => {
		const fetchData = async () => {
			const result = await getExams(examId);
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
		};
		fetchData();
	}, []);

	const handleRemoveItem = (e) => {
		const name = e.target.getAttribute("name");
		console.log(name);
		addNew(newQuestion.filter(item => item.count !== name));
	   };

	const handleAddNewQuestion = async () => {
		console.log(newquestion);
		const q = {
			questionDescription: newquestion.questionDescription,
			questionType: newquestion.questionType,
			choices: [ newquestion.choice1, newquestion.choice2, newquestion.choice3, newquestion.choice4 ],
			id: -1,
			examId: data.id,
			questionMark: newquestion.questionMark,
			questionNum: 0
		};
		setCount(count + 1);
		console.log(newQuestion);
		addNew([ ...newQuestion, { count, q } ]);

	//	addNew([ ...newQuestion, { count, q } ]);
		console.log(newQuestion);
		//	setNewquestion({})
		/*	const newQ = {
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
			updateTime: '',
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
			.then((data) => {
				window.location.href = './id=' + data.id;
			})
			.catch((error) => {
				alert('Error from processDataAsycn() with async( When promise gets rejected ): ' + error);
			});*/
		console.log('add new ');
	};

	//	console.log(data);

	return (
		<Page className={classes.root} title="Exam">
			<Container maxWidth="lg">
				<Editable.Provider value={editable}>
					<ExamBasicContent.Provider value={examBasicInfo}>
						<ExamBasicInfo />
					</ExamBasicContent.Provider>
					<Box p={1} />
					<Collapse in={editable}>
						{newQuestion.map((q) => (
							<span name={q.count} onClick={handleRemoveItem}>
								<NewQuestionCon.Provider value={{ newquestion, setNewquestion }}>
									{q.count}
									<Button name={q.count} onClick={handleRemoveItem}>Delete</Button>
									<NewQuestion />
									
									<Box p={1} />
								</NewQuestionCon.Provider>
							</span>
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
								/*	if (
									newquestion.questionDescription == '' ||
									newquestion.questionMark == 0 ||
									newquestion.questionType == ''
								) {
									alert('The question description, type and mark can not be empty!');
								} else {*/

								const q = {
									questionDescription: newquestion.questionDescription,
									questionType: newquestion.questionType,
									choices: [
										newquestion.choice1,
										newquestion.choice2,
										newquestion.choice3,
										newquestion.choice4
									],
									id: -1,
									examId: data.id,
									questionMark: newquestion.questionMark,
									questionNum: 0
								};
								console.log(count);
								console.log(q);
								setCount(count + 1);
								addNew([ ...newQuestion, { count, newquestion } ]);
								console.log(newQuestion);
								//	}
							}}
						>
							Add New Question
						</Button>
					</Box>
				</Collapse>
				<Box p={2}>
					<Grid container spacing={5} justify="center">
						<Grid item xs={3} lg={3}>
							<Button color="secondary" variant="contained" fullWidth>
								Cancel
							</Button>
						</Grid>
						<Grid item xs={3} lg={3}>
							<Button color="primary" variant="contained" fullWidth onClick={handleAddNewQuestion}>
								Save
							</Button>
						</Grid>
					</Grid>
				</Box>
			</Container>
		</Page>
	);
};

export default EditExam;

import React, { createContext, useEffect } from 'react';
import {
	Box,
	Button,
	Container,
	makeStyles,
	Grid,
	Card,
	CardHeader,
	CardContent,
	TextField,
	Collapse
} from '@material-ui/core';
import Page from 'src/components/Page';
import SubmissionInfo from './SubmissionInfo';
import QuestionCard from './QuestionCard';
import { getSubmission, markExam } from '../../../api/instructorAPI';
import { viewResult } from '../../../api/examAPI';
import routes from 'src/routes';
import { useRoutes } from 'react-router-dom';
import Loading from '../../../utils/loading';
import moment from 'moment';

const useStyles = makeStyles((theme) => ({
	root: {
		backgroundColor: theme.palette.background.dark,
		minHeight: '100%',
		paddingBottom: theme.spacing(3),
		paddingTop: theme.spacing(3)
	}
}));

export const CountContext = createContext();
export const SubmissionContent = createContext();
export const QuestionContent = createContext();
export const NewQuestionCon = createContext();

const MarkExamView = () => {
	const classes = useStyles();
	const [ isLoading, setLoading ] = React.useState(false);
	const [ data, setData ] = React.useState();
	const [ submissionInfo, setSubmissionInfo ] = React.useState('');
	const routeResult = useRoutes(routes);
	const id = routeResult.props.value.params.submission;
	const examId = routeResult.props.value.params.examId;
	const [ status, setStatus ] = React.useState();
	const [ answers, setAnswers ] = React.useState([]);

	useEffect(() => {
		const fetchData = async () => {
			setLoading(true);
			let result = {};
			if (typeof id !== 'undefined') {
				await getSubmission(id).then((response) => {
					console.log(response);
					result = response.data;
					if (response.data == false) {
						alert('Please login to continue.');
						window.location.replace('/');
					}
				});
			} else
				await viewResult(examId).then((response) => {
					console.log(response);
					result = response.data;
					if (response.data == false) {
						alert('Please login to continue.');
						window.location.replace('/');
					}
				});
			console.log(result);
			const a = result;
			setData(result);
			setAnswers(result.submission.answers);
			setStatus(a.exam.status);
			var readyData = {
				subjectCode: a.exam.subject.title,
				subjectTitle: a.exam.subject.subjectCode,
				examTitle: a.exam.title,
				student: a.submission.student.userName,
				subTime: a.submission.subTime,
				totalMark: a.submission.totalMark,
				status: a.exam.status
			};
			setSubmissionInfo(readyData);
			setLoading(false);
		};
		fetchData();
	}, []);

	const [ marks ] = React.useState(
		answers.map((q) => {
			return q.mark;
		})
	);

	const [ comment, setComment ] = React.useState('');

	const handleComment = (e) => {
		setComment(e.target.value);
	};

	const handleMark = (index, mark) => {
		/*	console.log(mark,index,marks);
		const curMarks = [...marks];
		curMarks[index] = mark;
		setMarks(curMarks);*/
		const curData = [ ...answers ];
		curData[index].mark = parseFloat(mark);
		setAnswers(curData);
		data.answers = answers;
	};

	const handleSubmit = async (e) => {
		e.preventDefault();
		setLoading(true);
		data.submission.comment = comment;
		data.submission.markTime = moment().format('YYYY-MM-DD HH:mm:ss');
		data.exam.questionList = null;
		console.log(data);
		await markExam(data.submission)
			.then((response) => {
				setLoading(false);
				if (response.data == false) {
					alert('Please login to continue.');
					window.location.replace('/');
				} else {
					alert('Saving mark Successfully!');
					/*	window.location.replace( '/oea/students/subject=' +
						data.exam.subject.id +
						'&exam=' +
						data.exam.id);*/
					window.location.reload();
				}
			})
			.catch((error) => {
				setLoading(false);
				alert(error);
			});
	};

	return (
		<Page className={classes.root} title="Exam">
			{!isLoading && data ? (
				<Container maxWidth="lg">
					<Box p={1} />
					<SubmissionContent.Provider value={submissionInfo}>
						<SubmissionInfo />
					</SubmissionContent.Provider>
					{data.exam.questionList.map((nq, index) => {
						return (
							<div key={index}>
								<Box p={1} />
								<QuestionCard
									value={index}
									mark={marks[index]}
									handleMark={handleMark}
									question={nq}
									answer={data.submission.answers[index]}
									marker={data.submission.marker}
									status={status}
								/>
							</div>
						);
					})}
					<Box p={1} />
					<Card>
						<CardHeader title={'Comment:'} />
						<CardContent>
							<TextField
								id="standard-basic"
								fullWidth
								multiline
								rows={4}
								disabled={status !== 'CLOSED'}
								defaultValue={data.submission.comment}
								variant="outlined"
								onChange={handleComment}
							/>
						</CardContent>
					</Card>
					<Box p={2} />
					<Grid item xs={12}>
						<Collapse in={status === 'CLOSED'}>
							<Button color="primary" fullWidth variant="contained" onClick={handleSubmit}>
								Submit
							</Button>
						</Collapse>
					</Grid>
				</Container>
			) : (
				<Loading isLoading={isLoading} />
			)}
		</Page>
	);
};

export default MarkExamView;

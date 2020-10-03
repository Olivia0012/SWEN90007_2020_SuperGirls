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
import { useNavigate, useRoutes } from 'react-router-dom';
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
	const navigate = useNavigate();
	const [ isLoading, setLoading ] = React.useState(false);
	const [ data, setData ] = React.useState();
	const [ submissionInfo, setSubmissionInfo ] = React.useState('');
	const routeResult = useRoutes(routes);
	const id = routeResult.props.value.params.submission;
	const examId = routeResult.props.value.params.examId;

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
						navigate('/', { replace: true });
					}
				});
			} else
				await viewResult(examId).then((response) => {
					result = response.data;
					if (response.data == false) {
						alert('Please login to continue.');
						navigate('/', { replace: true });
					}
				});
			console.log(result);
			const a = result;
			setData(result);
			setAnswers(result.answers);
			var readyData = {
				subjectCode: a.exam.subject.title,
				subjectTitle: a.exam.subject.subjectCode,
				examTitle: a.exam.title,
				student: a.student.userName,
				subTime: a.subTime,
				totalMark: a.totalMark
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
		data.comment = comment;
		data.markTime = moment().format('YYYY-MM-DD HH:mm:ss');
		data.exam.questionList = null;
		console.log(data);
		await markExam(data)
			.then((a) => {
				console.log(a);
				if (a.data !== false) {
					setLoading(false);
					alert('Saving mark Successfully!');
					navigate('/oea/students/subject=' + data.exam.subject.id + '&exam=' + data.exam.id, {
						replace: true
					});
				} else {
					alert('Save marks failed, please contact admin for help.');
				}
			})
			.catch((error) => {
				setLoading(false);
				alert('Error : ' + error);
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
					{data.answers.map((nq, index) => {
						return (
							<div key={index}>
								<Box p={1} />
								<QuestionCard
									value={index}
									mark={marks[index]}
									handleMark={handleMark}
									question={nq.question}
									answer={nq}
									marker={data.marker}
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
								disabled={data.marker && data.marker.userName}
								defaultValue={data.comment}
								variant="outlined"
								onChange={handleComment}
							/>
						</CardContent>
					</Card>
					<Box p={2} />
					<Grid item xs={12}>
						<Collapse in={!data.marker || !data.marker.userName}>
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

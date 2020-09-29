import React, { createContext, useEffect } from 'react';
import { Box, Button, Container, makeStyles, Grid, Card, CardHeader, CardContent, TextField } from '@material-ui/core';
import Page from 'src/components/Page';
import ExamInfo from './ExamInfo';
import QuestionCard from './QuestionCard';
import { submitExam, deleteAnswers } from '../../../api/examAPI';
import routes from 'src/routes';
import { useRoutes } from 'react-router-dom';
import Loading from '../../../utils/loading';
import moment from 'moment';
import { getSubmission } from 'src/api/instructorAPI';

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

const TakeExam = () => {
	const classes = useStyles();
	const [ isLoading, setLoading ] = React.useState(false);
	const [ data, setData ] = React.useState();
	const routeResult = useRoutes(routes);
	const subjectId = routeResult.props.value.params.subject;
	const submissionId = routeResult.props.value.params.submission;

	
	const [ oriAnswers, setOriAnswers ] = React.useState([]);

	useEffect(() => {
		const fetchData = async () => {
			setLoading(true);
			const result = await getSubmission(submissionId);
		//	console.log(JSON.parse(result.data));
			setOriAnswers(result.data.answers);
			setData(result.data);
			setLoading(false);
		};
		fetchData();
	}, []);

	const [ answers, setAnswers ] = React.useState(
		oriAnswers.map((an) => {
		//	return q.id;
			//	answer.question = q;
				return an;
		})
	);


	const handleAnswer = (index, answer) => {
		const curData = [ ...answers ];
		curData[index] = answer;
		setAnswers(curData);
		data.answers[index].answer = answer;
	};

	const handleSubmit = async (e) => {
		e.preventDefault();
		setLoading(true);
		const examStatusCheck = await getSubmission(submissionId);
		if(examStatusCheck.data.exam.status == 'CLOSED'){
			alert("Failed to submit because the exam has been closed.");
			data.subTime = '';
			data.answers = [];
			await submitExam(data)
			.then((a) => {
				setLoading(false);
				window.location.href = '../subjects';
			})
			.catch((error) => {
				setLoading(false);
				alert('Error from processDataAsycn() with async( When promise gets rejected ): ' + error);
			});
		}else{
			data.subTime = moment().format('YYYY-MM-DD HH:mm:ss');
			await submitExam(data)
			.then((a) => {
				setLoading(false);
				alert("Submitted Successfully.");
				window.location.href = '../subjects';
			})
			.catch((error) => {
				setLoading(false);
				alert('Error from processDataAsycn() with async( When promise gets rejected ): ' + error);
			});
		}
		
	};

	return (
		<Page className={classes.root} title="TakeExam">
			{!isLoading ? (
				<Container maxWidth="lg">
					<Box p={1} />
					{data ? <ExamInfo examInfo={data.exam} /> : <div />}
					{data ? (
						oriAnswers.map((nq, index) => {
							return (
								<div key={index}>
									<Box p={1} />
									<QuestionCard
										value={index}
										question={nq.question}
										answer={answers[index]}
										handleAnswer={handleAnswer}
									/>
								</div>
							);
						})
					) : (
						<div />
					)}
					<Box p={2} />
					<Grid item xs={12}>
						<Button color="primary" fullWidth variant="contained" onClick={handleSubmit}>
							Submit
						</Button>
					</Grid>
				</Container>
			) : (
				<Loading isLoading={isLoading} />
			)}
		</Page>
	);
};

export default TakeExam;

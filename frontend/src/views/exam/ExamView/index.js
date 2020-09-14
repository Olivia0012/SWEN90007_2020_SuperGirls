import React, { createContext, useEffect } from 'react';
import { Box, Button, Container, makeStyles, Card, CardHeader, Grid, Collapse } from '@material-ui/core';
import Page from 'src/components/Page';
import ExamBasicInfo from './ExamBasicInfo';
import NewQuestion from './NewQuestion';
import QuestionCard from './QuestionCard';
import { getExams } from '../../../api/examAPI';

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

const Exam = () => {
	const classes = useStyles();
	const [ count, setCount ] = React.useState(1);
	const [ newQuestion, addNew ] = React.useState([]);
	const [ data, setData ] = React.useState({});
	const [ examBasicInfo, setExamBasicInfo ] = React.useState('');
	const [ editable, setEditable ] = React.useState(false);

	const handleEdit = () => {
		setEditable((pre) => !pre);
	};

	useEffect(() => {
		const fetchData = async () => {
			//	setIsLoading(true);
			const result = await getExams(1);
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

	console.log(data.subject);

	return (
		<Page className={classes.root} title="Exam">
			<Container maxWidth="lg">
				<Grid container direction="row" xs={12} justify="flex-end" alignItems="center" spacing={2}>
					<Button color="primary" variant="contained" onClick={handleEdit}>
						Edit
					</Button>
					<Box p={1} />
					<Button color="primary" variant="contained" onClick={handleEdit}>
						Publish
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
						<div>Loading......</div>
					)}
				
					<Box p={1} />
					{//	setEditable(true),
					newQuestion.map((q) => (
						<div>
							<NewQuestion />
							<Box p={1} />
						</div>
					))}
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
				<Box p={2}>
					<Grid container spacing={5} justify="center">
						<Grid item xs={3} lg={3}>
							<Button color="secondary" variant="contained" fullWidth>
								Cancel
							</Button>
						</Grid>
						<Grid item xs={3} lg={3}>
							<Button color="primary" variant="contained" fullWidth>
								Save
							</Button>
						</Grid>
					</Grid>
				</Box>
				</Collapse>
			</Container>
		</Page>
	);
};

export default Exam;

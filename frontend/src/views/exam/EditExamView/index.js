import React, { createContext, useContext } from 'react';
import { Box, Button, Container, makeStyles } from '@material-ui/core';
import Page from 'src/components/Page';
import ExamBasicInfo from './ExamBasicInfo';
import BlogDetailComponent from './BlogDetailComponent';
import QuestionCard from './QuestionCard';

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
export const t = 4;

const EditExam = () => {
	const classes = useStyles();
	const [ count, setCount ] = React.useState(1);
	const [ newQuestion, addNew ] = React.useState([]);

	return (
		<Page className={classes.root} title="Settings">
			<Container maxWidth="lg">
				<ExamBasicInfo/>
				<CountContext.Provider value={blogInfo}>
					<BlogDetailComponent/>
				</CountContext.Provider>
				{newQuestion.map((nq) => {
					console.log(nq);
					return (
						<div key={nq}>
							<Box p={1} />
							<CountContext.Provider value={nq}>
								<QuestionCard />
							</CountContext.Provider>
						</div>
					);
				})}

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

			</Container>
		</Page>
	);
};

export default EditExam;

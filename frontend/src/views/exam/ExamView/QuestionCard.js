import React, { useContext } from 'react';
import PropTypes from 'prop-types';
import { green } from '@material-ui/core/colors';
import HighlightOffIcon from '@material-ui/icons/HighlightOff';
import EditIcon from '@material-ui/icons/Edit';
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import { QuestionContent, Editable } from './index';
import { deleteQuestion } from '../../../api/examAPI';
import Loading from '../../../utils/loading';

import {
	Box,
	Card,
	Collapse,
	Button,
	IconButton,
	TextField,
	InputLabel,
	Select,
	MenuItem,
	CardContent,
	CardHeader,
	Divider,
	Grid,
	Typography,
	makeStyles
} from '@material-ui/core';

const useStyles = makeStyles({
	root: {},
	item: {
		display: 'flex',
		flexDirection: 'column'
	}
});

const QuestionCard = (props) => {
	//	const classes = useStyles();
	//	const [ editable, setEditable ] = React.useState(false);
	const [ isLoading, setLoading ] = React.useState(false);
	const [ isOpen, setOpen ] = React.useState(false);
	//	const question = useContext(QuestionContent);
	const editable = useContext(Editable);

	const { handleQuestion, value, question } = props;

	console.log(props);

	const [ values, setValues ] = React.useState(question);
/*	const [ values, setValues ] = React.useState({
		questionType: question.questionType,
		questionMark: question.questionMark,
		id: question.id,
		choices: question.choices,
		examId: question.examId,
		questionDescription: question.questionDescription,
		questionNum: question.questionNum
	});*/

	//	const [ choices, setChoices ] = React.useState(question.choices);

	//  console.log(t)
	//	console.log(choices);

	const handleChange = (prop) => (event) => {
		setValues({ ...values, [prop]: event.target.value });
		handleQuestion(value,{ ...values, [prop]: event.target.value });
		console.log(values);
	//	handleQuestion({ ...question, [prop]: event.target.value });
	};

	const handleDeleteQuestion = async () => {
		setLoading(true);
		await deleteQuestion(question.id)
			.then((a) => {
				setLoading(false);
				alert('Deleted Successfully!');
				window.location.href = './id=' + question.examId;
			})
			.catch((error) => {
				setLoading(false);
				alert('Error from processDataAsycn() with async( When promise gets rejected ): ' + error);
			});
	};

	const handleEditChoice = (event, index) => {
		const curChoice = event.target.value;
		values.choices[index] = curChoice;
		handleQuestion(value,values);
		console.log(values);
	};


	function SecondLine() {
		return (
			<React.Fragment>
				<Grid item xs={12}>
					<Box p={1}>
						<Typography color="textPrimary" gutterBottom variant="h5">
							Choices :
						</Typography>
					</Box>
				</Grid>

				{question.choices.map((item, index) => (
					<Grid item xs={12} key={index}>
						<Box p={1}>
							<TextField
								id="standard-basic"
								defaultValue={item}
								fullWidth
								onChange={(event) => {
									handleEditChoice(event, index);
								}}
								disabled={!editable}
							/>
						</Box>
					</Grid>
				))}
			</React.Fragment>
		);
	}

	return !isLoading ? (
		<Card>
			<Collapse in={!editable}>
				<CardHeader
					title={'Question ' + question.questionNum + ' : ' + question.questionDescription}
				/>
			</Collapse>
			<Collapse in={editable}>
				<CardContent>
					<Grid container direction="row" justify="flex-end" alignItems="center">
						<Grid container>
							<Grid item xs={2} lg={1}>
								<Typography color="textPrimary" gutterBottom variant="h5">
									{'Question ' + question.questionNum + ' : '}
								</Typography>
							</Grid>
							<Grid item xs={9} lg={10}>
								<TextField
									id="standard-basic"
									defaultValue={values.questionDescription}
									fullWidth
									onChange={handleChange('questionDescription')}
									disabled={!editable}
								/>
							</Grid>
							<Grid item xs={1} lg={1}>
								<IconButton aria-label="add an alarm" onClick={handleDeleteQuestion}>
									<HighlightOffIcon color="primary" size="small" />
								</IconButton>
							</Grid>
						</Grid>
					</Grid>
				</CardContent>
			</Collapse>
			<Divider />
			<Collapse in={true}>
				<CardContent>
					<Box p={3}>
						<Grid container spacing={5} direction="column" justify="flex-start" alignItems="center">
							<Grid container direction="row" justify="space-between" alignItems="center" spacing={2}>
								<Grid item xs={12} sm={4}>
									<InputLabel id="demo-simple-select-label">Type</InputLabel>
									<Select
										labelId="demo-simple-select-label"
										id="demo-simple-select"
										defaultValue={values.questionType}
										fullWidth
										disabled={!editable}
										onChange={handleChange('questionType')}
									>
										<MenuItem value={'ANSWER'}>Short-Answer</MenuItem>
										<MenuItem value={'CHOICE'}>Multi-Choice</MenuItem>
									</Select>
								</Grid>
								<Grid item xs={12} sm={4}>
									<TextField
										id="outlined-multiline-flexible"
										fullWidth
										defaultValue={values.questionMark}
										disabled={!editable}
										onChange={handleChange('questionMark')}
										label="Mark"
									/>
								</Grid>
							</Grid>
							<Box p={1} />
							<Collapse in={values.questionType == 'CHOICE' ? true : false}>
								<Grid container>
									<SecondLine />
								</Grid>
							</Collapse>
						</Grid>
					</Box>
				</CardContent>
			</Collapse>
		</Card>
	) : (
		<Loading isLoading={isLoading} />
	);
};

QuestionCard.propTypes = {
	className: PropTypes.string
};

export default QuestionCard;

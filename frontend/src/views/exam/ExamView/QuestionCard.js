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

const QuestionCard = ({ className, ...rest }) => {
	//	const classes = useStyles();
	//	const [ editable, setEditable ] = React.useState(false);
	const [ isLoading, setLoading ] = React.useState(false);
	const [ isOpen, setOpen ] = React.useState(false);
	const question = useContext(QuestionContent);
	const editable = useContext(Editable);
	const [ values, setValues ] = React.useState({
		type: '',
		mark: question.questionMark,
		content: ''
	});

	//	const [ choices, setChoices ] = React.useState(question.choices);

	//  console.log(t)
	//	console.log(choices);

	const handleChange = (prop) => (event) => {
		setValues({ ...values, [prop]: event.target.value });
	};

	const handleDeleteQuestion = async () => {
		setLoading(true);
		await deleteQuestion(question.id).then((a) => {
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
		console.log(event.target.value);
		console.log(index);
	};

	function FirstLine() {
		return (
			<React.Fragment>
				<Grid item xs={12} sm={4}>
					<InputLabel id="demo-simple-select-label">Type</InputLabel>
					<Select
						labelId="demo-simple-select-label"
						id="demo-simple-select"
						value={question.questionType}
						fullWidth
						disabled={!editable}
						onChange={handleChange('type')}
					>
						<MenuItem value={'ANSWER'}>Short-Answer</MenuItem>
						<MenuItem value={'CHOICE'}>Multi-Choice</MenuItem>
					</Select>
				</Grid>
				<Grid item xs={12} sm={4}>
					<TextField
						id="outlined-multiline-flexible"
						fullWidth
						value={values.mark}
						disabled={!editable}
						onChange={handleChange('mark')}
						label="Mark"
					/>
				</Grid>
			</React.Fragment>
		);
	}

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
					onDoubleClick={() => {
						setOpen((prev) => !prev);
					}}
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
									defaultValue={question.questionDescription}
									fullWidth
									onChange={(event) => {
										//	handleEditChoice(event, index);
									}}
									disabled={!editable}
								/>
							</Grid>
							<Grid item xs={1} lg={1}>
								<IconButton aria-label="add an alarm">
									<HighlightOffIcon color="primary" size="small" onClick={handleDeleteQuestion} />
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
								<FirstLine />
							</Grid>
							<Box p={1} />
							<Collapse in={question.questionType == 'CHOICE' ? true : false}>
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

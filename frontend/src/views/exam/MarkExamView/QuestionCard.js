import React, { useContext } from 'react';
import PropTypes from 'prop-types';
import clsx from 'clsx';
import HighlightOffIcon from '@material-ui/icons/HighlightOff';
import EditIcon from '@material-ui/icons/Edit';
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import { QuestionContent } from './index';
import { deleteQuestion } from '../../../api/examAPI';
import Loading from '../../../utils/loading';

import {
	Box,
	FormControl,
	FormLabel,
	FormGroup,
	FormControlLabel,
	Checkbox,
	FormHelperText,
	Card,
	Collapse,
	Paper,
	InputAdornment,
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
import data from 'src/views/student/UserListView/data';

const useStyles = makeStyles({
	root: {},
	item: {
		display: 'flex',
		flexDirection: 'column'
	}
});

function QuestionCard (props){
	const classes = useStyles();
	const [ isLoading, setLoading ] = React.useState(false);
	const question = useContext(QuestionContent);
	let choices = [];
	choices = question.question.choices;

	const { handleMark, value} = props;
	const handleChange = (event) => {
		const curMark = event.target.value;
		handleMark(value,curMark);
	}
	
	return !isLoading ? (
		<Card>
			<CardHeader
				action={
					<TextField
						label="Mark"
						onChange={handleChange}
						id="standard-start-adornment"
						className={clsx(classes.margin, classes.textField)}
						InputProps={{
							endAdornment: (
								<InputAdornment position="end">{' / ' + question.question.questionMark}</InputAdornment>
							)
						}}
					/>
				}
				title={'Question' + question.question.questionNum + ' : ' + question.question.questionDescription}
			/>
			<Divider />
			<CardContent>
				{question.question.questionType === 'ANSWER' ? (
					<Box p={3}>
						<Typography color="textPrimary" gutterBottom variant="h6">
							Answer:
						</Typography>
						<Box p={1} />
						<Paper variant="outlined">
							<Typography color="textPrimary" gutterBottom variant="body1">
								{question.anwer}
								<br />
								<br />
							</Typography>
						</Paper>
					</Box>
				) : (
					<Box p={3}>
						<FormControl component="fieldset" className={classes.formControl}>
							<FormLabel component="legend">Choices</FormLabel>
							<FormGroup>
								{question.question.choices ? (
									question.question.choices.map((choice, index) => (
										<FormControlLabel key={index}
											control={
												<Checkbox
													checked={index == question.anwer ? true : false}
													name="gilad"
												/>
											}
											label={choice}
										/>
									))
								) : (
									<div />
								)}
							</FormGroup>
						</FormControl>
					</Box>
				)}
			</CardContent>
		</Card>
	) : (
		<Loading isLoading={isLoading} />
	);
};

QuestionCard.propTypes = {
	className: PropTypes.string
};

export default QuestionCard;

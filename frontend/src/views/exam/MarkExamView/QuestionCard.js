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
	RadioGroup,
	FormControlLabel,
	Radio,
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

function QuestionCard(props) {
	const classes = useStyles();
	const [ isLoading, setLoading ] = React.useState(false);

	const { handleMark, value, question, answer, marker, status } = props;

	const handleChange = (event) => {
		const curMark = event.target.value;
		handleMark(value, curMark);
	};

	return !isLoading ? (
		<Card>
			<CardHeader
				action={
					<TextField
						label="Mark"
						onChange={handleChange}
						//	placeholder={answer.mark}
						disabled={status !=='CLOSED'}
						defaultValue={answer?answer.mark:0}
						id="standard-start-adornment"
						className={clsx(classes.margin, classes.textField)}
						InputProps={{
							endAdornment: (
								<InputAdornment position="end">{' / ' + question.questionMark}</InputAdornment>
							)
						}}
					/>
				}
				title={'Question' + (value + 1) + ' : ' + question.questionDescription}
			/>
			<Divider />
			<CardContent>
				{question.questionType === 'ANSWER' ? (
					<Box p={3}>
						<Typography color="textPrimary" gutterBottom variant="h6">
							Answer:
						</Typography>
						<Box p={1} />
						<Paper variant="outlined">
							<Typography color="textPrimary" gutterBottom variant="body1">
								{answer?answer.answer:''}
								<br />
								<br />
							</Typography>
						</Paper>
					</Box>
				) : (
					<Box p={3}>
						<FormControl component="fieldset">
							<FormLabel component="legend">Options</FormLabel>
							{question.choices ? (
								question.choices.map(
									(choice, index) =>
										choice !== '' ? (
											<RadioGroup
												aria-label="gender"
												name="gender1"
												value={answer && answer.answer == index ? index : ''}
											>
												<FormControlLabel value={index} control={<Radio />} label={choice} />
											</RadioGroup>
										) : (
											<div />
										)
								)
							) : (
								<div />
							)}
						</FormControl>
					</Box>
				)}
			</CardContent>
		</Card>
	) : (
		<Loading isLoading={isLoading} />
	);
}

QuestionCard.propTypes = {
	className: PropTypes.string
};

export default QuestionCard;

import React, { useContext } from 'react';
import PropTypes from 'prop-types';
import { deleteQuestion } from '../../../api/examAPI';
import Loading from '../../../utils/loading';

import {
	Box,
	FormControl,
	FormGroup,
	FormLabel,
	FormControlLabel,
	RadioGroup,
	Card,
	Paper,
	TextField,
	CardContent,
	CardHeader,
	Divider,
	Radio,
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

function QuestionCard(props) {
	const [ isLoading, setLoading ] = React.useState(false);
	
	const { answer, handleAnswer, value,question } = props;

	const [curAnswer, setCurAnswer] = React.useState('');

	const handleChange = (e, index) => {
		setCurAnswer(e.target.value);
		const curAnswer = index;
	//	setAnswer(curAnswer);
		handleAnswer(value,e.target.value);
	};

	console.log(curAnswer);
//	const [ answer, setAnswer ] = React.useState('');

	const updateAnswer = (e) => {
		const curAnswer = e.target.value;
		setCurAnswer(curAnswer);
		handleAnswer(value,curAnswer);
	};
	console.log(curAnswer);


	return !isLoading ? (
		<Card>
			<CardHeader
				action={
					<Box p={1}>
						<Typography color="textPrimary" gutterBottom variant="h6">
							Mark: {question.questionMark}
						</Typography>
					</Box>
				}
				title={'Q' + (value + 1) + ' : ' + question.questionDescription}
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
							<TextField
								id="standard-basic"
								fullWidth
								multiline
								rows={4}
								variant="outlined"
								onChange={updateAnswer}
							/>
						</Paper>
					</Box>
				) : (
					<Box p={3}>
						<FormControl component="fieldset">
							<FormLabel component="legend">Choose one from the following options:</FormLabel>
							{question.choices ? (
								question.choices.map(
									(choice, index) =>
										choice !== '' ? (
											<RadioGroup
												aria-label="gender"
												name="gender1"
												//value={curAnswer}
												onChange={handleChange}
											>
												<FormControlLabel value={index+""} control={<Radio />} label={choice} />
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

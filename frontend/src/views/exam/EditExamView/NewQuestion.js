import React, { useContext } from 'react';
import PropTypes from 'prop-types';
import HighlightOffIcon from '@material-ui/icons/HighlightOff';
import { NewQuestionCon } from './index';

import {
	Box,
	Card,
	Collapse,
	IconButton,
	TextField,
	InputLabel,
	Select,
	MenuItem,
	CardContent,
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

const NewQuestion = ({ className, ...rest }) => {
	const classes = useStyles();
	//	const [ editable, setEditable ] = React.useState(false);
	const [ isOpen, setOpen ] = React.useState(false);
	const [ values, setValues ] = React.useState({
		questionType: '',
		questionMark: '',
		questionDescription: ''
	});
	const [ choices, setChoices ] = React.useState({
		choice1: '',
		choice2: '',
		choice3: '',
		choice4: ''
	});

	let { newquestion, setNewquestion } = useContext(NewQuestionCon);
//	console.log(newquestion);

	//  console.log(t)
//	console.log(values.description);

	const handleChange = (prop) => (event) => {
		setValues({ ...values, [prop]: event.target.value });
		setNewquestion({ ...newquestion, [prop]: event.target.value });
		//	setChoices({ ...choices, [prop]: event.target.value });
		//	setNewquestion({...newquestion, choices:choices});
	};

	
	return (
		<Card>
			<CardContent>
				<Grid container direction="row" justify="flex-end" alignItems="center">
					<Grid container>
						<Grid item xs={2} lg={1}>
							<Typography color="textPrimary" gutterBottom variant="h5">
								{'Question ' + ' : '}
							</Typography>
						</Grid>
						<Grid item xs={9} lg={10}>
							<TextField
								id="standard-basic"
								defaultValue={values.description}
								fullWidth
								onChange={handleChange('questionDescription')}
							/>
						</Grid>
					</Grid>
				</Grid>
			</CardContent>
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
										onChange={handleChange('questionType')}
									>
										<MenuItem value={'ANSWER'}>Short-Answer</MenuItem>
										<MenuItem value={'CHOICE'}>Multi-Choice</MenuItem>
									</Select>
								</Grid>
								<Grid item xs={12} sm={4}>
									<TextField
										id="standard-basic"
										defaultValue={values.mark}
										fullWidth
										onChange={handleChange('questionMark')}
										label="Mark"
									/>
								</Grid>
							</Grid>
							<Box p={1} />
							<Collapse in={values.questionType == 'CHOICE' ? true : false}>
								<Grid container>
									<Grid item xs={12}>
										<Box p={1}>
											<Typography color="textPrimary" gutterBottom variant="h5">
												Choices :
											</Typography>
										</Box>
									</Grid>

									<Grid item xs={12}>
										<Box p={1}>
											<TextField
												id="standard-basic"
												defaultValue={choices.choice1}
												fullWidth
												onChange={handleChange('choice1')}
											/>
										</Box>
									</Grid>
									<Grid item xs={12}>
										<Box p={1}>
											<TextField
												id="standard-basic"
												defaultValue={choices.choice2}
												fullWidth
												onChange={handleChange('choice2')}
											/>
										</Box>
									</Grid>
									<Grid item xs={12}>
										<Box p={1}>
											<TextField
												id="standard-basic"
												defaultValue={choices.choice3}
												fullWidth
												onChange={handleChange('choice3')}
											/>
										</Box>
									</Grid>
									<Grid item xs={12}>
										<Box p={1}>
											<TextField
												id="standard-basic"
												defaultValue={choices.choice4}
												fullWidth
												onChange={handleChange('choice4')}
											/>
										</Box>
									</Grid>
								</Grid>
							</Collapse>
						</Grid>
					</Box>
				</CardContent>
			</Collapse>
		</Card>
	);
};

NewQuestion.propTypes = {
	className: PropTypes.string
};

export default NewQuestion;

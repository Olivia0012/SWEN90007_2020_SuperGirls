import React from 'react';
import PropTypes from 'prop-types';

import {
	Box,
	Card,
	Collapse,
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



const NewQuestion = (props) => {
	const [ values, setValues ] = React.useState({
		questionType: '',
		questionMark: '',
		id: -1,
		choices: [ '', '', '', '' ],
		examId: 0,
		questionDescription: '',
		questionNum: 0
	});

	const { handleAdd, qIndex } = props;

	const handleChange = (prop) => (event) => {
		setValues({ ...values, [prop]: event.target.value });
		handleAdd(qIndex,{ ...values, [prop]: event.target.value });
	};

	const handleEditChoice = (event, index) => {
		const curChoice = event.target.value;
		values.choices[index] = curChoice;
		handleAdd(qIndex, values);
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
								defaultValue={values.questionDescription}
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
										defaultValue={values.questionMark}
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
									{values.choices.map((item, index) => (
										<Grid item xs={12} key={index}>
											<Box p={1}>
												<TextField
													id="standard-basic"
													defaultValue={item}
													fullWidth
													onChange={(event) => {
														handleEditChoice(event, index);
													}}
												/>
											</Box>
										</Grid>
									))}
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

import React, { useContext } from 'react';
import PropTypes from 'prop-types';
import { green } from '@material-ui/core/colors';
import HighlightOffIcon from '@material-ui/icons/HighlightOff';
import EditIcon from '@material-ui/icons/Edit';
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import { QuestionContent, Editable } from './index';

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

const NewQuestion = ({ className, ...rest }) => {
	//	const classes = useStyles();
	//	const [ editable, setEditable ] = React.useState(false);
	const [ isOpen, setOpen ] = React.useState(false);
	const [ values, setValues ] = React.useState({
		type: '',
		mark: '',
		description: ''
	});

	const [ choices, setChoices ] = React.useState({
		choice1: '',
		choice2: '',
		choice3: '',
		choice4: ''
	});

	//  console.log(t)
	console.log(choices);

	const handleChange = (prop) => (event) => {
		setValues({ ...values, [prop]: event.target.value });
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
						value={values.type}
						fullWidth
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

				<Grid item xs={12}>
					<Box p={1}>
						<TextField
							id="standard-basic"
							defaultValue={choices.choice1}
							fullWidth
							onChange={(event) => {
								//	handleEditChoice(event, index);
							}}
						/>
					</Box>
				</Grid>
				<Grid item xs={12}>
					<Box p={1}>
						<TextField
							id="standard-basic"
							defaultValue={choices.choice2}
							fullWidth
							onChange={(event) => {
								//	handleEditChoice(event, index);
							}}
						/>
					</Box>
				</Grid>
				<Grid item xs={12}>
					<Box p={1}>
						<TextField
							id="standard-basic"
							defaultValue={choices.choice3}
							fullWidth
							onChange={(event) => {
								//	handleEditChoice(event, index);
							}}
						/>
					</Box>
				</Grid>
				<Grid item xs={12}>
					<Box p={1}>
						<TextField
							id="standard-basic"
							defaultValue={choices.choice4}
							fullWidth
							onChange={(event) => {
								//	handleEditChoice(event, index);
							}}
						/>
					</Box>
				</Grid>
			</React.Fragment>
		);
	}

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
								onChange={(event) => {
									//	handleEditChoice(event, index);
								}}
							/>
						</Grid>
						<Grid item xs={1} lg={1}>
							<IconButton aria-label="add an alarm">
								<HighlightOffIcon
									color="primary"
									size="small"
									onClick={() => {
										alert("Please input 'DELETE' to  delete this question.");
									}}
								/>
							</IconButton>
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
								<FirstLine />
							</Grid>
							<Box p={1} />
							<Collapse in={values.type == 'CHOICE' ? true : false}>
								<Grid container>
									<SecondLine />
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

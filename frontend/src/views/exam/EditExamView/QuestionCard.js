import React, { useContext } from 'react';
import PropTypes from 'prop-types';
import { green } from '@material-ui/core/colors';
import HighlightOffIcon from '@material-ui/icons/HighlightOff';
import EditIcon from '@material-ui/icons/Edit';
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import { CountContext } from './index';

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
	const [ age, setAge, qN ] = React.useState('');
	const [ isOpen, setOpen ] = React.useState(false);
	const [ isMultiChoice, setMultiChoice ] = React.useState(false);
	const [ values, setValues ] = React.useState({
		type: '',
		mark: '',
		content: ''
	});

	const count = useContext(CountContext);

	const handleChange = (prop) => (event) => {
		if (event.target.value == 20) setMultiChoice(true);
		if (event.target.value == 10) setMultiChoice(false);
		setValues({ ...values, [prop]: event.target.value });
	};

	function FirstLine() {
		return (
			<React.Fragment>
				<Grid item xs={12} sm={4}>
					<InputLabel id="demo-simple-select-label">Question Type</InputLabel>
					<Select
						labelId="demo-simple-select-label"
						id="demo-simple-select"
						value={values.type}
						fullWidth
						onChange={handleChange('type')}
					>
						<MenuItem value={10}>Short-Answer</MenuItem>
						<MenuItem value={20}>Multi-Choice</MenuItem>
					</Select>
				</Grid>
				<Grid item xs={12} sm={4}>
					<TextField
						id="outlined-multiline-flexible"
						required
						fullWidth
						value={values.mark}
						onChange={handleChange('mark')}
						label="Mark"
					/>
					value={values.mark}
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
							Description
						</Typography>
						<TextField
							id="outlined-multiline-flexible"
							multiline
							fullWidth
							rows={2}
							value={values.content}
							onChange={handleChange('content')}
							variant="outlined"
						/>
					</Box>
				</Grid>
			</React.Fragment>
		);
	}

	function ThirdLine() {
		return (
			<React.Fragment>
				<Grid item xs={12}>
					<Box p={1}>
						<Typography color="textPrimary" gutterBottom variant="h5">
							Choices
						</Typography>
					</Box>
				</Grid>
				<Grid item xs={12}>
					<Grid container>
						<Grid item xs={9} lg={10}>
							<TextField id="standard-basic" value={age} fullWidth rows={2} label="A." />
						</Grid>
						<Grid item xs={3} lg={2}>
							<IconButton color="primary" aria-label="add an alarm" fontSize="sm">
								<EditIcon color="primary" fontSize="sm" />
							</IconButton>
							<IconButton aria-label="add an alarm" fontSize="sm">
								<HighlightOffIcon color="error" fontSize="sm" />
							</IconButton>
						</Grid>
					</Grid>
				</Grid>
				<Grid item xs={12}>
					<Grid container>
						<Grid item xs={9} lg={10}>
							<TextField id="standard-basic" value={age} fullWidth rows={2} label="A." />
						</Grid>
						<Grid item xs={3} lg={2}>
							<IconButton color="primary" aria-label="add an alarm" fontSize="sm">
								<EditIcon color="primary" fontSize="sm" />
							</IconButton>
							<IconButton aria-label="add an alarm" fontSize="sm">
								<HighlightOffIcon color="error" fontSize="sm" />
							</IconButton>
						</Grid>
					</Grid>
				</Grid>
				<Grid item xs={12}>
					<Box display="flex" justifyContent="center">
						<IconButton color="secondary" aria-label="add an alarm">
							<AddCircleOutlineIcon style={{ color: green[500] }} fontSize="large" />
						</IconButton>
					</Box>
				</Grid>
			</React.Fragment>
		);
	}
	const b = useContext(CountContext);
	console.log(b);
	return (
		<Card>
			<CardHeader
				onDoubleClick={() => {
					setOpen((prev) => !prev);
				}}
				action={
					/*	<IconButton color="secondary" aria-label="add an alarm">
						<KeyboardArrowDownIcon />
					</IconButton>*/
					<IconButton aria-label="add an alarm">
						<HighlightOffIcon
							color="error"
							fontSize="sm"
							onClick={() => {
								alert("Please input 'DELETE' to  delete this question.");
							}}
						/>
					</IconButton>
				}
				title={'Question ' + b}
			/>
			<Divider />
			<Collapse in={isOpen}>
				<CardContent>
					<Box p={3}>
						<Grid container spacing={5} direction="column" justify="flex-start" alignItems="center">
							<Grid
								container
								direction="row"
								justify="space-between"
								alignItems="center"
								xs={12}
								spacing={2}
							>
								<FirstLine />
							</Grid>
							<Box p={1} />
							<Grid container xs={12}>
								<SecondLine />
							</Grid>
							<Box p={1} />
							<Collapse in={isMultiChoice}>
								<Grid container xs={12} lg={12}>
									<ThirdLine />
								</Grid>
							</Collapse>
						</Grid>
					</Box>
				</CardContent>
				<Divider />
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
		</Card>
	);
};

QuestionCard.propTypes = {
	className: PropTypes.string
};

export default QuestionCard;

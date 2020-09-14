import React, { useContext } from 'react';
import PropTypes from 'prop-types';
import clsx from 'clsx';
import { ExamBasicContent, Editable } from './index';

import { Typography, Card, TextField, CardContent, makeStyles, Grid, CardHeader, Divider } from '@material-ui/core';

import { grid } from '@material-ui/system';
import QuestionCard from './QuestionCard';
import { Divide } from 'react-feather';

const useStyles = makeStyles({
	root: {},
	item: {
		display: 'flex',
		flexDirection: 'column'
	}
});

const ExamBasicInfo = ({ className, ...rest }) => {
	const classes = useStyles();
	const examBasicInfo = useContext(ExamBasicContent);
	const editable = useContext(Editable);
	const [ value, setValue ] = React.useState('');
	console.log(value);
	var a = examBasicInfo.examTitle;

	const handleChange = (prop) => (event) => {
		//	setValues({ ...values, [prop]: event.target.value });
		//   qN(event.target.value);
	};
	const handleEditTitle = (event) => {
		console.log(event.target.value);
		setValue(event.target.value);
	};

	return (
		<form className={clsx(classes.root, className)} {...rest}>
			<Card>
				<CardHeader
					action={
						<Typography color="textPrimary" gutterBottom variant="h5">
							{examBasicInfo.status}
						</Typography>
					}
					title={
						examBasicInfo.subjectCode + ' - ' + examBasicInfo.subjectTitle + ' - ' + examBasicInfo.examTitle
					}
				/>
				<Divider />
				<CardContent>
					<Grid container container direction="row" xs={12} justify="space-between" alignItems="center" spacing={2}>
						<TextField
							id="standard-basic"
							label="title"
							defaultValue={a}
							onChange={(event) => {
								handleEditTitle(event);
							}}
							disabled={!editable}
						/>
						<Typography color="textPrimary" gutterBottom variant="h5">
							Creator : {examBasicInfo.creator} 
						</Typography>
						<Typography color="textPrimary" gutterBottom variant="h5">
							CreatedTime: {examBasicInfo.createTime}
						</Typography>
					</Grid>
				</CardContent>
			</Card>
		</form>
	);
};

ExamBasicInfo.propTypes = {
	className: PropTypes.string
};

export default ExamBasicInfo;

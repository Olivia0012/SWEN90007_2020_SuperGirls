import React, { useContext } from 'react';
import PropTypes from 'prop-types';
import clsx from 'clsx';
import { SubmissionContent } from './index';

import { Typography, Card, Box, CardContent, makeStyles, Grid, CardHeader, Divider } from '@material-ui/core';


const useStyles = makeStyles({
	root: {},
	item: {
		display: 'flex',
		flexDirection: 'column'
	}
});

const SubmissionInfo = ({ className, ...rest }) => {
	const classes = useStyles();
	const submissionInfo = useContext(SubmissionContent);
	const [ value, setValue ] = React.useState('');
	console.log(value);
	var a = submissionInfo.examTitle;


	return (
		<form className={clsx(classes.root, className)} {...rest}>
			<Card>
				<CardHeader
					action={
						<Typography color="textPrimary" gutterBottom variant="h5">
							{submissionInfo.status}
						</Typography>
					}
					title={
						'   '+submissionInfo.subjectCode + ' - ' + submissionInfo.subjectTitle 
					}
				/>
				<Divider />
				<CardContent>
					<Box p = {3}>
					<Grid container  direction="row" justify="space-between" alignItems="center" spacing={2}>
						<Typography color="textPrimary" gutterBottom variant="body1">
							Exam :  {submissionInfo.examTitle} 
						</Typography>
						<Typography color="textPrimary" gutterBottom variant="body1">
							Student :  {submissionInfo.student} 
						</Typography>
						<Typography color="textPrimary" gutterBottom variant="body1">
							SubTime :  {submissionInfo.subTime}
						</Typography>
					</Grid>
					</Box>
				</CardContent>
			</Card>
		</form>
	);
};

SubmissionInfo.propTypes = {
	className: PropTypes.string
};

export default SubmissionInfo;

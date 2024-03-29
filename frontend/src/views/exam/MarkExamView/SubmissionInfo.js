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
	
	var a = submissionInfo.examTitle;
	console.log(submissionInfo);

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
							TotalMark :  {submissionInfo.totalMark==0? '-':submissionInfo.totalMark}
						</Typography>
						<Typography color="textPrimary" gutterBottom variant="body1">
							SubTime :  {submissionInfo.subTime}
						</Typography>
						<Typography color="textPrimary" gutterBottom variant="body1">
							Status :  {submissionInfo.status}
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

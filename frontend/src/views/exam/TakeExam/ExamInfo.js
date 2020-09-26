import React from 'react';
import PropTypes from 'prop-types';
import clsx from 'clsx';

import { Typography, Card, Box, CardContent, makeStyles, Grid, CardHeader, Divider } from '@material-ui/core';


const useStyles = makeStyles({
	root: {},
	item: {
		display: 'flex',
		flexDirection: 'column'
	}
});

const ExamInfo = (props) => {
	const [ value, setValue ] = React.useState('');
	console.log(value);

	const {examInfo} = props;
	console.log(props);


	return (
			<Card>
				<CardHeader
					action={
						<Typography color="textPrimary" gutterBottom variant="h5">
							{examInfo.status}
						</Typography>
					}
					title={
						'   '+examInfo.subject.subjectCode + ' - ' + examInfo.subject.title 
					}
				/>
				<Divider />
				<CardContent>
					<Box p = {3}>
					<Grid container  direction="row" justify="space-between" alignItems="center" spacing={2}>
						<Typography color="textPrimary" gutterBottom variant="body1">
							Exam :  {examInfo.title} 
						</Typography>
						
					</Grid>
					</Box>
				</CardContent>
			</Card>
	);
};

ExamInfo.propTypes = {
	className: PropTypes.string
};

export default ExamInfo;

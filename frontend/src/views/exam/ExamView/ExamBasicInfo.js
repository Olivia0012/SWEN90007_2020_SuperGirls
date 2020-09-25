import React, { useContext } from 'react';
import PropTypes from 'prop-types';
import { Editable } from './index';

import { Typography, Card, TextField, CardContent, Grid, CardHeader, Divider } from '@material-ui/core';


const ExamBasicInfo = (props) => {
	const { handleTitle, examBasicInfo } = props;
	const editable = useContext(Editable);

	const handleEditTitle = (event) => {
		handleTitle(event.target.value);
	};

	return (
		<Card>
			<CardHeader
				action={
					<Typography color="textPrimary" gutterBottom variant="h5">
						{examBasicInfo.status}
					</Typography>
				}
				title={'Subject Code: ' + examBasicInfo.subjectCode + ' -  Subject: ' + examBasicInfo.subjectTitle}
			/>
			<Divider />
			<CardContent>
				<Grid
					container
					container
					direction="row"
					xs={12}
					justify="space-between"
					alignItems="center"
					spacing={2}
				>
					<TextField
						id="standard-basic"
						defaultValue={examBasicInfo.examTitle}
						onChange={handleEditTitle}
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
	);
};

ExamBasicInfo.propTypes = {
	className: PropTypes.string
};

export default ExamBasicInfo;

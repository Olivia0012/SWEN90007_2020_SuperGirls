import React from 'react';
import PropTypes from 'prop-types';
import clsx from 'clsx';
import { Box, Button, Card, CardContent, CardHeader, Divider, makeStyles, Typography } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
	root: {},
	importButton: {
		marginRight: theme.spacing(1)
	},
	exportButton: {
		marginRight: theme.spacing(1)
	}
}));

const Toolbar = (props, { className, ...rest }) => {
	const classes = useStyles();
	const { subject, exam } = props;

	return (
		<div className={clsx(classes.root, className)} {...rest}>
			<Box mt={3}>
				<Card>
					<CardHeader title={subject.title + ' - ' + subject.subjectCode} />
					<Divider />
					<CardContent>
						<Typography color="textPrimary" gutterBottom variant="body1">
							Exam Title : {exam}
						</Typography>
					</CardContent>
				</Card>
			</Box>
		</div>
	);
};

Toolbar.propTypes = {
	className: PropTypes.string
};

export default Toolbar;

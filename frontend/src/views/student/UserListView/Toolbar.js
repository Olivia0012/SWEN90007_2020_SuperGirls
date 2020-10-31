import React from 'react';
import PropTypes from 'prop-types';
import clsx from 'clsx';
import { Box, Grid, Card, CardContent, CardHeader, Divider, makeStyles, Typography } from '@material-ui/core';

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
	const { subject, exam, status } = props;

	return (
		<div className={clsx(classes.root, className)} {...rest}>
			<Box mt={3}>
				<Card>
					<CardHeader title={subject.title + ' - ' + subject.subjectCode} />
					<Divider />
					<CardContent>
						<Grid container direction="row" justify="flex-start" alignItems="center" spacing={5}>
							<Grid item>
								<Typography color="textPrimary" gutterBottom variant="body1">
									Exam Title : {exam}
								</Typography>
							</Grid>
							<Grid item>
								<Typography color="textPrimary" gutterBottom variant="body1">
									Status : {status}
								</Typography>
							</Grid>
						</Grid>
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

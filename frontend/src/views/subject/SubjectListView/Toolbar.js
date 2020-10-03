import React from 'react';
import PropTypes from 'prop-types';
import clsx from 'clsx';
import { Box, Typography, Card, CardContent,makeStyles } from '@material-ui/core';
import { Search as SearchIcon } from 'react-feather';

const useStyles = makeStyles((theme) => ({
	root: {},
	importButton: {
		marginRight: theme.spacing(1)
	},
	exportButton: {
		marginRight: theme.spacing(1)
	}
}));

const Toolbar = (props,{ className, ...rest }) => {
	const classes = useStyles();

	return (
		<div className={clsx(classes.root, className)} {...rest}>
			<Box mt={3}>
				<Card>
					<CardContent>
						<Box  p={1}>
							<Typography color="primary" gutterBottom variant="h5">
              Hi {props.userName}, Welcome to the Online Exam Application ! 
							</Typography>
						</Box>
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

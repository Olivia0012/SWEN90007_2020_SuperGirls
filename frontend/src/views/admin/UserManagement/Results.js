import React, { useState } from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import { addNewExam } from '../../../api/examAPI';
import PerfectScrollbar from 'react-perfect-scrollbar';
import {
	Link,
	Box,
	Card,
	Checkbox,
	Table,
	TableBody,
	TableCell,
	TableHead,
	TablePagination,
	TableRow,
	Typography,
	makeStyles,
	Button,
	Collapse,
	CardActions,
	DialogActions,
	Dialog,
	DialogTitle,
	DialogContent,
	DialogContentText,
	TextField
} from '@material-ui/core';
import Alert from '@material-ui/lab/Alert';

const useStyles = makeStyles((theme) => ({
	root: {},
	avatar: {
		marginRight: theme.spacing(2)
	}
}));

const Results = (props, { className, customers, ...rest }) => {
	const classes = useStyles();
	const { users } = props;

	return (
		<Card className={clsx(classes.root, className)} {...rest}>
			<PerfectScrollbar>
				<Box minWidth={1050}>
					<Table>
						<TableHead>
							<TableRow>
								<TableCell>No.</TableCell>
								<TableCell>User Type</TableCell>
								<TableCell>User Name</TableCell>
							</TableRow>
						</TableHead>
						<TableBody>
							{users.map((row, index) => (
								<TableRow key={row.name}>
									<TableCell>{index}</TableCell>
									<TableCell>{row.userName}</TableCell>
									<TableCell>{row.role}</TableCell>
								</TableRow>
							))}
						</TableBody>
					</Table>
				</Box>
			</PerfectScrollbar>
		</Card>
	);
};

Results.propTypes = {
	className: PropTypes.string,
	customers: PropTypes.array.isRequired
};

export default Results;

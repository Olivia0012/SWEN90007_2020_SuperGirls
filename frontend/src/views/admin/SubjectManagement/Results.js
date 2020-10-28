import React, { useState } from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import PerfectScrollbar from 'react-perfect-scrollbar';
import {
	Link,
	Box,
	Card,
	Fade,
	Table,
	TableBody,
	TableCell,
	TableHead,
	TablePagination,
	TableRow,
	Typography,
	makeStyles,
	Button,
	Modal
} from '@material-ui/core';
import InstructorM from './UserTransferList';
import { getUsers } from '../../../api/instructorAPI';
import UserTransferList from './UserTransferList';

const useStyles = makeStyles((theme) => ({
	root: {},
	avatar: {
		marginRight: theme.spacing(2)
	},
	modal: {
		display: 'flex',
		alignItems: 'center',
		justifyContent: 'center'
	},
	paper: {
		backgroundColor: theme.palette.background.paper,
		border: '2px solid #000',
		boxShadow: theme.shadows[5],
		padding: theme.spacing(2, 4, 3)
	},
	button: {
		'& > *': {
			margin: theme.spacing(1)
		}
	}
}));

const Results = (props, { className, customers, ...rest }) => {
	const classes = useStyles();
	const [ open, setOpen ] = React.useState(false);
	const [ openS, setOpenS ] = React.useState(false);
	const [ data, setData ] = useState([]);
	const [ isLoading, setLoading ] = useState(false);
	const [ id, setId ] = useState();
	const [ role, setRole ] = useState();
	const { subjects } = props;

	const handleOpen = (role, id) => {
		setOpen(true);
		setId(id);
		setRole(role);
	};

	console.log(data);

	const handleClose = () => {
		setOpen(false);
	};

	return (
		<Card className={clsx(classes.root, className)} {...rest}>
			<PerfectScrollbar>
				<Box minWidth={1050}>
					<Table>
						<TableHead>
							<TableRow>
								<TableCell align="center">Subject Code</TableCell>
								<TableCell align="center">Subject Title</TableCell>
								<TableCell align="center">Assign Instructor</TableCell>
								<TableCell align="center">Assign Student</TableCell>
							</TableRow>
						</TableHead>
						<TableBody>
							{subjects.map((row) => (
								<TableRow key={row.id}>
									<TableCell align="center">{row.subjectCode}</TableCell>
									<TableCell align="center">{row.title}</TableCell>
									<TableCell align="center">
										<Button
											size="small"
											variant="contained"
											onClick={() => handleOpen('INSTRUCTOR', row.id)}
										>
											Assign
										</Button>
									</TableCell>
									<TableCell align="center">
										<Button
											size="small"
											variant="contained"
											onClick={() => handleOpen('STUDENT', row.id)}
										>
											Assign
										</Button>
									</TableCell>
								</TableRow>
							))}
						</TableBody>
					</Table>
				</Box>
			</PerfectScrollbar>
			<Modal className={classes.modal} open={open}>
				<Fade in={open}>
					<div className={classes.paper}>
						<h2 style={{ fontFamily: 'Arial', textAlign: 'center' }}> Assign {role} </h2>
						<UserTransferList role={role} id={id} />
						{/** <Button
							size="small"
							variant="contained"
							onClick={handleClose}
							style={{ marginLeft: 180, marginTop: 12 }}
						>
							Cancel
						</Button>*/}
					</div>
				</Fade>
			</Modal>
		</Card>
	);
};

Results.propTypes = {
	className: PropTypes.string,
	customers: PropTypes.array.isRequired
};

export default Results;

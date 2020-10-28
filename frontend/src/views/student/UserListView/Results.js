import React, { useState } from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import moment from 'moment';
import PerfectScrollbar from 'react-perfect-scrollbar';
import {
	InputAdornment,
	Box,
	Card,
	Checkbox,
	Table,
	TableBody,
	TableCell,
	TableHead,
	TablePagination,
	TableRow,
	IconButton,
	Typography,
	makeStyles,
	Button,
	Dialog,
	DialogTitle,
	Collapse,
	DialogContent,
	DialogContentText,
	DialogActions,
	TextField
} from '@material-ui/core';
import Alert from '@material-ui/lab/Alert';
import EditIcon from '@material-ui/icons/Edit';
import { editTotalMark } from '../../../api/instructorAPI';
import { lockMarkExam } from '../../../api/examAPI';

const useStyles = makeStyles((theme) => ({
	root: {},
	avatar: {
		marginRight: theme.spacing(2)
	}
}));

const Results = ({ className, exam, customers, ...rest }) => {
	const classes = useStyles();
	const [ selectedCustomerIds, setSelectedCustomerIds ] = useState([]);
	const [ limit, setLimit ] = useState(10);
	const [ page, setPage ] = useState(0);
	const [ openAlert, setOpen ] = React.useState(false);
	const [ openGreen, setOpenGreen ] = React.useState(false);
	const [ error, setError ] = React.useState();
	const [ open, setDMOpen ] = React.useState(false);
	const [ student, setStudent ] = useState();
	const [ mark, setMark ] = useState(0);
	var m = 0;
	exam.questionList.map((q) => {
		m += parseFloat(q.questionMark);
	});
	console.log(customers[0].submissions[0]);

	const [ tMark, setTMark ] = useState(m);
	//setTMark(m);

	const handleChange = (event) => {
		setMark(event.target.value);
		//	student.submissions[0].totalMark = event.target.value;
	};

	const handleOpen = async (customer) => {
		await lockMarkExam(customer.submissions[0].id, true)
			.then((response) => {
				console.log(response);
				const result = response.data;
				if (response.data.valid == false) {
					alert('Please login to continue.');
					window.location.replace('/');
				} else {
					if (response.data.acquirelock == 'true') {
						setStudent(customer);
						setMark(customer.submissions[0].totalMark);
						setDMOpen(!open);
					} else {
						alert(response.data.acquirelock);
					}
				}
			})
			.catch((error) => {
				alert(error);
				window.location.replace('../');
			});
	};

	const handleClose = async () => {
		await lockMarkExam(student.submissions[0].id, false)
			.then((response) => {
				console.log(response);
				const result = response.data;
				if (response.data.valid == false) {
					alert('Please login to continue.');
					window.location.replace('/');
				} else {
					if (response.data.acquirelock == 'true') {
						setDMOpen(false);
					} else {
						alert(response.data.acquirelock);
					}
				}
			})
			.catch((error) => {
				alert(error);
				window.location.replace('../');
			});
	};

	const handleMark = async(submissionId) => {
		await lockMarkExam(submissionId, true)
		.then((response) => {
			console.log(response);
			const result = response.data;
			if (response.data.valid == false) {
				alert('Please login to continue.');
				window.location.replace('/');
			} else {
				if (response.data.acquirelock == 'true') {
					setDMOpen(false);
					window.location.replace('./submission=' + submissionId);
				} else {
					alert(response.data.acquirelock);
				}
			}
		})
		.catch((error) => {
			alert(error);
			window.location.replace('../');
		});
		
	}

	const handleSubmit = async () => {
		if (parseFloat(mark) > tMark) {
			setError('Invalid mark');
			setOpen(!openAlert);
		} else {
			student.submissions[0].totalMark = mark;
			student.submissions[0].answers = [];
			student.submissions[0].exam = customers[0].submissions[0].exam;
			student.submissions[0].questionList = customers[0].submissions[0].questionList;
			console.log('Student', student);
			//	student.submissions[0].exam={id:parseInt(id)};

			await editTotalMark(student.submissions[0])
				.then((res) => {
					if (res.data == false) {
						alert('Please login to continue.');
						//	  window.location.replace('/');
					} else {
						alert('Update successfully.');
						window.location.reload();
					}
				})
				.catch((error) => {
					alert(error + ', Please login to continue.');
					window.location.replace('/');
				});
		}
	};

	const handleSelectAll = (event) => {
		let newSelectedCustomerIds;

		if (event.target.checked) {
			newSelectedCustomerIds = customers.map((customer) => customer.id);
		} else {
			newSelectedCustomerIds = [];
		}

		setSelectedCustomerIds(newSelectedCustomerIds);
	};

	const handleSelectOne = (event, id) => {
		const selectedIndex = selectedCustomerIds.indexOf(id);
		let newSelectedCustomerIds = [];

		if (selectedIndex === -1) {
			newSelectedCustomerIds = newSelectedCustomerIds.concat(selectedCustomerIds, id);
		} else if (selectedIndex === 0) {
			newSelectedCustomerIds = newSelectedCustomerIds.concat(selectedCustomerIds.slice(1));
		} else if (selectedIndex === selectedCustomerIds.length - 1) {
			newSelectedCustomerIds = newSelectedCustomerIds.concat(selectedCustomerIds.slice(0, -1));
		} else if (selectedIndex > 0) {
			newSelectedCustomerIds = newSelectedCustomerIds.concat(
				selectedCustomerIds.slice(0, selectedIndex),
				selectedCustomerIds.slice(selectedIndex + 1)
			);
		}

		setSelectedCustomerIds(newSelectedCustomerIds);
	};

	const handleLimitChange = (event) => {
		setLimit(event.target.value);
	};

	const handlePageChange = (event, newPage) => {
		setPage(newPage);
	};

	return (
		<Card className={clsx(classes.root, className)} {...rest}>
			<PerfectScrollbar>
				<Box minWidth={1050}>
					<Table>
						<TableHead>
							<TableRow>
								<TableCell padding="checkbox" />
								<TableCell />
								<TableCell align="center" colSpan={3}>
									Exam
								</TableCell>
								<TableCell align="center" />
							</TableRow>
							<TableRow align="center">
								<TableCell padding="checkbox">
									<Checkbox
										checked={selectedCustomerIds.length === customers.length}
										color="primary"
										indeterminate={
											selectedCustomerIds.length > 0 &&
											selectedCustomerIds.length < customers.length
										}
										onChange={handleSelectAll}
									/>
								</TableCell>
								<TableCell>Student Name</TableCell>
								<TableCell align="center">Total Mark [Out of {tMark}]</TableCell>
								<TableCell align="center">Comment</TableCell>
								<TableCell align="center">Marker</TableCell>
								<TableCell align="center">Action</TableCell>
							</TableRow>
						</TableHead>
						<TableBody>
							{customers.map((customer) => (
								<TableRow
									hover
									key={customer.userName}
									selected={selectedCustomerIds.indexOf(customer.id) !== -1}
								>
									<TableCell padding="checkbox">
										<Checkbox
											checked={selectedCustomerIds.indexOf(customer.id) !== -1}
											onChange={(event) => handleSelectOne(event, customer.id)}
											value="true"
										/>
									</TableCell>
									<TableCell>
										<Box alignItems="center" display="flex">
											<Typography color="textPrimary" variant="body1">
												{customer.userName}
											</Typography>
										</Box>
									</TableCell>
									<TableCell align="center">
										{customer.submissions[0].id !== 0 ? (
											customer.submissions[0].totalMark + '/' + tMark
										) : (
											'-'
										)}
										{'  '}
										{customer.submissions[0].id !== 0 && exam.status == 'CLOSED' ? (
											<IconButton color="primary" onClick={() => handleOpen(customer)}>
												<EditIcon fontSize="small" />
											</IconButton>
										) : (
											<div />
										)}
									</TableCell>
									<TableCell align="center">
										{customer.submissions[0].id !== 0 && customer.submissions[0].comment ? (
											customer.submissions[0].comment
										) : (
											'-'
										)}
									</TableCell>
									<TableCell align="center">
										{customer.submissions[0].id !== 0 && customer.submissions[0].marker ? (
											customer.submissions[0].marker.userName
										) : (
											'-'
										)}
									</TableCell>
									<TableCell align="center">
										{customer.submissions[0].id !== 0 &&
										exam.status == 'CLOSED' ? (
											<Button onClick={() => handleMark(customer.submissions[0].id)} color="primary">
												Mark
											</Button>
										) : (
											<div>-</div>
										)}
									</TableCell>
								</TableRow>
							))}
						</TableBody>
					</Table>
					<Dialog
						open={open}
						onClose={handleClose}
						aria-labelledby="max-width-dialog-title"
						maxWidth="small"
						//	fullWidth
					>
						<DialogTitle id="form-dialog-title">Total Mark</DialogTitle>
						<DialogContent>
							<Collapse in={!openGreen}>
								<DialogContentText>Please input the total mark in the textField.</DialogContentText>
								<TextField
									id="outlined-multiline-flexible"
									required
									fullWidth
									value={mark}
									onChange={handleChange}
									label="Total Mark"
									variant="outlined"
									className={clsx(classes.margin, classes.textField)}
									InputProps={{
										endAdornment: <InputAdornment position="end">{' / ' + tMark}</InputAdornment>
									}}
								/>
							</Collapse>
						</DialogContent>
						<DialogContent>
							<Collapse in={openAlert}>
								<Alert severity="error">{error}</Alert>
							</Collapse>
							<Collapse in={openGreen}>
								<Alert severity="success">Save Total Mark Successfully!</Alert>
							</Collapse>
						</DialogContent>
						<DialogActions>
							<Collapse in={!openGreen}>
								<Button onClick={handleClose} color="primary">
									Cancel
								</Button>
							</Collapse>
							<Button onClick={handleSubmit} color="primary" type="submit">
								Confirm
							</Button>
						</DialogActions>
					</Dialog>
				</Box>
			</PerfectScrollbar>
			<TablePagination
				component="div"
				count={customers.length}
				onChangePage={handlePageChange}
				onChangeRowsPerPage={handleLimitChange}
				page={page}
				rowsPerPage={limit}
				rowsPerPageOptions={[ 5, 10, 25 ]}
			/>
		</Card>
	);
};

Results.propTypes = {
	className: PropTypes.string,
	customers: PropTypes.array.isRequired
};

export default Results;

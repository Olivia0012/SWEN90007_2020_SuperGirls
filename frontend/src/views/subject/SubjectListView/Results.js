import React, { useState } from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import { addNewExam } from '../../../api/examAPI';
import PerfectScrollbar from 'react-perfect-scrollbar';
import { useNavigate } from 'react-router-dom';
import { green } from '@material-ui/core/colors';
import moment from 'moment';
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import {
	Link,
	Box,
	Card,
	IconButton,
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

const Results = ({ className, customers, ...rest }) => {
	const classes = useStyles();
	const navigate = useNavigate();
	const [ selectedCustomerIds, setSelectedCustomerIds ] = useState([]);
	const [ limit, setLimit ] = useState(10);
	const [ isLoading, setLoading ] = useState(false);
	const [ page, setPage ] = useState(0);
	const [ openAlert, setOpen ] = React.useState(false);
	const [ openGreen, setOpenGreen ] = React.useState(false);
	const [ error, setError ] = React.useState();
	const [ open, setDMOpen ] = React.useState(false);
	const [ values, setValues ] = React.useState({
		title: '',
		subjectTitle: ''
	});
	const handleChange = (prop) => (event) => {
		setValues({ ...values, [prop]: event.target.value });
	};

	const [ subject, setSubject ] = React.useState();
	const handleOpen = (event, subject) => {
		setDMOpen(true);
		console.log(subject);
		setSubject(subject);
		setValues({ ...values, subjectTitle: subject.title });
	};

	const handleClose = () => {
		setDMOpen(false);
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

	const AddNewExam = async () => {
		if (openGreen) {
			window.location.href = './subjects';
		} else {
			console.log(values);
			setLoading(true);
			//	alert("Adding new exam!");
			const newExam = {
				createdTime: moment().format('YYYY-MM-DD HH:mm:ss') + '',
				updatedTime: '',
				creator: { id: 4, passWord: '', userName: '', role: '' },
				id: -1,
				locked: false,
				questionList: null,
				status: 'CREATED',
				subject: { id: subject.id, title: '', subjectCode: '' },
				title: values.title
			};
			const a = await addNewExam(newExam)
				.then((res) => {
					setLoading(false);
					if (res.data == false) {
						alert('Invalid session,Please login to continuee.');
						window.location.href = '../';
					}
					setOpenGreen(true);
				})
				.catch((error) => {
					setLoading(false);
					setOpen(true);
					setError(error + '');
					//			alert('Error from processDataAsycn() with async( When promise gets rejected ): ' + error);
				});
			console.log(a);
		}
	};

	return (
		<Card className={clsx(classes.root, className)} {...rest}>
			<PerfectScrollbar>
				<Box minWidth={1050}>
					<Table>
						<TableHead>
							<TableRow>
								<TableCell />
								<TableCell />
								<TableCell />
								<TableCell align="center" colSpan={3}>
									Exams
								</TableCell>
								<TableCell />
								<TableCell />
							</TableRow>
							<TableRow>
								<TableCell>Subject Code</TableCell>
								<TableCell>Subject Title</TableCell>
								<TableCell>New Exam</TableCell>
								<TableCell align="center">Title</TableCell>
								<TableCell>Status</TableCell>
								<TableCell>Creator</TableCell>
								<TableCell>CreatedTime</TableCell>
								<TableCell>Students</TableCell>
							</TableRow>
						</TableHead>
						<TableBody>
							{customers.slice(0, limit).map(
								(customer) =>
									customer.exams.length !== 0 ? (
										customer.exams.map((item, index) => (
											<TableRow
												hover
												key={customer.title}
												selected={selectedCustomerIds.indexOf(customer.id) !== -1}
											>
												<TableCell>
													<Box alignItems="center" display="flex">
														<Typography color="textPrimary" variant="body1">
															{index == 0 ? customer.subjectCode : ''}
														</Typography>
													</Box>
												</TableCell>
												<TableCell>{index == 0 ? customer.title : ''}</TableCell>
												<TableCell align="center">
													{index == 0 ? (
														<IconButton
															color="secondary"
															aria-label="add an alarm"
															style={{ width: 50 }}
															onClick={(event) => handleOpen(event, customer)}
														>
															<AddCircleOutlineIcon style={{ color: green[500] }} />
														</IconButton>
													) : (
														<div />
													)}
													
												</TableCell>
												<TableCell align="center">
													<Link href={'./exam/id=' + item.id}>{item.title}</Link>
												</TableCell>
												<TableCell>{item.status}</TableCell>
												<TableCell>{item.creator.userName}</TableCell>
												<TableCell>{item.createdTime}</TableCell>

												<TableCell>
													<Button
														color="primary"
														//	variant="contained"
														onClick={() =>
															navigate(
																'/oea/students/subject=' +
																	customer.id +
																	'&exam=' +
																	item.id,
																{ replace: true }
															)}
													>
														View
													</Button>
												</TableCell>
											</TableRow>
										))
									) : (
										<TableRow
											hover
											key={customer.title}
											selected={selectedCustomerIds.indexOf(customer.id) !== -1}
										>
											<TableCell>
												<Box alignItems="center" display="flex">
													<Typography color="textPrimary" variant="body1">
														{customer.subjectCode}
													</Typography>
												</Box>
											</TableCell>
											<TableCell>
												<Box alignItems="center" display="flex">
													<Typography color="textPrimary" variant="body1">
														{customer.title}
													</Typography>
												</Box>
											</TableCell>
											<TableCell />
											<TableCell />
											<TableCell />
											<TableCell />
											<TableCell />
											<TableCell>
												<Button
													color="primary"
													variant="contained"
													onClick={(event) => handleOpen(event, customer)}
												>
													Add
												</Button>
											</TableCell>
										</TableRow>
									)
							)}
						</TableBody>
					</Table>
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
			<Dialog open={open} onClose={handleClose} aria-labelledby="max-width-dialog-title" maxWidth="lg" fullWidth>
				<DialogTitle id="form-dialog-title">New Exam</DialogTitle>
				<DialogContent>
					<Collapse in={!openGreen}>
						<DialogContentText>
							{new Date().toLocaleTimeString()}Please input the title for the new exam to the subject{' '}
							{values.subjectTitle}.
						</DialogContentText>
						<TextField
							id="outlined-multiline-flexible"
							required
							fullWidth
							value={values.title}
							onChange={handleChange('title')}
							label="Title"
							variant="outlined"
						/>
					</Collapse>
				</DialogContent>
				<DialogContent>
					<Collapse in={openAlert}>
						<Alert severity="error">{error}</Alert>
					</Collapse>
					<Collapse in={openGreen}>
						<Alert severity="success">Add New Exam Successfully!</Alert>
					</Collapse>
				</DialogContent>
				<DialogActions>
					<Collapse in={!openGreen}>
						<Button onClick={handleClose} color="primary">
							Cancel
						</Button>
					</Collapse>
					<Button onClick={AddNewExam} color="primary" type="submit">
						Confirm
					</Button>
				</DialogActions>
			</Dialog>
		</Card>
	);
};

Results.propTypes = {
	className: PropTypes.string,
	customers: PropTypes.array.isRequired
};

export default Results;

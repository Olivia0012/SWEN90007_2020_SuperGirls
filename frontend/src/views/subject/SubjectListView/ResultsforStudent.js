import React, { useState } from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import { addNewExam, addSubmission } from '../../../api/examAPI';
import PerfectScrollbar from 'react-perfect-scrollbar';
import { useNavigate } from 'react-router-dom';
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
import { getSubmission } from 'src/api/instructorAPI';

const useStyles = makeStyles((theme) => ({
	root: {},
	avatar: {
		marginRight: theme.spacing(2)
	}
}));

const ResultsforStudent = ({ className, customers, ...rest }) => {
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

	const handleTakeExam = async (status, exam, subjectId) => {
		if (status == 'PUBLISHED') {
			exam.creator = null;
			exam.subject.id = subjectId;
			await addSubmission(exam)
				.then((response) => {
					const result = response.data;
					if (response.data == false) {
						alert('Please login to continue.');
						window.location.href = '../';
					}

					if(response.data == -1){
						alert('You have taken this exam.');
					}else{
						window.location.href = './takeExam/submission=' + result;
					}

					
				})
				.catch((error) => {
					alert('Error from processDataAsycn() with async( When promise gets rejected ): ' + error);
				});
		} else {
			window.location.href = './viewResult/examId=' + exam.id;
		}
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
								<TableCell />
								<TableCell />
								<TableCell align="center" colSpan={2}>
									Exams
								</TableCell>
								<TableCell />
							</TableRow>
							<TableRow>
								<TableCell>Subject Code</TableCell>
								<TableCell>Subject Title</TableCell>
								<TableCell align="center">Title</TableCell>
								<TableCell>Status</TableCell>
								<TableCell />
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
												<TableCell key={customer.title}>
													<Box alignItems="center" display="flex">
														<Typography color="textPrimary" variant="body1">
															{index == 0 ? customer.subjectCode : ''}
														</Typography>
													</Box>
												</TableCell>
												<TableCell>{index == 0 ? customer.title : ''}</TableCell>
												<TableCell align="center">{item.title}</TableCell>
												<TableCell>{item.status}</TableCell>
												<TableCell>
													<Button
														color="primary"
														variant={item.status == 'PUBLISHED' ? 'contained' : 'outlined'}
														onClick={() => {
															handleTakeExam(item.status, item, customer.id);
														}}
													>
														{item.status == 'PUBLISHED' ? 'Take' : 'View'}
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
						<Alert severity="success">Successfully!</Alert>
					</Collapse>
				</DialogContent>
				<DialogActions>
					<Collapse in={!openGreen}>
						<Button onClick={handleClose} color="primary">
							Cancel
						</Button>
					</Collapse>
					<Button onClick={() => alert('confirm')} color="primary" type="submit">
						Confirm
					</Button>
				</DialogActions>
			</Dialog>
		</Card>
	);
};

ResultsforStudent.propTypes = {
	className: PropTypes.string,
	customers: PropTypes.array.isRequired
};

export default ResultsforStudent;

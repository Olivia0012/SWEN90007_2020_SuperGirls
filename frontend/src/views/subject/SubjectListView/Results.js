import React, { useState } from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import moment from 'moment';
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
	Button
} from '@material-ui/core';
import getInitials from '../../../utils/getInitials';

const useStyles = makeStyles((theme) => ({
	root: {},
	avatar: {
		marginRight: theme.spacing(2)
	}
}));

const Results = ({ className, customers, ...rest }) => {
	const classes = useStyles();
	const [ selectedCustomerIds, setSelectedCustomerIds ] = useState([]);
	const [ limit, setLimit ] = useState(10);
	const [ page, setPage ] = useState(0);

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

	const handleViewExam = (event) => {
		//	event.preventDefault();
		//	console.log(item);
		console.log(event);
		//	window.location.href="./exam?id="+item.id;
	};

	return (
		<Card className={clsx(classes.root, className)} {...rest}>
			<PerfectScrollbar>
				<Box minWidth={1050}>
					<Table>
						<TableHead>
							<TableRow>
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
								<TableCell>Subject Code</TableCell>
								<TableCell>Subject Title</TableCell>
								<TableCell>Exams</TableCell>
								<TableCell>New Exam</TableCell>
								<TableCell>Students</TableCell>
							</TableRow>
						</TableHead>
						<TableBody>
							{customers.slice(0, limit).map((customer) => (
								<TableRow
									hover
									key={customer.title}
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
												{customer.subjectCode}
											</Typography>
										</Box>
									</TableCell>
									<TableCell>{customer.title}</TableCell>
									<TableCell>
										{customer.exams.map((item) => (
											<Link href={'./exam/id=' + item.id}>
												{item.title}
												{item.id}
											</Link>
										))}
									</TableCell>
									<TableCell>
										<Button color="primary" variant="contained">
											Add
										</Button>
									</TableCell>
									<TableCell>
										<Button color="primary" variant="contained">
											View
										</Button>
									</TableCell>
								</TableRow>
							))}
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
		</Card>
	);
};

Results.propTypes = {
	className: PropTypes.string,
	customers: PropTypes.array.isRequired
};

export default Results;

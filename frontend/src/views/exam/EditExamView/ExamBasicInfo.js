import React, { useEffect } from 'react';
import PropTypes from 'prop-types';
import clsx from 'clsx';
import { getExams } from '../../../api/examAPI';

import { Box, Button, Card, TextField, CardContent, CardHeader, Divider, makeStyles, Grid } from '@material-ui/core';

import { grid } from '@material-ui/system';
import QuestionCard from './NewQuestion';

const useStyles = makeStyles({
	root: {},
	item: {
		display: 'flex',
		flexDirection: 'column'
	}
});

const Notifications = ({ className, ...rest }) => {
	const classes = useStyles();
	const [ values, setValues ] = React.useState({
		title: ''
	});
	const [ data, setData ] = React.useState('');

	const handleChange = (prop) => (event) => {
		setValues({ ...values, [prop]: event.target.value });
		//   qN(event.target.value);
	};

	useEffect(() => {
		const fetchData = async () => {
			//	setIsLoading(true);
			const result = await getExams();
			setData(result);
			console.log(result);
		};
		fetchData();
	}, []);

	return (
		<form className={clsx(classes.root, className)} {...rest}>
			<Card>
				<CardHeader
					title="[ SWEN90007 ] Software Architecture Desgin "
				/>
				<Divider />
				<CardContent>
					<Box display="flex" p={1}>
						<Grid Container xs={12}>
								<TextField
									id="outlined-multiline-flexible"
									required
									fullWidth
									value={values.title}
									onChange={handleChange('title')}
									placeholder="e.g. Exam 1 / Quiz 1"
									label="Exam Title"
									variant="outlined"
								/>
						</Grid>
					</Box>
				</CardContent>
			</Card>
		</form>
	);
};

Notifications.propTypes = {
	className: PropTypes.string
};

export default Notifications;

import React, { useState, useEffect } from 'react';
import { Box, Container, makeStyles } from '@material-ui/core';
import { useRoutes } from 'react-router-dom';
import Page from 'src/components/Page';
import Results from './Results';
import Toolbar from './Toolbar';
import routes from 'src/routes';
import { getAllStudentsBySubject_Exam } from '../../../api/instructorAPI';
import Loading from 'src/utils/loading';

const useStyles = makeStyles((theme) => ({
	root: {
		backgroundColor: theme.palette.background.dark,
		minHeight: '100%',
		paddingBottom: theme.spacing(3),
		paddingTop: theme.spacing(3)
	}
}));

const StudentListView = () => {
	const classes = useStyles();
	const [ data, setData ] = useState();
	const [ isLoading, setLoading ] = useState(false);
	const routeResult = useRoutes(routes);
//	console.log(routeResult.props.value.params.subject);

	useEffect(() => {
		const fetchData = async () => {
			setLoading(true);

			await getAllStudentsBySubject_Exam(
				routeResult.props.value.params.subject,
				routeResult.props.value.params.exam
			).then((response) => {
				console.log(response);
				const result = response.data;
				if (response.data == false) {
					alert('Please login to continue.');
					window.location.replace('/');
				}
				setData(result);
				setLoading(false);
			});
		};

		fetchData();
	}, []);

	console.log(data);
	return (
		<Page className={classes.root} title="Subjects">
			{!isLoading && data ? (
				<Container maxWidth={false}>
					<Toolbar subject={data.subject} exam={data.exam.title} status={data.examState} />
					<Box mt={3}>
						<Results customers={data.submissions} exam={data.exam}/>
					</Box>
				</Container>
			) : (
				<Loading />
			)}
		</Page>
	);
};

export default StudentListView;

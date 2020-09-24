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
	const [ data, setData ] = useState([]);
	const [ isLoading, setLoading ] = useState(false);
	const routeResult = useRoutes(routes);
	console.log(routeResult.props.value.params.subject);

	useEffect(() => {
		const fetchData = async () => {
			setLoading(true);
		//	const result = await getAllStudentsBySubjectId(routeResult.props.value.params.subject);
			const result = await getAllStudentsBySubject_Exam(routeResult.props.value.params.subject,routeResult.props.value.params.exam);
			setData(result.data);
			setLoading(false);
			console.log(data);
			console.log(isLoading);
		};

		fetchData();
	}, []);

	console.log(data);
	return (
		<Page className={classes.root} title="Customers">
			{!isLoading ? (
				<Container maxWidth={false}>
					<Toolbar />
					{//data.map((item) => (
						<Box mt={3}>
							<Results  customers={data} />
						</Box>
					//))
				}
				</Container>
			) : (
				<Loading />
			)}
		</Page>
	);
};

export default StudentListView;

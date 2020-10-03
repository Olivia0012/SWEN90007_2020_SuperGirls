import React, { useState, useEffect, createContext } from 'react';
import { Box, Container, makeStyles } from '@material-ui/core';
import Page from 'src/components/Page';
import Results from './Results';
import ResultsforStudent from './ResultsforStudent';
import Toolbar from './Toolbar';
import Loading from '../../../utils/loading';
import { getSubjectsByUserId } from '../../../api/instructorAPI';
import { useNavigate, useLocation, useRoutes } from 'react-router-dom';
import routes from 'src/routes';

const useStyles = makeStyles((theme) => ({
	root: {
		backgroundColor: theme.palette.background.dark,
		minHeight: '100%',
		paddingBottom: theme.spacing(3),
		paddingTop: theme.spacing(3)
	},
	backdrop: {
		zIndex: theme.zIndex.drawer + 1,
		color: '#fff'
	}
}));

export const SubjectContext = createContext();

const SubjectListView = () => {
	const navigate = useNavigate();
	const classes = useStyles();
	const [ data, setData ] = useState([]);
	const [ isLoading, setLoading ] = useState(false);
	const [ subjects, setSubjects ] = useState();
	const location = useLocation();
	const routeResult = useRoutes(routes);

	console.log(location);
	console.log(routeResult);

	useEffect(() => {
		const fetchData = async () => {
			setLoading(true);
			//	const token = location.state.token;
			const subjects = await getSubjectsByUserId().then((response) => {
				console.log(response);
				const result = response.data;
				if (response.data == false) {
					alert('Please login to continue.');
					navigate('/', { replace: true });
					//	window.location.href="../";
				} else {
					// if the user is a student
					if (response.data.user.role == 'STUDENT') {
						console.log(response.data);
						response.data.subjects.map((subject) => {
							for (var i = subject.exams.length - 1; i >= 0; i--) {
								console.log(i + '=' + subject.exams[i]);
								if (subject.exams[i].status !== 'PUBLISHED' && subject.exams[i].status !== 'RELEASED') {
									subject.exams.splice(i, 1);
								}
							}
						});
					}

					setData(response.data);
					setSubjects(response.data.subjects);
					setLoading(false);
					console.log(response.data);
					console.log(isLoading);
				}
			});
		};

		fetchData();
	}, []);

	console.log(data);

	return (
		<Page className={classes.root} title="Subjects">
			<Container maxWidth={false}>
				<Toolbar userName={data.user ? data.user.userName : ''} />
				{!isLoading ? (
					<SubjectContext.Provider value={(subjects, setSubjects)}>
						<Box mt={3}>
							{typeof data.user !== 'undefined' ? data.user.role === 'INSTRUCTOR' ? (
								<Results customers={data.subjects} />
							) : (
								<ResultsforStudent customers={data.subjects} />
							) : (
								<div />
							)}
						</Box>
					</SubjectContext.Provider>
				) : (
					<Loading isLoading={isLoading} />
				)}
			</Container>
		</Page>
	);
};

export default SubjectListView;

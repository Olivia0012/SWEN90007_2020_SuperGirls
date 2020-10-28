import React, { useState, useEffect, createContext } from 'react';
import { Box, Container, makeStyles } from '@material-ui/core';
import Page from 'src/components/Page';
import Results from './Results';
import Toolbar from './Toolbar';
import Loading from '../../../utils/loading';
import { getAllSubjects } from '../../../api/instructorAPI';

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


const SubjectManagement = () => {
	const classes = useStyles();
	const [ data, setData ] = useState([]);
	const [ isLoading, setLoading ] = useState(false);
	const [ subjects, setSubjects ] = useState();

	useEffect(() => {
		const fetchData = async () => {
			setLoading(true);
			const result = await getAllSubjects()
				.then((res) => {
					if (res.data == false) {
						alert('Please login to continue.');
						window.location.replace('/');
					} else {
						setData(res.data);
						setSubjects(res.data);
						setLoading(false);
						console.log(res.data);
					}
				})
				.catch((error) => {
					alert(error + 'Please login to continue.');
					window.location.replace('/');
				});
		};

		fetchData();
	}, []);

	console.log(data);

	return (
		<Page className={classes.root} title="Customers">
			<Container maxWidth={false}>
				<Toolbar />
				{!isLoading ? (
						<Box mt={3}>
							<Results subjects={data} />
						</Box>
				) : (
					<Loading isLoading={isLoading} />
				)}
			</Container>
		</Page>
	);
};

export default SubjectManagement;

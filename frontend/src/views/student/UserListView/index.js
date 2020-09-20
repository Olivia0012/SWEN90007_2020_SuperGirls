import React, { useState, useEffect } from 'react';
import { Box, Container, makeStyles } from '@material-ui/core';
import Page from 'src/components/Page';
import Results from './Results';
import Toolbar from './Toolbar';
import { getAllStudentsBySubjectId } from '../../../api/instructorAPI';

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

	useEffect(() => {
		const fetchData = async () => {
			setLoading(true);
			const result = await getAllStudentsBySubjectId(1);
			setData(result.data);
			setLoading(false);
			console.log(data);
			console.log(isLoading);
		};

		fetchData();
	}, []);

	return (
		<Page className={classes.root} title="Customers">
			<Container maxWidth={false}>
				<Toolbar />
				{data.map((item) => (
					<Box mt={3}>
						<Results exam={item} customers={data}/>
					</Box>
				))}
			</Container>
		</Page>
	);
};

export default StudentListView;
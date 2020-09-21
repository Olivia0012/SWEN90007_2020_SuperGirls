import React, { useState, useEffect, createContext } from 'react';
import { Box, Container, makeStyles } from '@material-ui/core';
import Page from 'src/components/Page';
import Results from './Results';
import Toolbar from './Toolbar';
import Loading from '../../../utils/loading'
import { getSubjectsByUserId } from '../../../api/examAPI';

const useStyles = makeStyles((theme) => ({
	root: {
		backgroundColor: theme.palette.background.dark,
		minHeight: '100%',
		paddingBottom: theme.spacing(3),
		paddingTop: theme.spacing(3)
	},
	backdrop: {
		zIndex: theme.zIndex.drawer + 1,
		color: '#fff',
	  },
}));

export const SubjectContext = createContext();

const SubjectListView = () => {
	const classes = useStyles();
	const [ data, setData ] = useState([]);
	const [ isLoading, setLoading ] = useState(false);
	const [ subjects, setSubjects ] = useState();

	useEffect(() => {
		const fetchData = async () => {
			setLoading(true);
			const result = await getSubjectsByUserId(4);
			setData(result.data);
			setSubjects(result.data);
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
				{!isLoading ? (
					<SubjectContext.Provider value={(subjects, setSubjects)}>
						<Box mt={3}>
							<Results customers={data} />
						</Box>
					</SubjectContext.Provider>
				) : (
					<Loading isLoading={isLoading}/>
				)}
			</Container>
		</Page>
	);
};

export default SubjectListView;

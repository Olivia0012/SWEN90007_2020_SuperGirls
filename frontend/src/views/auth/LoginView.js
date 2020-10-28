import React from 'react';
import { Link as RouterLink, useNavigate } from 'react-router-dom';
import CryptoJS from 'crypto-js';
import * as Yup from 'yup';
import { Formik } from 'formik';
import {
	Box,
	Button,
	Container,
	Grid,
	Link,
	TextField,
	Typography,
	makeStyles,
	TableHead,
	TableRow,
	TableCell,
	Table,
	TableBody
} from '@material-ui/core';
import Page from 'src/components/Page';
import { login } from '../../api/instructorAPI';

export let token = '';

const useStyles = makeStyles((theme) => ({
	root: {
		backgroundColor: theme.palette.background.dark,
		height: '100%',
		paddingBottom: theme.spacing(3),
		paddingTop: theme.spacing(3)
	}
}));

export let loginUser = {
	avatar: '/static/images/avatars/avatar_6.png',
	jobTitle: 'Administrator',
	name: 'Olivia'
};

const LoginView = () => {
	const classes = useStyles();
	const navigate = useNavigate();

	const handleSubmit = async (auth) => {
		var key = '1538663015386630';
		const secretKey = CryptoJS.enc.Utf8.parse(key);
		let pwd = CryptoJS.AES.encrypt(auth.password, secretKey, {  
			mode: CryptoJS.mode.ECB,  
			padding: CryptoJS.pad.Pkcs7  
		});
		//	let token;
		await login(auth.email, pwd); //.then(response=>{
		/*	token = response.headers.token;
			const userInfo = response;
			console.log(token);
			console.log(response.data);
			if(response.data !== false){
				const authUser = {
					token:token,
					user: userInfo.data
				};
				navigate('./oea', {
					replace: true, state: authUser
				});
			//	window.location.href="./oea";
			}else{
				alert("Username or password is not correct, please try again.")
			}
		});*/
	};

	return (
		<Page className={classes.root} title="Login">
			<Box display="flex" flexDirection="column" height="100%" justifyContent="center">
				<Container maxWidth="sm">
					<Box mb={6}>
						<Typography align="center" color="primary" variant="h1">
							Online Exam Application
						</Typography>
					</Box>
					<Formik
						initialValues={{
							email: 'Edu',
							password: '111'
						}}
						validationSchema={Yup.object().shape({
							email: Yup.string().max(255).required('Username is required'),
							password: Yup.string().max(255).required('Password is required')
						})}
						onSubmit={(auth) => handleSubmit(auth)}
					>
						{({ errors, handleBlur, handleChange, handleSubmit, isSubmitting, touched, values }) => (
							<form onSubmit={handleSubmit}>
								<Box mb={1}>
									<Typography color="textSecondary" variant="h3">
										Sign In
									</Typography>
								</Box>
								<Grid container spacing={3} />
								<Box mt={3} mb={1} />
								<TextField
									error={Boolean(touched.email && errors.email)}
									fullWidth
									helperText={touched.email && errors.email}
									label="User Name"
									margin="normal"
									name="email"
									onBlur={handleBlur}
									onChange={handleChange}
									value={values.email}
									variant="outlined"
								/>
								<TextField
									error={Boolean(touched.password && errors.password)}
									fullWidth
									helperText={touched.password && errors.password}
									label="Password"
									margin="normal"
									name="password"
									onBlur={handleBlur}
									onChange={handleChange}
									type="password"
									value={values.password}
									variant="outlined"
								/>
								<Box my={2}>
									<Button
										color="primary"
										disabled={isSubmitting}
										fullWidth
										size="large"
										type="submit"
										variant="contained"
									>
										Sign in now
									</Button>
								</Box>
								<Typography color="textSecondary" variant="body1">
									Don&apos;t have an account?{' '}
									<Link component={RouterLink} to="/register" variant="h6">
										Sign up
									</Link>
								</Typography>
							</form>
						)}
					</Formik>
					<Box mb={1} />
					<Typography align="center" color="textSecondary" variant="body1">
						<br />Account for testing<br />
						<Box>
							<Table size="small">
								<TableHead>
									<TableRow>
										<TableCell>Role</TableCell>
										<TableCell>User Name</TableCell>
										<TableCell>Password</TableCell>
									</TableRow>
								</TableHead>
								<TableBody>
									<TableRow>
										<TableCell>ADMIN</TableCell>
										<TableCell>admin</TableCell>
										<TableCell>111</TableCell>
									</TableRow>
									<TableRow>
										<TableCell>INSTRUCTOR</TableCell>
										<TableCell>Edu</TableCell>
										<TableCell>111</TableCell>
									</TableRow>
									<TableRow>
										<TableCell />
										<TableCell>Maria</TableCell>
										<TableCell>111</TableCell>
									</TableRow>
									<TableRow>
										<TableCell>STUDENT</TableCell>
										<TableCell>Olivia</TableCell>
										<TableCell>111</TableCell>
									</TableRow>
									<TableRow>
										<TableCell />
										<TableCell>Susan</TableCell>
										<TableCell>111</TableCell>
									</TableRow>
									<TableRow>
										<TableCell />
										<TableCell>Lucy</TableCell>
										<TableCell>111</TableCell>
									</TableRow>
								</TableBody>
							</Table>
						</Box>
					</Typography>
				</Container>
			</Box>
		</Page>
	);
};

export default LoginView;

import React from 'react';
import { Link as RouterLink,useNavigate } from 'react-router-dom';
import * as Yup from 'yup';
import { Formik } from 'formik';
import { Box, Button, Container, Grid, Link, TextField, Typography, makeStyles } from '@material-ui/core';
import Page from 'src/components/Page';
import { login } from '../../api/instructorAPI';
const axios = require('axios');

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
		//	let token;
		await login(auth.email, auth.password).then((res) => {
		//	alert(res.headers.token);
			if (
				res.headers.token !== 'undefined' &&
				res.headers.token !== null &&
				typeof res.headers.token !== 'undefined'
			) {
				localStorage.setItem('token', res.headers.token);
				navigate('/oea', { replace: true });
			} else {
				alert('Login failed, please try again later.');
			}

			// 登录成功后，将token存储到localStorage中
			//	storage.token = response.headers.token;
			// 设置以后的请求配置：把token放在请求头中(不需要每次传入用户名和密码)
		/*	axios.interceptors.request.use(
				function(config) {
					config.withCredentials = true;
					config.headers = {
						token: res.headers.token
					};
					return config;
				},
				function(error) {
					return Promise.reject(error);
				}
			);*/
		});
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
					<Box mb={3} />
					<Typography align="center" color="default" variant="body1">
						Account for testing:
					</Typography>
					<Typography align="center" color="textSecondary" variant="body1">
						<br />INSTRUCTOR<br />
						Username: Edu <br />Password: 111
						<br />
						<br />STUDENT
						<br />
						Username: Olivia<br /> Password: 111
					</Typography>
				</Container>
			</Box>
		</Page>
	);
};

export default LoginView;

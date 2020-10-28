const axios = require('axios');
axios.defaults.withCredentials = true;

//login
export async function login(username, password) {
	window.localStorage.clear();
	let storage = window.localStorage;
	storage.setItem('token', null);
	const endpoint = '/login?userName=' + username + '&passWord=' + password; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
			withCredentials: 'true'
		}
	}).then((res) => {
		console.log(res);
		if (res.data === false) {
			window.location.href = '/';
		} else {
			if (res.data.role === 'ADMIN') {
				window.location.href = '/admin';
			} else {
				window.location.href = '/oea';
			}
		}
		localStorage.setItem('token', res.headers.token);
		localStorage.setItem('role', res.data.role);
		localStorage.setItem('name', res.data.userName);

		// 登录成功后，将token存储到localStorage中
		//	storage.token = response.headers.token;
		// 设置以后的请求配置：把token放在请求头中(不需要每次传入用户名和密码)
		axios.interceptors.request.use(
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
		);
	});
	console.log(dataFetched);
	return dataFetched;
}

//logout
export async function logout(token) {
	const endpoint = '/logout'; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			withCredentials: 'true',
			token: localStorage.getItem('token')
		}
	});
	window.localStorage.clear();
	console.log(dataFetched);
	return dataFetched;
}

//fetch all exams by subjectId
export async function getAllStudentsBySubjectId(subjectId) {
	const endpoint = `/student?subjectId=` + subjectId; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'GET',
		headers: {
			withCredentials: true,
			'Content-Type': 'application/json',
			token: localStorage.getItem('token')
		}
		//	data: JSON.stringify((JSON.parse(surveyInfo))),
	});
	console.log(dataFetched);
	return dataFetched;
}

//fetch all exams by subjectId
export async function getAllStudentsBySubject_Exam(subjectId, examId) {
	const endpoint = `/student?subject=` + subjectId + `&exam=` + examId; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'GET',
		headers: {
			withCredentials: true,
			'Content-Type': 'application/json',
			token: localStorage.getItem('token')
		}
		//	data: JSON.stringify((JSON.parse(surveyInfo))),
	});
	console.log(dataFetched);
	return dataFetched;
}

export async function getSubmission(submissionId) {
	//	const endpoint = '/subject?userName=Edu&passWord=111';//?userId='+userId;//subjectId=`+subjectId;
	const endpoint = '/submission?id=' + submissionId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'GET',
		mode: 'cors',
		//请求时添加Cookie
		credentials: 'include',
		crossDomain: true,
		headers: {
			'Content-Type': 'application/json',
			token: localStorage.getItem('token')
			//  "Access-Control-Allow-Origin": "http://localhost:8080",
		}
	});
	console.log(dataFetched);
	return dataFetched;
}

export async function markExam(submissions) {
	const endpoint = '/markExam';
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'POST',
		mode: 'cors',
		//请求时添加Cookie
		credentials: 'include',
		crossDomain: true,
		headers: {
			'Content-Type': 'application/json',
			token: localStorage.getItem('token')
		},
		data: JSON.stringify(submissions)
	});
	console.log(dataFetched);
	return dataFetched;
}

//edit total mark
export async function editTotalMark(submission) {
	const endpoint = '/edittotalmark';
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'POST',
		mode: 'cors',
		//请求时添加Cookie
		credentials: 'include',
		crossDomain: true,
		headers: {
			'Content-Type': 'application/json',
			token: localStorage.getItem('token')
		},
		data: JSON.stringify(submission)
	});
	console.log(dataFetched);
	return dataFetched;
}



export async function getSubjectsByUserId(token) {
	const endpoint = '/subject'; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'GET',
		headers: {
			withCredentials: true,
			'Content-Type': 'application/json',
			token: localStorage.getItem('token')
		}
	});
	console.log(dataFetched);
	return dataFetched;
}
/**
 * Admin functions
 */

// admin get all subjects
export async function getAllSubjects() {
	const endpoint = '/admin/subjects'; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'GET',
		headers: {
			withCredentials: true,
			'Content-Type': 'application/json',
			token: localStorage.getItem('token')
		}
	});
	return dataFetched;
}

// admin add new subjects
export async function addSubject(subject) {
	const endpoint = '/admin/addsubject'; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'POST',
		headers: {
			withCredentials: true,
			'Content-Type': 'application/json',
			token: localStorage.getItem('token')
		},
		data: JSON.stringify(subject)
	});
	console.log(dataFetched);
	return dataFetched;
}

// admin search users
export async function getUsers(userType,subjectId) {
	const endpoint = '/admin/users?userType='+userType+'&subjectId='+subjectId; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'GET',
		headers: {
			withCredentials: true,
			'Content-Type': 'application/json',
			token: localStorage.getItem('token')
		}
	});
	console.log(dataFetched);
	return dataFetched;
}

// admin add new user
export async function addUser(user) {
	const endpoint = '/admin/adduser'; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'POST',
		headers: {
			withCredentials: true,
			'Content-Type': 'application/json',
			token: localStorage.getItem('token')
		},
		data: JSON.stringify(user)
	});
	console.log(dataFetched);
	return dataFetched;
}

// admin add users to subject
export async function addUserInSubject(users, subjectId,role) {
	const endpoint = '/admin/userinsubject?subjectId='+subjectId+'&role='+role; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'POST',
		headers: {
			withCredentials: true,
			'Content-Type': 'application/json',
			token: localStorage.getItem('token')
		},
		data: JSON.stringify(users)
	});
	console.log(dataFetched);
	return dataFetched;
}

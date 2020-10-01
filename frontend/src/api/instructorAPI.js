const axios = require('axios');
axios.defaults.withCredentials = true;

const proxy = `https://online-exam-app-supergirls.herokuapp.com`;

//login
export async function login(username, password) {
	console.log(proxy);
	console.log(username);
	let storage = window.localStorage;
	storage.setItem('token', null);
	const endpoint = `/login?userName=` + username + `&passWord=` + password; //subjectId=`+subjectId;
	console.log(endpoint);
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			withCredentials: 'true',
			'Access-Control-Allow-Origin':'*'
		}
	}).then( res => {
		if(res.headers.token  !== 'undefined' || res.headers.token  !== null || typeof res.headers.token  !== 'undefined'){
		   window.location.href = "./oea";
		}
		localStorage.setItem('token', res.headers.token);
		// 登录成功后，将token存储到localStorage中
	//	storage.token = response.headers.token;
		// 设置以后的请求配置：把token放在请求头中(不需要每次传入用户名和密码)
		axios.interceptors.request.use(function(config) {
		  config.withCredentials = true
		  config.headers = {
			token : res.headers.token
		  }
		  return config;
		}, function (error) {
		  return Promise.reject(error);
		});
	  });
	console.log(dataFetched);
	return dataFetched;
}

//logout
export async function logout(token) {
	const endpoint = '/api/logout'; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			withCredentials: 'true',
			'token':localStorage.getItem("token")
		}
	});
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
			'token':localStorage.getItem("token")
		}
		//	data: JSON.stringify((JSON.parse(surveyInfo))),
	});
	console.log(dataFetched);
	return dataFetched;
}

//fetch all exams by subjectId
export async function getAllStudentsBySubject_Exam(subjectId,examId) {
	const endpoint = `/student?subject=` + subjectId+`&exam=`+examId; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'GET',
		headers: {
			withCredentials: true,
			'Content-Type': 'application/json',
			'token':localStorage.getItem("token")
		}
		//	data: JSON.stringify((JSON.parse(surveyInfo))),
	});
	console.log(dataFetched);
	return dataFetched;
}



export async function getSubmission(submissionId) {
	//	const endpoint = '/subject?userName=Edu&passWord=111';//?userId='+userId;//subjectId=`+subjectId;
	const endpoint = '/submission?id='+submissionId;
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
			'token':localStorage.getItem("token")
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
			'token':localStorage.getItem("token")
		},
		data: JSON.stringify(submissions),
	});
	console.log(dataFetched);
	return dataFetched;
}

export async function getSubjectsByUserId(token) {
	const endpoint = "/api/subject"; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'GET',
		headers: {
			'withCredentials' : true,
			'Content-Type': 'application/json',
			'token':localStorage.getItem("token")
		}
	});
	console.log(dataFetched);
	return dataFetched;
}

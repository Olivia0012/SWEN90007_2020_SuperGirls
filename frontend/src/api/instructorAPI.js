//import { useNavigate } from 'react-router-dom';


const axios = require('axios');
axios.defaults.withCredentials = true;


const proxy = `https://online-exam-app-supergirls.herokuapp.com`;
//const navigate = useNavigate();
//login
export async function login(username, password) {
	console.log(proxy);
	console.log(username);
	let storage = window.localStorage;
	storage.setItem('token', null);
	const endpoint = `/api/login?userName=` + username + `&passWord=` + password; //subjectId=`+subjectId;
	console.log(endpoint);
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			withCredentials: 'true',
			'Access-Control-Allow-Origin':'*'
		}
	})
	console.log(dataFetched);
	localStorage.setItem('token', dataFetched.headers.token);
	return dataFetched;
}

//logout
export async function logout(token) {
//	let storage = window.localStorage;
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
//	storage.setItem('token', null);
	console.log(dataFetched);
	return dataFetched;
}

//fetch all exams by subjectId
export async function getAllStudentsBySubjectId(subjectId) {
	const endpoint = `/api/student?subjectId=` + subjectId; //subjectId=`+subjectId;
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
	const endpoint = `/api/student?subject=` + subjectId+`&exam=`+examId; //subjectId=`+subjectId;
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
	const endpoint = '/api/submission?id='+submissionId;
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

	const endpoint = '/api/markExam';
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
//	console.log(dataFetched.config.data);
	return dataFetched;
}

export async function getSubjectsByUserId() {
	console.log(localStorage.getItem("token"));
	const token = localStorage.getItem("token");
	const endpoint = "/api/subject"; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'GET',
		headers: {
			'withCredentials' : true,
			'Content-Type': 'application/json',
			'token':token//localStorage.getItem("token")
		}
	});
	console.log(dataFetched);
	return dataFetched;
}

const axios = require('axios');
const PROXY_URL = 'http://127.0.0.1:8080/SWEN90007_2020_SuperGirls/';




//fetch all exams by subjectId
export async function getExams(examId) {
	const endpoint = `/exam?examId=` + examId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			'token':localStorage.getItem("token")
		}
		//		data: "JSON.stringify((JSON.parse(surveyInfo)))",
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

export async function addNewQuestion(newQuestion) {
	const endpoint = '/exam/addnewquestion';
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		method: 'POST', // HTTP POST method
		mode:'cors',
		headers: {
			'Content-Type': 'application/json',
			'token':localStorage.getItem("token")
		},
		data: JSON.stringify(newQuestion),
	});
	console.log(dataFetched);
	return dataFetched;
}

export async function deleteQuestion(questionId) {
	const endpoint = '/question/delete?questionId='+questionId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		method: 'GET', // HTTP POST method
		mode:'cors',
		headers: {
			'Content-Type': 'application/json',
			'token':localStorage.getItem("token")
		},
	//	data: JSON.stringify(question),
	});
	console.log(dataFetched);
	return dataFetched;
}

export async function addNewExam(newExam) {
	const endpoint = '/addexam';
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		method: 'POST', // HTTP POST method
		mode:'cors',
		headers: {
			'Content-Type': 'application/json',
			'token':localStorage.getItem("token")
		},
		data: JSON.stringify(newExam),
	});
	console.log(dataFetched);
	return dataFetched;
}

export async function deleteExam(examId) {
	const endpoint = '/exam/delete?examId='+examId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		method: 'GET', // HTTP POST method
		mode:'cors',
		headers: {
			'Content-Type': 'application/json',
			'token':localStorage.getItem("token")
		},
	//	data: JSON.stringify(question),
	});
	console.log(dataFetched);
	return dataFetched;
}

export async function editExam(exam) {
	const endpoint = '/editExam';
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
			//  "Access-Control-Allow-Origin": "http://localhost:8080",
		},
		data: JSON.stringify(exam),
	});
	console.log(dataFetched);
	return dataFetched;
}


export async function setExamStatus(examId) {
	const endpoint = '/publishExam?id='+examId;
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
		},
	});
	console.log(dataFetched);
	return dataFetched;
}


export async function submitExam(submission) {
	const endpoint = '/takeExam';
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
			//  "Access-Control-Allow-Origin": "http://localhost:8080",
		},
		data: JSON.stringify(submission),
	});
	console.log(dataFetched);
	return dataFetched;
}

export async function addSubmission(submission) {
	const endpoint = '/addsubmission';
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
			//  "Access-Control-Allow-Origin": "http://localhost:8080",
		},
		data: JSON.stringify(submission),
	});
	console.log(dataFetched);
	return dataFetched;
}

export async function viewResult(examId) {
	//	const endpoint = '/subject?userName=Edu&passWord=111';//?userId='+userId;//subjectId=`+subjectId;
	const endpoint = '/examResult?examId='+examId;
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


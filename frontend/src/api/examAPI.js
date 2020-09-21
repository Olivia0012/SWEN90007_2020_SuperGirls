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
			'Content-Type': 'application/json'
		}
		//		data: "JSON.stringify((JSON.parse(surveyInfo)))",
	});
	console.log(dataFetched);
	return dataFetched;
}

export async function getSubjectsByUserId(userId) {
	const endpoint = `/subject?userId=` + userId; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'GET',
		headers: {
			'Content-Type': 'application/json'
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
			'Content-Type': 'application/json'
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
			'Content-Type': 'application/json'
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
		},
	//	data: JSON.stringify(question),
	});
	console.log(dataFetched);
	return dataFetched;
}
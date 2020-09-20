import Qs from 'qs'
const axios = require('axios');
const PROXY_URL = 'http://127.0.0.1:8080/SWEN90007_2020_SuperGirls/';
//const URL = 'https://api/entries';

//fetch all exams by subjectId
export async function getExams(examId) {
	const endpoint = `http://127.0.0.1:8080/SWEN90007_2020_SuperGirls/exam?examId=` + examId;
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
	const endpoint = `http://127.0.0.1:8080/SWEN90007_2020_SuperGirls/subject?userId=` + userId; //subjectId=`+subjectId;
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
	const PROXY_URL = 'https://cors-anywhere.herokuapp.com/';
	const endpoint = `http://127.0.0.1:8080/SWEN90007_2020_SuperGirls/exam/addnew`;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		method: 'POST', // HTTP POST method
		headers: {
			'Access-Control-Allow-Origin': '*',
			'Content-Type': 'application/json'
		},
		data: Qs.stringify({ a: 1 })
	});
	console.log(dataFetched);
	return dataFetched;
}

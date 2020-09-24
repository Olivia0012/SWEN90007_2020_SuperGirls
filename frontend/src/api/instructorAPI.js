const axios = require('axios');
axios.defaults.withCredentials = true;

//login
export async function login(username, password) {
	const endpoint = '/login?userName=' + username + '&passWord=' + password; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			withCredentials: 'true'
		}
	});
	console.log(dataFetched);
	return dataFetched;
}

//logout
export async function logout(sessionId) {
	const endpoint = '/logout?user=' + sessionId; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			withCredentials: 'true'
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
			'Content-Type': 'application/json'
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
			'Content-Type': 'application/json'
		}
		//	data: JSON.stringify((JSON.parse(surveyInfo))),
	});
	console.log(dataFetched);
	return dataFetched;
}

export async function getSubjectsByUserId(userId) {
	//	const endpoint = '/subject?userName=Edu&passWord=111';//?userId='+userId;//subjectId=`+subjectId;
	const endpoint = '/checklogin';
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		//		method: "POST", // HTTP POST method
		method: 'GET',
		mode: 'cors',
		//请求时添加Cookie
		credentials: 'include',
		crossDomain: true,
		headers: {
			'Content-Type': 'application/json'
			//  "Access-Control-Allow-Origin": "http://localhost:8080",
		}
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
			'Content-Type': 'application/json'
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
			'Content-Type': 'application/json'
			//  "Access-Control-Allow-Origin": "http://localhost:8080",
		},
		data: JSON.stringify(submissions),
	});
	console.log(dataFetched);
	return dataFetched;
}

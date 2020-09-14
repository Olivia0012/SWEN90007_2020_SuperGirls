const axios = require('axios');

//fetch all exams by subjectId
export async function getExams(subjectId){
	const endpoint = `http://127.0.0.1:8080/SWEN90007_2020_SuperGirls/exam/viewone`;//subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
//		method: "POST", // HTTP POST method
		method: "GET", 
		headers: {
		  "Content-Type": "application/json",
		},
	//	data: JSON.stringify((JSON.parse(surveyInfo))),
	  });
	  console.log(dataFetched);
      return dataFetched;
}
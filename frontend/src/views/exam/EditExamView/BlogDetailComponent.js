import React, { createContext, useContext } from 'react';
import KeyboardArrowDownIcon from '@material-ui/icons/KeyboardArrowDown';
import {CountContext} from './index'
import t from './index'
 
 export default function BlogDetailComponent() {
    const fatherName= useContext(CountContext); 
    console.log(t)
	console.log(fatherName)
	return (
	  <div>
		<h3>React Blog Details</h3>
		<p>Author: {fatherName.eact.post}</p>
	  </div>
	);
  }
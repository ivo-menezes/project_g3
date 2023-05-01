import React, { useContext } from "react";
import AppContext from '../context/AppContext';

import {Link, useParams} from "react-router-dom";

import Header from "../components/header";
import Table from "../components/table";
import Button from "../components/button";


const ConsultBacklog = () => {

    // getting the projectCode from the URL match (this is a function of react-router-dom)
    const { projectCode } = useParams();

    // finding backlog for project from context
    // in the future this will be an API call with useEffect
    const { state } = useContext(AppContext);
    const backlog = state.backlogs.find(project => project.projectCode === projectCode).backlog;

    // getting headers for table
    // Table component expects headers formatted as an object in a specific way
    /*
    const formattedHeaders = {
        header1 : {label: "Number"},
        header2 : {label: "Text"},
        header3 : {label: "Status"},
        header4 : {label: ""}
    }
    */
    const headers = Object.keys(backlog[0]);
    const headersArray = headers.map((header_string, index) => {
        return {["header"+index] : {label : header_string}}
    })
    var formattedHeaders = Object.assign({}, ...headersArray)
    formattedHeaders = {...formattedHeaders, header4 : {label : ""}}

    return (
        <div>
            <Header className='header-listProjects' text={`Backlog for Project ${projectCode}`}/>
            <Table data={backlog} headers={formattedHeaders}/>
            <div className='bt-container '>
            <Link to='/createUserStory'>
                <Button className='button-ListProjects' name='Add UserStory'/>
            </Link>
            <Link to='/listProjects'>
                <Button className='button-ListProjects' name='Back to Project'/>
            </Link>
        </div>
        </div>
    )
}

export default ConsultBacklog;

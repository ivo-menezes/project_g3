import React, { useContext } from "react";
import AppContext from '../context/AppContext';
import {Link, useParams} from "react-router-dom";
import Header from "../components/header";
import Table from "../components/table";
import Button from "../components/button";

const headers = [
    {label: "Number", key: "id"},
    {label: "Text", key: "text" },
    {label: "Status", key: "status"},
]

const ConsultBacklog = () => {

    // getting the projectCode from the URL match (this is a function of react-router-dom)
    const { projectCode } = useParams();

    // finding backlog for project from context
    // in the future this will be an API call with useEffect

    const { state } = useContext(AppContext);
    const backlogForProject = state.backlogs.find(project => project.projectCode === projectCode);
    const backlog = backlogForProject ? backlogForProject.backlog : undefined;

    return (
        <div>
            <Header className='header-listProjects' text={`Project backlog`}/>
            {backlog ? (
                <>
                    <Table data={backlog} headers={headers}/>
                </>
            ) : (
                <div className="string-format">
                <h2>This project has an empty backlog!</h2>
                </div>
            )}
            <div className='bt-container '>
            <Link to='/createUserStory'>
                <Button className='button-ListProjects' name='Create UserStory'/>
            </Link>
            <Link to='/listProjects'>
                <Button className='button-ListProjects' name='Back to Project List'/>
            </Link>
        </div>
        </div>
    )
}

export default ConsultBacklog;











// getting headers for table
// Table component expects headers formatted as an object in a specific way

/* const headers = Object.keys(backlog[0]);
 const headersArray = headers.map((header_string, index) => {
     return {["header"+index] : {label : header_string}}
 })
 var formattedHeaders = Object.assign({}, ...headersArray)
 formattedHeaders = {...formattedHeaders, header4 : {label : ""}} */
import React, { useContext } from "react";
import AppContext from '../context/AppContext';
import {Link, useNavigate, useParams, useLocation} from "react-router-dom";
import Header from "../components/header";
import Table from "../components/table";
import Button from "../components/button";

const headers = [
    {label: "Project Code", key: "projectCode"},
    {label: "Number", key: "number"},
    {label: "Actor", key: "actor"},
    {label: "Description", key: "description" },
    {label: "Status", key: "status"},
    {label: "Priority", key: "priority"},
    {label: "Acceptance Criteria", key: "ac"},
]

const ConsultBacklog = () => {

    // getting the projectCode from the URL match (this is a function of react-router-dom)
    const { projectCode } = useParams();
    const navigate = useNavigate();
    const location = useLocation();

    // finding backlog for project from context
    // in the future this will be an API call with useEffect

    const { state } = useContext(AppContext);

    const backlogForProject = state.backlogs.filter((backlog) => backlog.projectCode === projectCode);

    const handleCreateUS = () => {
        const from = location.pathname;
        navigate(`/createUserStory/${projectCode}`, { state: { from } });
    }

    const handleRowClick = (id) => {
        navigate(`/viewProject/${id}`);
    };


    /*const backlogForProject = state.backlogs.find(project => project.projectCode === projectCode);
    const backlog = backlogForProject ? backlogForProject : undefined;*/

    return (
        <div>
            <Header
                className='header-listProjects'
                text={`Backlog for Project ${projectCode}`}
            />
            {backlogForProject.length > 0 ? (
                <Table data={backlogForProject} headers={headers}/>
            ) : (
                <div className="string-format">
                    <h2>This project has an empty backlog!</h2>
                </div>
            )}
            <div className='bt-container '>
                <Button
                        className='button-ListProjects'
                        name='Create UserStory'
                        onClick={handleCreateUS}
                />
                <Button
                    className='button-ListProjects'
                    name= "Back to Project"
                    onClick={() => handleRowClick(projectCode)}/>
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
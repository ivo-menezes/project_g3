import React, {useContext, useEffect} from "react";
import AppContext from '../context/AppContext';
import {useLocation, useNavigate, useParams} from "react-router-dom";
import Header from "../components/header";
import Table from "../components/table";
import Button from "../components/button";
import {fetchBacklog} from "../context/Actions";

const headers = [
    {label: "Project Code", key: "projectCode"},
    {label: "Number", key: "number"},
    {label: "Actor", key: "actor"},
    {label: "Description", key: "description"},
    {label: "Status", key: "status"},
    {label: "Priority", key: "priority"},
    {label: "Acceptance Criteria", key: "ac"},
]

const ConsultBacklog = () => {

    const {state, dispatch} = useContext(AppContext);

    // getting the projectCode from the URL match (this is a function of react-router-dom)
    const {projectCode} = useParams();

    //The effect scrolls the window to the top of the page, ensuring that the header and the top portion
    //of the content are visible when rendering the page.
    useEffect(() => {
        window.scrollTo(0, 0);

        // calling the fetchBacklog action that fetches the backlog for the given project from backend
        fetchBacklog(dispatch, projectCode);
    }, []);

    const backlogForProject = state.backlogs.data;
    // table component renders columns in the same order as the object properties...
    // to match the headers, need to sort the object properties
    const sortedBacklog = JSON.parse(JSON.stringify(backlogForProject, ["projectCode", "userStoryNumber", "actor", "description", "status", "priority", "acceptanceCriteria"]))

    const navigate = useNavigate();
    const location = useLocation();

    const handleCreateUS = () => {
        const from = location.pathname;
        navigate(`/createUserStory/${projectCode}`, {state: {from}});
    }

    const handleRowClick = (id) => {
        navigate(`/viewProject/${id}`);
    };


    /*const backlogForProject = state.backlogs.find(project => project.projectCode === projectCode);
    const backlog = backlogForProject ? backlogForProject : undefined;*/

    return (
        <div>
            <Header/>
            <div className="header-background-container"/>
            <Header
                className='header-list'
                text={`Backlog - Project ${projectCode}`}
            />
            <div className="table-container-a">
                {backlogForProject.length > 0 ? (
                    <Table className="table-b" data={sortedBacklog} headers={headers}/>
                ) : (
                    <div className="string-notification">
                        <span className="string-notification">This project has an empty backlog!</span>
                    </div>
                )}
            </div>
            <div className="bt-container">
                <Button
                    className='button-edit-stuff'
                    name='Create UserStory'
                    onClick={handleCreateUS}
                />
                <Button
                    className='button-edit-stuff'
                    name="Back to Project"
                    onClick={() => handleRowClick(projectCode)}
                />
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
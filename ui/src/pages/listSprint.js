import React, {useContext, useEffect} from "react";
import Header from "../components/header";
import Button from "../components/button";
import Table from "../components/table";
import {useParams, useNavigate, useLocation} from "react-router-dom";
import AppContext from "../context/AppContext";
import {fetchSprints} from "../context/Actions";

// Array defining the headers for the table
const headers = [
    { label: "Sprint Number", key: "sprintNumber"},
    { label: "Start Date", key: "startDate"},
    { label: "End Date", key: "endDate" },
    { label: "Status", key: "status"},
    { label: "Backlog", key: "view"},
];

const ListSprints = () => {
    // Accessing state and dispatch from the AppContext
    const {state, dispatch} = useContext(AppContext);
    // Extracting the projectCode parameter from the URL
    const {projectCode} = useParams();

    //The effect scrolls the window to the top of the page, ensuring that the header and the top portion
    //of the content are visible when rendering the page.
    useEffect(() => {
        window.scrollTo(0, 0);
        // Fetching the sprints for the specified projectCode
        fetchSprints(dispatch, projectCode)
    }, []);
    // Obtaining the navigate function for programmatic navigation
    const navigate = useNavigate();
    // Obtaining the current location object
    const location = useLocation();
    // Filtering sprints for the specified projectCode
    const sprintInProject = state.sprints.data.filter((sprint) => sprint.projectCode === projectCode);
    // Function to handle viewing the sprint backlog
    const handleViewSprintBacklog = (sprintNumber) => {
        navigate(`/sprintBacklog/${projectCode}/${sprintNumber}`);
    };
    // Mapping sprints to a formatted array
    const sprints = sprintInProject.map((sprint) => ({
        sprintNumber: sprint.sprintNumber,
        startDate: sprint.startDate,
        endDate: sprint.endDate,
        status: "status",
        view: ( <>
                <img
                    className="button-view"
                    src = "http://localhost:3000/images/Eye_icon.svg"
                    alt ="View"
                    onClick={() => handleViewSprintBacklog(sprint.sprintNumber)}
                />
            </>
        ),
    }));


    // Function to handle creating a new sprint
    const handleCreateSprint = () => {
        const from = location.pathname;
        navigate(`/createSprint/${projectCode}`, { state: { from } });
    };
    // Function to handle row click (navigate to the view project page)
    const handleRowClick = (id) => {
        navigate(`/viewProject/${id}`);
    };


    return (
        <div>
            <Header />
            <div className="header-background-container" />
            <Header text={`Sprint list - Project ${projectCode}`}
                className="header-list"
            />
            <div className="table-container-b">
            {sprintInProject.length > 0 ? (
                <Table className="table-b" data={sprints} headers={headers} />
            ) : (
                <div className="string-notification">
                    <span className="string-notification"> This project has an empty sprint list!</span>
                </div>
            )}
            </div>
            <div className="bt-container">
                <Button
                    className="button-edit-stuff"
                    name="Create Sprint"
                    onClick={handleCreateSprint}
                />
                <Button className='button-edit-stuff' name= "Back to Project" onClick={() => handleRowClick(projectCode)}/>
            </div>
        </div>
    );
};

export default ListSprints;
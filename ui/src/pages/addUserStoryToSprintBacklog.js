import React, {useContext, useEffect, useState} from "react";
import AppContext from '../context/AppContext';
import {useLocation, useNavigate, useParams} from "react-router-dom";
import Header from "../components/header";
import Table from "../components/table";
import Button from "../components/button";
import {fetchBacklog} from "../context/Actions";
import DropDownList from "../components/dropDownList";

const headers = [
    {label: "Number", key: "number"},
    {label: "Actor", key: "actor"},
    {label: "Description", key: "description"},
    {label: "Status", key: "status"},
    {label: "Priority", key: "priority"},
    {label: "Acceptance Criteria", key: "ac"},
    {label: "Select User Story", key: "select"},
]

const AddUserStory = () => {

    const {state, dispatch} = useContext(AppContext);

    // getting the projectCode and sprintNumber from the URL match (this is a function of react-router-dom)
    const {projectCode, sprintNumber} = useParams();

    //The effect scrolls the window to the top of the page, ensuring that the header and the top portion
    //of the content are visible when rendering the page.
    useEffect(() => {
        window.scrollTo(0, 0);

        // calling the fetchBacklog action that fetches the backlog for the given project from backend
        fetchBacklog(dispatch, projectCode);
    }, []);

    const backlogForProject = state.backlogs.data;

    // Define a state variable to store the selected user stories
    const [selectedUserStories, setSelectedUserStories] = useState([]);
    const [effort, setEffort] = useState();

    // Function to add or remove the selected User Stories from the selectedUserStories state based on the checkbox status
    const handleSelectUS = (userStoryNumber) => {
        setSelectedUserStories(userStoryNumber);
    };

    const effortValues = [0.5, 1, 2, 3, 5, 8, 13, 20, 40]

    const handleSelectEffort = (event) => {
        const effortValue = event.target.value;
        setEffort(effortValue)
    };

    // Update the finalBacklog array based on selectedUserStories
    const updatedFinalBacklog = backlogForProject.map((backlogForProject) => {

        const isSelected = selectedUserStories.includes(backlogForProject.userStoryNumber);

        return {
            number: backlogForProject.userStoryNumber,
            actor: backlogForProject.actor,
            description: backlogForProject.description,
            status: backlogForProject.status,
            priority: backlogForProject.priority,
            ac: backlogForProject.acceptanceCriteria,
            select: (
                <div className = "radioButton">
                    <input
                        type="radio"
                        name="US"
                        onChange={() => handleSelectUS(backlogForProject.userStoryNumber)}
                    />

                    {isSelected ?  (
                        <div className = "dropDownEffort">
                            <DropDownList
                                mandatory={true}
                                label='Effort estimate '
                                name={"effort"}
                                items={effortValues}
                                onChange={handleSelectEffort}
                            />
                        </div>
                    ) : (<div></div>)
                    }

                </div>
            ),
        };
    });

    const handleAddUserStory = () => {
        console.log(`adding user story ${selectedUserStories} with effort ${effort} to project ${projectCode}`)
        // addUserStoryToSprintBacklog(...)

        // Navigate back to the sprint backlog page
        handleBackToSprintBacklog();
    };

    const navigate = useNavigate();
    const location = useLocation();

    const handleBackToSprintBacklog = () => {
        const from = location.pathname;
        navigate(`/sprintBacklog/${projectCode}/${sprintNumber}`, {state: {from}});
    };


    return (
        <div>
            <Header/>
            <div className="header-background-container"/>
            <Header
                className='header-list'
                text={`Add user stories to sprint backlog - Sprint ${sprintNumber}`}
            />
            <div className="table-container-a">
                {backlogForProject.length > 0 ? (
                    <Table
                        className="table-b"
                        data={updatedFinalBacklog}
                        headers={headers}
                        onSelectUS={handleSelectUS} // Pass handleSelectUS as a prop
                    />
                ) : (
                    <div className="string-notification">
                        <span className="string-notification">No product backlog to select user stories from!</span>
                    </div>
                )}
            </div>
            <div className="bt-container">
                <Button
                    className='button-edit-stuff'
                    name='Add User Story'
                    onClick={handleAddUserStory}
                />
                <Button
                    className='button-edit-stuff'
                    name="Back to Sprint Backlog"
                    onClick={() => handleBackToSprintBacklog()}
                />
            </div>
        </div>

    )
}

export default AddUserStory;

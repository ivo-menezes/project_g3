import React, {useContext, useEffect, useState} from "react";
import AppContext from '../context/AppContext';
import {useLocation, useNavigate, useParams} from "react-router-dom";
import Header from "../components/header";
import Table from "../components/table";
import Button from "../components/button";
import {fetchBacklog} from "../context/Actions";

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
    // table component renders columns in the same order as the object properties...
    // to match the headers, need to sort the object properties
    const sortedBacklog = JSON.parse(
        JSON.stringify(backlogForProject, [
            "projectCode",
            "userStoryNumber",
            "actor",
            "description",
            "status",
            "priority",
            "acceptanceCriteria",
        ])
    );

    // Define a state variable to store the selected user stories
    const [selectedUserStories, setSelectedUserStories] = useState([]);

    // Function to add or remove the selected User Stories from the selectedUserStories state based on the checkbox status
    const handleSelectUS = (userStoryNumber) => {
        setSelectedUserStories((prevSelectedUserStories) => {
            if (prevSelectedUserStories.includes(userStoryNumber)) {
                // User Story already selected, remove it
                return prevSelectedUserStories.filter(
                    (story) => story !== userStoryNumber
                );
            } else {
                // User Story not selected, add it
                return [...prevSelectedUserStories, userStoryNumber];
            }
        });
    };

    // Update the finalBacklog array based on selectedUserStories
    const updatedFinalBacklog = sortedBacklog.map((backlogForProject) => {
        const isSelected = selectedUserStories.includes(
            backlogForProject.userStoryNumber
        );

        return {
            number: backlogForProject.userStoryNumber,
            actor: backlogForProject.actor,
            description: backlogForProject.description,
            status: backlogForProject.status,
            priority: backlogForProject.priority,
            ac: backlogForProject.acceptanceCriteria,
            select: (
                <input
                    type="checkbox"
                    onChange={() => handleSelectUS(backlogForProject.userStoryNumber)}
                    checked={isSelected}
                />
            ),
        };
    });

/*      const finalBacklog = sortedBacklog.map((backlogForProject) => ({
        number: backlogForProject.userStoryNumber,
        actor: backlogForProject.actor,
        description: backlogForProject.description,
        status: backlogForProject.status,
        priority: backlogForProject.priority,
        ac: backlogForProject.acceptanceCriteria,
        select: (
            <input
                type="checkbox"
                onChange={() => handleSelectUS(backlogForProject.userStoryNumber)}
            />
        ),

    }));*/

    const handleAddUserStory = () => {
        // Filter the backlogForProject to get the selected User Story objects
        const selectedStories = backlogForProject.filter(
            (story) => selectedUserStories.includes(story.userStoryNumber)
        );

        // Dispatch an action to add the selected User Stories to the sprint backlog
        // You need to define the action and handle it in your context reducer
        dispatch({ type: "ADD_USER_STORIES_TO_SPRINT", stories: selectedStories });

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
                        selectedUserStories={selectedUserStories} // Pass selectedUserStories as a prop
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

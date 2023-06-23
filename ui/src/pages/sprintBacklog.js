import React, {useContext, useEffect} from "react";
import AppContext from '../context/AppContext';
import {useLocation, useNavigate, useParams} from "react-router-dom";
import Header from "../components/header";
import Table from "../components/table";
import Button from "../components/button";
import {fetchSprintBacklog, setUserStoryStatus} from "../context/Actions";
import DropDownList from "../components/dropDownList";

const headers = [
    {label: "Number", key: "number"},
    {label: "Actor", key: "actor"},
    {label: "Description", key: "description"},
    {label: "Status", key: "status"},
    {label: "Acceptance Criteria", key: "ac"},
    {label: "Effort Estimate", key: "ef"},
]

const SprintBacklog = () => {

    const {state, dispatch} = useContext(AppContext);

    // getting the projectCode and sprintNumber from the URL match (this is a function of react-router-dom)
    const { projectCode, sprintNumber } = useParams();

    //The effect scrolls the window to the top of the page, ensuring that the header and the top portion
    //of the content are visible when rendering the page.
    useEffect(() => {
        window.scrollTo(0, 0);

        // calling the fetchBacklog action that fetches the backlog for the given sprint from backend
        fetchSprintBacklog(dispatch, projectCode,sprintNumber);
    }, []);

    const statusValue = [
        "TO_DO",
        "IN_PROGRESS",
        "TESTING",
        "DONE",
        "CANCELLED"
    ]

    //Function to handle status change in userStory in DropDownList component
    const handleChangeStatus = (event) => {
        const userStoryNumber = event.target.name;
        const userStoryStatus = event.target.value;
        //Object expected by the backend
        const updatedUserStory = {projectCode, userStoryNumber, sprintNumber, userStoryStatus}

        //Call to the "setUserStoryStatus" function that is implemented in "Context-Action".
        setUserStoryStatus(dispatch, updatedUserStory);
    }


    const backlogForSprint = state.backlogs.data;
    // table component renders columns in the same order as the object properties...
    // to match the headers, need to sort the object properties
    const sortedBacklog = JSON.parse(JSON.stringify(backlogForSprint, ["userStoryNumber", "projectCode", "sprintNumber", "userStoryActor",
        "userStoryDescription", "userStoryAcceptanceCriteria", "userStoryStatus","userStoryEffortEstimate"]))

    const finalBacklog = sortedBacklog.map((backlogForSprint) => ({
        userStoryNumber: backlogForSprint.userStoryNumber,
        userStoryActor: backlogForSprint.userStoryActor,
        userStoryDescription: backlogForSprint.userStoryDescription,
        userStoryStatus: (<div className="dropDownStatus">
            <DropDownList
                mandatory={true}
                label=''
                name={backlogForSprint.userStoryNumber}
                items={statusValue}
                value={backlogForSprint.userStoryStatus}
                onChange={handleChangeStatus}
            />
        </div>),
        userStoryAcceptanceCriteria: backlogForSprint.userStoryAcceptanceCriteria,
        userStoryEffortEstimate: backlogForSprint.userStoryEffortEstimate,


    }));

    const navigate = useNavigate();
    const location = useLocation();

    /*
    const handleCreateUS = () => {
        const from = location.pathname;
        navigate(`/createUserStory/${sprintNumber}`, {state: {from}});
    }*/

    const handleAddUserStory = () => {
        const from = location.pathname;
        const addUserStoryUrl = `/addUserStoryToSprintBacklog/${projectCode}/${sprintNumber}`;
        navigate(addUserStoryUrl, { state: { from } });
    };

    const handleRowClick = (projectCode) => {
        navigate(`/listSprints/${projectCode}`)
    };


    return (
        <div>
            <Header/>
            <div className="header-background-container"/>
            <Header
                className='header-list'
                text={`Sprint backlog - Sprint ${sprintNumber}`}
            />
            <div className="table-container-a">
                {backlogForSprint.length > 0 ? (
                    <Table className="table-b" data={finalBacklog} headers={headers}/>
                ) : (
                    <div className="string-notification">
                        <span className="string-notification">This sprint has an empty backlog!</span>
                    </div>
                )}
            </div>
            <div className="bt-container">
                <Button
                    className='button-edit-stuff'
                    name='Add UserStory'
                    onClick={handleAddUserStory}
                />
                <Button
                    className='button-edit-stuff'
                    name="Back to Sprint"
                    onClick={() => handleRowClick(projectCode)}
                />
            </div>
        </div>

    )
}

export default SprintBacklog;


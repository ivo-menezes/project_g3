import TextField from "../components/textField";
import AppContext from "../context/AppContext";
import React, {useContext, useEffect, useState} from "react";
import Header from "../components/header";
import Button from "../components/button";
import {Link, useNavigate, useParams} from "react-router-dom";
import {addSprint} from "../context/Actions";
import PickDate from "../components/date";
import sprints from "../store/sprints";

const CreateSprint = () => {
    // Extracts projectCode from the URL parameter using the useParams hook
    const {projectCode} = useParams();
    // Initializes the sprintNumber state variable using the useState hook
    const [sprintNumber, setSprintNumber] = useState(null);
    // Initializes the newSprint state variable using the useState hook
    const emptySprint = {
        projectCode : projectCode,
        startDate: '',
        endDate: ''
    }
    const [newSprint, setNewSprint] = useState(emptySprint)

    // Calculates the next available sprint number for a given project using the useEffect hook
    useEffect(() => {
        // Filters sprints by projectCode
        const projectSprints = sprints.filter(
            (sprint) => sprint.projectCode === projectCode
        );
        // Calculates the highest existing sprint number and adds 1
        const highestSprintNumber =
            projectSprints.reduce((max, sprint) => Math.max(max, sprint.id), 0) + 1;
        // Updates the sprintNumber state variable with the calculated number
        setSprintNumber(highestSprintNumber);
    }, [projectCode]);

    // Updates the newSprint state variable when a user types into an input field.
    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setNewSprint((sprint) => {
            return {...sprint, [name] : value}
        })
    }

    // Updates the newSprint state variable when a user selects a date using the PickDate component.
    const handleStartDateChange = (newDate, event) => {
        event.target = {type:"text", value:newDate, name:'startDate'}
        handleChange(event)
    }

    const handleEndDateChange = (newDate, event) => {
        event.target = {type:"text", value:newDate, name:'endDate'}
        handleChange(event)
    }
    // Extracts the dispatch function from the AppContext using the useContext hook
    const {dispatch} = useContext(AppContext);
    // Provides navigation functionality using the useNavigate hook from React Router
    const navigate = useNavigate();
    // Handles form submission by converting dates to strings and dispatching an addSprint action
    const handleSubmission = () => {
        // Date objects need to be converted to strings because the table component can't handle displaying objects
        for (const key in newSprint) {
            if (typeof newSprint[key] === 'object' && newSprint[key] instanceof Date) {
                // toISOString generates a string in the format '2022-11-14T00:55:31.820Z'
                // we just want the date part: split at the 'T' and only use the first part
                newSprint[key] = newSprint[key].toISOString().split('T')[0];
            }
        }
        // Allows to view in the console the newly created sprint
        console.log(newSprint)

        //Dispatches an addSprint action with the newSprint data
        addSprint(dispatch, newSprint);

        //Navigates to the sprint list for the current project
        navigate(`/listSprints/${projectCode}`)
    };

    // Renders the form for creating a new sprint
    return (
        <section className='form-create-sprint'>
            <Header className= 'header-create-sprint' text="CREATE SPRINT"/>
            <form onSubmit={handleSubmission}>

                <TextField  className="textField"
                    mandatory={true}
                    label='Number'
                    name="number"
                    value={sprintNumber}
                    readOnly
                />
                <div className="date">
                <PickDate
                    mandatory={true}
                    onChange={handleStartDateChange}
                    selectedDate={newSprint.startDate}
                    dateFormat="dd/MM/yyyy"
                    label='Start Date'
                    name="startDate"
                />
                </div>

                <div className="date">
                <PickDate
                    mandatory={false}
                    onChange={handleEndDateChange}
                    selectedDate={newSprint.endDate}
                    dateFormat="dd/MM/yyyy"
                    label='End Date'
                    name="endDate"
                />
                </div>
                
                <Button className= 'button-form-createSprint-save' name="Save"/>
                <Link to="/listProjects">
                    <Button className= 'button-form-createSprint-cancel' name="Cancel"/>
                </Link>
            </form>
        </section>
    );
}
export default CreateSprint;
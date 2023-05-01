import TextField from "../components/textField";
import AppContext from "../context/AppContext";
import React, {useContext} from "react";
import Header from "../components/header";
import Button from "../components/button";
import {Link} from "react-router-dom";
import {addProject} from "../context/Actions";
import DropDownList from "../components/dropDownList";
import Date from "../components/date";

const CreateProject = () => {
    const {state, dispatch} = useContext(AppContext);
    const {
        code,
        name,
        description,
        customer,
        startDate,
        endDate,
        budget,
        selectedStatus,
        selectedTypology,
        selectedSprintDuration,
        NumberOfPlannedSprints
    } = state;

    const status = [
        "Planned ",
        "Inception",
        "Elaboration",
        "Construction",
        "Transition",
        "Warranty",
        "Closed"
    ]

    const typology = [
        "Fixed Cost",
        "Time and materials",
    ]

    const sprintDuration = [
        '1 - Week',
        '2 - Weeks',
        '3 - Weeks',
        '4 - Weeks',
    ]

    const save = () => {
        const project = {
            code,
            name,
            description,
            customer,
            startDate,
            endDate,
            budget,
            selectedStatus,
            selectedTypology,
            selectedSprintDuration
        }

        const action = addProject(project);
        dispatch(action);
        alert('Project Created')
    };
    return (
        <section className='form-create-project'>
            <Header className= 'header-create-project' text="CREATE PROJECT"/>
            <form onSubmit={save}>

                <TextField  className="textField"
                    mandatory={true}
                    label='Code'
                    dispatch={dispatch}
                    value={code}
                />

                <TextField className="textField"
                    mandatory={true}
                    label='Name'
                    dispatch={dispatch}
                    value={name}
                />

                <TextField className="textField"
                    mandatory={true}
                    label='Description'
                    dispatch={dispatch}
                    value={description}
                />

                <TextField className="textField"
                    mandatory={true}
                    label='Customer'
                    dispatch={dispatch}
                    value={customer}
                />

                <Date
                    mandatory={true}
                    dispatch={dispatch}
                    selectedDate={startDate}
                    dateFormat="dd/MM/yyyy"
                    label='Start Date'
                />

                <Date
                    mandatory={false}
                    dispatch={dispatch}
                    selectedDate={endDate}
                    dateFormat="dd/MM/yyyy"
                    label='End Date'
                />
                <DropDownList
                    mandatory={true}
                    label='Status'
                    dispatch={dispatch}
                    items={status}
                    value={selectedStatus}
                />

                <DropDownList
                    mandatory={true}
                    label='Typology'
                    dispatch={dispatch}
                    items={typology}
                    value={selectedTypology}
                />

                <DropDownList
                    mandatory={true}
                    label='Sprint Duration'
                    dispatch={dispatch}
                    items={sprintDuration}
                    value={selectedSprintDuration}
                />

                <TextField className="textField"
                    mandatory={true}
                    label='Number Of Planned Sprints'
                    dispatch={dispatch}
                    value={NumberOfPlannedSprints}
                />

                <TextField className="textField"
                    mandatory={true}
                    label='Budget'
                    dispatch={dispatch}
                    value={budget}
                />
                <Button className= 'button-form-createProject-save'name="Save"/>
                <Link to="/listProjects">
                    <Button className= 'button-form-createProject-cancel' name="Cancel"/>
                </Link>
            </form>
        </section>
    );
}
export default CreateProject;
import TextField from "../components/textField";
import AppContext from "../context/AppContext";
import React, {useContext, useState} from "react";
import Header from "../components/header";
import Button from "../components/button";
import {Link, useNavigate} from "react-router-dom";
import {addProject} from "../context/Actions";
import DropDownList from "../components/dropDownList";
import PickDate from "../components/date";

const CreateProject = () => {

    // using a local state to save user input before submitting
    const emptyProject = {
        id : '',
        title : '',
        description : '',
        customer : '',
        startDate : '',
        endDate : '',
        budget : '',
        selectedStatus : '',
        selectedTypology : '',
        selectedSprintDuration : '',
        numberOfPlannedSprints : ''
    }

    const [newProject, setNewProject] = useState(emptyProject)

    // updates the corresponding field in newProject when a TextField or DropDownList is changed
    // has to be passed to the component that generates the event
    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setNewProject((project) => {
            return {...project, [name] : value}
        })
    }

    const handleStartDateChange = (newDate, event) => {
        event.target = {type:"text", value:newDate, name:'startDate'}
        handleChange(event)
    }

    const handleEndDateChange = (newDate, event) => {
        event.target = {type:"text", value:newDate, name:'endDate'}
        handleChange(event)
    }

    // submits the newProject to the global context via the addProject action
    const {dispatch} = useContext(AppContext);
    const navigate = useNavigate();
    const handleSubmission = () => {
        addProject(dispatch, newProject)
        navigate('/listProjects')
    }

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


    return (
        <section className='form-create-project'>
            <Header className='header-create-project' text="CREATE PROJECT"/>
            <form onSubmit={handleSubmission}>

                <TextField className="textField"
                           mandatory={true}
                           label='Code'
                           name={'id'}
                           whenTyped={handleChange}
                />

                <TextField className="textField"
                           mandatory={true}
                           label='Name'
                           name={'title'}
                           whenTyped={handleChange}
                />

                <TextField className="textField"
                           mandatory={false}
                           label='Description'
                           name={'description'}
                           whenTyped={handleChange}
                />

                <TextField className="textField"
                           mandatory={false}
                           label='Customer'
                           name={'customer'}
                           whenTyped={handleChange}
                />

                <div className="date">
                    <PickDate
                        mandatory={false}
                        dateFormat="dd/MM/yyyy"
                        label='Start Date'
                        name={'startDate'}
                        selectedDate={newProject.startDate}
                        onChange={handleStartDateChange}
                    />
                </div>

                <div className="date">
                    <PickDate
                        mandatory={false}
                        dateFormat="dd/MM/yyyy"
                        label='End Date'
                        name={'endDate'}
                        selectedDate={newProject.endDate}
                        onChange={handleEndDateChange}
                    />
                </div>

                <div className="dropDownList">
                    <DropDownList
                        mandatory={false}
                        label='Status'
                        name={'status'}
                        items={status}
                        onChange={handleChange}
                    />
                </div>

                <div className="dropDownList">
                    <DropDownList
                        mandatory={false}
                        label='Typology'
                        name={'typology'}
                        items={typology}
                        onChange={handleChange}
                    />
                </div>

                <div className="dropDownList">
                    <DropDownList
                        mandatory={false}
                        label='Sprint Duration'
                        name={'sprintDuration'}
                        items={sprintDuration}
                        onChange={handleChange}
                    />
                </div>


                <TextField className="textField"
                           mandatory={false}
                           label='Number Of Planned Sprints'
                           name={'numberOfPlannedSprints'}
                           whenTyped={handleChange}
                />

                <TextField className="textField"
                           mandatory={false}
                           label='Budget'
                           name={'budget'}
                           whenTyped={handleChange}
                />
                <Button className='button-form-createProject-save' name="Save"/>
                <Link to="/listProjects">
                    <Button className='button-form-createProject-cancel' name="Cancel"/>
                </Link>
            </form>
        </section>
    );
}
export default CreateProject;
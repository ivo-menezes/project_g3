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

    const navigate = useNavigate();

    // getting the global state to which the new project will be submitted
    const {globalState, dispatch} = useContext(AppContext);

    // using local state to save user input before submitting
    const emptyProject = {
        id : '',
        title : '',
        description : '',
        customer : '',
        startDate : new Date(),
        endDate : new Date(),
        budget : '',
        selectedStatus : '',
        selectedTypology : '',
        selectedSprintDuration : '',
        numberOfPlannedSprints : ''
    }
    const [project, changeProject] = useState(emptyProject);

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        changeProject((project) => {
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

    const handleSubmission = () => {
        addProject(dispatch, project)
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
                        selectedDate={project.startDate}
                        onChange={handleStartDateChange}
                    />
                </div>

                <div className="date">
                    <PickDate
                        mandatory={false}
                        dateFormat="dd/MM/yyyy"
                        label='End Date'
                        name={'endDate'}
                        selectedDate={project.endDate}
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
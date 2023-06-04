import TextField from "../components/textField";
import AppContext from "../context/AppContext";
import React, {useContext, useEffect, useState} from "react";
import Header from "../components/header";
import Button from "../components/button";
import {Link, useNavigate} from "react-router-dom";
import {addProject, postProject} from "../context/Actions";
import DropDownList from "../components/dropDownList";
import PickDate from "../components/date";

const CreateProject = () => {
    //The effect scrolls the window to the top of the page, ensuring that the header and the top portion
    //of the content are visible when rendering the page.
    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);

    // using a local state to save user input before submitting
    const emptyProject = {
        projectCode: '',
        projectName: '',
        description: '',
        startDate: '',
        endDate: '',
        customerID: '',
        typologyID: '',
        businessSectorID: '',
        // the following are optional
        projectBudget: '',
        sprintDuration: '',
        projectNumberOfPlannedSprints: ''
    }

    const [newProject, setNewProject] = useState(emptyProject)

    // updates the corresponding field in newProject when a TextField or DropDownList is changed
    // has to be passed to the component that generates the event
    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setNewProject((project) => {
            return {...project, [name]: value}
        })
    }

    const handleStartDateChange = (newDate, event) => {
        event.target = {type: "text", value: newDate, name: 'startDate'}
        handleChange(event)
    }

    const handleEndDateChange = (newDate, event) => {
        event.target = {type: "text", value: newDate, name: 'endDate'}
        handleChange(event)
    }

    // submits the newProject to the global context via the addProject action
    const {dispatch} = useContext(AppContext);
    const navigate = useNavigate();
    const handleSubmission = () => {
        // Date objects need to be converted to strings because the table component can't handle displaying objects
        for (const key in newProject) {
            if (typeof newProject[key] === 'object' && newProject[key] instanceof Date) {
                // toISOString generates a string in the format '2022-11-14T00:55:31.820Z'
                // we just want the date part: split at the 'T' and only use the first part
                newProject[key] = newProject[key].toISOString().split('T')[0];
            }
        }

        console.log(newProject)
        postProject(dispatch, newProject)
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
        <div>
            <Header/>
            <div className="header-background-container"/>
            <Header/>
            <section className='form-create'>
                <Header className='header-create' text="Create project"/>

                <form onSubmit={handleSubmission}>

                    <TextField className="textField"
                               mandatory={true}
                               label='Code'
                               name={'projectCode'}
                               whenTyped={handleChange}
                    />

                    <TextField className="textField"
                               mandatory={true}
                               label='Name'
                               name={'projectName'}
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
                               name={'customerID'}
                               whenTyped={handleChange}
                    />

                    <TextField className="textField"
                               mandatory={false}
                               label='Business Sector'
                               name={'businessSectorID'}
                               whenTyped={handleChange}
                    />

                    <TextField className="textField"
                               mandatory={false}
                               label='Typology'
                               name={'typologyID'}
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
                            label='Sprint Duration'
                            name={'sprintDuration'}
                            items={sprintDuration}
                            onChange={handleChange}
                        />
                    </div>


                    <TextField className="textField"
                               mandatory={false}
                               label='Number Of Planned Sprints'
                               name={'projectNumberOfPlannedSprints'}
                               whenTyped={handleChange}
                    />

                    <TextField className="textField"
                               mandatory={false}
                               label='Budget'
                               name={'projectBudget'}
                               whenTyped={handleChange}
                    />
                    <div className="button-container">
                        <Button className='button-form-create-save' name="Save"/>
                        <Link to="/listProjects">
                            <Button className='button-form-cancel' name="Cancel"/>
                        </Link>
                    </div>
                </form>
            </section>
        </div>
    );
}

export default CreateProject;
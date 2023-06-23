import TextField from "../components/textField";
import AppContext from "../context/AppContext";
import React, {useContext, useEffect, useState} from "react";
import Header from "../components/header";
import Button from "../components/button";
import {Link, useNavigate} from "react-router-dom";
import {postProject} from "../context/Actions";
import DropDownList from "../components/dropDownList";
import PickDate from "../components/date";

const CreateProject = () => {

    const {state, dispatch} = useContext(AppContext);

    //The effect scrolls the window to the top of the page, ensuring that the header and the top portion
    //of the content are visible when rendering the page.
    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);

    const customers = state.customers.data;
    const typologies = state.typologies.data;
    const sectors = state.businessSectors.data;

    const customerValues = customers.map((element) => {
        return element.customerDesignation;
    })

    const typologyValues = typologies.map((element) => {
        return element.typologyDesignation;
    })

    const sectorValues = sectors.map((element) => {
        return element.businessSectorDesignation;
    })

    // using a local state to save user input before submitting
    const emptyProject = {
        projectCode: '',
        projectName: '',
        description: '',
        startDate: '',
        endDate: '',
        customerID: customers[0].customerID,
        typologyID: typologies[0].typologyId,
        businessSectorID: sectors[0].businessSectorId,
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

    const handleCustomerChange = (event) => {
        const name = event.target.name;
        const designation = event.target.value;

        const customer = customers.filter((customer) => customer.customerDesignation === designation)[0];
        const id = customer.customerID;

        event.target = {value : id, name : name}

        handleChange(event)
    }

    const handleTypologyChange = (event) => {
        const name = event.target.name;
        const designation = event.target.value;

        const typology = typologies.filter((typology) => typology.typologyDesignation === designation)[0];
        const id = typology.typologyId;

        event.target = {value : id, name : name}

        handleChange(event)
    }

    const handleSectorChange = (event) => {
        const name = event.target.name;
        const designation = event.target.value;

        const sector = sectors.filter((sector) => sector.businessSectorDesignation === designation)[0];
        const id = sector.businessSectorId;

        event.target = {value : id, name : name}

        handleChange(event)
    }

    // submits the newProject to the global context via the addProject action
    const navigate = useNavigate();
    const handleSubmission = (event) => {
        event.preventDefault();

        // Date objects need to be converted to strings because the table component can't handle displaying objects
        for (const key in newProject) {
            if (typeof newProject[key] === 'object' && newProject[key] instanceof Date) {
                // The "Z", which defines dates as UTC time will be dropped. Dates will be set as local.
                // Adjust the date to the correct time zone by subtracting the timezone offset
                const timezoneOffset = newProject[key].getTimezoneOffset();
                const adjustedDate = new Date(newProject[key].getTime() - timezoneOffset * 60000);

                // toISOString generates a string in the format '2022-11-14T00:55:31.820Z'
                // we just want the date part: split at the 'T' and only use the first part
                newProject[key] = adjustedDate.toISOString().split('T')[0];
            }
        }

        postProject(dispatch, newProject, navigate)
    }

    const sprintDuration = ['1', '2', '3', '4',]

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

                    <div className="dropDownList">
                        <DropDownList
                            mandatory={false}
                            label='Customer'
                            name={'customerID'}
                            items={customerValues}
                            onChange={handleCustomerChange}
                        />
                    </div>

                    <div className="dropDownList">
                        <DropDownList
                            mandatory={false}
                            label='Typology'
                            name={'typologyID'}
                            items={typologyValues}
                            onChange={handleTypologyChange}
                        />
                    </div>

                    <div className="dropDownList">
                        <DropDownList
                            mandatory={false}
                            label='Business Sector'
                            name={'businessSectorID'}
                            items={sectorValues}
                            onChange={handleSectorChange}
                        />
                    </div>

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
                            minDate={newProject.startDate}
                        />
                    </div>

                    <div className="dropDownList">
                        <DropDownList
                            mandatory={false}
                            label='Sprint Duration (in weeks)'
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
import React, {useContext, useEffect, useState} from "react";
import AppContext from "../context/AppContext";
import Header from "../components/header";
import Button from "../components/button";
import DropDownList from "../components/dropDownList";
import PickDate from "../components/date";
import {Link, useNavigate, useParams} from "react-router-dom";
import {addResource, postResource} from "../context/Actions";
import TextField from "../components/textField";

const AssociateResource = () => {
    //The effect scrolls the window to the top of the page, ensuring that the header and the top portion
    //of the content are visible when rendering the page.
    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);

    //gets the project code from the URL parameter using the useParams hook.
    const {projectCode} = useParams();
    //gets the dispatch function and state from the AppContext using the useContext hook.
    const {state, dispatch} = useContext(AppContext);
    const project = state.projects.data.find((project) => project.projectCode === projectCode);

    const navigate = useNavigate();

    //the role has a default value of Team Member, otherwise, if we leave it empty, if we don't actively choose (or
    //change the role to another role and back to 'Team Member'), it will save the role as being empty.
    const emptyResource = {
        projectCode: projectCode,
        role: "Team Member",
        email: '',
        startDate: '',
        endDate: '',
        costPerHour: '',
        percentageOfAllocation: ''
    }

    const [newResource, setNewResource] = useState(emptyResource);
    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setNewResource((resource) => {
            return {...resource, [name]: value}
        })
    }
    const handleStartDateChoice = (newSDate, event) => {
        event.target = {type: "text", value: newSDate, name: 'startDate'}
        handleChange(event)
    }

    const handleEndDateChoice = (newDate, event) => {
        event.target = {type: "text", value: newDate, name: 'endDate'}
        handleChange(event)
    }

    const handleSubmission = (event) => {
        event.preventDefault();

        for (const key in newResource) {
            if (typeof newResource[key] === 'object' && newResource[key] instanceof Date) {

                const timezoneOffset = newResource[key].getTimezoneOffset();
                const adjustedDate = new Date(newResource[key].getTime() - timezoneOffset * 60000);

                newResource[key] = adjustedDate.toISOString().split('T')[0];
            }
        }
        console.log(newResource)
        postResource(dispatch, projectCode, newResource, navigate)
        //creates an array of strings representing each key-value pair in the newResource object, values are stringified
        //individually and joined with the '\n' character.
        const formattedResource = Object.entries(newResource).map(([key, value]) => {
            return `${key}: ${JSON.stringify(value)}`;
        });
        /*alert(formattedResource.join("\n"));*/
    }
    const role = [
        'Team Member',
        'Product Owner',
        'Scrum Master',
        'Project Manager'
    ]

    return (
        <div>
            <Header/>
            <div className="header-background-container"/>
            <Header/>
            <section className={'form-create'}>
                <Header className='header-create' text="Associate new resource to project"/>
                <form onSubmit={handleSubmission}>

                    <div className="dropDownList">
                        <DropDownList
                            mandatory={true}
                            label='Role'
                            name={'role'}
                            items={role}
                            onChange={handleChange}
                        />
                    </div>
                    <TextField className="textField"
                               mandatory={true}
                               label="Email"
                               name={'email'}
                               whenTyped={handleChange}
                    />
                    <div className="date">
                        <PickDate
                            mandatory={true}
                            dateFormat="dd/MM/yyyy"
                            label="Start Date"
                            name={'startDate'}
                            selectedDate={newResource.startDate}
                            onChange={handleStartDateChoice}
                            minDate={new Date(project.startDate)}
                            maxDate={new Date(project.endDate)}
                        />
                    </div>
                    <div className="date">
                        <PickDate
                            mandatory={false}
                            dateFormat="dd/MM/yyyy"
                            label="End Date"
                            name={'endDate'}
                            selectedDate={newResource.endDate}
                            onChange={handleEndDateChoice}
                            minDate={newResource.startDate}
                            maxDate={new Date(project.endDate)}
                        />
                    </div>
                    <TextField className="textField"
                               mandatory={true}
                               label="Cost Per Hour"
                               name={'costPerHour'}
                               whenTyped={handleChange}
                    />
                    <TextField className="textField"
                               mandatory={true}
                               label="Percentage of Allocation"
                               name={'percentageOfAllocation'}
                               whenTyped={handleChange}
                    />
                    <div className="button-container">
                        <Button className='button-form-create-save' name="Save"/>
                        <Link to={`/listResources/${projectCode}`}>
                            <Button className='button-form-cancel' name="Cancel"/>
                        </Link>
                    </div>
                </form>
            </section>
        </div>
    );
}
export default AssociateResource;
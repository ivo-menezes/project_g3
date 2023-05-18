import React, {useContext, useState} from "react";
import AppContext from "../context/AppContext";
import Header from "../components/header";
import Button from "../components/button";
import DropDownList from "../components/dropDownList";
import PickDate from "../components/date";
import {Link, useNavigate, useParams} from "react-router-dom";
import {addResource} from "../context/Actions";
import TextField from "../components/textField";

const AssociateResource = () => {
    //gets the project code from the URL parameter using the useParams hook.
    const {projectCode} = useParams();
    //gets the dispatch function and state from the AppContext using the useContext hook.
    const {dispatch}  = useContext(AppContext);


    const navigate = useNavigate();

    const emptyResource = {
        projectCode : projectCode,
        role : '',
        email : '',
        startDate : '',
        endDate : '',
        costPerHour : '',
        allocationPercentage : ''
    }

    const [newResource, setNewResource] = useState(emptyResource)
    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setNewResource((resource) => {
            return {...resource, [name] : value}
        })
    }
    const handleStartDateChoice = (newSDate, event) => {
        event.target = {type:"text", value:newSDate, name:'startDate'}
        handleChange(event)
    }

    const handleEndDateChoice = (newDate, event) => {
        event.target = {type:"text", value:newDate, name:'endDate'}
        handleChange(event)
    }

    const handleSubmission = () => {
        for (const key in newResource){
            if (typeof newResource[key] === 'object' && newResource[key] instanceof Date){
                newResource[key] = newResource[key].toISOString().split('T')[0];
            }
        }
        addResource(dispatch, newResource)
        alert('New resource created')
        navigate(`/listResources/${projectCode}`)
    }
    const role = [
        'Team Member',
        'Product Owner',
        'Scrum Master',
        'Project Manager'
    ]

    return (
        <section className={'form-create-project'}>
            <Header className='header-create-project' text="Associate new resource to project"/>
            <form onSubmit={handleSubmission}>

                <div className={"dropDownList"}>
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
                           name={'allocationPercentage'}
                           whenTyped={handleChange}
                />
                <Button className='button-form-createProject-save' name="Save"/>
                <Link to={`/listResources/${projectCode}`}>
                    <Button className='button-form-createProject-cancel' name="Cancel"/>
                </Link>
            </form>
        </section>
    );
}
export default AssociateResource;
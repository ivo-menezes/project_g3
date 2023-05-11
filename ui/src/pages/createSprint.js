import TextField from "../components/textField";
import AppContext from "../context/AppContext";
import React, {useContext, useState} from "react";
import Header from "../components/header";
import Button from "../components/button";
import {Link, useNavigate, useParams} from "react-router-dom";
import {addSprint} from "../context/Actions";
import PickDate from "../components/date";

const CreateSprint = () => {

    const {projectCode} = useParams();
    const emptySprint = {
        projectCode : projectCode,
        number: '',
        startDate: '',
        endDate: ''
    }

    const [newSprint, setNewSprint] = useState(emptySprint)

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setNewSprint((sprint) => {
            return {...sprint, [name] : value}
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

    const {dispatch} = useContext(AppContext);
    const navigate = useNavigate();
    const handleSubmission = () => {
        // Date objects need to be converted to strings because the table component can't handle displaying objects
        for (const key in newSprint) {
            if (typeof newSprint[key] === 'object' && newSprint[key] instanceof Date) {
                // toISOString generates a string in the format '2022-11-14T00:55:31.820Z'
                // we just want the date part: split at the 'T' and only use the first part
                newSprint[key] = newSprint[key].toISOString().split('T')[0];
            }
        }
        console.log(newSprint)
        addSprint(dispatch, newSprint);
        navigate(`/listSprints/${projectCode}`)
    };

    return (
        <section className='form-create-sprint'>
            <Header className= 'header-create-sprint' text="CREATE SPRINT"/>
            <form onSubmit={handleSubmission}>

                <TextField  className="textField"
                    mandatory={true}
                    label='Number'
                    name="number"
                    whenTyped={handleChange}
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
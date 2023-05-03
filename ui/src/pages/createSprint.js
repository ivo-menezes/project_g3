import TextField from "../components/textField";
import AppContext from "../context/AppContext";
import React, {useContext} from "react";
import Header from "../components/header";
import Button from "../components/button";
import {Link} from "react-router-dom";
import {addSprint} from "../context/Actions";
import Date from "../components/date";

const CreateSprint = () => {
    const {state, dispatch} = useContext(AppContext);
    const {
        number,
        startDate,
        endDate
    } = state;

    

    const save = () => {
        const sprint = {
            number,
            startDate,
            endDate
        }

        addSprint(dispatch, sprint);
        alert('Sprint Created')
    };

    return (
        <section className='form-create-sprint'>
            <Header className= 'header-create-sprint' text="CREATE SPRINT"/>
            <form onSubmit={save}>

                <TextField  className="textField"
                    mandatory={true}
                    label='Number'
                    dispatch={dispatch}
                    value={number}
                />
                <div className="date">
                <Date
                    mandatory={true}
                    dispatch={dispatch}
                    selectedDate={startDate}
                    dateFormat="dd/MM/yyyy"
                    label='Start Date'
                />
                </div>

                <div className="date">
                <Date
                    mandatory={false}
                    dispatch={dispatch}
                    selectedDate={endDate}
                    dateFormat="dd/MM/yyyy"
                    label='End Date'
                />
                </div>
                
                <Button className= 'button-form-createSprint-save' name="Save"/>
                <Link to="/listSprints">
                    <Button className= 'button-form-createSprint-cancel' name="Cancel"/>
                </Link>
            </form>
        </section>
    );
}
export default CreateSprint;
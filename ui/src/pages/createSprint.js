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

        const action = addSprint(sprint);
        dispatch(action);
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
                
                <Button className= 'button-form-createSprint-save'name="Save"/>
                <Link to="/listSprints">
                    <Button className= 'button-form-createSprint-cancel' name="Cancel"/>
                </Link>
            </form>
        </section>
    );
}
export default CreateSprint;
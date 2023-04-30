import TextField from "../components/textField";
import AppContext from "../context/AppContext";
import React, {useContext} from "react";
import Header from "../components/header";
import Button from "../components/button";
import {Link} from "react-router-dom";

const CreateProject = () => {
    const {state, dispatch} = useContext(AppContext);
    const {textValue} = state;
    const {code} = textValue;

    return (
        <div className="form-container">
            <Header text="Create Project" />
            <form>
                <TextField
                    mandatory = {true}
                    label = 'Code Project'
                    placeholder = '...'
                    dispatch={dispatch}
                    value ={code}
                    className="form-input"
                />

                <TextField
                    mandatory = {true}
                    label = 'Project Name'
                    placeholder = '...'
                    dispatch={dispatch}
                    value ={code}
                    className="form-input"
                />

                <TextField
                    mandatory = {true}
                    label = 'Project Description'
                    placeholder = '...'
                    dispatch={dispatch}
                    value ={code}
                    className="form-input"
                />

                <TextField
                    mandatory = {true}
                    label = 'Customer'
                    placeholder = '...'
                    dispatch={dispatch}
                    value ={code}
                    className="form-input"
                />

                <div>
                    <label htmlFor="start-date" className="form-input label" >Start Date:</label>
                    <input
                        type="date"
                        id="start-date"
                        name="start-date"
                        onChange={(event) => console.log(event.target.value)}
                    />
                </div>

                <TextField
                    mandatory = {true}
                    label = 'Project Status'
                    placeholder = 'PLANNED'
                    dispatch={dispatch}
                    value ={code}
                    readOnly={true}
                    className="form-input"
                />

                <TextField
                    mandatory = {true}
                    label = 'Budget'
                    placeholder = '...'
                    dispatch={dispatch}
                    value ={code}
                    className="form-input"
                />

                <div>
                    <Button className="form-submit" name="Save" />
                    <Link to="/listProjects">
                        <Button className="form-submit" name="Cancel" />
                    </Link>
                </div>
            </form>
        </div>
    );
}
export default CreateProject;
import React, { useContext } from "react";
import AppContext from "../context/AppContext";
import { addUserStory } from '../context/Actions';
import Button from '../components/button';
import Header from '../components/header';
import TextArea from '../components/textArea';
import TextField from '../components/textField';
import { Link } from 'react-router-dom';

const CreateUserStory = () => {
    const { state, dispatch } = useContext(AppContext);
    const { number, actor, description, priority, ac } = state;

    const submitForm = (event) => {
        event.preventDefault();
        const userStory = {
            number,
            actor,
            description,
            priority,
            ac
        }
        const action = addUserStory(userStory);
        dispatch(action);
        alert('User Story Created')
    };

    return (
        <div className="form-container">
            <Header text="Create User Story" />
            <form className="form" onSubmit={submitForm}>
                <TextField
                    label="Number:"
                    value={number}
                    placeholder=""
                    mandatory={true}
                    dispatch={dispatch}
                    name="number"
                    className="form-input"
                />
                <TextField
                    label="Actor:"
                    value={actor}
                    placeholder=""
                    mandatory={true}
                    dispatch={dispatch}
                    name="actor"
                    className="form-input"
                />
                <TextField
                    label="Description:"
                    value={description}
                    placeholder=""
                    mandatory={true}
                    dispatch={dispatch}
                    name="description"
                    className="form-input"
                />
                <TextField
                    label="Priority:"
                    value={priority}
                    placeholder="(optional)"
                    mandatory={false}
                    dispatch={dispatch}
                    name="priority"
                    className="form-input"
                />
                <TextArea
                    label="Acceptance Criteria:"
                    value={ac}
                    mandatory={true}
                    placeholder=""
                    dispatch={dispatch}
                    className="form-input"
                />
                <div>
                    <Button className="form-submit" name="Save" onClick={submitForm} />
                    <Link to="/testPage">
                        <Button className="form-submit" name="Cancel" />
                    </Link>
                </div>
            </form>
        </div>
    );
};

export default CreateUserStory;

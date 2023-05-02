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
        <div className="form-create-userStory">
            <Header className = "header-create-userStory" text="CREATE USER STORY" />
            <form onSubmit={submitForm}>

                <TextField className="textField"
                           label="Number:"
                           value={number}
                           placeholder=""
                           mandatory={true}
                           dispatch={dispatch}

                />
                <TextField className="textField"
                           label="Actor:"
                           value={actor}
                           placeholder=""
                           mandatory={true}
                           dispatch={dispatch}

                />
                <TextField className="textField"
                           label="Description:"
                           value={description}
                           placeholder=""
                           mandatory={true}
                           dispatch={dispatch}

                />
                <TextField className="textField"
                           label="Priority:"
                           value={priority}
                           placeholder="(optional)"
                           mandatory={false}
                           dispatch={dispatch}

                />
                <TextArea className="textArea"
                          label="Acceptance Criteria:"
                          value={ac}
                          mandatory={true}
                          placeholder=""
                          dispatch={dispatch}
                />

                <Button className="button-form-createUserStory-save" name="Save" />
                <Link to="/testPage">
                    <Button className="button-form-createUserStory-cancel" name="Cancel" />
                </Link>
            </form>
        </div>
    );
};

export default CreateUserStory;

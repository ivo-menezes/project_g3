import React, { useReducer } from 'react';
import { ADD_USER_STORY } from '../context/Actions';
import reducer from '../context/Reducer';
import Button from '../components/button';
import Header from '../components/header';
import TextArea from '../components/textArea';
import TextField from '../components/textField';
import { Link } from 'react-router-dom';


const initialState = { textValue: "", userStories: [] };

const CreateUserStory = () => {
    const [state, dispatch] = useReducer(reducer, initialState);


    const submitForm = (event) => {
        event.preventDefault();
        dispatch({
            type: ADD_USER_STORY,
            payload: { number, actor, description, priority, ac }
        });
    };


    const { number, actor, description, priority, ac } = state;

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

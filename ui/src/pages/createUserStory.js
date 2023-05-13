import React, {useContext, useState} from "react";
import AppContext from "../context/AppContext";
import { addUserStory} from '../context/Actions';
import Button from '../components/button';
import Header from '../components/header';
import TextField from '../components/textField';
import {Link, useNavigate, useParams} from 'react-router-dom';
import DropDownList from "../components/dropDownList";

const CreateUserStory = () => {

    const {projectCode} = useParams();
    const emptyUserStory = {
        projectCode: projectCode,
        number: "",
        actor: "",
        description: "",
        status: "To Do",
        priority: "",
        ac: "",
    }

    const [newUserStory, setNewUserStory] = useState(emptyUserStory)

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setNewUserStory((backlog) => {
            return {...backlog, [name] : value}
        })
    }

    const {dispatch} = useContext(AppContext);
    const navigate = useNavigate();

    const handleSubmission = () => {
        addUserStory(dispatch, newUserStory);
        navigate(`/backlog/${projectCode}`);
    };

    return (
        <section className="form-create-userStory">
            <Header className="header-create-userStory" text="CREATE USER STORY" />
            <form onSubmit={handleSubmission}>
                <TextField
                    className="textField"
                    mandatory={true}
                    label="Number"
                    name={"number"}
                    whenTyped={handleChange}
                />
                <TextField
                    className="textField"
                    mandatory={true}
                    label="Actor"
                    name={"actor"}
                    whenTyped={handleChange}
                />
                <TextField
                    className="textField"
                    mandatory={true}
                    label="Description"
                    name={"description"}
                    whenTyped={handleChange}
                />
                <TextField
                    className="textField"
                    mandatory={false}
                    label="Priority"
                    name={"priority"}
                    whenTyped={handleChange}
                />
                <TextField
                    className="textField"
                    mandatory={false}
                    label="Acceptance Criteria"
                    name={"ac"}
                    whenTyped={handleChange}
                />
                <Button className="button-form-createUserStory-save" name="Save" />
                <Link to="/listProjects">
                    <Button className="button-form-createUserStory-cancel" name="Cancel" />
                </Link>
            </form>
        </section>
    );
};

export default CreateUserStory;

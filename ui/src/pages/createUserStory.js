import React, {useContext, useEffect, useState} from "react";
import AppContext from "../context/AppContext";
import {addUserStory, postUserStory} from '../context/Actions';
import Button from '../components/button';
import Header from '../components/header';
import TextField from '../components/textField';
import {Link, useNavigate, useParams} from 'react-router-dom';
import TextArea from "../components/textArea";
import {postUserStoryToBackend} from "../services/Service";

const CreateUserStory = () => {
    //The effect scrolls the window to the top of the page, ensuring that the header and the top portion
    //of the content are visible when rendering the page.
    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);
    // using a local state to save user input before submitting
    const {projectCode} = useParams();
    const emptyUserStory = {
        projectCode: projectCode,
        userStoryNumber: "",
        actor: "",
        description: "",
        priority: "",
        acceptanceCriteria: "",
    }

    const [newUserStory, setNewUserStory] = useState(emptyUserStory)

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setNewUserStory((backlog) => {
            return {...backlog, [name]: value}
        })
    }

    const {dispatch} = useContext(AppContext);
    const navigate = useNavigate();

    const handleSubmission = () => {
        //addUserStory(dispatch, newUserStory);
        console.log(newUserStory)
        postUserStory(dispatch, projectCode, newUserStory)
        navigate(`/backlog/${projectCode}`);
    };

    return (
        <div>
            <Header/>
            <div className="header-background-container"/>
            <Header/>
            <section className="form-create">
                <Header className="header-create" text="Create user story"/>

                <form onSubmit={handleSubmission}>
                    <TextField
                        className="textField"
                        mandatory={true}
                        label="Number"
                        name={"userStoryNumber"}
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
                    <TextArea
                        className="textArea"
                        mandatory={true}
                        label="Acceptance Criteria"
                        name={"acceptanceCriteria"}
                        whenTyped={handleChange}
                    />
                    <div className="button-container">
                        <Button className="button-form-create-save" name="Save"/>
                        <Link to="/listProjects">
                            <Button className="button-form-cancel" name="Cancel"/>
                        </Link>
                    </div>
                </form>
            </section>
        </div>
    );
};

export default CreateUserStory;

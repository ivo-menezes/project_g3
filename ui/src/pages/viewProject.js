import React, { useContext } from "react";
import Button from "../components/button";
import { Link, useParams } from "react-router-dom";
import Header from "../components/header";
import AppContext from "../context/AppContext";

const ViewProject = () => {
    const { id } = useParams();
    const { state } = useContext(AppContext);

    console.log("projectList in ViewProject:", state.projectList);

    const project = state.projectList.find(
        (project) => project.id === parseInt(id)
    );

    console.log("projectId in ViewProject:", id);
    console.log("project in ViewProject:", project);

    return (
        <div>
            <Header
                text="PROJECT VIEW"
                className="header-listProjects"
                style={{ marginLeft: "65px" }}
            />
            {project ? (
                <>
                    <ul>
                        <li style={{ marginLeft: "65px" }}>
                            <h2>{project.title}</h2>
                        </li>
                        <li style={{ marginLeft: "65px" }}>Code: {project.id}</li>
                    </ul>
                </>
            ) : (
                <p>No project found with code</p>
            )}
            <div className="bt-container ">
                <Link to="/createUserStory">
                    <Button className="button-ListProjects" name="Create User Story" />
                </Link>
                <Link to={`/listSprints`}>
                    <Button className="button-ListProjects" name="Sprint List" />
                </Link>
                <Link to={`/listProjects`}>
                    <Button className="button-ListProjects" name="Back to Project List" />
                </Link>
            </div>
        </div>
    );
};

export default ViewProject;
import React, { useContext } from "react";
import Button from "../components/button";
import {Link, useParams} from "react-router-dom";
import Header from "../components/header";
import AppContext from "../context/AppContext";

const ViewProject = () => {
    const { id } = useParams();
    const {state} = useContext(AppContext);

    const project = state.projectList.find(
        (project) => project.id === id
    );

    return (
        <div>
            <Header
                text={`Information about project ${id}`}
                className="header-listProjects"
                style={{ marginLeft: "65px" }}
            />
            {project ? (
                <>
                    <ul>
                        <li className="project-info">NAME: {project.title} </li>
                        <li className="project-info">CODE: {project.id} </li>
                        <li className="project-info">DESCRIPTION: {project.description} </li>
                        <li className="project-info">CUSTOMER: {project.customer} </li>
                        <li className="project-info">START DATE: {project.startDate} </li>
                        <li className="project-info">END DATE: {project.endDate} </li>
                        <li className="project-info">STATUS: {project.status} </li>
                        <li className="project-info">TYPOLOGY: {project.typology} </li>
                        <li className="project-info">SPRINT DURATION: {project.sprintDuration} </li>
                        <li className="project-info">NUMBER OF PLANNED SPRINTS: {project.numberOfPlannedSprints} </li>
                        <li className="project-info">BUDGET: {project.budget} </li>
                    </ul>
                </>
            ) : (
                <p>No project found with code {id}</p>
            )}
            <div className="bt-container ">
                <Link to={`/listSprints/${id}`}>
                    <Button className="button-ListProjects" name="Sprint List" />
                </Link>
                <Link to={`/backlog/${id}`}>
                    <Button className="button-ListProjects" name="Product Backlog" />
                </Link>
                <Link to={`/listProjects`}>
                    <Button className="button-ListProjects" name="Back to Project List" />
                </Link>
            </div>
        </div>
    );
};

export default ViewProject;
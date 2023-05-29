import React, { useContext } from "react";
import Button from "../components/button";
import { Link, useParams } from "react-router-dom";
import Header from "../components/header";
import AppContext from "../context/AppContext";

const ViewProject = () => {
    const { id } = useParams();
    const { state } = useContext(AppContext);

    const project = state.projectList.find((project) => project.id === id);

    // Define column labels and their corresponding attributes
    const columnLabels = [
        { label: "Name:", attribute: "title" },
        { label: "Code:", attribute: "id" },
        { label: "Description:", attribute: "description" },
        { label: "Customer:", attribute: "customer" },
        { label: "Start date:", attribute: "startDate" },
        { label: "End date:", attribute: "endDate" },
        { label: "Status:", attribute: "status" },
        { label: "Typology:", attribute: "typology" },
        { label: "Sprint duration:", attribute: "sprintDuration" },
        { label: "Number of planned sprints:", attribute: "numberOfPlannedSprints" },
        { label: "Budgets:", attribute: "budget" },
    ];

    return (
        <div>
            <Header />
            <div className="header-background-container" />
            <Header
                text={`${id} - ${project.title}`}
                className="header-list"
            />
            {project ? (
                <>
                    <div className="project-info-container">
                        <table className="table-view-projects">
                            <tbody>
                            {columnLabels.map((column) => (
                                <tr key={column.label}>
                                    <td>{column.label}</td>
                                    <td>{project[column.attribute]}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </>
            ) : (
                <p>No project found with code {id}</p>
            )}
            <div className="bt-container">
                <Link to={`/listSprints/${id}`}>
                    <Button className="button-edit-stuff" name="Sprint List" />
                </Link>
                <Link to={`/backlog/${id}`}>
                    <Button className="button-edit-stuff" name="Product Backlog" />
                </Link>
                <Link to={`/listResources/${id}`}>
                    <Button className="button-edit-stuff" name="Resource list" />
                </Link>
                <Link to={`/listProjects`}>
                    <Button className="button-edit-stuff" name="Back to Project List" />
                </Link>
            </div>
        </div>
    );
};

export default ViewProject;

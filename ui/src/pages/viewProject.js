import React, {useContext, useEffect} from "react";
import Button from "../components/button";
import { Link, useParams } from "react-router-dom";
import Header from "../components/header";
import AppContext from "../context/AppContext";

const ViewProject = () => {
    //The effect scrolls the window to the top of the page, ensuring that the header and the top portion
    //of the content are visible when rendering the page.
    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);
    const { id } = useParams();
    const { state } = useContext(AppContext);

    const project = state.projects.data.find((project) => project.projectCode === id);

    // Define column labels and their corresponding attributes
    const columnLabels = [
        { label: "Name:", attribute: "projectName" },
        { label: "Code:", attribute: "projectCode" },
        { label: "Description:", attribute: "description" },
        { label: "Customer:", attribute: "customerID" },
        { label: "Start date:", attribute: "startDate" },
        { label: "End date:", attribute: "endDate" },
        { label: "Status:", attribute: "projectStatus" },
        { label: "Typology:", attribute: "typologyID" },
        { label: "Business Sector:", attribute: "businessSectorID"},
        { label: "Sprint duration (in weeks):", attribute: "sprintDuration" },
        { label: "Number of planned sprints:", attribute: "projectNumberOfPlannedSprints" },
        { label: "Budget:", attribute: "projectBudget" },
    ];

    return (
        <div>
            <Header />
            <div className="header-background-container" />
            <Header
                text={`Project ${id} - ${project ? project.projectName : ""}`}
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

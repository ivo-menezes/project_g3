import React, {useContext} from "react";
import Header from "../components/header";
import Button from "../components/button";
import Table from "../components/table";
import {Link, useParams} from "react-router-dom";
import AppContext from "../context/AppContext";

const headers = [
    { label: "ID", key: "id"},
    { label: "Start Date", key: "startDate"},
    { label: "End Date", key: "endDate" },
];

const ListSprints = () => {

    const {projectCode} = useParams();
    const {state} = useContext(AppContext);

    const sprintInProject = state.listSprint.find(project => project.projectCode === projectCode);
    const sprint = sprintInProject ? sprintInProject.sprint : undefined;

    return (
        <div>
            <Header text="Sprint List" className= 'header-listProjects' text={`Sprint list`}
                    style={{ marginLeft: "65px" }} />
            {sprint ? (
                <>
                    <Table data={sprint} headers={headers} />
                </>
            ) : (
                <div className="string-format">
                <h2>This project has an empty sprint list!</h2>
                </div>
            )}
            <div className='bt-container '>
                <Link to="/createSprint">
                <Button
                    style={{ marginLeft: "50px" }}
                    className="button-ListProjects"
                    name="Create Sprint"
                />
                </Link>
                <Link to='/listProjects'>
                    <Button className='button-ListProjects' name= "Back to Project List"/>
                </Link>
            </div>
        </div>
    );
};

export default ListSprints;
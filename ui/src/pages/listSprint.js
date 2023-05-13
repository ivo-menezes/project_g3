import React, {useContext} from "react";
import Header from "../components/header";
import Button from "../components/button";
import Table from "../components/table";
import {useParams, useNavigate, useLocation} from "react-router-dom";
import AppContext from "../context/AppContext";

const headers = [
    { label: "Project Code", key: "projectCode"},
    { label: "ID", key: "id"},
    { label: "Start Date", key: "startDate"},
    { label: "End Date", key: "endDate" },
];

const ListSprints = () => {

    const {projectCode} = useParams();
    const {state} = useContext(AppContext);
    const navigate = useNavigate();
    const location = useLocation();

    const sprintInProject = state.sprints.filter((sprint) => sprint.projectCode === projectCode);
    console.log(sprintInProject);

    const handleCreateSprint = () => {
        const from = location.pathname;
        navigate(`/createSprint/${projectCode}`, { state: { from } });
    };
    const handleRowClick = (id) => {
        console.log("Clicked row with ID:", id);
        navigate(`/viewProject/${id}`);
    };


    return (
        <div>
            <Header text={`Sprint list for project ${projectCode}`}
                className="header-listProjects"
                style={{ marginLeft: "65px" }}
            />
            {sprintInProject.length > 0 ? (
                <Table data={sprintInProject} headers={headers} />
            ) : (
                <div className="string-format">
                    <h2>This project has an empty sprint list!</h2>
                </div>
            )}
            <div className="bt-container ">
                <Button
                    style={{ marginLeft: "50px" }}
                    className="button-ListProjects"
                    name="Create Sprint"
                    onClick={handleCreateSprint}
                />
                <Button className='button-ListProjects' name= "Back to Project" onClick={() => handleRowClick(projectCode)}/>
            </div>
        </div>
    );
};

export default ListSprints;
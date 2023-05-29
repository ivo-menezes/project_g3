import React, {useContext} from "react";
import Header from "../components/header";
import Button from "../components/button";
import Table from "../components/table";
import {useParams, useNavigate, useLocation} from "react-router-dom";
import AppContext from "../context/AppContext";

const headers = [
    { label: "Project code", key: "projectCode"},
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
            <Header />
            <div className="header-background-container" />
            <Header text={`Sprint list - Project ${projectCode}`}
                className="header-list"
            />
            <div className="table-container-b">
            {sprintInProject.length > 0 ? (
                <Table className="table-b" data={sprintInProject} headers={headers} />
            ) : (
                <div className="string-notification">
                    <span className="string-notification"> This project has an empty sprint list!</span>
                </div>
            )}
            </div>
            <div className="bt-container">
                <Button

                    className="button-edit-stuff"
                    name="Create Sprint"
                    onClick={handleCreateSprint}
                />
                <Button className='button-edit-stuff' name= "Back to Project" onClick={() => handleRowClick(projectCode)}/>
            </div>
        </div>
    );
};

export default ListSprints;
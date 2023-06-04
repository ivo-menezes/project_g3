import React, {useContext, useEffect} from "react";
import Header from "../components/header";
import Button from "../components/button";
import Table from "../components/table";
import {useParams, useNavigate, useLocation} from "react-router-dom";
import AppContext from "../context/AppContext";
import {fetchSprints} from "../context/Actions";

const headers = [
    { label: "Project code", key: "projectCode"},
    { label: "ID", key: "id"},
    { label: "Start Date", key: "startDate"},
    { label: "End Date", key: "endDate" },
];

const ListSprints = () => {

    const {state, dispatch} = useContext(AppContext);
    const {projectCode} = useParams();

    //The effect scrolls the window to the top of the page, ensuring that the header and the top portion
    //of the content are visible when rendering the page.
    useEffect(() => {
        window.scrollTo(0, 0);

        fetchSprints(dispatch, projectCode)
    }, []);



    const navigate = useNavigate();
    const location = useLocation();

    const sprintInProject = state.sprints.data.filter((sprint) => sprint.projectCode === projectCode);

    const handleCreateSprint = () => {
        const from = location.pathname;
        navigate(`/createSprint/${projectCode}`, { state: { from } });
    };
    const handleRowClick = (id) => {
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
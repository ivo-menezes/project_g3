import React, {useContext} from "react";
import Header from "../components/header";
import HeaderBackground from "../components/headerBackground";
import Button from "../components/button";
import Table from "../components/table";
import {Link, useNavigate } from "react-router-dom";
import AppContext from "../context/AppContext";


const headers = [
    { label: "Code", key: "id" },
    { label: "Name", key: "title" },
    { label: "", key: "view" },
];

const ListProjects = () => {
    const { state } = useContext(AppContext)
    const { projectList } = state;

    const navigate = useNavigate();

    const handleRowClick = (id) => {
        console.log("Clicked row with ID:", id);
        navigate(`/viewProject/${id}`);
    };

    const projects = projectList.map((project) => ({
        id: project.id,
        title: project.title,
        view: (
            <Button
                className="sprint-button-view"
                name={"View"}
                onClick={() => handleRowClick(project.id)}
            ></Button>
        ),
    }));

    return (
        <div>
            <Header />
            <div className="header-background-container" />
            <Header
                className="header-listProjects"
                text="Project List"
                style={{ marginLeft: "65px" }}
            />
            <div className="table-container">
                <Table data={projects} headers={headers} />
            </div>
            <div className="bt-container">
                <Link to="/createProject">
                    <Button className="button-ListProjects" name="Create Project" />
                </Link>
            </div>
        </div>
    );
};
export default ListProjects;
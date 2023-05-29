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
        view: ( <>
                <img
                    className="button-view"
                    src="Eye icon.svg"
                    alt="View"
                    onClick={() => handleRowClick(project.id)}
                />
            </>
        ),
    }));

    return (
        <div>
            <Header />
            <div className="header-background-container" />
            <Header
                className="header-list"
                text="Project List"
            />
            <div className="table-container-a">
                <Table className="table-a" data={projects} headers={headers} />
            </div>
            <div className="bt-container">
                <Link to="/createProject">
                    <Button className="button-create" name="Create Project" />
                </Link>
            </div>
        </div>
    );
};
export default ListProjects;
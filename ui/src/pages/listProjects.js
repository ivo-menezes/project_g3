import React, {useContext} from "react";
import Header from "../components/header";
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

    const navigate = useNavigate();
    const { state } = useContext(AppContext);

    const project = state.projectList;

    const handleRowClick = (id) => {
        console.log("Clicked row with ID:", id);
        navigate(`/viewProject/${id}`);
    };

    const projects = project.map((project) => ({
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
            <Header className= 'header-listProjects' text='Project List' style={{marginLeft: "65px"}}/>
            <Table data={projects} headers={headers}/>
            <div className='bt-container '>
                <Link to='/createProject'>
                    <Button className='button-ListProjects' name='Create Project'/>
                </Link>
            </div>

        </div>
    )
}
export default ListProjects;
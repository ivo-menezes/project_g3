import React from "react";
import Header from "../components/header";
import Button from "../components/button";
import Table from "../components/table";
import {Link} from "react-router-dom";
import projects from "../store/projects";

const headers = {
    header1: {label: "Code"},
    header2: {label: "Name"}
}

const ListProjects = () => {
    return (
        <div>
            <Header text='Project List' style={{marginLeft: "65px"}}/>
            <Table data={projects} headers={headers}/>
            <Link to='/createProject'>
                <Button className='button-ListProjects' name='Create Project'/>
            </Link>
            <Link to='/testPage'>
                <Button className='button-ListProjects' name='Test Page'/>
            </Link>
        </div>
    )
}
export default ListProjects;
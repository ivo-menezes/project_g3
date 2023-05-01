import React from "react";
import Header from "../components/header";
import Button from "../components/button";
import Table from "../components/table";
import {Link} from "react-router-dom";
import projects from "../store/projects";

const headers = {
    header1: {label: "Code"},
    header2: {label: "Name"},
    header3: {label: ""},
}

const ListProjects = () => {
    return (
        <div>
            <Header className= 'header-listProjects' text='Project List' style={{marginLeft: "65px"}}/>
            <Table data={projects} headers={headers}/>
            <div className='bt-container '>
                <Link to='/createProject'>
                    <Button className='button-ListProjects' name='Create Project'/>
                </Link>
                <Link to='/testPage'>
                    <Button className='button-ListProjects' name='Test Page'/>
                </Link>
            </div>

        </div>
    )
}
export default ListProjects;
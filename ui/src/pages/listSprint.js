import React from "react";
import Header from "../components/header";
import Button from "../components/button";
import Table from "../components/table";
import { Link, useNavigate } from "react-router-dom";
import sprints from "../store/sprints";


const headers = {
    header1: { label: "ID" },
    header2: { label: "Start Date" },
    header3: { label: "End Date" },
    header4: { label: "" },
};

const ListSprints = () => {

    const navigate = useNavigate();

    const handleViewClick = () => {
        navigate(`/viewSprintPage`);
    };

    return (
        <div>
            <Header text="Sprint List" className= 'header-listProjects' style={{ marginLeft: "65px" }} />
                <Table data={sprints} headers={headers} onViewClick={handleViewClick} />
            <div className='bt-container '>
                <Link to="/createSprint">
                <Button
                    style={{ marginLeft: "50px" }}
                    className="button-ListProjects"
                    name="Create Sprint"
                />
                </Link>
                <Link to='/testPage'>
                    <Button className='button-ListProjects' name='Back'/>
                </Link>
            </div>
        </div>
    );
};

export default ListSprints;
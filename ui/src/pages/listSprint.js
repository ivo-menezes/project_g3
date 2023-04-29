import React from "react";
import Header from "../components/header";
import Button from "../components/button";
import Table from "../components/table";
import { Link } from "react-router-dom";
import sprints from "../store/sprints";


const headers = {
    header1: { label: "ID" },
    header2: { label: "Start Date" },
    header3: { label: "End Date" },
};

const ListSprints = () => {

    return (
        <div>
            <Header text="Sprint List" style={{ marginLeft: "65px" }} />
                <Table data={sprints} headers={headers}/>
            <Link to="/createSprint">
                <Button
                    style={{ marginLeft: "50px" }}
                    className="button-ListProjects"
                    name="Create Sprint"
                />
            </Link>
        </div>
    );
};

export default ListSprints;
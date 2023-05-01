import React from "react";
import Header from "../components/header";
import { Link } from "react-router-dom";
import Button from "../components/button";

const ViewSprint = () => {
    return(
        <div>
            <Header text="Page Under Construction" className= 'header-listProjects' style={{ marginLeft: "65px" }} />
            <div className='bt-container '>
                <Link to='/listSprints'>
                    <Button className='button-ListProjects' name='Back'/>
                </Link>
            </div>
        </div>
    );
}

export default ViewSprint;
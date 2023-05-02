import Button from "../components/button";
import { Link } from "react-router-dom"
import Header from "../components/header";
import React from "react";

const TestPage = () => {
    return (
        <div>
            <Header text="PROJECT VIEW" className= 'header-listProjects' style={{ marginLeft: "65px" }} />
            <h2 className= 'header-listProjects' style={{ marginLeft: "65px" }} >Page under construction</h2>
            <div className='bt-container '>
            <Link to='/createUserStory'>
                <Button className='button-ListProjects' name='Create User Story' />
            </Link>
            <Link to='/listSprints'>
                <Button className='button-ListProjects' name='Sprint List' />
            </Link>
            </div>
        </div>
    )
}
export default TestPage;
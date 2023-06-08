import React, {useContext, useEffect} from "react";
import Header from "../components/header";
import HeaderBackground from "../components/headerBackground";
import Button from "../components/button";
import Table from "../components/table";
import {Link, useNavigate } from "react-router-dom";
import AppContext from "../context/AppContext";
import {fetchProjects} from "../context/Actions";


const headers = [
    { label: "Code", key: "id" },
    { label: "Name", key: "title" },
    { label: "", key: "view" },
];

const ListProjects = () => {

    const { state, dispatch } = useContext(AppContext)

    //The effect scrolls the window to the top of the page, ensuring that the header and the top portion
    //of the content are visible when rendering the page.
    useEffect(() => {
        window.scrollTo(0, 0);

        // use the fetchProjects action to get list of projects from backend
        fetchProjects(dispatch)
    }, []);


    const navigate = useNavigate();

    const handleRowClick = (id) => {
        navigate(`/viewProject/${id}`);
    };

    const projectList = state.projects.data
    const projects = projectList.map((project) => ({
        id: project.projectCode,
        title: project.projectName,
        view: ( <>
                <img
                    className="button-view"
                    src="Eye_icon.svg"
                    alt="View"
                    onClick={() => handleRowClick(project.projectCode)}
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
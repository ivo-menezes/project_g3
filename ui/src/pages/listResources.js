import React, {useContext} from "react";
import Header from "../components/header";
import Button from "../components/button";
import Table from "../components/table";
import AppContext from "../context/AppContext";
import {useNavigate, useParams} from "react-router-dom";

const headers =  [
    {label: "Project Code", key: "projectCode"},
    {label: "Role", key: "role"},
    {label: "Email", key: "email"},
    {label: "Start Date", key: "startDate"},
    {label: "End Date", key: "endDate"},
    {label: "Cost Per Hour", key: "costPerHour"},
    {label: "Percentage of Allocation", key: "allocationPercentage"},
]

const ResourceList = () => {
    const{projectCode} = useParams();
    const {state} = useContext(AppContext);
    const navigate = useNavigate();

    const resourcesInProject = state.resources.filter((resource) =>
    resource.projectCode === projectCode);
    console.log(resourcesInProject);

    const handleAssociateResource = () => {
        console.log(projectCode);
        navigate(`/associateResource/${projectCode}`);
    }
    const handleReturn = (projectCode) => {
        console.log(projectCode);
        navigate(`/viewProject/${projectCode}`)
    }

    return(
        <div>
            <Header text={`Resource list for project ${projectCode}`}
                    className="header-listProjects"
                    style={{marginLeft: "65px"}}
            />
            {resourcesInProject.length > 0 ? (
                <Table data={resourcesInProject} headers={headers} />
            ) : (
                <div className="string-format">
                    <h2>This project has no resources!</h2>
                </div>
            )}
            <div className="bt-container">
                <Button
                    style={{ marginLeft: "50px"}}
                    className={"button-ListProjects"}
                    name="Associate Resource"
                    onClick={handleAssociateResource}
                    />
                <Button className="button-ListProjects" name="Back to Project"
                        onClick={() => handleReturn(projectCode)}/>
            </div>
        </div>
    );
};
export default ResourceList;
import React, {useContext, useEffect} from "react";
import Header from "../components/header";
import Button from "../components/button";
import Table from "../components/table";
import AppContext from "../context/AppContext";
import {useNavigate, useParams} from "react-router-dom";
import {fetchResources} from "../context/Actions";

const headers =  [
    {label: "Role", key: "role"},
    {label: "Email", key: "email"},
    {label: "Start Date", key: "startDate"},
    {label: "End Date", key: "endDate"},
    {label: "Cost/Hour", key: "costPerHour"},
    {label: "% Allocation", key: "allocationPercentage"}
]

const ResourceList = () => {
    //The effect scrolls the window to the top of the page, ensuring that the header and the top portion
    //of the content are visible when rendering the page.
    useEffect(() => {
        window.scrollTo(0, 0);

        fetchResources(dispatch, projectCode)
    }, []);

    const{projectCode} = useParams();
    const {state, dispatch} = useContext(AppContext);
    const navigate = useNavigate();

    const resourcesInProject = state.resources.data.filter((resource) =>
    resource.projectCode === projectCode);
    console.log(resourcesInProject);

    const resource = resourcesInProject.map((resource) => ({
        role: resource.role,
        email: resource.email,
        startDate: resource.startDate,
        endDate: resource.endDate,
        costPerHour: resource.costPerHour,
        allocationPercentage: resource.percentageOfAllocation
    }));

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
            <Header/>
            <div className="header-background-container"/>
            <Header text={`Resource list - Project ${projectCode}`}
                    className="header-list"
            />
            <div className="table-container-c">
            {resourcesInProject.length > 0 ? (
                <Table className="table-c" data={resource} headers={headers} />
            ) : (
                <div className="string-notification">
                    <h2>This project has no resources!</h2>
                </div>
            )}
            </div>
            <div className="bt-container">
                <Button
                    className={"button-edit-stuff"}
                    name="Associate Resource"
                    onClick={handleAssociateResource}
                    />
                <Button className="button-edit-stuff" name="Back to Project"
                        onClick={() => handleReturn(projectCode)}/>
            </div>
        </div>
    );
};
export default ResourceList;
import {
    createBrowserRouter
} from 'react-router-dom';

import App from "../App";
import ListProjects from "../pages/listProjects";
import CreateProject from "../pages/createProject";
import ViewProject from "../pages/viewProject";
import CreateUserStory from '../pages/createUserStory';
import ListSprints from '../pages/listSprint';
import ProjectBacklog from '../pages/projectBacklog';
import CreateSprint from '../pages/createSprint';
import ViewSprint from "../pages/viewSprintPage";
import PageUnderConstruction from "../pages/pageUnderConstruction";
import ListResources from "../pages/listResources";
import AssociateResource from "../pages/associateResource";
import AboutUs from "../pages/aboutUs";
import SprintBacklog from "../pages/sprintBacklog";
import AddUserStory from "../pages/addUserStoryToSprintBacklog"; // Import the SprintBacklog component


const Router= createBrowserRouter([
    {
        path:"/",
        element: <App/>,
    },
    {
        path:"/pageUnderConstruction",
        element: <PageUnderConstruction/>,
    },
    {
        path:"/listProjects",
        element: <ListProjects/>,
    },

    {
        path: "/createProject",
        element: <CreateProject/>
    },
    {
        path: "/createUserStory/:projectCode",
        element: <CreateUserStory/>
    },
    {
        path: "/listSprints/:projectCode",
        element: <ListSprints/>
    },
    {
        path: "/createSprint",
        element: <CreateSprint/>
    },
    {
        path: "/backlog/:projectCode",
        element: <ProjectBacklog />
    },
    {
        path: "/viewSprintPage",
        element: <ViewSprint />
    },
    {
        path: "/viewProject/:id",
        element: <ViewProject />
    },
    {
        path: "/createSprint/:projectCode",
        element: <CreateSprint />,
    },
    {
        path: "/listResources/:projectCode",
        element: <ListResources />,
    },
    {
        path: "/associateResource/:projectCode",
        element: <AssociateResource />,
    },
    {
        path: "/aboutUs",
        element: <AboutUs/>
    },
    // Add new route for the sprintBacklog page
    {
        path: "/sprintBacklog/:projectCode/:sprintNumber",
        element: <SprintBacklog />,
    },
    // Add new route for the addUserStoryToSprintBacklog page
    {
        path: "/addUserStoryToSprintBacklog/:projectCode/:sprintNumber",
        element: <AddUserStory />,
    }

]);
export default Router;
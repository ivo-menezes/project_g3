import {
    createBrowserRouter
} from 'react-router-dom';

import App from "../App";
import ListProjects from "../pages/listProjects";
import CreateProject from "../pages/createProject";
import ViewProject from "../pages/viewProject";
import CreateUserStory from '../pages/createUserStory';
import ListSprints from '../pages/listSprint';
import ConsultBacklog from '../pages/consultBacklog';
import CreateSprint from '../pages/createSprint';
import ViewSprint from "../pages/viewSprintPage";
import PageUnderConstruction from "../pages/pageUnderConstruction";
import ListResources from "../pages/listResources";
import AssociateResource from "../pages/associateResource";
import AboutUs from "../pages/aboutUs";


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
        element: <ConsultBacklog />
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
    }
]);
export default Router;
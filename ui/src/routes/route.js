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


const Router= createBrowserRouter([
    {
        path:"/",
        element: <App/>,
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
        path: "/createUserStory",
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
    }
]);
export default Router;
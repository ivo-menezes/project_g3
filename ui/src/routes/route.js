import {
    createBrowserRouter
} from 'react-router-dom';

import App from "../App";
import ListProjects from "../pages/listProjects";
import CreateProject from "../pages/createProject";
import TestPage from "../pages/testPage";
import CreateUserStory from '../pages/createUserStory';
import ListSprints from '../pages/listSprint';
import CreateSprint from '../pages/createSprint';


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
        path: "/testPage",
        element: <TestPage />
    },
    {
        path: "/createUserStory",
        element: <CreateUserStory/>
    },
    {
        path: "/listSprints",
        element: <ListSprints/>
    },
    {
        path: "/createSprint",
        element: <CreateSprint/>
    }
]);
export default Router;
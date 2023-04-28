import {
    createBrowserRouter
} from 'react-router-dom';

import App from "../App";
import ListProjects from "../pages/listProjects";
import CreateProject from "../pages/createProject";

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
    }
]);
export default Router;
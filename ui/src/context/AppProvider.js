import React, {useReducer} from 'react';
import PropTypes from "prop-types";
import { Provider } from "./AppContext";
import reducer from './Reducer';
import projects from "../store/projects";
import backlogs from "../store/productBacklogs";
import sprints from "../store/sprints";
import resources from "../store/resources";

/*
Will delete this part when switching from the local store to the API.
 */
const AppProvider = (props) =>{
    const initialState = {
        projectList: projects,
        backlogs : {
            loading : true,
            error : null,
            data : []
        },
        sprints : sprints,
        resources : resources,
        textValue:'',
        startDate: null,
        endDate: null,
    }

    /* When switching from the local store to the API, all properties should be empty or null. Data will be
    received from the H2 database in Runtime.

    const AppProvider = (props) => {
    const initialState = {
    projectList: [],
    backlogs: [],
    sprints: [],
    resources: [],
    textValue: '',
    startDate: null,
    endDate: null,
  };
     */

    const [state, dispatch] = useReducer(reducer, initialState);

    /* UseEffect can perform data-fetching side-effect.

    useEffect(() => {
    const fetchSetup = async () => {
    try {
      const response = await axios.get('/localhost:8080'); // API endpoint
      const setup = response.data; // Assuming the response contains the setup data
      dispatch({ type: 'ADD_SETUP', payload: setup }); // 'ADD_SETUP' needs to be created in Reducer.js.
    } catch (error) {
      console.error('Error fetching setup:', error);
    }
  };

  fetchSetup();
}, []);
     */


    return (
        <Provider value={{
            state,
            dispatch}}>
            {props.children}
        </Provider>
    );
};
AppProvider.propTypes = {
    children: PropTypes.node,
};

export default AppProvider;

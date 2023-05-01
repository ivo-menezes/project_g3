import React, {useReducer} from 'react';
import PropTypes from "prop-types";
import { Provider } from "./AppContext";
import reducer from './Reducer';
import projects from "../store/projects";
import backlogs from "../store/productBacklogs";

const AppProvider = (props) =>{
    const initialState = {
        projectList: projects,
        backlogs : backlogs,
        textValue:'',
        startDate: null,
        endDate: null,
    }

    const [state, dispatch] = useReducer(reducer, initialState);
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

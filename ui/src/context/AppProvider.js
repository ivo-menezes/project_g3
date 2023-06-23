import React, {useReducer} from 'react';
import PropTypes from "prop-types";
import { Provider } from "./AppContext";
import reducer from './Reducer';


const AppProvider = (props) =>{
    const initialState = {
        projects: {
            loading : true,
            error : null,
            data : []
        },
        backlogs : {
            loading : true,
            error : null,
            data : []
        },
        sprints : {
            loading : true,
            error : null,
            data : []
        },
        resources : {
            loading : true,
            error : null,
            data : []
        },
        customers : {
            loading : true,
            error : null,
            data : []
        },
        typologies : {
            loading : true,
            error : null,
            data : []
        },
        businessSectors : {
            loading : true,
            error : null,
            data : []
        }
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

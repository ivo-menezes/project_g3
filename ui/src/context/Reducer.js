import {
    ADD_RESOURCE,
    ADD_SPRINT,
    CHANGE_TEXT,
    FETCH_BACKLOG_ERROR,
    FETCH_BACKLOG_STARTED,
    FETCH_BACKLOG_SUCCESS
} from "./Actions";
import { ADD_USER_STORY } from "./Actions";
import {ADD_PROJECT} from "./Actions";
import {SET_START_DATE} from "./Actions";
import {SET_END_DATE} from "./Actions";

const reducer = (state, action) => {
    switch (action.type) {

        case CHANGE_TEXT:
            return {
                ...state,
                textValue: action.payload.value,
            };
        case ADD_USER_STORY:
            return {
                ...state,
                backlogs: [...state.backlogs, action.payload],
            };
        case ADD_PROJECT:
            return {
                ...state,
                projectList: [...state.projectList, action.payload],
            };
        case SET_START_DATE:
            return {
                ...state,
                startDate: action.payload,
            };
        case SET_END_DATE:
            return {
                ...state,
                endDate: action.payload,
            };
        case ADD_SPRINT:
            return {
                ...state,
                sprints: [...state.sprints, action.payload]
            }
        case ADD_RESOURCE:
            return{
                ...state,
                resources: [...state.resources, action.payload]
            }

        case FETCH_BACKLOG_STARTED:
            return {
                ...state,
                backlogs: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_BACKLOG_SUCCESS:
            return {
                ...state,
                backlogs: {
                    loading: false,
                    error: null,
                    data : action.payload
                }
            }

        case FETCH_BACKLOG_ERROR:
            console.log(action.payload)
            return {
                ...state,
                backlogs: {
                    loading: false,
                    error: action.payload,
                    data: []
                }
            }

        default:
            return state;
    }
};

export default reducer;

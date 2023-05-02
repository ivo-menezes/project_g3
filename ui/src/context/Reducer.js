import {CHANGE_TEXT} from "./Actions";
import { ADD_USER_STORY } from "./Actions";
import {ADD_PROJECT} from "./Actions";
import {SET_START_DATE} from "./Actions";
import {SET_END_DATE} from "./Actions";

const reducer = (state, action) => {
    debugger
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
        default:
            return state;
    }
};

export default reducer;
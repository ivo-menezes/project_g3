import {CHANGE_TEXT} from "./Actions";
import { ADD_USER_STORY } from "./Actions";

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
                userStories: [...state.userStories, action.payload],
            };
        default:
            return state;
    }
};

export default reducer;

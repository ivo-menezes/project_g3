import {CHANGE_TEXT} from "./Actions";

const reducer = (state, action) => {
    debugger
    switch (action.type) {

        case CHANGE_TEXT:
            return {
                ...state,
                textValue: action.payload.value,
            };
        default:
            return state;
    }
};

export default reducer;

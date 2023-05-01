export const CHANGE_TEXT = 'CHANGE_TEXT';
export const ADD_USER_STORY = 'ADD_USER_STORY';
export const  ADD_PROJECT = 'ADD_PROJECT';
export const SET_START_DATE = 'SET_START_DATE'
export const SET_END_DATE = 'SET_END_DATE'


export function changeText(value) {
    return {
        type: CHANGE_TEXT,
        payload: {
            value: value
        },
    }
};

export const addUserStory = (userStory) => {
    return {
        type: ADD_USER_STORY,
        payload: userStory,
    }
};

export const addProject = (project) => {
    return {
        type: ADD_PROJECT,
        payload: project,
    }
}

export const setStartDate = (startDate) => {
    return {
        type: SET_START_DATE,
        payload: startDate,
    }
}

export const setEndDate = (endDate) => {
    return {
        type: SET_END_DATE,
        payload: endDate,
    }
}
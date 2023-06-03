import {fetchBacklogFromBackend} from "../services/Service";

export const CHANGE_TEXT = 'CHANGE_TEXT';
export const ADD_USER_STORY = 'ADD_USER_STORY';
export const  ADD_PROJECT = 'ADD_PROJECT';
export const SET_START_DATE = 'SET_START_DATE'
export const SET_END_DATE = 'SET_END_DATE'
export const ADD_SPRINT = 'ADD_SPRINT'
export const ADD_RESOURCE = 'ADD_RESOURCE'


export function changeText(value) {
    return {
        type: CHANGE_TEXT,
        payload: {
            value: value
        },
    }
}

export const addUserStory = (dispatch, userStory) => {
    dispatch ({
        type: ADD_USER_STORY,
        payload: userStory,
    })
};

export const addProject = (dispatch, project) => {
    dispatch(
        {
            type: ADD_PROJECT,
            payload: project,
        }
    )
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

export const addSprint = (dispatch, sprint) => {
    dispatch ({
        type: ADD_SPRINT,
        payload: sprint,
    })
}

export const addResource = (dispatch, resource) => {
    dispatch({
        type: ADD_RESOURCE,
        payload: resource,
    })
}

// Fetch backlog actions
export const FETCH_BACKLOG_STARTED = "FETCH_BACKLOG_STARTED"
export const FETCH_BACKLOG_SUCCESS = "FETCH_BACKLOG_SUCCESS"
export const FETCH_BACKLOG_ERROR = "FETCH_BACKLOG_ERROR"


const fetchBacklogSuccess = (backlog) => {
    return {
        type: FETCH_BACKLOG_SUCCESS,
        payload: backlog
    }
}

const fetchBacklogFailure = (message) => {
    return {
        type: FETCH_BACKLOG_ERROR,
        payload: message
    }
}

export const fetchBacklog = (dispatch, projectCode) => {
    dispatch({
        type: FETCH_BACKLOG_STARTED
    })
    fetchBacklogFromBackend(
        (response) => dispatch(fetchBacklogSuccess(response)),
        (errorMessage) => dispatch(fetchBacklogFailure(errorMessage)),
        projectCode)
}

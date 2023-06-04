import {
    fetchBacklogFromBackend,
    fetchProjectsFromBackend,
    fetchSprintsFromBackend, postProjectToBackend, postSprintToBackend,
    postUserStoryToBackend
} from "../services/Service";

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


// Fetch projects actions
export const FETCH_PROJECTS_STARTED = "FETCH_PROJECTS_STARTED"
export const FETCH_PROJECTS_SUCCESS = "FETCH_PROJECTS_SUCCESS"
export const FETCH_PROJECTS_ERROR = "FETCH_PROJECTS_ERROR"

const fetchProjectsSuccess = (projects) => {
    return {
        type: FETCH_PROJECTS_SUCCESS,
        payload: projects
    }
}

const fetchProjectsFailure = (message) => {
    return {
        type: FETCH_PROJECTS_ERROR,
        payload: message
    }
}

export const fetchProjects = (dispatch) => {
    dispatch({
        type: FETCH_PROJECTS_STARTED
    })
    fetchProjectsFromBackend(
        (response) => dispatch(fetchProjectsSuccess(response)),
        (errorMessage) => dispatch(fetchProjectsFailure(errorMessage))
    )
}

// Fetch projects actions
export const FETCH_SPRINTS_STARTED = "FETCH_SPRINTS_STARTED"
export const FETCH_SPRINTS_SUCCESS = "FETCH_SPRINTS_SUCCESS"
export const FETCH_SPRINTS_ERROR = "FETCH_SPRINTS_ERROR"

const fetchSprintsSuccess = (sprints) => {
    return {
        type: FETCH_SPRINTS_SUCCESS,
        payload: sprints
    }
}

const fetchSprintsFailure = (message) => {
    return {
        type: FETCH_SPRINTS_ERROR,
        payload: message
    }
}

export const fetchSprints = (dispatch, projectCode) => {
    dispatch({
        type: FETCH_SPRINTS_STARTED
    })
    fetchSprintsFromBackend(
        (response) => dispatch(fetchSprintsSuccess(response)),
        (errorMessage) => dispatch(fetchSprintsFailure(errorMessage)),
        projectCode
    )
}

export const postUserStory = (dispatch, projectCode, userStory) => {
    postUserStoryToBackend(
        (response) => console.log("user story successfully posted"),
        (error) => console.log("user story failed to be posted"),
        projectCode, userStory
    )
}

export const postProject = (dispatch, project) => {
    postProjectToBackend(
        project
    )
}

export const postSprint = (dispatch, projectCode, sprint) => {
    postSprintToBackend(
        projectCode,
        sprint
    )
}
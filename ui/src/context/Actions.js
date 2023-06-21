import {
    fetchBacklogFromBackend,
    fetchProjectsFromBackend,
    fetchResourcesFromBackend,
    fetchSprintsFromBackend,
    patchStatusSprintToBackend,
    postProjectToBackend,
    postResourceToBackend,
    postSprintToBackend,
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

// Fetch sprints actions
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

// Fetch resources actions
export const FETCH_RESOURCES_STARTED = "FETCH_RESOURCES_STARTED"
export const FETCH_RESOURCES_SUCCESS = "FETCH_RESOURCES_SUCCESS"
export const FETCH_RESOURCES_ERROR = "FETCH_RESOURCES_ERROR"

const fetchResourcesSuccess = (resources) => {
    return {
        type: FETCH_RESOURCES_SUCCESS,
        payload: resources
    }
}

const fetchResourcesFailure = (message) => {
    return {
        type: FETCH_RESOURCES_ERROR,
        payload: message
    }
}

export const fetchResources = (dispatch, projectCode) => {
    dispatch({
        type: FETCH_RESOURCES_STARTED
    })
    fetchResourcesFromBackend(
        (response) => dispatch(fetchResourcesSuccess(response)),
        (errorMessage) => dispatch(fetchResourcesFailure(errorMessage)),
        projectCode
    )
}

export const postUserStory = (dispatch, projectCode, userStory, navigate) => {
    postUserStoryToBackend(
        (response) => navigate(`/backlog/${projectCode}`),
        (error) => console.log(error),
        projectCode, userStory
    )
}

export const postProject = (dispatch, project, navigate) => {
    postProjectToBackend(
        (response) => navigate('/listProjects'),
        (error) => console.log(error),
        project
    )
}

export const postSprint = (dispatch, projectCode, sprint, navigate) => {
    postSprintToBackend(
        (response) => navigate(`/listSprints/${projectCode}`),
        (error) => console.log(error),
        projectCode,
        sprint
    )
}

export const postResource = (dispatch, projectCode, resource, navigate) => {
    postResourceToBackend(
        (response) => navigate(`/listResources/${projectCode}`),
        (error) => console.log(error),
        projectCode,
        resource
    )
}

export const setSprintStatus = (dispatch, updatedSprint) => {
    //fetchSprints function to fetch the sprints and update the state using dispatch.
    patchStatusSprintToBackend(() => fetchSprints(dispatch,updatedSprint.projectCode),
                        //Function to handle errors and print to console
                        (error) => console.log(error),
                               //Is the status object that contains information about the status of the sprint
                                updatedSprint)
}
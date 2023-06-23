import {
    fetchBacklogFromBackend, fetchBusinessSectorsFromBackend, fetchCustomersFromBackend,
    fetchProjectsFromBackend,
    fetchResourcesFromBackend, fetchSprintBacklogFromBackend,
    fetchSprintsFromBackend, fetchTypologiesFromBackend, patchAddUserStoryToSprintBacklog,
    patchStatusSprintToBackend, patchStatusUserStoryToBackend,
    postProjectToBackend,
    postResourceToBackend,
    postSprintToBackend,
    postUserStoryToBackend
} from "../services/Service";

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

// Fetch customers actions
export const FETCH_CUSTOMERS_STARTED = "FETCH_CUSTOMERS_STARTED"
export const FETCH_CUSTOMERS_SUCCESS = "FETCH_CUSTOMERS_SUCCESS"
export const FETCH_CUSTOMERS_ERROR = "FETCH_CUSTOMERS_ERROR"

const fetchCustomersSuccess = (customers) => {
    return {
        type: FETCH_CUSTOMERS_SUCCESS,
        payload: customers
    }
}

const fetchCustomersFailure = (message) => {
    return {
        type: FETCH_CUSTOMERS_ERROR,
        payload: message
    }
}

export const fetchCustomers = (dispatch) => {
    dispatch({
        type: FETCH_CUSTOMERS_STARTED
    })
    fetchCustomersFromBackend(
        (response) => dispatch(fetchCustomersSuccess(response)),
        (errorMessage) => dispatch(fetchCustomersFailure(errorMessage))
    )
}

// Fetch typologies actions
export const FETCH_TYPOLOGIES_STARTED = "FETCH_TYPOLOGIES_STARTED"
export const FETCH_TYPOLOGIES_SUCCESS = "FETCH_TYPOLOGIES_SUCCESS"
export const FETCH_TYPOLOGIES_ERROR = "FETCH_TYPOLOGIES_ERROR"

const fetchTypologiesSuccess = (typologies) => {
    return {
        type: FETCH_TYPOLOGIES_SUCCESS,
        payload: typologies
    }
}

const fetchTypologiesFailure = (message) => {
    return {
        type: FETCH_TYPOLOGIES_ERROR,
        payload: message
    }
}

export const fetchTypologies = (dispatch) => {
    dispatch({
        type: FETCH_TYPOLOGIES_STARTED
    })
    fetchTypologiesFromBackend(
        (response) => dispatch(fetchTypologiesSuccess(response)),
        (errorMessage) => dispatch(fetchTypologiesFailure(errorMessage))
    )
}

// Fetch business sectors actions
export const FETCH_BUSINESSSECTORS_STARTED = "FETCH_BUSINESSSECTORS_STARTED"
export const FETCH_BUSINESSSECTORS_SUCCESS = "FETCH_BUSINESSSECTORS_SUCCESS"
export const FETCH_BUSINESSSECTORS_ERROR = "FETCH_BUSINESSSECTORS_ERROR"

const fetchBusinessSectorsSuccess = (businessSectors) => {
    return {
        type: FETCH_BUSINESSSECTORS_SUCCESS,
        payload: businessSectors
    }
}

const fetchBusinessSectorsFailure = (message) => {
    return {
        type: FETCH_BUSINESSSECTORS_ERROR,
        payload: message
    }
}

export const fetchBusinessSectors = (dispatch) => {
    dispatch({
        type: FETCH_BUSINESSSECTORS_STARTED
    })
    fetchBusinessSectorsFromBackend(
        (response) => dispatch(fetchBusinessSectorsSuccess(response)),
        (errorMessage) => dispatch(fetchBusinessSectorsFailure(errorMessage))
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

export const setUserStoryStatus = (dispatch, updatedUserStory) => {
    //fetchUserStories function to fetch the userStory and update the state using dispatch.
    patchStatusUserStoryToBackend(() => fetchSprintBacklog(dispatch,updatedUserStory.projectCode,updatedUserStory.sprintNumber),
        //Function to handle errors and print to console
        (error) => console.log(error),
        //Is the status object that contains information about the status of the user story in sprint
        updatedUserStory)
}

export const addUserStorySB = (dispatch, projectCode, sprintNumber, payload, navigate) => {
    patchAddUserStoryToSprintBacklog(
        (response) => navigate(`/sprintBacklog/${projectCode}/${sprintNumber}`),
        (error) => console.log(error),
        payload
    )
}

export const fetchSprintBacklog = (dispatch, projectCode, sprintNumber) => {
    dispatch({
        type: FETCH_BACKLOG_STARTED
    })
    fetchSprintBacklogFromBackend(
        (response) => dispatch(fetchBacklogSuccess(response)),
        (errorMessage) => dispatch(fetchBacklogFailure(errorMessage)),
        projectCode,sprintNumber)
}

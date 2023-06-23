import {
    FETCH_BACKLOG_ERROR,
    FETCH_BACKLOG_STARTED,
    FETCH_BACKLOG_SUCCESS, FETCH_BUSINESSSECTORS_ERROR,
    FETCH_BUSINESSSECTORS_STARTED, FETCH_BUSINESSSECTORS_SUCCESS,
    FETCH_CUSTOMERS_ERROR,
    FETCH_CUSTOMERS_STARTED,
    FETCH_CUSTOMERS_SUCCESS,
    FETCH_PROJECTS_ERROR,
    FETCH_PROJECTS_STARTED,
    FETCH_PROJECTS_SUCCESS,
    FETCH_RESOURCES_ERROR,
    FETCH_RESOURCES_STARTED,
    FETCH_RESOURCES_SUCCESS,
    FETCH_SPRINTS_ERROR,
    FETCH_SPRINTS_STARTED,
    FETCH_SPRINTS_SUCCESS,
    FETCH_TYPOLOGIES_ERROR,
    FETCH_TYPOLOGIES_STARTED,
    FETCH_TYPOLOGIES_SUCCESS,
} from "./Actions";


const reducer = (state, action) => {
    switch (action.type) {
        
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
            return {
                ...state,
                backlogs: {
                    loading: false,
                    error: action.payload,
                    data: []
                }
            }

        case FETCH_PROJECTS_STARTED:
            return {
                ...state,
                projects: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_PROJECTS_SUCCESS:
            return {
                ...state,
                projects: {
                    loading: false,
                    error: null,
                    data : action.payload
                }
            }

        case FETCH_PROJECTS_ERROR:
            return {
                ...state,
                projects: {
                    loading: false,
                    error: action.payload,
                    data : []
                }
            }

        case FETCH_SPRINTS_STARTED:
            return {
                ...state,
                sprints: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_SPRINTS_SUCCESS:
            return {
                ...state,
                sprints: {
                    loading: false,
                    error: null,
                    data : action.payload
                }
            }

        case FETCH_SPRINTS_ERROR:
            return {
                ...state,
                sprints: {
                    loading: false,
                    error: action.payload,
                    data : []
                }
            }

        case FETCH_RESOURCES_STARTED:
            return {
                ...state,
                resources: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_RESOURCES_SUCCESS:
            return {
                ...state,
                resources: {
                    loading: false,
                    error: null,
                    data : action.payload
                }
            }

        case FETCH_RESOURCES_ERROR:
            return {
                ...state,
                resources: {
                    loading: false,
                    error: action.payload,
                    data : []
                }
            }

        case FETCH_CUSTOMERS_STARTED:
            return {
                ...state,
                customers: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_CUSTOMERS_SUCCESS:
            return {
                ...state,
                customers: {
                    loading: false,
                    error: null,
                    data : action.payload
                }
            }

        case FETCH_CUSTOMERS_ERROR:
            return {
                ...state,
                customers: {
                    loading: false,
                    error: action.payload,
                    data : []
                }
            }

        case FETCH_TYPOLOGIES_STARTED:
            return {
                ...state,
                typologies: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_TYPOLOGIES_SUCCESS:
            return {
                ...state,
                typologies: {
                    loading: false,
                    error: null,
                    data : action.payload
                }
            }

        case FETCH_TYPOLOGIES_ERROR:
            return {
                ...state,
                typologies: {
                    loading: false,
                    error: action.payload,
                    data : []
                }
            }

        case FETCH_BUSINESSSECTORS_STARTED:
            return {
                ...state,
                businessSectors: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_BUSINESSSECTORS_SUCCESS:
            return {
                ...state,
                businessSectors: {
                    loading: false,
                    error: null,
                    data : action.payload
                }
            }

        case FETCH_BUSINESSSECTORS_ERROR:
            return {
                ...state,
                businessSectors: {
                    loading: false,
                    error: action.payload,
                    data : []
                }
            }

        default:
            return state;
    }
};

export default reducer;

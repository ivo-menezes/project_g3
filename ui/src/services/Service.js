import axios from "axios";

const API_URL = "http://localhost:8080/";

export const fetchBacklogFromBackend = (success, failure, projectCode) => {
    const ENDPOINT = `projects/${projectCode}/productbacklog`
    axios.get(API_URL + ENDPOINT)
        .then(response => {
            const responseData = response.data;
            success(responseData)
        })
        .catch(error => failure(error.message))
}

export const fetchProjectsFromBackend = (success, failure) => {
    const ENDPOINT = "projects";
    axios.get(API_URL + ENDPOINT)
        .then(response => {
            const responseData = response.data;
            success(responseData);
        })
        .catch(error => failure(error.message))
}

export const fetchSprintsFromBackend = (success, failure, projectCode) => {
    const ENDPOINT = `projects/${projectCode}/sprints`;
    axios.get(API_URL + ENDPOINT)
        .then(response => {
            const responseData = response.data;
            success(responseData);
        })
        .catch(error => failure(error.message))
}

export const postUserStoryToBackend = (success, failure, projectCode, userStory) => {
    const ENDPOINT = `projects/${projectCode}/productbacklog`;
    axios.post(API_URL + ENDPOINT, userStory)
        .then(response => success(response))
        .catch(error => failure(error))
}

export const postProjectToBackend = (project) => {
    const ENDPOINT = "projects";
    axios.post(API_URL + ENDPOINT, project)
        .then(response => console.log(response))
        .catch(error => console.log(error))
}
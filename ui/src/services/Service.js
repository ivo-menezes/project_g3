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

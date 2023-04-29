export const CHANGE_TEXT = 'CHANGE_TEXT';

export function changeText(valor) {
    return {
        type: CHANGE_TEXT,
        payload: {
            value: valor
        },
    }
};

export const ADD_USER_STORY = 'ADD_USER_STORY';

export const addUserStory = (userStory) => {
    return {
        type: ADD_USER_STORY,
        payload: userStory,
    }
};
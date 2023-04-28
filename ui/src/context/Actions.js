export const CHANGE_TEXT = 'CHANGE_TEXT';

export function changeText(valor) {
    return {
        type: CHANGE_TEXT,
        payload: {
            value: valor
        },
    }
};
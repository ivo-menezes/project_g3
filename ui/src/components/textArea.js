import React from "react";
import { changeText } from "../context/Actions";

const TextArea = ({ label, value, mandatory, placeholder, dispatch, className }) => {
    const whenTyped = (event) => {
        const action = changeText(event.target.value);
        dispatch(action);
    };

    return (
        <div className={className}>
            <div className="label-wrapper">
                <label>{label}</label>
            </div>
            <textarea
                value={value}
                onChange={whenTyped}
                required={mandatory}
                placeholder={placeholder}
            ></textarea>
        </div>
    );
};

export default TextArea;

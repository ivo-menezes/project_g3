import React from "react";
import {changeText} from "../context/Actions";

const DropDownList = ({label, mandatory, dispatch, value, items}) => {

    const whenTyped = (event) => {
        const action = changeText(event.target.value);
        dispatch(action);
    }

    return (
        <div>
            <label>{label}</label>
            <select onChange={whenTyped} required={mandatory} value={value}>
                {items.map((item) => {
                    return <option key={item}>{item}</option>;
                })}
            </select>
        </div>
    );
};

export default DropDownList;


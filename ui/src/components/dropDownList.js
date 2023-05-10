import React from "react";

const DropDownList = ({name, label, mandatory, value, items, onChange}) => {

    return (
        <div>
            <label>{label}</label>
            <select name={name} onChange={onChange} required={mandatory} value={value}>
                {items.map((item) => {
                    return <option key={item}>{item}</option>;
                })}
            </select>
        </div>
    );
};

export default DropDownList;


import React from "react";

const Button = (props) => {
    return <button{...props}>{props.name}</button>
};

export default Button;
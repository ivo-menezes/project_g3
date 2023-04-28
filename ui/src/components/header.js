import React from "react";

const Header = (props) => {
    return <h1{...props}>{props.text}</h1>;
}

export default Header;
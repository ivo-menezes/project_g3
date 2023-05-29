import React from "react";
import Header from "../components/header";

const PageUnderConstruction = () => {
    return (
        /*
        <div className="welcome-page">
            <div className="title-welcome-container">
                <Header className="string-under-construction" text="Page under construction!"/>
            </div>
        </div>
        */
        <div className="welcome-page">
            <div className="title-welcome-container">
                <Header className="string-under-construction" text="We are RELENTLESS"/>
                <div className="subtitle-welcome-container">
                    <Header className="subtitle-welcome" text={<span>and working hard to build this page for you!
                        <br/>Stay tunned</span>}/>
                </div>
            </div>
        </div>
    );
};

export default PageUnderConstruction;
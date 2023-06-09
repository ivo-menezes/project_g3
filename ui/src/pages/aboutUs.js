import React from "react";
import Header from "../components/header";

const AboutUs = () => {

    return (
        <div className="welcome-page">
            <div className="title-welcome-container-container">
                <div className="grid-container">
                    <div className="grid-container">
                        <div className="grid-item">
                            <div className="image-box">
                            <img src="http://localhost:3000/images/André_crop.jpg" alt="André" />
                            <div className="caption">André Campos</div>
                            </div>
                        </div>
                        <div className="grid-item">
                            <div className="image-box">
                            <img src="http://localhost:3000/images/Cristina_crop.jpg" alt="Cristina" />
                            <div className="caption">Cristina Teixeira</div>
                            </div>
                        </div>
                        <div className="grid-item">
                            <div className="image-box">
                            <img src="http://localhost:3000/images/Deborah_crop.jpg" alt="Deborah" />
                            <div className="caption">Deborah Oliveira</div>
                            </div>
                        </div>
                        <div className="grid-item">
                            <Header className="grid-item-text" text={<span>WE</span>} />
                        </div>
                        <div className="grid-item">
                            <Header className="grid-item-text are" text={<span>ARE</span>} />
                        </div>
                        <div className="grid-item">
                            <div className="image-box">
                            <img src="http://localhost:3000/images/Ivo_crop.jpg" alt="Ivo" />
                            <div className="caption">Ivo Menezes</div>
                            </div>
                        </div>
                        <div className="grid-item">
                            <div className="image-box">
                            <img src="http://localhost:3000/images/Joana_crop.jpg" alt="Joana" />
                            <div className="caption">Joana Fonseca</div>
                            </div>
                        </div>
                        <div className="grid-item">
                            <div className="image-box">
                            <img src="http://localhost:3000/images/Marisa_crop.jpg" alt="Marisa" />
                            <div className="caption">Marisa Graça</div>
                            </div>
                        </div>
                        <div className="grid-item">
                            <div className="image-box">
                                <img src="http://localhost:3000/images/Pedro_crop.jpg" alt="Pedro" />
                                <div className="caption">Pedro Silva</div>
                            </div>
                        </div>
                        <div className="grid-item">
                            <div className="image-box">
                                <img src="http://localhost:3000/images/Ricardo_crop.jpg" alt="Ricardo" />
                                <div className="caption">Ricardo Tavares</div>
                            </div>
                        </div>
                        <div className="grid-item">
                            <div className="image-box">
                                <img src="http://localhost:3000/images/Tiago_crop.jpg" alt="Tiago" />
                                <div className="caption">Tiago Silva</div>
                            </div>
                        </div>
                        <div className="grid-item logo">
                            <div className="image-box">
                                <img src="http://localhost:3000/images/GoScrum_logo.svg" alt="Logo" className="logo-image" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AboutUs;

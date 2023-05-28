import Header from "../components/header";
import Button from "../components/button";
import { Link } from "react-router-dom";

const Welcome = () => {
    return (
        <div className="welcome-page">
            <div className="title-welcome-container">
                <Header className="title-welcome" text="Welcome!" />
                <div className="subtitle-welcome-container">
                    <Header className="subtitle-welcome" text={<span>Start exploring your<br />projects now</span>} />
                </div>
                <Link to="/listProjects">
                    <Button className="button-welcome" name="Projects" />
                </Link>
            </div>
        </div>
    );
};

export default Welcome;

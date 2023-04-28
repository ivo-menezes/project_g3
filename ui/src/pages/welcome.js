import Header from "../components/header";
import Button from "../components/button";
import { Link } from "react-router-dom";

const Welcome = () => {
    return (
        <div className="container">
            <Header className='header-welcome' text='Welcome!'/>
            <Link to='/listProjects'>
                <Button className='button-welcome' name='Projects'/>
            </Link>

        </div>
    )
}

export default Welcome;
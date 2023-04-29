import Button from "../components/button";
import { Link } from "react-router-dom"

const TestPage = () => {
    return (
        <div>
            <h1>This is a test page</h1>
            <Link to='/createUserStory'>
                <Button className='button-ListProjects' name='Create User Story' />
            </Link>
            <Link to='/listSprints'>
                <Button className='button-ListProjects' name='Sprint List' />
            </Link>
        </div>
    )
}
export default TestPage;
import Button from "../components/button";
import Grid from "../components/grid";
import { Link }  from "react-router-dom"

const ListProjects = () => {
    return (
        <div>
            <Grid/>
            <Link to='/createProject'>
            <Button className='button-ListProjects' name='Create Project'/>
            </Link>
            <Link to='/testPage'>
                <Button className='button-ListProjects' name='Test Page'/>
            </Link>
        </div>
    )
}
export default ListProjects;
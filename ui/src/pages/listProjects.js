import Button from "../components/button";
import Grid from "../components/grid";
import { Link }  from "react-router-dom"

const ListProjects = () => {
    return (
        <div>
            <Grid/>
            <Button name='Next'/>
            <Button name='Previous'/>
            <Link to='/createProject'>
            <Button name='Create Project'/>
            </Link>
        </div>
    )
}
export default ListProjects;
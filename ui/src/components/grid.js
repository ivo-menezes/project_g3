import Button from "./button";
import projects from "../store/projects";

const Grid = () => {
    return (
        <div>
            <ol className='project-list'>
                {
                    projects.map(item => (
                        <li className='project-item' key={item.id}>
                            <h3>{item.title}</h3>
                            <Button className='project-button-view' name='View'></Button>
                        </li>
                    ))
                }
            </ol>
        </div>
    )
}

export default Grid;
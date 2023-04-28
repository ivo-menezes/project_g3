import Button from "./button";
import projects from "../store/projects";

const Grid = () => {
    return (
        <div>
            <ol>
                {
                    projects.map(item => (
                        <li key={item.id}>
                            <h3>{item.title}</h3>
                                <Button name='Edit'></Button>
                        </li>
                    ))
                }
            </ol>
        </div>
    )
}

export default Grid;
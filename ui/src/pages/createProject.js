import TextField from "../components/textField";
import AppContext from "../context/AppContext";
import {useContext} from "react";

const CreateProject = () => {
    const {state, dispatch} = useContext(AppContext);
    const {textValue} = state;
    const {code} = textValue;

    return (
        <section>
            <form>
                <h2> Fill in the information to create a project</h2>
                <TextField
                    mandatory = {true}
                    label = 'Code Project'
                    placeholder = '...'
                    dispatch={dispatch}
                    value ={code}
                />
            </form>
        </section>
    );
}
export default CreateProject;
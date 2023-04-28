import {changeText} from "../context/Actions";

const TextField = ({label, value, mandatory, placeholder, dispatch }) => {

    const whenTyped =(event)=>{
        const action = changeText(event.target.value);
        dispatch(action);
    }

    return (
        <div>
            <label>
                {label}
            </label>
            <input value={value} onChange={whenTyped} required={mandatory} placeholder={placeholder}/>
        </div>
    )

}

export default TextField;
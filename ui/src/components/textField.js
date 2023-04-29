import {changeText} from "../context/Actions";

const TextField = ({label, value, mandatory, placeholder, dispatch, className }) => {

    const whenTyped =(event)=>{
        const action = changeText(event.target.value);
        dispatch(action);
    }

    return (
        <div className={className}>
            <div className="label-wrapper">
                <label>
                    {label}
                </label>
            </div>
            <input value={value} onChange={whenTyped} required={mandatory} placeholder={placeholder}/>
        </div>
    )

}

export default TextField;
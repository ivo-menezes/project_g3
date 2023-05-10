const TextField = ({label, value, mandatory, placeholder, dispatch, className, readOnly, whenTyped , name}) => {

    return (
        <div className={className}>
            <div className="label-wrapper">
                <label>
                    {label}
                </label>
            </div>
            <input value={value}
                   onChange={whenTyped}
                   required={mandatory}
                   placeholder={placeholder}
                   readOnly={readOnly}
                   name={name}
            />
        </div>
    )

}

export default TextField;
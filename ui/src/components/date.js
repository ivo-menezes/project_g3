import React from "react";
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

const Date = ({label, mandatory, selectedDate, dateFormat, onChange}) => {

    return (
        <div>
            <label>{label}</label>
            <DatePicker
                required={mandatory}
                selected={selectedDate}
                onChange={onChange}
                dateFormat={dateFormat}
            />
        </div>

    );
};
export default Date;
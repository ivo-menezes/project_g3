import React from "react";
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

const Date = ({label, mandatory, selectedDate, dateFormat, onChange, minDate, maxDate}) => {

    return (
        <div>
            <label>{label}</label>
            <DatePicker
                required={mandatory}
                selected={selectedDate}
                onChange={onChange}
                dateFormat={dateFormat}
                minDate={minDate}
                maxDate={maxDate}
            />
        </div>

    );
};
export default Date;
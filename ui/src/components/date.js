import React from "react";
import DatePicker from 'react-datepicker';
import {setEndDate, setStartDate} from "../context/Actions";
import 'react-datepicker/dist/react-datepicker.css';

const Date = ({label, dispatch, mandatory, selectedDate, dateFormat}) => {

    const handleDateChange = (date) => {
        if (label === 'Start Date') {
            const action = setStartDate(date)
            dispatch(action);
        } else {
            const action = setEndDate(date)
            dispatch(action);
        }
    };
    return (
        <div>
            <label>{label}</label>
            <DatePicker
                required={mandatory}
                selected={selectedDate}
                onChange={handleDateChange}
                dateFormat={dateFormat}
            />
        </div>

    );
};
export default Date;
import React from 'react';
import Button from "./button";


const TableBody = ({ data, onViewClick  }) => {

    const handleViewClick = (item) => {
        onViewClick (item);
    }
    const rows = data.map((row, index) => (
        <tr key={index}>
            {Object.values(row).map((cellValue, cellIndex) =>
                (<td key={cellIndex} >{cellValue} </td>
                ))}
            <td>
                <Button className='sprint-button-view' name={'View'} onClick={() => handleViewClick(row)} ></Button>
            </td>
        </tr>
    ));

    return <tbody>{rows}</tbody>;
};

export default TableBody;
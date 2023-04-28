import React from 'react';

const TableBody = ({ data }) => {

    const rows = data.map((row, index) => (
        <tr key={index}>
            {Object.values(row).map((cellValue, cellIndex) =>
                (<td key={cellIndex} style={{border: '1px solid black'}}>{cellValue}</td>
                ))}
        </tr>
    ));

    return <tbody>{rows}</tbody>;
};

export default TableBody;
import React from "react";

const TableBody = ({ data }) => {
    return (
        <tbody>
        {data.map((row, rowIndex) => (
            <tr key={rowIndex}>
                {Object.values(row).map((cellValue, cellIndex) => (
                    <td key={cellIndex}>{cellValue}</td>
                ))}
            </tr>
        ))}
        </tbody>
    );
};

export default TableBody;
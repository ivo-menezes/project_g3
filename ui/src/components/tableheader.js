import React from 'react';

const TableHeader = ({ headers }) => {
    return (
        <thead>
        <tr>
            {Object.values(headers).map((header, index) =>
                (<th key={index} style={{border: '1px solid black'}}>{header.label}</th>
                ))}
        </tr>
        </thead>
    );
};

export default TableHeader;
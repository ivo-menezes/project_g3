import React from 'react';

const TableHeader = ({ headers }) => {
    return (
        <thead>
        <tr>
            {Object.values(headers).map((header, index) =>
                (<th key={index}>{header.label}</th>
                ))}
        </tr>
        </thead>
    );
};

export default TableHeader;
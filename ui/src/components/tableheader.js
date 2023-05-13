import React from "react";

const TableHeader = ({ headers }) => {
    return (
        <thead className="table-header">
        <tr>
            {headers.map((header) => (
                <th key={header.key}>{header.label}</th>
            ))}
        </tr>
        </thead>
    );
};

export default TableHeader;
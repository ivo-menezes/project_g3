import React from 'react';
import TableHeader from './tableheader';
import TableBody from './tablebody';

const Table = ({ headers, data, className }) => {
    return (
        <table className={`table ${className}`}>
            <TableHeader headers={headers} />
            <TableBody data={data} />
        </table>
    );
};

export default Table;
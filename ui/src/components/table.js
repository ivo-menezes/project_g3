import React from 'react';
import TableHeader from './tableheader';
import TableBody from './tablebody';

const Table = ({ headers, data}) => {
    return (
        <table className={'table'}>
            <TableHeader headers={headers} />
            <TableBody data={data}/>
        </table>
    );
};

export default Table;
import React from 'react';
import TableHeader from './tableheader';
import TableBody from './tablebody';

const Table = ({ headers, data }) => {
    return (
        <>
            <table style={{marginTop: '50px', marginLeft: '50px', border: '1px solid black'}} >
                <TableHeader headers={headers} />
                <TableBody data={data} />
            </table>
        </>
    );
};

export default Table;
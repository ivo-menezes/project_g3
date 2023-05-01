import React from 'react';
import TableHeader from './tableheader';
import TableBody from './tablebody';


const Table = ({ headers, data, onViewClick }) => {
    return (
        <>
            <table className={'table-sprint'} >
                <TableHeader headers={headers} />
                <TableBody data={data} onViewClick={onViewClick} />
            </table>
        </>
    );
};

export default Table;
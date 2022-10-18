import './Table.css';

function InvoiceTable(props) {
    return <>
        <h2>Invoices</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Street</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip</th>
                    <th>Item Type</th>
                    <th>Item ID</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                {props.tableData.map(row =>
                    <tr>
                        <td>{row.id}</td>
                        <td>{row.name}</td>
                        <td>{row.street}</td>
                        <td>{row.city}</td>
                        <td>{row.state}</td>
                        <td>{row.zip}</td>
                        <td>{row.itemType}</td>
                        <td>{row.itemId}</td>
                        <td>{row.quantity}</td>
                    </tr>)}
            </tbody>
        </table>
    </>
}

export default InvoiceTable;
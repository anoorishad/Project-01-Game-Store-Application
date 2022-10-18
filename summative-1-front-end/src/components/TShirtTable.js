import './Table.css';

function TShirtTable(props) {
    return <>
        <h2>T-Shirts</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Size</th>
                    <th>Color</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                {props.tableData.map(row =>
                    <tr>
                        <td>{row.id}</td>
                        <td>{row.size}</td>
                        <td>{row.color}</td>
                        <td>{row.description}</td>
                        <td>{row.price}</td>
                        <td>{row.quantity}</td>
                    </tr>)}
            </tbody>
        </table>
    </>
}

export default TShirtTable;
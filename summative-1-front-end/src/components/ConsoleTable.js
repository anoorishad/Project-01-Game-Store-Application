import './Table.css';

function ConsoleTable(props) {
    return <>
        <h2>Consoles</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Model</th>
                    <th>Manufacturer</th>
                    <th>Memory</th>
                    <th>Processor</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                {props.tableData.map(row =>
                    <tr>
                        <td>{row.id}</td>
                        <td>{row.model}</td>
                        <td>{row.manufacturer}</td>
                        <td>{row.memory}</td>
                        <td>{row.processor}</td>
                        <td>{row.price}</td>
                        <td>{row.quantity}</td>
                    </tr>)}
            </tbody>
        </table>
    </>
}

export default ConsoleTable;
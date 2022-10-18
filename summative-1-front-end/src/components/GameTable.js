import './Table.css';

function GameTable(props) {
    return <>
        <h2>Games</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>ESRB Rating</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Studio</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                {props.tableData.map(row =>
                    <tr>
                        <td>{row.id}</td>
                        <td>{row.title}</td>
                        <td>{row.esrbRating}</td>
                        <td>{row.description}</td>
                        <td>{row.price}</td>
                        <td>{row.studio}</td>
                        <td>{row.quantity}</td>
                    </tr>)}
            </tbody>
        </table>
    </>
}

export default GameTable;
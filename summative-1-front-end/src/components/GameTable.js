import './Table.css';

function GameTable(props) {
    function getRecordClass(row) {
        const classes = ["record"];
        if(props.activeRecordId === row.id) {
            classes.push("active")
        }
        return classes.join(" ");
    }

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
                    <tr key={row.id} className={getRecordClass(row)} onClick={() => props.setActiveRecordId(row.id)}>
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
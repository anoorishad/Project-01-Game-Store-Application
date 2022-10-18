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
                <tr>
                    <td>1</td>
                    <td>The Bestest Game EVAR!!!</td>
                    <td>M</td>
                    <td>The only game you'll ever need to play. Seriously. Why are you still reading this? Go buy it already!</td>
                    <td>69.99</td>
                    <td>AwesomeSoft Studios</td>
                    <td>2</td>
                </tr>
            </tbody>
        </table>
    </>
}

export default GameTable;
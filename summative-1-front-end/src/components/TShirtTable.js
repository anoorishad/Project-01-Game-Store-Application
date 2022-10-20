import { useEffect, useState } from "react";

import './Table.css';

function TShirtTable(props) {
    const [data, setData] = useState([]);

    const [size, setSize] = useState("");
    const [color, setColor] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    
    const [activeRecordId, setActiveRecordId] = useState(0);

    useEffect(() => {
        fetch("http://localhost:8080/tshirts")
        .then((response) => response.json()
        .then((responseBody) => setData(responseBody)))
    }, []);

    function getRecordClass(row) {
        const classes = ["record"];
        if(activeRecordId === row.id) {
            classes.push("active")
        }
        return classes.join(" ");
    }

    function onRecordClick(row) {
        if(activeRecordId === row.id) {
            setActiveRecordId(0);
            setSize("");
            setColor("");
            setDescription("");
            setPrice("");
            setQuantity("");
        }
        else {
            setActiveRecordId(row.id);
            setSize(row.size);
            setColor(row.color);
            setDescription(row.description);
            setPrice(row.price);
            setQuantity(row.quantity);
        }
    }

    function onFormSubmit(e) {
        e.preventDefault(); // Don't forget this since we're not using the default form behavior
        if(activeRecordId) {
            fetch("http://localhost:8080/tshirts", {
                method: "PUT",
                body: JSON.stringify({id: activeRecordId, size, color, description, price, quantity}),
                headers: {'Content-Type': 'application/json'}
            })
            .then((response) => {
                if(response.ok) {
                    fetch("http://localhost:8080/tshirts/" + activeRecordId)
                    .then(response => {
                        if(response.ok) {
                            response.json().then(resData => {
                                const newData = [...data].filter((value) => value.id !== resData.id); // filter out the old record that is being updated...
                                newData.push(resData); // ...and put the new version of it in
                                newData.sort((a, b) => a.id - b.id); // Sort by ID
                                setData(newData);
                            });
                        }
                        else {
                            alert("Error while getting record!");
                        }
                    });
                }
                else {
                    alert("Error while updating record!");
                }
            });
        }
        else {
            fetch("http://localhost:8080/tshirts", {
                method: "POST",
                body: JSON.stringify({size, color, description, price, quantity}),
                headers: {'Content-Type': 'application/json'}
            })
            .then((response) => {
                if(response.ok) {
                    response.json().then(resData => setData([...data, resData]))
                }
                else {
                    alert("Error while creating record!");
                }
            });
        }
    }

    return <div>
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
                {data.map(row =>
                    <tr key={row.id} className={getRecordClass(row)} onClick={() => onRecordClick(row)}>
                        <td>{row.id}</td>
                        <td>{row.size}</td>
                        <td>{row.color}</td>
                        <td>{row.description}</td>
                        <td>{row.price}</td>
                        <td>{row.quantity}</td>
                    </tr>)}
            </tbody>
        </table>
        <form onSubmit={onFormSubmit}>
            <div>
                <label htmlFor="size-input">Size</label>
                <input name="size-input" onChange={(e) => setSize(e.target.value)} value={size}></input>
            </div>
            <div>
                <label htmlFor="color-input">Color</label>
                <input name="color-input" onChange={(e) => setColor(e.target.value)} value={color}></input>
            </div>
            <div>
                <label htmlFor="description-input">Description</label>
                <input name="description-input" onChange={(e) => setDescription(e.target.value)} value={description}></input>
            </div>
            <div>
                <label htmlFor="price-input">Price</label>
                <input name="price-input" onChange={(e) => setPrice(e.target.value)} value={price}></input>
            </div>
            <div>
                <label htmlFor="quantity-input">Quantity</label>
                <input name="quantity-input" onChange={(e) => setQuantity(e.target.value)} value={quantity}></input>
            </div>
            <input type="submit"></input>
        </form>
    </div>
}

export default TShirtTable;
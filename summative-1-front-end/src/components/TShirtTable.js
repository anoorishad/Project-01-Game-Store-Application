import { useState } from "react";

import './Table.css';

function TShirtTable(props) {
    const [size, setModel] = useState("");
    const [color, setManufacturer] = useState("");
    const [description, setMemory] = useState("");
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    
    const [activeRecordId, setActiveRecordId] = useState(0);

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
            setModel("");
            setManufacturer("");
            setMemory("");
            setPrice("");
            setQuantity("");
        }
        else {
            setActiveRecordId(row.id);
            setModel(row.size);
            setManufacturer(row.color);
            setMemory(row.description);
            setPrice(row.price);
            setQuantity(row.quantity);
        }
    }

    function onFormSubmit(e) {
        e.preventDefault(); // Don't forget this since we're not using the default form behavior
        if(activeRecordId) {
            fetch("/", {
                method: "PUT",
                body: {id: activeRecordId, size, color, description, price, quantity}
            }).then(() => console.log({id: activeRecordId, size, color, description, price, quantity}));
        }
        else {
            fetch("/", {
                method: "POST",
                body: {size, color, description, price, quantity}
            }).then(() => console.log({size, color, description, price, quantity}));
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
                {props.tableData.map(row =>
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
                <label htmlFor="title-input">Size</label>
                <input name="title-input" onChange={(e) => setModel(e.target.value)} value={size}></input>
            </div>
            <div>
                <label htmlFor="esrb-rating-input">Color</label>
                <input name="esrb-rating-input" onChange={(e) => setManufacturer(e.target.value)} value={color}></input>
            </div>
            <div>
                <label htmlFor="description-input">Description</label>
                <input name="description-input" onChange={(e) => setMemory(e.target.value)} value={description}></input>
            </div>
            <div>
                <label htmlFor="studio-input">Price</label>
                <input name="studio-input" onChange={(e) => setPrice(e.target.value)} value={price}></input>
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
import { useState } from "react";

import './Table.css';

function InvoiceTable(props) {
    const [name, setName] = useState("");
    const [street, setStreet] = useState("");
    const [city, setCity] = useState("");
    const [state, setState] = useState("");
    const [zip, setZip] = useState("");
    const [itemType, setItemType] = useState("");
    const [itemId, setItemId] = useState("");
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
            setName("");
            setStreet("");
            setCity("");
            setState("");
            setZip("");
            setItemType("");
            setItemId("");
            setQuantity("");
        }
        else {
            setActiveRecordId(row.id);
            setName(row.name);
            setStreet(row.street);
            setCity(row.city);
            setState(row.state);
            setZip(row.zip);
            setItemType(row.itemType);
            setItemId(row.itemId);
            setQuantity(row.quantity);
        }
    }

    function onFormSubmit(e) {
        e.preventDefault(); // Don't forget this since we're not using the default form behavior
        if(activeRecordId) {
            fetch("/", {
                method: "PUT",
                body: {id: activeRecordId, name, street, city, state, zip, itemType, itemId, quantity}
            }).then(() => console.log({id: activeRecordId, name, street, city, state, zip, itemType, itemId, quantity}));
        }
        else {
            fetch("/", {
                method: "POST",
                body: {name, street, city, state, zip, itemType, itemId, quantity}
            }).then(() => console.log({name, street, city, state, zip, itemType, itemId, quantity}));
        }
    }

    return <div>
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
                    <tr key={row.id} className={getRecordClass(row)} onClick={() => onRecordClick(row)}>
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
        <form onSubmit={onFormSubmit}>
            <div>
                <label htmlFor="name-input">Name</label>
                <input name="name-input" onChange={(e) => setName(e.target.value)} value={name}></input>
            </div>
            <div>
                <label htmlFor="street-input">Street</label>
                <input name="street-input" onChange={(e) => setStreet(e.target.value)} value={street}></input>
            </div>
            <div>
                <label htmlFor="city-input">City</label>
                <input name="city-input" onChange={(e) => setCity(e.target.value)} value={city}></input>
            </div>
            <div>
                <label htmlFor="state-input">State</label>
                <input name="state-input" onChange={(e) => setState(e.target.value)} value={state}></input>
            </div>
            <div>
                <label htmlFor="zip-input">Zip</label>
                <input name="zip-input" onChange={(e) => setZip(e.target.value)} value={zip}></input>
            </div>
            <div>
                <label htmlFor="item-type-input">Item Type</label>
                <input name="item-type-input" onChange={(e) => setItemType(e.target.value)} value={itemType}></input>
            </div>
            <div>
                <label htmlFor="item-id-input">Item ID</label>
                <input name="item-id-input" onChange={(e) => setItemId(e.target.value)} value={itemId}></input>
            </div>
            <div>
                <label htmlFor="quantity-input">Quantity</label>
                <input name="quantity-input" onChange={(e) => setQuantity(e.target.value)} value={quantity}></input>
            </div>
            <input type="submit"></input>
        </form>
    </div>
}

export default InvoiceTable;
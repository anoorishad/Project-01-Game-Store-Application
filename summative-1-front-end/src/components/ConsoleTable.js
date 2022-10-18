import { useState } from "react";

import './Table.css';

function ConsoleTable(props) {
    const [model, setModel] = useState("");
    const [manufacturer, setManufacturer] = useState("");
    const [memory, setMemory] = useState("");
    const [processor, setProcessor] = useState("");
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
            setProcessor("");
            setPrice("");
            setQuantity("");
        }
        else {
            setActiveRecordId(row.id);
            setModel(row.model);
            setManufacturer(row.manufacturer);
            setMemory(row.memory);
            setProcessor(row.processor);
            setPrice(row.price);
            setQuantity(row.quantity);
        }
    }

    function onFormSubmit(e) {
        e.preventDefault(); // Don't forget this since we're not using the default form behavior
        if(activeRecordId) {
            fetch("/", {
                method: "PUT",
                body: {id: activeRecordId, model, manufacturer, memory, processor, price, quantity}
            }).then(() => console.log({id: activeRecordId, model, manufacturer, memory, processor, price, quantity}));
        }
        else {
            fetch("/", {
                method: "POST",
                body: {model, manufacturer, memory, processor, price, quantity}
            }).then(() => console.log({model, manufacturer, memory, processor, price, quantity}));
        }
    }

    return <div>
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
                    <tr key={row.id} className={getRecordClass(row)} onClick={() => onRecordClick(row)}>
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
        <form onSubmit={onFormSubmit}>
            <div>
                <label htmlFor="title-input">Model</label>
                <input name="title-input" onChange={(e) => setModel(e.target.value)} value={model}></input>
            </div>
            <div>
                <label htmlFor="esrb-rating-input">Manufacturer</label>
                <input name="esrb-rating-input" onChange={(e) => setManufacturer(e.target.value)} value={manufacturer}></input>
            </div>
            <div>
                <label htmlFor="description-input">Memory</label>
                <input name="description-input" onChange={(e) => setMemory(e.target.value)} value={memory}></input>
            </div>
            <div>
                <label htmlFor="price-input">Processor</label>
                <input name="price-input" onChange={(e) => setProcessor(e.target.value)} value={processor}></input>
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

export default ConsoleTable;
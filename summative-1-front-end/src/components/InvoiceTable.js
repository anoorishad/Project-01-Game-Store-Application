import { useEffect, useState } from "react";

import './Table.css';

function InvoiceTable(props) {
    const [idFilter, setIdFilter] = useState("");
    const [nameFilter, setNameFilter] = useState("");

    const [data, setData] = useState([]);

    const [name, setName] = useState("");
    const [street, setStreet] = useState("");
    const [city, setCity] = useState("");
    const [state, setState] = useState("");
    const [zip, setZip] = useState("");
    const [itemType, setItemType] = useState("");
    const [itemId, setItemId] = useState("");
    const [quantity, setQuantity] = useState("");

    useEffect(() => {
        fetch("http://localhost:8080/invoices")
        .then((response) => response.json()
        .then((responseBody) => setData(responseBody)))
    }, []);

    function onFormSubmit(e) {
        e.preventDefault(); // Don't forget this since we're not using the default form behavior
        fetch("http://localhost:8080/invoices", {
            method: "POST",
            body: JSON.stringify({name, street, city, state, zipcode: zip, itemType, itemId, quantity}),
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

    function onFilterInvoicesById(e) {
        e.preventDefault();
        if(!idFilter) {
            return;
        }
        fetch("http://localhost:8080/invoices/" + idFilter)
        .then(response => response.json()
        .then(data => setData([data]))
        .catch(err => {
            console.error(err)
            setData([]);
        }));
    }

    function onFilterInvoicesByCustomerName(e) {
        e.preventDefault();
        if(!nameFilter) {
            return;
        }
        fetch("http://localhost:8080/invoices/name/" + nameFilter)
        .then(response => response.json()
        .then(data => setData(data))
        .catch(err => {
            console.error(err)
            setData([]);
        }));
    }

    function onClearInvoiceFilters() {
        fetch("http://localhost:8080/invoices")
        .then((response) => response.json()
        .then((responseBody) => setData(responseBody)))
    }

    return <div>
        <h2>Invoices</h2>
        <form onSubmit={onFilterInvoicesById}>
            <label htmlFor="id-filter-input">Filter by ID</label>
            <input name="id-filter-input" onChange={(e) => setIdFilter(e.target.value)} value={idFilter}></input>
            <input type="submit" value="Filter"></input>
        </form>
        <form onSubmit={onFilterInvoicesByCustomerName}>
            <label htmlFor="title-filter-input">Filter by Customer Name</label>
            <input name="title-filter-input" onChange={(e) => setNameFilter(e.target.value)} value={nameFilter}></input>
            <input type="submit" value="Filter"></input>
        </form>
        <button onClick={onClearInvoiceFilters}>Show All</button>
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
                {data.map(row =>
                    <tr key={row.id}>
                        <td>{row.id}</td>
                        <td>{row.name}</td>
                        <td>{row.street}</td>
                        <td>{row.city}</td>
                        <td>{row.state}</td>
                        <td>{row.zipcode}</td>
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
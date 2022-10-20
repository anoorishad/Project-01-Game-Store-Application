import { useEffect, useState } from "react";

import './Table.css';

function ConsoleTable(props) {
    const [idFilter, setIdFilter] = useState("");
    const [manufacturerFilter, setManufacturerFilter] = useState("");

    const [data, setData] = useState([]);

    const [model, setModel] = useState("");
    const [manufacturer, setManufacturer] = useState("");
    const [memory, setMemory] = useState("");
    const [processor, setProcessor] = useState("");
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    
    const [activeRecordId, setActiveRecordId] = useState(0);

    useEffect(() => {
        fetch("http://localhost:8080/consoles")
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
            setMemory(row.memoryAmount);
            setProcessor(row.processor);
            setPrice(row.price);
            setQuantity(row.quantity);
        }
    }

    function onFormSubmit(e) {
        e.preventDefault(); // Don't forget this since we're not using the default form behavior
        if(activeRecordId) {
            fetch("http://localhost:8080/consoles", {
                method: "PUT",
                body: JSON.stringify({id: activeRecordId, model, manufacturer, memoryAmount: memory, processor, price, quantity}),
                headers: {'Content-Type': 'application/json'}
            })
            .then((response) => {
                if(response.ok) {
                    fetch("http://localhost:8080/consoles/" + activeRecordId)
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
            fetch("http://localhost:8080/consoles", {
                method: "POST",
                body: JSON.stringify({model, manufacturer, memoryAmount: memory, processor, price, quantity}),
                headers: {'Content-Type': 'application/json'}
            })
            .then((response) => {
                console.log(response);
                if(response.ok) {
                    response.json().then(resData => setData([...data, resData]))
                }
                else {
                    alert("Error while creating record!");
                }
            });
        }
    }

    function onDeleteButtonClicked(e, row) {
        e.stopPropagation(); // Don't let event propagate to record for selection/activation
        fetch("http://localhost:8080/consoles/" + row.id, {
            method: "DELETE"
        })
        .then(response => {
            if(response.ok) {
                const newData = [...data].filter((value) => value.id !== row.id); // filter out the record that is being deleted
                setData(newData);
                setActiveRecordId(0); // Don't forget to deselect the record
            }
            else {
                alert("Error while deleting record!");
            }
        })
    }

    function onFilterConsolesById(e) {
        e.preventDefault();
        if(!idFilter) {
            return;
        }
        fetch("http://localhost:8080/consoles/" + idFilter)
        .then(response => response.json()
        .then(data => setData([data]))
        .catch(err => {
            console.error(err)
            setData([]);
        }));
        setActiveRecordId(0); // Don't forget to deselect the record
    }

    function onFilterConsolesByManufacturer(e) {
        e.preventDefault();
        if(!manufacturerFilter) {
            return;
        }
        fetch("http://localhost:8080/consoles/manufacturer/" + manufacturerFilter)
        .then(response => response.json()
        .then(data => setData(data))
        .catch(err => {
            console.error(err)
            setData([]);
        }));
        setActiveRecordId(0); // Don't forget to deselect the record
    }

    function onClearConsoleFilters() {
        fetch("http://localhost:8080/consoles")
        .then((response) => response.json()
        .then((responseBody) => setData(responseBody)))
        setActiveRecordId(0); // Don't forget to deselect the record
    }

    return <div>
        <h2>Consoles</h2>
        <form onSubmit={onFilterConsolesById}>
            <label htmlFor="id-filter-input">Filter by ID</label>
            <input name="id-filter-input" onChange={(e) => setIdFilter(e.target.value)} value={idFilter}></input>
            <input type="submit" value="Filter"></input>
        </form>
        <form onSubmit={onFilterConsolesByManufacturer}>
            <label htmlFor="title-filter-input">Filter by Manufacturer</label>
            <input name="title-filter-input" onChange={(e) => setManufacturerFilter(e.target.value)} value={manufacturerFilter}></input>
            <input type="submit" value="Filter"></input>
        </form>
        <button onClick={onClearConsoleFilters}>Show All</button>
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
                {data.map(row =>
                    <tr key={row.id} className={getRecordClass(row)} onClick={() => onRecordClick(row)}>
                        <td>{row.id}</td>
                        <td>{row.model}</td>
                        <td>{row.manufacturer}</td>
                        <td>{row.memoryAmount}</td>
                        <td>{row.processor}</td>
                        <td>{row.price}</td>
                        <td>{row.quantity}</td>
                        <td><button onClick={(e) => onDeleteButtonClicked(e, row)}>Delete</button></td>
                    </tr>)}
            </tbody>
        </table>
        <form onSubmit={onFormSubmit}>
            <div>
                <label htmlFor="model-input">Model</label>
                <input name="model-input" onChange={(e) => setModel(e.target.value)} value={model}></input>
            </div>
            <div>
                <label htmlFor="manufacturer-input">Manufacturer</label>
                <input name="manufacturer-input" onChange={(e) => setManufacturer(e.target.value)} value={manufacturer}></input>
            </div>
            <div>
                <label htmlFor="memory-input">Memory</label>
                <input name="memory-input" onChange={(e) => setMemory(e.target.value)} value={memory}></input>
            </div>
            <div>
                <label htmlFor="processor-input">Processor</label>
                <input name="processor-input" onChange={(e) => setProcessor(e.target.value)} value={processor}></input>
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

export default ConsoleTable;
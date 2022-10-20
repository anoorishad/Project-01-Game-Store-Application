import { useEffect, useState } from "react";

import './Table.css';

function GameTable(props) {
    const [idFilter, setIdFilter] = useState("");
    const [titleFilter, setTitleFilter] = useState("");
    const [studioFilter, setStudioFilter] = useState("");
    const [esrbRatingFilter, setEsrbRatingFilter] = useState("");

    const [data, setData] = useState([]);
    const [title, setTitle] = useState("");
    const [esrbRating, setEsrbRating] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState("");
    const [studio, setStudio] = useState("");
    const [quantity, setQuantity] = useState("");
    
    const [activeRecordId, setActiveRecordId] = useState(0);

    useEffect(() => {
        fetch("http://localhost:8080/games")
        .then((response) => response.json()
        .then((responseBody) => setData(responseBody)))
    }, []);

    function onRecordClick(row) {
        if(activeRecordId === row.id) {
            setActiveRecordId(0);
            setTitle("");
            setEsrbRating("");
            setDescription("");
            setPrice("");
            setStudio("");
            setQuantity("");
        }
        else {
            setActiveRecordId(row.id);
            setTitle(row.title);
            setEsrbRating(row.esrbRating);
            setDescription(row.description);
            setPrice(row.price);
            setStudio(row.studio);
            setQuantity(row.quantity);
        }
    }

    function onFormSubmit(e) {
        e.preventDefault(); // Don't forget this since we're not using the default form behavior
        if(activeRecordId) {
            fetch("http://localhost:8080/games", {
                method: "PUT",
                body: JSON.stringify({id: activeRecordId, title, esrbRating, description, price, studio, quantity}),
                headers: {'Content-Type': 'application/json'}
            })
            .then((response) => {
                if(response.ok) {
                    fetch("http://localhost:8080/games/" + activeRecordId)
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
            fetch("http://localhost:8080/games", {
                method: "POST",
                body: JSON.stringify({title, esrbRating, description, price, studio, quantity}),
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

    function onDeleteButtonClicked(e, row) {
        e.stopPropagation(); // Don't let event propagate to record for selection/activation
        fetch("http://localhost:8080/games/" + row.id, {
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

    function onFilterGamesById(e) {
        e.preventDefault();
        if(!idFilter) {
            return;
        }
        fetch("http://localhost:8080/games/" + idFilter)
        .then(response => response.json()
        .then(data => setData([data]))
        .catch(err => {
            console.error(err)
            setData([]);
        }));
        setActiveRecordId(0); // Don't forget to deselect the record
    }

    function onFilterGamesByTitle(e) {
        e.preventDefault();
        if(!titleFilter) {
            return;
        }
        fetch("http://localhost:8080/games/title/" + titleFilter)
        .then(response => response.json()
        .then(data => setData(data))
        .catch(err => {
            console.error(err)
            setData([]);
        }));
        setActiveRecordId(0); // Don't forget to deselect the record
    }

    function onFilterGamesByStudio(e) {
        e.preventDefault();
        if(!studioFilter) {
            return;
        }
        fetch("http://localhost:8080/games/studio/" + studioFilter)
        .then(response => response.json()
        .then(data => setData(data))
        .catch(err => {
            console.error(err)
            setData([]);
        }));
        setActiveRecordId(0); // Don't forget to deselect the record
    }

    function onFilterGamesByEsrbRating(e) {
        e.preventDefault();
        if(!esrbRatingFilter) {
            return;
        }
        fetch("http://localhost:8080/games/esrb/" + esrbRatingFilter)
        .then(response => response.json()
        .then(data => setData(data))
        .catch(err => {
            console.error(err)
            setData([]);
        }));
        setActiveRecordId(0); // Don't forget to deselect the record
    }

    function onClearGameFilters() {
        fetch("http://localhost:8080/games")
        .then((response) => response.json()
        .then((responseBody) => setData(responseBody)))
        setActiveRecordId(0); // Don't forget to deselect the record
    }

    return <div>
        <h2>Games</h2>
        <form onSubmit={onFilterGamesById}>
            <label htmlFor="id-filter-input">Filter by ID</label>
            <input name="id-filter-input" onChange={(e) => setIdFilter(e.target.value)} value={idFilter}></input>
            <input type="submit" value="Filter"></input>
        </form>
        <form onSubmit={onFilterGamesByTitle}>
            <label htmlFor="title-filter-input">Filter by Title</label>
            <input name="title-filter-input" onChange={(e) => setTitleFilter(e.target.value)} value={titleFilter}></input>
            <input type="submit" value="Filter"></input>
        </form>
        <form onSubmit={onFilterGamesByStudio}>
            <label htmlFor="studio-filter-input">Filter by Studio</label>
            <input name="studio-filter-input" onChange={(e) => setStudioFilter(e.target.value)} value={studioFilter}></input>
            <input type="submit" value="Filter"></input>
        </form>
        <form onSubmit={onFilterGamesByEsrbRating}>
            <label htmlFor="esrb-rating-filter-input">Filter by ESRB Rating</label>
            <input name="esrb-rating-filter-input" onChange={(e) => setEsrbRatingFilter(e.target.value)} value={esrbRatingFilter}></input>
            <input type="submit" value="Filter"></input>
        </form>
        <button onClick={onClearGameFilters}>Show All</button>
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
                {data.map(row =>
                    <tr key={row.id} onClick={() => onRecordClick(row)}>
                        <td>{row.id}</td>
                        <td>{row.title}</td>
                        <td>{row.esrbRating}</td>
                        <td>{row.description}</td>
                        <td>{row.price}</td>
                        <td>{row.studio}</td>
                        <td>{row.quantity}</td>
                        <td><button onClick={(e) => onDeleteButtonClicked(e, row)}>Delete</button></td>
                    </tr>)}
            </tbody>
        </table>
        <form onSubmit={onFormSubmit}>
            <div>
                <label htmlFor="title-input">Title</label>
                <input name="title-input" onChange={(e) => setTitle(e.target.value)} value={title}></input>
            </div>
            <div>
                <label htmlFor="esrb-rating-input">ESRB Rating</label>
                <input name="esrb-rating-input" onChange={(e) => setEsrbRating(e.target.value)} value={esrbRating}></input>
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
                <label htmlFor="studio-input">Studio</label>
                <input name="studio-input" onChange={(e) => setStudio(e.target.value)} value={studio}></input>
            </div>
            <div>
                <label htmlFor="quantity-input">Quantity</label>
                <input name="quantity-input" onChange={(e) => setQuantity(e.target.value)} value={quantity}></input>
            </div>
            <input type="submit"></input>
        </form>
    </div>
}

export default GameTable;
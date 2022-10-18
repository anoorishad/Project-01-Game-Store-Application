import { useState } from "react";

function GameForm(props) {
    const [title, setTitle] = useState("");
    const [esrbRating, setEsrbRating] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState("");
    const [studio, setStudio] = useState("");
    const [quantity, setQuantity] = useState("");

    return <form onSubmit={() => alert(title)}>
        <div>
            <label htmlFor="title-input">Title</label>
            <input name="title-input" onChange={(e) => setTitle(e.target.value)}></input>
        </div>
        <div>
            <label htmlFor="esrb-rating-input">ESRB Rating</label>
            <input name="esrb-rating-input" onChange={(e) => setEsrbRating(e.target.value)}></input>
        </div>
        <div>
            <label htmlFor="description-input">Description</label>
            <input name="description-input" onChange={(e) => setDescription(e.target.value)}></input>
        </div>
        <div>
            <label htmlFor="price-input">Price</label>
            <input name="price-input" onChange={(e) => setPrice(e.target.value)}></input>
        </div>
        <div>
            <label htmlFor="studio-input">Studio</label>
            <input name="studio-input" onChange={(e) => setStudio(e.target.value)}></input>
        </div>
        <div>
            <label htmlFor="quantity-input">Quantity</label>
            <input name="quantity-input" onChange={(e) => setQuantity(e.target.value)}></input>
        </div>
        <input type="submit"></input>
    </form>
}

export default GameForm;
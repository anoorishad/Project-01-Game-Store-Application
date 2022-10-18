function GameForm(props) {
    return <form>
        <label htmlFor="title-input">Title</label>
        <input name="title-input"></input>
        <label htmlFor="esrb-rating-input">ESRB Rating</label>
        <input name="esrb-rating-input"></input>
        <label htmlFor="description-input">Description</label>
        <input name="description-input"></input>
        <label htmlFor="price-input">Price</label>
        <input name="price-input"></input>
        <label htmlFor="studio-input">Studio</label>
        <input name="studio-input"></input>
        <label htmlFor="quantity-input">Quantity</label>
        <input name="quantity-input"></input>
        <input type="submit"></input>
    </form>
}

export default GameForm;
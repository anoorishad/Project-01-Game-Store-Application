import { useState } from "react";
import GameTable from "./components/GameTable";
import ConsoleTable from "./components/ConsoleTable";
import TShirtTable from "./components/TShirtTable";
import InvoiceTable from "./components/InvoiceTable";

import './App.css';

function App() {
  const [gameData] = useState([
    {
      id: 1,
      title: "The Bestest Game EVAR!!!",
      esrbRating: "M",
      description: "The only game you'll ever need to play. Seriously. Why are you still reading this? Go buy it already!",
      price: 69.99,
      studio: "AwesomeSoft Studios",
      quantity: 2
    },
    {
      id: 2,
      title: "Mediocre Game",
      esrbRating: "T",
      description: "A standard game, with all the positive and negative connotations that apply. Opinion is sharply divided.",
      price: 29.99,
      studio: "Some Indie Studio You've Probably Never Heard Of",
      quantity: 12
    }
  ]);
  const [consoleData] = useState([
    {
      id: 1,
      model: "PreyStation",
      manufacturer: "Sonny",
      memory: "8 GB",
      processor: "Cell Processor 3",
      price: 499.99,
      quantity: 5
    }
  ]);
  const [tShirtData] = useState([
    {
      id: 1,
      size: "XL",
      color: "White",
      description: "We were too lazy to add dye to this shirt. But it's still a really nice shirt.",
      price: 6.99,
      quantity: 50
    }
  ]);
  const [invoiceData] = useState([
    {
      id: 1,
      name: "Joe Blow",
      street: "1234 Main Street",
      city: "Anytown",
      state: "AK",
      zip: 99123,
      itemType: "Game",
      itemId: 1,
      quantity: 2
    }
  ]);

  return (
    <div className="App">
      <header className="App-header">
        <h1>Summative Project 1 Front End</h1>
      </header>
      <main>
        <GameTable tableData={gameData}></GameTable>
        <ConsoleTable tableData={consoleData}></ConsoleTable>
        <TShirtTable tableData={tShirtData}></TShirtTable>
        <InvoiceTable tableData={invoiceData}></InvoiceTable>
      </main>
    </div>
  );
}

export default App;

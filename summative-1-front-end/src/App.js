import { useState } from "react";
import GameTable from "./components/GameTable";
import ConsoleTable from "./components/ConsoleTable";

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
      manufacturer: "Sonie",
      memory: "8 GB",
      processor: "Cell Processor 3",
      price: 499.99,
      quantity: 5
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
      </main>
    </div>
  );
}

export default App;

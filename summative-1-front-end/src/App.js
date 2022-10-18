import { useState } from "react";
import GameTable from "./components/GameTable";

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
    }
  ]);

  return (
    <div className="App">
      <header className="App-header">
        <h1>Summative Project 1 Front End</h1>
      </header>
      <main>
        <GameTable tableData={gameData}></GameTable>
      </main>
    </div>
  );
}

export default App;

import { useState } from "react";
import GameTable from "./components/GameTable";
import ConsoleTable from "./components/ConsoleTable";
import TShirtTable from "./components/TShirtTable";
import InvoiceTable from "./components/InvoiceTable";
import GameForm from "./components/GameForm";

import './App.css';

function App() {
  const [activeTab, setActiveTab] = useState("game");

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

  function getViewTabButtonStyle(tabName) {
    let className = "view-tab";
    if(activeTab === tabName) {
      className += " active";
    }
    return className;
  }

  function getVisibleTable() {
    switch(activeTab) {
      case "game":
        return <>
            <GameTable tableData={gameData}></GameTable>
          </>
      case "console":
        return <ConsoleTable tableData={consoleData}></ConsoleTable>
      case "t-shirt":
        return <TShirtTable tableData={tShirtData}></TShirtTable>
      case "invoice":
        return <InvoiceTable tableData={invoiceData}></InvoiceTable>
      default:
        return <div>Invalid active tab value: {activeTab}</div>
    }
  }

  function loadActiveRecordIntoGameFormFields(tab) {
    
  }

  return (
    <div className="App">
      <header className="App-header">
        <h1>Summative Project 1 Front End</h1>
      </header>
      <main>
        <div>
          <button className={getViewTabButtonStyle("game")} onClick={() => setActiveTab("game")}>Games</button>
          <button className={getViewTabButtonStyle("console")} onClick={() => setActiveTab("console")}>Consoles</button>
          <button className={getViewTabButtonStyle("t-shirt")} onClick={() => setActiveTab("t-shirt")}>T-Shirts</button>
          <button className={getViewTabButtonStyle("invoice")} onClick={() => setActiveTab("invoice")}>Invoices</button>
        </div>
        <div>
          {getVisibleTable()}
        </div>
      </main>
    </div>
  );
}

export default App;

import { useState } from "react";
import GameTable from "./components/GameTable";
import ConsoleTable from "./components/ConsoleTable";
import TShirtTable from "./components/TShirtTable";
import InvoiceTable from "./components/InvoiceTable";

import './App.css';

function App() {
  const [activeTab, setActiveTab] = useState("game");

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
        return <GameTable></GameTable>
      case "console":
        return <ConsoleTable></ConsoleTable>
      case "t-shirt":
        return <TShirtTable></TShirtTable>
      case "invoice":
        return <InvoiceTable tableData={invoiceData}></InvoiceTable>
      default:
        return <div>Invalid active tab value: {activeTab}</div>
    }
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

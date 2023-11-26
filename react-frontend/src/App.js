import React from 'react';
import { Button } from "antd";
import './App.css';
import EmployeeComponent from "./component/EmployeeComponent";

function App() {
  return (
      <div className="container">
        <EmployeeComponent/>
        <Button type="primary">Button</Button>
      </div>
  );
}
export default App;

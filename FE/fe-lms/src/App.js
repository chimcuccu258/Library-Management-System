import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Dashboard from "./pages/admin/Dashboard";
import Login from "./pages/admin/Login";
import "./App.css";
import "./"
import UserProfile from "./pages/admin/userProfile";

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/admin/dashboard" element={<Dashboard />} />
          <Route path="/admin/login" element={<Login />} />
          <Route path="/admin/userProfile" element={<UserProfile />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;




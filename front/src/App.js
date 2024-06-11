import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import ChatApp from "./pages/ChatApp";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/chat" element={<ChatApp />} />
            </Routes>
        </Router>
    );
}

export default App;

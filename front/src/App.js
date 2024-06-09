import { HttpGet } from "./service/httpService";
import React from "react";
import { useState, useEffect } from "react";

function App() {
   const [hello, setHello] = useState('')

    useEffect(() => {
        HttpGet("/api/hello").then((data)=>{
            if(data) {
                setHello(data);
            }
        });
    }, []);

    return (
        <div>
            데이터 가져오기 = {hello}
        </div>
    );
}

export default App;

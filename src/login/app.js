const { request, response } = require("express");
const express = require("express");
const {MongoClient} = require("mongodb");
const bodyParser = require("body-parser");
const port = 3000;
const url="";
const dbName = "lab5";
const colName = "user-info";

const app = express();
app.use(bodyParser.urlencoded());

app.get("/", (request, response) => {
    response.sendFile(__dirname+"/"+"index.html")
});

app.post("/", (request, response) => {
    let data = {
        name: request.body.name,
        lang: request.body.lang,
        progLang: request.body.progLang
    }
    MongoClient.connect(url, (err, db) => {
        if (err) throw err;
        let dbo = db.db(dbName);
        dbo.collection(colName).insertOne(data, (err, res) => {
            if (err) throw err;
            console.log("One record was inserted");
            db.close();
        });
    });
    response.sendFile(__dirname+"/"+"index.html");
});

app.listen(port, (err) => {
    if (err) throw err;
    console.log("Running");
});

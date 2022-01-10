/**
 JS for consumer
 Author: Dana Clemmer
 File: consumerScript.js
 Date: 11/23/2021
 **/

window.onload = function() {
    //access the API here...
    let uri = "http://localhost:8080/pnwAdventures/api/adventures";
    let config = {
        method: "GET",
        mode: "cors",
        headers: {
            "Content-Type": "application/json"
        }
    }

    fetch(uri, config)
        .then((response) => {
            return response.json();
        })
        .then((json) => {
            displayDataElements(json);
        });
}

function displayDataElements(jsonData)
{
    //select the area to put our data within
    let fillTable = document.getElementById("adventures");

    for (let i = 0; i < jsonData.length; i++)
    {
        addAdventureRow(fillTable, jsonData[i]);
    }
}

function addAdventureRow(fillTable, adventure)
{
    //create a new row
    let row = document.createElement("tr");
    let id = document.createElement("td");
    let destination = document.createElement("td");
    let region = document.createElement("td");
    let state = document.createElement("td");
    let itinerary = document.createElement("td");
    let beach = document.createElement("td");
    let camp = document.createElement("td");
    let hike = document.createElement("td");
    let waterfall = document.createElement("td");

    //assign data values
    id.innerHTML = adventure.adventureId;
    destination.innerHTML = adventure.destination;
    region.innerHTML = adventure.region;
    state.innerHTML = adventure.state;
    itinerary.innerHTML = adventure.itinerary;
    beach.innerHTML = adventure.beachIncluded;
    camp.innerHTML = adventure.campIncluded;
    hike.innerHTML = adventure.hikeIncluded;
    waterfall.innerHTML = adventure.waterfallIncluded;

    //assemble the row
    row.appendChild(id);
    row.appendChild(destination);
    row.appendChild(region);
    row.appendChild(state);
    row.appendChild(itinerary);
    row.appendChild(beach);
    row.appendChild(camp);
    row.appendChild(hike);
    row.appendChild(waterfall);

    fillTable.appendChild(row);
}
/**
 JS for admin
 Author: Dana Clemmer
 File: dashboardScript.js
 Date: 11/23/2021
 **/

window.onload = function() {
    numRequestsSite();
    numRequestsAdventureApi();
    port();
    timezone();
    country();
    operatingSystem();
    sessions();
    bufferMem();
}

function numRequestsSite()
{
    let uri = "http://localhost:8080/actuator/metrics/http.server.requests";
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
            displayNumRequestsSite(json);
        });
}

function displayNumRequestsSite(jsonData)
{
    //select the area to put our data within
    let numRequests = document.getElementById("numRequestsSite");

    //assign data values
    numRequests.innerHTML = jsonData.measurements[0].value;

}

function numRequestsAdventureApi()
{
    let uri = "http://localhost:8080/actuator/metrics/http.server.requests?tag=uri:/api/adventures";
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
            displayNumRequestsAdvApi(json);
        });
}

function displayNumRequestsAdvApi(jsonData)
{
    //select the area to put our data within
    let numRequests = document.getElementById("numRequestsAdvApi");

    //assign data values
    numRequests.innerHTML = numRequests.innerHTML.replace('0' , jsonData.measurements[0].value);

}

function sessions()
{
    let uri = "http://localhost:8080/actuator/metrics/tomcat.sessions.active.current";
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
            numSessions(json);
        });
}

function numSessions(jsonData)
{
    //select the area to put our data within
    let numSessions = document.getElementById("sessions");

    //assign data values
    numSessions.innerHTML = numSessions.innerHTML.replace("0", jsonData.measurements[0].value);
}

function bufferMem()
{
    let uri = "http://localhost:8080/actuator/metrics/jvm.buffer.memory.used";
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
            memAmount(json);
        });
}

function memAmount(jsonData)
{
    //select the area to put our data within
    let bufferMem = document.getElementById("bufferMem");

    //assign data values
    bufferMem.innerHTML = jsonData.measurements[0].value;
}

function port()
{
    let uri = "http://localhost:8080/actuator/env";
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
            displayPort(json);
        });
}

function displayPort(jsonData)
{
    //select the area to put our data within
    let port = document.getElementById("port");

    //assign data values
    port.innerHTML = jsonData.propertySources[0].properties["local.server.port"].value;
}

function timezone()
{
    let uri = "http://localhost:8080/actuator/env";
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
            displayTimezone(json);
        });
}

function displayTimezone(jsonData)
{
    //select the area to put our data within
    let timezone = document.getElementById("timezone");

    //assign data values
    timezone.innerHTML = jsonData.propertySources[2].properties["user.timezone"].value;
}

function country()
{
    let uri = "http://localhost:8080/actuator/env";
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
            displayCountry(json);
        });
}

function displayCountry(jsonData)
{
    //select the area to put our data within
    let country = document.getElementById("country");

    //assign data values
    country.innerHTML = jsonData.propertySources[2].properties["user.country"].value;
}

function operatingSystem()
{
    let uri = "http://localhost:8080/actuator/env";
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
            displayOs(json);
        });
}

function displayOs(jsonData)
{
    //select the area to put our data within
    let os = document.getElementById("os");

    //assign data values
    os.innerHTML = jsonData.propertySources[2].properties["os.name"].value;
}
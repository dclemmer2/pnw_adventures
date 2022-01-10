/**
 JS for site
 Author: Dana Clemmer
 File: javascript.js
 Date: 10/20/2021
 **/

document.getElementById('switchView').onclick = switchDisplay;

//switch between summary pg display of table or grid
function switchDisplay() {
    let table = document.getElementById('table');
    let grid = document.getElementById('grid');

    if(table.classList.contains('d-none')) {
        table.classList.remove('d-none');
        grid.classList.add('d-none');
    }
    else {
        table.classList.add('d-none');
        grid.classList.remove('d-none');
    }
}

//populate state field based on what region user selects
function populateState() {
    let region = document.getElementById('region');
    let selectedValue = region.options[region.selectedIndex].value;
    let state = document.getElementById('state');
    if(selectedValue === "Puget Sound" || selectedValue === "Olympic Peninsula" || selectedValue === "North Cascades" ||
        selectedValue === "Central Cascades" || selectedValue === "South Cascades") {
        state.value = "WA";
    }
    else if(selectedValue === "Columbia River Gorge") {
        state.value = "WA/OR";
    }
    else if(selectedValue === "Oregon Coast") {
        state.value = "OR";
    }
}

//Required field flags:
function firstRequired() {
    let input = document.getElementById('first').value;
    let flag = document.getElementById('firstRequired');
    if(input !== "")
        flag.classList.add('d-none');
    else
        flag.classList.remove('d-none');
}
function lastRequired() {
    let input = document.getElementById('last').value;
    let flag = document.getElementById('lastRequired');
    if(input !== "")
        flag.classList.add('d-none');
    else
        flag.classList.remove('d-none');
}
function userRequired() {
    let input = document.getElementById('username').value;
    let flag = document.getElementById('userRequired');
    if(input !== "")
        flag.classList.add('d-none');
    else
        flag.classList.remove('d-none');
}
function passRequired() {
    let input = document.getElementById('pass').value;
    let flag = document.getElementById('passRequired');
    if(input !== "")
        flag.classList.add('d-none');
    else
        flag.classList.remove('d-none');
}
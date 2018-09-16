

function addNewComponent() {
    var parser = new DOMParser();
    var xhr= new XMLHttpRequest();
    xhr.open('GET', '/assets/html/newComponent.html', false);
    xhr.onreadystatechange= function() {
        if (this.readyState!==4) return;
        if (this.status!==200) return;
        var node = parser.parseFromString(this.responseText, "text/html");
        node = node.getElementsByClassName("form-group")[0];
        document.getElementById('component-rows').appendChild(node);
    };
    xhr.send();
    addUnits();
}


function addMainContent(path) {
    var xhr= new XMLHttpRequest();
    xhr.open('GET', path, false);
    xhr.onreadystatechange= function() {
        if (this.readyState!==4) return;
        if (this.status!==200) return;
        document.getElementById('main-content').innerHTML = this.responseText;
        // document.getElementById('main-form').onkeypress = disableKeyWordSubmit
    };
    xhr.send();
}


function addSuggestComponents() {
    var xhr= new XMLHttpRequest();
    xhr.open('GET', "/api/component", true);
    xhr.onreadystatechange= function() {
        if (this.readyState!==4) return;
        if (this.status!==200) return; // or whatever error handling you want
        var json = JSON.parse(this.responseText);
        var node;
        json.forEach(element => {
            node = document.createElement("option")
            node.setAttribute('value', element.name);
            document.getElementById('components').appendChild(node);
            
        });
    };
    xhr.send();
}

function addUnits() {
    //TODO: not tested yet
    var injectPlace = document.getElementsByClassName("select-unit")
    var xhr= new XMLHttpRequest();
    xhr.open('GET', "/api/unit", true);
    xhr.onreadystatechange= function() {
        if (this.readyState!==4) return;
        if (this.status!==200) return; // or whatever error handling you want
        var json = JSON.parse(this.responseText);
        var node;
        json.forEach(element => {
            node = document.createElement("option")
            node.setAttribute('value', element.name);
            node.innerHTML = element.name
            injectPlace[injectPlace.length-1].appendChild(node);
            
        });
    };
    xhr.send();
}


function addAlertMessage(message, colour) {
    var node = document.createElement("div");
    node.setAttribute("class", "alert " + colour);
    node.setAttribute("role", "alert");
    node.innerHTML = message;

    document.getElementById('alert-div').innerHTML = node.outerHTML;
}

function generateTable(days) {
    addMainContent("/assets/html/addTable.html");
    let soupIds = getCollectionFromDatabase("soup", "id");
    let dishIds = getCollectionFromDatabase("dish", "id");

    let randomSoups = getRandom(soupIds, days.length);
    let randomDishes = getRandom(dishIds, days.length);

    for(let i=0; i<days.length; i++) {
        addTableRow(days[i], randomSoups[i], randomDishes[i])
    }
}

function addTableRow(day, soupId, dishId) {
    let xhr= new XMLHttpRequest();
    let parser = new DOMParser();


    var soupJson = getElementFromDatabase("soup", soupId);
    var dishJson = getElementFromDatabase("dish", dishId);


    xhr.open('GET', "/assets/html/addRow.html", false);
    xhr.onreadystatechange= function() {
        if (this.readyState!==4) return;
        if (this.status!==200) return;
        var node = parser.parseFromString(this.responseText, "text/xml").documentElement;

        node.getElementsByTagName("th")[0].innerHTML = day;

        var soupNode = node.getElementsByClassName("soup")[0];
        var dishNode = node.getElementsByClassName("dish")[0];

        soupNode.setAttribute("id", soupJson["id"]);
        soupNode.innerHTML = soupJson["name"];
        console.log(dishJson);
        console.log(dishNode);
        console.log(dishNode.getAttribute("id"));
        dishNode.setAttribute("id", dishJson["id"]);
        dishNode.innerHTML = dishJson["name"];

        document.getElementsByTagName('tbody')[0].innerHTML += node.outerHTML;
    };
    xhr.send();
}

function cleanContent() {
    document.getElementById('main-content').innerHTML = ""
}








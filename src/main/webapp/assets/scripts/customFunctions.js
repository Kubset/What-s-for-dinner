Array.prototype.contains = function ( needle ) {
    for (let i in this) {
        if (this[i] === needle) return true;
    }
    return false;
 };

Array.prototype.remove = function (array) {
     for(let i=0; i<array.length; i++) {
        let index = this.indexOf(array[i]);
        if(index !== -1) {
            this.splice(this.indexOf(array[i]),1)
        }
    }
};

function getRandom(array, n) {
    let result = new Array(n),
        len = array.length,
        taken = new Array(len);
    if (n > len)
        throw new RangeError("getRandom: more elements taken than available");
    while (n--) {
        let x = Math.floor(Math.random() * len);
        result[n] = array[x in taken ? taken[x] : x];
        taken[x] = --len in taken ? taken[len] : len;
    }
    return result;
}


function getCollectionFromDatabase(collectionName,attribute) {
    let elements = [];
    let xhr= new XMLHttpRequest();
    xhr.open('GET', "/api/"+ collectionName, false);
    xhr.onreadystatechange = function() {
        if (this.readyState!==4) return;
        if (this.status!==200) return;
        let json = JSON.parse(this.responseText);
        json.forEach(element => {
            elements.push(element[attribute])
        });
    };
    xhr.send();

    return elements;
}

function getElementFromDatabase(elementName, id) {
    let json = "";
    let xhr= new XMLHttpRequest();

    xhr.open('GET', "/api/" + elementName + "/" + id, false);
    xhr.onreadystatechange = function() {
        if (this.readyState!==4) return;
        if (this.status!==200) return;
        json = JSON.parse(this.responseText);
    };
    xhr.send();

    return json;
}

function deleteComponent(element) {
    element.parentNode.parentNode.remove()
}

function reloadComponents(element) {
    let componentType = document.getElementById("-1").getAttribute("class");
    console.log(componentType)
    let icon = document.getElementById("-1").parentNode.lastChild;
    icon.setAttribute("class", "fas fa-trash-alt small-trash");
    icon.setAttribute("onclick", "changeComponent(this,'" + componentType + "')");
    changeComponent(element, componentType);

}
function changeComponent(element, componentType) {
    let isReload = document.getElementsByClassName("fas fa-sync small-sync").length;
    if(isReload) return;

    let records = document.getElementsByClassName(componentType);
    let currentNode = element.parentNode.getElementsByTagName("div")[0];
    let tableNode = document.getElementsByTagName("table")[0];

    tableNode.setAttribute("data-used-id",
                tableNode.getAttribute("data-used-id") + currentNode.getAttribute("id") + " ");

    let allIds = getCollectionFromDatabase(componentType,"id");
    let usedIds = tableNode.getAttribute("data-used-id")
                           .split(" ")
                           .map(item => {if(item.length)
                                        return parseInt(item,10)});

    let currentIds = [];
    for(let i=0; i<records.length; i++) {
        currentIds[i] = parseInt(records[i].getAttribute("id"))
    }

    allIds.remove(usedIds);
    allIds.remove(currentIds);

    if(allIds.length > 0) {
        let randomId = allIds[Math.floor(Math.random()*allIds.length)];
        let json = getElementFromDatabase(componentType, randomId);

        currentNode.setAttribute("id", randomId);
        currentNode.innerHTML = json["name"];
    } else {
        let currentNode = element.parentNode;
        tableNode.setAttribute("data-used-id", "");
        currentNode.innerHTML = "<div id='-1' class='" + componentType + "'></div>" +
                                "<i class='fas fa-sync small-sync' onclick ='reloadComponents(this)'></i>"
    }
}


function postDinnerCollection() {

    let soups = document.getElementsByClassName("soup");
    let dishes = document.getElementsByClassName("dish");

    let soupIds = [];
    let dishIds = [];
    for(let i=0; i<soups.length; i++) {
       soupIds.push(soups[i].getAttribute("id"))
    }
    for(let i=0; i<dishes.length; i++) {
        dishIds.push(dishes[i].getAttribute("id"))
    }

    addAlertMessage("processing...", "alert-success");

    setTimeout(function(){
        cleanContent();
        generateDishComponentRecipeTable(dishIds, soupIds);
    }, 2000);


}



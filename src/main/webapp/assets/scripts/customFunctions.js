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

Array.prototype.contains = function ( needle ) {
    for (let i in this) {
        if (this[i] === needle) return true;
    }
    return false;
 };



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

function deleteComponent(element) {
    element.parentNode.parentNode.remove()
}

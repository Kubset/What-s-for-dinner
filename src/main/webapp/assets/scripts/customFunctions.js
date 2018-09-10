Array.prototype.contains = function ( needle ) {
    for (i in this) {
        console.log(i)
        if (this[i] == needle) return true;
    }
    return false;
 }


function getSoupsFromDatabase() {
    var soups = []
    var xhr= new XMLHttpRequest();
    xhr.open('GET', "/api/soup", false);
    xhr.onreadystatechange = function() {
        if (this.readyState!==4) return;
        if (this.status!==200) return;
        var json = JSON.parse(this.responseText);
        var node;
        json.forEach(element => {
            soups.push(element["name"])
        });
    };
    xhr.send();

    return soups;
}

function deleteComponent(element) {
    element.parentNode.parentNode.remove()
}
function addNewComponent() {
    var parser = new DOMParser();
    var xhr= new XMLHttpRequest();
    xhr.open('GET', '/assets/html/newComponent.html', true);
    xhr.onreadystatechange= function() {
        if (this.readyState!==4) return;
        if (this.status!==200) return; // or whatever error handling you want
        var node = parser.parseFromString(this.responseText, "text/html");
        node = node.getElementsByClassName("form-group")[0];
        document.getElementById('component-rows').appendChild(node);
    };
    xhr.send();
}

function addMainContent(path) {
    var xhr= new XMLHttpRequest();
    xhr.open('GET', path, true);
    xhr.onreadystatechange= function() {
        if (this.readyState!==4) return;
        if (this.status!==200) return; // or whatever error handling you want
        document.getElementById('main-content').innerHTML += this.responseText;
    };
    xhr.send();
}

function addSuggestSoups() {
    var xhr= new XMLHttpRequest();
    xhr.open('GET', "api/component", true);
    xhr.onreadystatechange= function() {
        if (this.readyState!==4) return;
        if (this.status!==200) return; // or whatever error handling you want
        // document.getElementById('main-content').innerHTML += this.responseText;
        var json = JSON.parse(this.responseText);
        console.log(json)
        // document.getElementById('components').appendChild(node)
    };
    xhr.send();
}

function addSuggestDishes() {

}


window.onload = function() {
    var path = window.location.pathname

    switch(path) {
        case '/soup/add':
            addMainContent("/assets/html/addSoupContent.html")
            addSuggestSoups();
            break;

        case '/dish/add':
            addMainContent("/assets/html/addDishContent.html")
            break;

    }
}



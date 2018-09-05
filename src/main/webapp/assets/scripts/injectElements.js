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
        document.getElementById('main-content').innerHTML = this.responseText;
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





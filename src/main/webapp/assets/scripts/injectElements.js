
Array.prototype.contains = function ( needle ) {
   for (i in this) {
       console.log(i)
       if (this[i] == needle) return true;
   }
   return false;
}


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


function addAlertMessage(message, colour) {
    var node = document.createElement("div");
    node.setAttribute("class", "alert " + colour);
    node.setAttribute("role", "alert");
    node.innerHTML = message;

    document.getElementById('alert-div').innerHTML = node.outerHTML;

}


function sendReq() {
    var isValidate = true;

    var form = document.getElementById('main-form')
    var components = form.getElementsByTagName('input');

    if(components.length == 1) {
        isValidate = false;
        addAlertMessage("You have to add at least one component", "alert-danger")
    }

    var soups = getSoupsFromDatabase();
    if(soups.contains(components[0].value)) {
        isValidate = false;
        addAlertMessage("Meal with this name already exist", "alert-danger");
    }

    for(let i=0; i<components.length; i++) {
        if(components[i].value.length == 0) {
            isValidate = false;
            addAlertMessage("There is at least one empty meal or component field", "alert-danger")
        }
    }

    if(isValidate) {
        addAlertMessage("Succesfully added to database", "alert-primary")
        setTimeout(function(){ form.submit() }, 2000);
    }

}





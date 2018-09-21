class ContentInjector {
    static addNewComponent() {
        let parser = new DOMParser();
        let xhr= new XMLHttpRequest();
        xhr.open('GET', '/assets/html/newComponent.html', false);
        xhr.onreadystatechange= function() {
            if (this.readyState!==4) return;
            if (this.status!==200) return;
            let node = parser.parseFromString(this.responseText, "text/html");
            node = node.getElementsByClassName("form-group")[0];
            document.getElementById('component-rows').appendChild(node);
        };
        xhr.send();
        this.addUnits();
    }

    static addMainContent(path) {
        let xhr= new XMLHttpRequest();
        xhr.open('GET', path, false);
        xhr.onreadystatechange= function() {
            if (this.readyState!==4) return;
            if (this.status!==200) return;
            document.getElementById('main-content').innerHTML = this.responseText;
        };
        xhr.send();
    }

    static addSuggestComponents() {
        let xhr= new XMLHttpRequest();
        xhr.open('GET', "/api/component", true);
        xhr.onreadystatechange= function() {
            if (this.readyState!==4) return;
            if (this.status!==200) return; // or whatever error handling you want
            let json = JSON.parse(this.responseText);
            let node;
            json.forEach(element => {
                node = document.createElement("option");
                node.setAttribute('value', element.name);
                document.getElementById('components').appendChild(node);

            });
        };
        xhr.send();
    }

    static addUnits() {
        let injectPlace = document.getElementsByClassName("select-unit");
        let xhr= new XMLHttpRequest();
        xhr.open('GET', "/api/unit", true);
        xhr.onreadystatechange= function() {
            if (this.readyState!==4) return;
            if (this.status!==200) return; // or whatever error handling you want
            let json = JSON.parse(this.responseText);
            let node;
            json.forEach(element => {
                node = document.createElement("option");
                node.setAttribute('value', element.name);
                node.innerHTML = element.name;
                injectPlace[injectPlace.length-1].appendChild(node);

            });
        };
        xhr.send();
    }

    static addAlertMessage(message, colour) {
        let node = document.createElement("div");
        node.setAttribute("class", "alert " + colour);
        node.setAttribute("role", "alert");
        node.innerHTML = message;

        document.getElementById('alert-div').innerHTML = node.outerHTML;
    }

    static cleanContent() {
        document.getElementById('main-content').innerHTML = ""
    }
}
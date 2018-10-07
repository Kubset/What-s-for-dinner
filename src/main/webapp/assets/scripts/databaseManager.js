class DatabaseManager {
    getCollectionFromDatabase(collectionName, attribute) {
        let xhr = new XMLHttpRequest();
        let elements = [];
        xhr.open('GET', "/api/" + collectionName, false);
        xhr.onreadystatechange = function () {
            if (this.readyState !== 4) return;
            if (this.status !== 200) return;
            if(typeof attribute !== "undefined") {
                let json = JSON.parse(this.responseText);
                json.forEach(element => {
                    elements.push(element[attribute])
                });
            } else {
                elements = JSON.parse(this.responseText);
            }
        };
        xhr.send();
        return elements;

    }


    getElementFromDatabase(elementName, id) {
        let json = "";
        let xhr = new XMLHttpRequest();

        xhr.open('GET', "/api/" + elementName + "/" + id, false);
        xhr.onreadystatechange = function () {
            if (this.readyState !== 4) return;
            if (this.status !== 200) return;
            json = JSON.parse(this.responseText);
        };
        xhr.send();

        return json;
    }

    deleteElementFromDatabase(elementName, id) {
        let xhr = new XMLHttpRequest();

        xhr.open('DELETE', "/api/" + elementName + "/" + id, false);
        xhr.onreadystatechange = function () {
            if (this.readyState !== 4) return;
            if (this.status !== 200) return;
        };
        xhr.send();

    }

}
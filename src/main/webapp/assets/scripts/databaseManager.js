class DatabaseManager {
    getCollectionFromDatabase(collectionName, attribute) {
        let elements = [];
        let xhr = new XMLHttpRequest();
        xhr.open('GET', "/api/" + collectionName, false);
        xhr.onreadystatechange = function () {
            if (this.readyState !== 4) return;
            if (this.status !== 200) return;
            let json = JSON.parse(this.responseText);
            json.forEach(element => {
                elements.push(element[attribute])
            });
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
}
class TableGenerator {
    generateSoupMainDishTable(days) {
        ContentInjector.addMainContent("/assets/html/addSoupMainDishTable.html");

        let databaseManager = new DatabaseManager();
        let soupIds = databaseManager.getCollectionFromDatabase("soup", "id");
        let dishIds = databaseManager.getCollectionFromDatabase("dish", "id");

        let randomSoups = getRandom(soupIds, days.length);
        let randomDishes = getRandom(dishIds, days.length);

        for (let i = 0; i < days.length; i++) {
            this.addSoupMainDishRow(days[i], randomSoups[i], randomDishes[i])
        }
    }

    addSoupMainDishRow(day, soupId, dishId) {
        let xhr = new XMLHttpRequest();
        let parser = new DOMParser();
        let databaseManager = new DatabaseManager();


        let soupJson = databaseManager.getElementFromDatabase("soup", soupId);
        let dishJson = databaseManager.getElementFromDatabase("dish", dishId);


        xhr.open('GET', "/assets/html/addSoupMainDishRow.html", false);
        xhr.onreadystatechange = function () {
            if (this.readyState !== 4) return;
            if (this.status !== 200) return;
            let node = parser.parseFromString(this.responseText, "text/xml").documentElement;

            node.getElementsByTagName("th")[0].innerHTML = day;

            let soupNode = node.getElementsByClassName("soup")[0];
            let dishNode = node.getElementsByClassName("dish")[0];

            soupNode.setAttribute("id", soupJson["id"]);
            soupNode.innerHTML = soupJson["name"];

            dishNode.setAttribute("id", dishJson["id"]);
            dishNode.innerHTML = dishJson["name"];

            document.getElementsByTagName('tbody')[0].innerHTML += node.outerHTML;
        };
        xhr.send();
    }

    generateDishComponentRecipeTable(dishIds, soupIds) {
        ContentInjector.addMainContent("/assets/html/addDishComponentRecipeTable.html");

        for (let i = 0; i < dishIds.length; i++) {
            this.addDishComponentRecipeRow("dish", dishIds[i]);
        }

        for (let i = 0; i < dishIds.length; i++) {
            this.addDishComponentRecipeRow("soup", soupIds[i]);
        }
    }

    addDishComponentRecipeRow(name, id) {
        let xhr = new XMLHttpRequest();
        let parser = new DOMParser();
        let databaseManager = new DatabaseManager();

        let json = databaseManager.getElementFromDatabase(name, id);

        xhr.open('GET', "/assets/html/addDishComponentRecipeRow.html", false);
        xhr.onreadystatechange = function () {
            if (this.readyState !== 4) return;
            if (this.status !== 200) return;
            let node = parser.parseFromString(this.responseText, "text/xml").documentElement;

            node.getElementsByTagName("th")[0].innerHTML = json["name"];

            let componentsNode = node.getElementsByClassName("component-list")[0];
            let recipeNode = node.getElementsByClassName("recipe")[0];

            let jsonComponents = json["components"];
            let listRow;

            for (let i = 0; i < jsonComponents.length; i++) {
                listRow = "<li class='component'>" +
                    jsonComponents[i]["name"] + " " +
                    jsonComponents[i]["count"] +
                    jsonComponents[i]["unit"]["name"] +
                    "</li>";
                componentsNode.innerHTML += listRow;
            }


            //TODO: add recipes to database and get there
            recipeNode.innerHTML = "recipe place";

            document.getElementsByTagName('tbody')[0].innerHTML += node.outerHTML;
        };
        xhr.send();
    }
}
class MealManager {
    static editMeal(element) {
        //TODO: 3xparentNode
        element = element.parentNode.parentNode.parentNode
        let nameNode = element.getElementsByTagName("th")[0];
        let actionNode = element.getElementsByClassName("actions")[0];
        let databaseManager = new DatabaseManager();

        let elementType = nameNode.getAttribute("class");
        let id = actionNode.getAttribute("id");

        let targetObject = databaseManager.getElementFromDatabase(elementType, id);
        let componentList = targetObject["components"];

        if(nameNode.getAttribute("class") === "soup") {
            ContentInjector.addMainContent("/assets/html/addSoupContent.html");
            document.getElementById("soup-name").value = targetObject["name"];
        } else if(nameNode.getAttribute("class") === "dish") {
            ContentInjector.addMainContent("/assets/html/addDishContent.html");
            document.getElementById("dish-name").value = targetObject["name"];
        }

        document.getElementById("recipe"). value = targetObject["recipe"];

        for(let i=0; i<componentList.length; i++) {
            ContentInjector.addNewComponent(componentList[i]["name"],
                                            componentList[i]["count"],
                                            componentList[i]["unit"]["name"]);
        }

        let form = document.getElementsByTagName("form")[0];
        form.appendChild(createHiddenNode("id", id))

    }

    static deleteMeal(element) {
        element = element.parentNode;
        ComponentManager.deleteComponent(element);

        let id = element.getAttribute("id");
        element = element.parentNode.parentNode;
        let mealName = element.getElementsByTagName("th")[0].getAttribute("class");

        let databaseManager = new DatabaseManager;
        databaseManager.deleteElementFromDatabase(mealName, id);
    }

}
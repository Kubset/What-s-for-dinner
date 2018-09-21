class ComponentManager {
    static deleteComponent(element) {
        element.parentNode.parentNode.remove()
    }

    static changeComponent(element, componentType) {
        let isReload = document.getElementsByClassName("fas fa-sync small-sync").length;
        if (isReload) return;

        let databaseManager = new DatabaseManager();
        let records = document.getElementsByClassName(componentType);
        let currentNode = element.parentNode.getElementsByTagName("div")[0];
        let tableNode = document.getElementsByTagName("table")[0];

        tableNode.setAttribute("data-used-id",
            tableNode.getAttribute("data-used-id") + currentNode.getAttribute("id") + " ");

        let allIds = databaseManager.getCollectionFromDatabase(componentType, "id");
        let usedIds = tableNode.getAttribute("data-used-id")
            .split(" ")
            .map(item => {
                if (item.length)
                    return parseInt(item, 10)
            });

        let currentIds = [];
        for (let i = 0; i < records.length; i++) {
            currentIds[i] = parseInt(records[i].getAttribute("id"))
        }

        allIds.remove(usedIds);
        allIds.remove(currentIds);

        if (allIds.length > 0) {
            let randomId = allIds[Math.floor(Math.random() * allIds.length)];
            let json = databaseManager.getElementFromDatabase(componentType, randomId);

            currentNode.setAttribute("id", randomId);
            currentNode.innerHTML = json["name"];
        } else {
            let currentNode = element.parentNode;
            tableNode.setAttribute("data-used-id", "");
            currentNode.innerHTML = "<div id='-1' class='" + componentType + "'></div>" +
                "<i class='fas fa-sync small-sync' onclick ='ComponentManager.reloadComponents(this)'></i>"
        }
    }

    static reloadComponents(element) {
        let componentType = document.getElementById("-1").getAttribute("class");
        let icon = document.getElementById("-1").parentNode.lastChild;
        icon.setAttribute("class", "fas fa-trash-alt small-trash");
        icon.setAttribute("onclick", "ComponentManager.changeComponent(this,'" + componentType + "')");
        this.changeComponent(element, componentType);

    }
}

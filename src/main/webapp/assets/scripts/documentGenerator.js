class DocumentGenerator {
    static sendShoppingList() {
        let form = document.getElementById("main-form");
        let hiddenNode = document.createElement("input");
        hiddenNode.setAttribute("hidden", "true");
        hiddenNode.setAttribute("name", "components");
        let nodeComponents = document.getElementsByClassName("component-list");

        let components = "";
        let tempComponents;
        for(let i=0; i<nodeComponents.length; i++) {
            tempComponents = nodeComponents[i].getElementsByClassName("component");
            tempComponents = Array.prototype.map.call(tempComponents, e => e = e.innerHTML);

            components += tempComponents.toString();
            components += ",";
        }
        components = components.substring(0,components.length-1);
        hiddenNode.setAttribute("value", components);

        if(!document.getElementsByTagName("input").length) {
            form.appendChild(hiddenNode);
        }
        form.submit();
    }

    static sendRecipeList() {
        //TODO: not implemented yet
        let form = document.getElementById("main-form");
        form.submit();
    }
}
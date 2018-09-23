class DocumentGenerator {
    static sendShoppingList() {
        let form = document.getElementById("main-form");
        let formData = document.getElementById("form-data");
        formData.innerHTML = "";

        let nodeComponents = document.getElementsByClassName("component-list");
        let components = "";
        let tempComponents;
        for(let i=0; i<nodeComponents.length; i++) {
            tempComponents = nodeComponents[i].getElementsByClassName("component");
            tempComponents = Array.prototype.map.call(tempComponents, e => e = e.innerHTML);

            components += tempComponents.toString();
            components += "#";
        }
        components = components.substring(0,components.length-1);

        formData.appendChild(createHiddenNode("components",components));
        form.submit();
    }

    static sendRecipeList() {

        let form = document.getElementById("main-form");
        let formData = document.getElementById("form-data");
        formData.innerHTML = "";


        let nodeNames = document.getElementsByClassName("meal-name");
        let nodeRecipes = document.getElementsByClassName("recipe");
        let names = "";
        let recipes = "";
        let temp;
        for(let i=0; i<nodeNames.length; i++) {
            names += nodeNames[i].innerHTML;
            recipes += nodeRecipes[i].innerHTML;

            names += "#";
            recipes += "#";
        }
        names = names.substring(0,recipes.length-1);
        recipes = recipes.substring(0,recipes.length-1);

        formData.appendChild(createHiddenNode("recipes", recipes));
        formData.appendChild(createHiddenNode("meal-names", names));
        form.submit();
    }
}
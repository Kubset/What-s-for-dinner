class FormValidator {

    static validateForm(formName) {
        let isValidate = true;
        let requestMethod = this.validateTypeOfForm();
        let databaseManager = new DatabaseManager();

        let mealName = document.getElementsByTagName("input")[0];
        let recipe = document.getElementById("recipe");
        let id = document.getElementsByName("id")[0];

        let componentNames = document.getElementsByName("component")
        let componentUnits = document.getElementsByName("unit");
        let componentCounts = document.getElementsByName("count");

        if(!this.validateNumberComponents(componentNames))
            isValidate = false;

        //TODO: name meal validate makes application slow
        // if(requestMethod === "POST") {
        //     if(!this.validateMealInDatabase(mealName, formName)) {
        //         isValidate = false;
        //     }
        // }

        if(!this.validateEmptyFields(componentNames)) {
            isValidate = false;
        }

        if(!this.validateEmptyFields(componentCounts)) {
            isValidate = false;
        }

        if(!recipe.value.length) {
            ContentInjector.addAlertMessage("There is empty recipe area", "alert-danger")
            isValidate = false;
        }

        if(!mealName.value.length) {
            ContentInjector.addAlertMessage("You have empty meal field", "alert-danger");
            isValidate =  false;
        }

        if (!this.validateByRegex(recipe.value)) {
            isValidate = false;
        }

        for(let i=0; i<componentNames.length; i++) {
            if(!this.validateByRegex(componentNames[i].value))
                isValidate = false;
        }

        if(!this.validateByRegex(mealName.value)) {
            isValidate = false;
        }

        if(!this.validateByRegex(recipe.value)) {
            isValidate = false;
        }

        if (isValidate) {
            ContentInjector.addAlertMessage("Successfully added to database", "alert-primary");
            setTimeout(function () {
                let json = {}
                let jsonComponents = {};
                json["name"] = mealName.value;
                json["recipe"] = recipe.value;
                json["favourite"] = -1;
                json["components"] = [];

                for(let i=0; i<componentNames.length; i++) {
                    let component = {};
                    component["name"] = componentNames[i].value;
                    component["count"] = componentCounts[i].value;
                    component["unit"] = {};
                    component["unit"]["name"] = componentUnits[i].value;
                    json["components"][i] = component;
                }


                if(id !== undefined) {
                    json["id"] = id.value;
                    databaseManager.editElementInDatabase(formName, json)
                } else {
                    databaseManager.addElementToDatabase(formName, json);
                }
                location.reload();
            }, 2000);
        }



    }

    static validatePrepareForm() {
        let checkedValues = document.getElementsByTagName('input');
        let isSomethingChecked = false;
        let tableGenerator = new TableGenerator();

        for (let i = 0; i < checkedValues.length; i++) {
            if (checkedValues[i].checked) isSomethingChecked = true;
        }

        if (isSomethingChecked) {
            ContentInjector.addAlertMessage("Processing...", "alert-success");
            let checkedDays = [];
            for (let i = 0; i < checkedValues.length; i++) {
                if (checkedValues[i].checked) checkedDays.push(checkedValues[i].getAttribute("value"))
            }

            setTimeout(function () {
                ContentInjector.cleanContent();
                tableGenerator.generateSoupMainDishTable(checkedDays);
            }, 2000);
        } else {
            ContentInjector.addAlertMessage("Please mark at least one day", "alert-danger")
        }
    }

    static validateByRegex(str) {
        let regex = /^[^@#!$%&*^<>~]*$/;

        if(!regex.test(str)) {
            ContentInjector.addAlertMessage("Illegal input data, You can't use special characters like:\n" +
                "@#!$%^&*~<>/\\", "alert-danger");
            return false;
        }
        return true;
    }

    static validateNumberComponents(components) {
        if (!components.length) {
            ContentInjector.addAlertMessage("You have to add at least one component", "alert-danger")
            return false;
        }
        return true;
    }

    static validateMealInDatabase(mealName, formName) {
        let databaseManager = new DatabaseManager();
        let meals = databaseManager.getCollectionFromDatabase(formName, "name");
        if (meals.contains(mealName.value)) {
            ContentInjector.addAlertMessage("Meal with this name already exist", "alert-danger");
            return false;
        }
        return true;
    }

    static validateTypeOfForm() {
        if (!document.getElementsByName("id").length) {
            return "POST";
        }
        else {
            return "PUT";
        }
    }

    static validateEmptyFields(components) {
        for (let i = 0; i < components.length; i++) {
            if (!components[i].value.length) {
                ContentInjector.addAlertMessage("There is at least one empty meal or component field", "alert-danger")
                return false;
            }
        }
        return true;
    }

}

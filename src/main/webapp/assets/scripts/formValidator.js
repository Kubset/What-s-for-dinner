class FormValidator {
    static validateAddForm(formName) {
        let isValidate = true;

        let databaseManager = new DatabaseManager();
        let form = document.getElementById('main-form');
        let components = form.getElementsByTagName('input');
        let recipe = document.getElementById('recipe');

        if (components.length < 3) {
            isValidate = false;
            ContentInjector.addAlertMessage("You have to add at least one component", "alert-danger")
        }

        if(!document.getElementsByName("id").length) {
            let soups = databaseManager.getCollectionFromDatabase(formName, "name");
            if (soups.contains(components[0].value)) {
                isValidate = false;
                ContentInjector.addAlertMessage("Meal with this name already exist", "alert-danger");
            }
        }


        for (let i = 0; i < components.length; i++) {
            if (!components[i].value.length) {
                isValidate = false;
                ContentInjector.addAlertMessage("There is at least one empty meal or component field", "alert-danger")
            }
        }


        if(!recipe.value.length) {
            isValidate = false;
            ContentInjector.addAlertMessage("There is empty recipe area", "alert-danger")
        }


        for(let i=0; i<components.length; i++) {
            if(!this.validateByRegex(components[i].value)) {
                ContentInjector.addAlertMessage("Illegal input data, You can't use special characters like:\n" +
                    "@#!$%^&*~<>/\\", "alert-danger");
                isValidate = false;
            }
        }

        if (!this.validateByRegex(recipe.value)) {
            ContentInjector.addAlertMessage("Illegal input data, You can't use special characters like:\n" +
                                            "@#!$%^&*~<>/\\", "alert-danger");
            isValidate = false;
        }



        if (isValidate) {
            ContentInjector.addAlertMessage("Successfully added to database", "alert-primary");
            setTimeout(function () {
                form.submit()
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

    }
}

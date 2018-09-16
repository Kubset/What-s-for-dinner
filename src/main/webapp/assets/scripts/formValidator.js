//TODO: refactor name
function validateForm(formName) {
    let isValidate = true;

    let form = document.getElementById('main-form');
    let components = form.getElementsByTagName('input');

    if(components.length === 1) {
        isValidate = false;
        addAlertMessage("You have to add at least one component", "alert-danger")
    }

    let soups = getCollectionFromDatabase(formName,"name");
    if(soups.contains(components[0].value)) {
        isValidate = false;
        addAlertMessage("Meal with this name already exist", "alert-danger");
    }

    for(let i=0; i<components.length; i++) {
        if(!components[i].value.length) {
            isValidate = false;
            addAlertMessage("There is at least one empty meal or component field", "alert-danger")
        }
    }

    if(isValidate) {
        addAlertMessage("Successfully added to database", "alert-primary");
        setTimeout(function(){ form.submit() }, 2000);
    }

}

function validatePrepareForm() {
    let checkedValues = document.getElementsByTagName('input');
    let isSomethingChecked = false;

    for(let i=0; i<checkedValues.length; i++) {
        if(checkedValues[i].checked) isSomethingChecked = true;
    }

    if(isSomethingChecked) {
        addAlertMessage("Processing...", "alert-success");
        let checkedDays = [];
        for(let i=0; i<checkedValues.length; i++) {
            if(checkedValues[i].checked) checkedDays.push(checkedValues[i].getAttribute("value"))
        }

        setTimeout(function(){
                               cleanContent();
                               generateTable(checkedDays);
                             }, 2000);
    } else {
        addAlertMessage("Please mark at least one day", "alert-danger")
    }


}
window.onload = function() {
    let path = window.location.pathname;

    switch(path) {
        case '/soup/add':
            addMainContent("/assets/html/addSoupContent.html");
            addSuggestComponents();
            break;

        case '/dish/add':
            addMainContent("/assets/html/addDishContent.html");
            addSuggestComponents();
            break;

        case '/prepare/dinner':
            addMainContent("/assets/html/addPrepareContent.html");

    }
};

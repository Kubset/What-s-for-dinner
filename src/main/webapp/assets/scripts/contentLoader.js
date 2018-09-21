window.onload = function() {
    let path = window.location.pathname;

    switch(path) {
        case '/soup/add':
            ContentInjector.addMainContent("/assets/html/addSoupContent.html");
            ContentInjector.addSuggestComponents();
            break;

        case '/dish/add':
            ContentInjector.addMainContent("/assets/html/addDishContent.html");
            ContentInjector.addSuggestComponents();
            break;

        case '/prepare/dinner':
            ContentInjector.addMainContent("/assets/html/addPrepareContent.html");

    }
};

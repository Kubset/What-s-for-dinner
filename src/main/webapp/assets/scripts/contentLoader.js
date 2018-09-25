window.onload = function() {
    let path = window.location.pathname;
    let tableGenerator = new TableGenerator();
    ContentInjector.addSuggestComponents();

    switch(path) {
        case '/soup/add':
            ContentInjector.addMainContent("/assets/html/addSoupContent.html");
            break;

        case '/dish/add':
            ContentInjector.addMainContent("/assets/html/addDishContent.html");
            break;

        case '/dish/edit':
            tableGenerator.generateEditTable("dish");
        break;

        case '/soup/edit':
            tableGenerator.generateEditTable("soup");
            break;

        case '/prepare/dinner':
            ContentInjector.addMainContent("/assets/html/addPrepareContent.html");

    }
};

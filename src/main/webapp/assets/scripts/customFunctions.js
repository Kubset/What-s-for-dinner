Array.prototype.contains = function ( needle ) {
    for (let i in this) {
        if (this[i] === needle) return true;
    }
    return false;
 };

Array.prototype.remove = function (array) {
     for(let i=0; i<array.length; i++) {
        let index = this.indexOf(array[i]);
        if(index !== -1) {
            this.splice(this.indexOf(array[i]),1)
        }
    }
};

function getRandom(array, n) {
    let result = new Array(n),
        len = array.length,
        taken = new Array(len);
    if (n > len)
        throw new RangeError("getRandom: more elements taken than available");
    while (n--) {
        let x = Math.floor(Math.random() * len);
        result[n] = array[x in taken ? taken[x] : x];
        taken[x] = --len in taken ? taken[len] : len;
    }
    return result;
}

function disableKeyWordSubmit(e) {
    let key = e.charCode || e.keyCode || 0;
    if (key === 13) {
        e.preventDefault();
    }
}

function postDinnerCollection() {

    let soups = document.getElementsByClassName("soup");
    let dishes = document.getElementsByClassName("dish");
    let tableGenerator = new TableGenerator();

    let soupIds = [];
    let dishIds = [];
    for(let i=0; i<soups.length; i++) {
       soupIds.push(soups[i].getAttribute("id"))
    }
    for(let i=0; i<dishes.length; i++) {
        dishIds.push(dishes[i].getAttribute("id"))
    }

    ContentInjector.addAlertMessage("processing...", "alert-success");

    setTimeout(function(){
        ContentInjector.cleanContent();
        tableGenerator.generateDishComponentRecipeTable(dishIds, soupIds);
    }, 2000);


}


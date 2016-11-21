var method = Cake.prototype;

var ingredient = require('./ingredient');

function Cake(name) {
    this._name = name;
    this._ingredients = [];
}

method.getName = function() {
    return this._name;
};

method.addIngredient = function(ingredient) {
        this._ingredients.push(ingredient);
}

method.getIngredients = function() {
    for (var ingredient in this._ingredients) {
         console.log(ingredient.name);
    }
}

module.exports = Cake;
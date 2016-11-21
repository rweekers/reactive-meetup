var method = Ingredient.prototype;

function Ingredient(name, type) {
    this._name = name;
    this._type = type;
}

method.getName = function() {
    return this._name;
};

method.getType = function() {
    return this._type;
}

module.exports = Ingredient;
module.exports = class Cake {

	constructor(name) {
		this.name = name;
		this.ingredients = [];
	}

	getName() {
		return this.name;
	}

	addIngredient(i) {
		this.ingredients.push(i);
		return this;
	}

	toString() {
		let recipe = this.name + ': ';
		for (let i of this.ingredients) {
			recipe = recipe + i.getName().toLowerCase() + ' ';
		}
		return recipe;
	}
}

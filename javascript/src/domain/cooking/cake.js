module.exports = class Cake {

	constructor(name, ingredients = [], baked = false) {
		this.name = name;
		this.ingredients = ingredients;
		this.baked = baked;
	}

	getName() {
		return this.name;
	}

	addIngredient(ingredient) {
		if (this.baked) {
			throw 'Nope. You cannot add any ingredients after the cake has been baked.';
		}
		return new Cake(this.name, [...this.ingredients, ingredient], this.baked);
	}

	bake() {
		if (!this.ingredients.length) {
			throw 'Trying to serve "gebakken lucht"? Add some ingredients before baking the cake!';
		}
		if (this.baked) {
			throw 'Oops, you\'ve baked the cake again, now it is burned!';
		}
		return new Cake(this.name, this.ingredients, true);
	}

	toString() {
		return [
			this.name + ':',
			...this.ingredients.map((ingredient) => ingredient.getName().toLowerCase()),
			this.baked ? '- freshly baked, om nom nom!' : '- still uncooked :('
		].join(' ');
	}
}

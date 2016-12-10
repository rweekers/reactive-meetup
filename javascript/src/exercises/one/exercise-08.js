const Rx = require('rxjs/Rx');
const ingredient$ = require('../../util/example-streams.js').ingredient$;
const Cake = require('../../domain/cooking/cake');

// ASSIGNMENT: Make a cake using the only fruits from the ingredient$ stream. After having added all ingredients, make sure to bake
// the cake, so it's actually edible.
//
// HINT: Start by selecting the ingredients that you are going to use for the cake.
//
// HINT: Use the following expression to check if an ingredient is fruit:
//
//   ingredient.getType() === 'FRUIT'
//
// HINT: When making the cake start with an "empty" cake: new Cake('Fruitcake')
//
// HINT: You can add an ingredient to a cake using the addIngredient(ingredient) method.
//
// HINT: To bake the cake, use the bake() method of the Cake class.

const cake$ = ingredient$
	.filter((ingredient) => ingredient.getType() === 'FRUIT')
	.reduce((cake, ingredient) => cake.addIngredient(ingredient), new Cake('Fruitcake'))
	.map((cake) => cake.bake());

// If you've followed the recipe then you should end up with one baked delicious and healthy strawberry pineapple cake.

cake$.subscribe((cake) => console.log(cake.toString()));
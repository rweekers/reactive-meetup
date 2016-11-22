var Rx = require('rxjs/Rx');
var ingredient$ = require('../../util/example-streams.js').ingredient$;
var Ingredient = require('../../domain/cooking/ingredient');
var Cake = require('../../domain/cooking/cake');

// ASSIGNMENT: Make a cake using the only fruits from the ingredient$ stream.
//
// HINT: Start by selecting the ingredients that you are going to use for the cake.
//
// HINT: Use the following expression to check if an ingredient is fruit:
//
//   ingredient.getType() === 'FRUIT'
//
// HINT: When making the cake start with an "empty" cake: new Cake("Fruitcake")
//
// HINT: You can add an ingredient to a cake using the addIngredient method.

const cake$ = ingredient$
    .filter((ingredient) => ingredient.getType() === 'FRUIT')
    .scan((cake, ingredient) => cake.addIngredient(ingredient), new Cake("Fruitcake"));

cake$.subscribe((cake) => console.log(cake.createRecipe()));

// If you've followed the recipe then you should end up with a delicious and healthy strawberry pineapple cake.
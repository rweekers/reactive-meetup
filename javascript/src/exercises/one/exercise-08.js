const Rx = require('rxjs/Rx');
const ingredient$ = require('../../util/example-streams.js').ingredient$;
const Cake = require('../../domain/cooking/cake');

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

const cake$ = null; // ???

// If you've followed the recipe then you should end up with a delicious and healthy strawberry pineapple cake.

cake$.subscribe((cake) => console.log(cake.toString()));
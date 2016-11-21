var Rx = require('rxjs/Rx');
var streams = require('../../util/example-streams.js');
var Ingredient = require('../../domain/ingredient');
var Cake = require('../../domain/cake');

const cake$ = streams.ingredient$
    .filter((ingredient) => ingredient.getType() === 'FRUIT')
    .scan((acc, cur) => acc.addIngredient(cur), new Cake("Fruitcake"));

cake$.subscribe((x) => console.log(x.getName()));
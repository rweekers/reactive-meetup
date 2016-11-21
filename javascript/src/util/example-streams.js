var Rx = require('rxjs/Rx');
var sample = require('./utils').sample;
var Ingredient = require('../domain/ingredient');

const number$ = sample(Rx.Observable.of(1, 9, 4, 7, 6, 2, 2, 7, 3, 4, 8));

exports.number$ = number$;

exports.numbersWithErrors$ = number$.take(4).concat(Rx.Observable.of(new Error("uh oh! an error!")));

exports.word$ = sample(Rx.Observable.of("Jirble:", "spill", "a", "liquid", "by", "shaking", "or", "unsteady",
                "moving", "of", "the", "vessel"));

exports.ingredient$ = sample(Rx.Observable.of(new Ingredient("Flour", "BASE"),
                new Ingredient("Sugar", "SWEET"), new Ingredient("Strawberry", "FRUIT"),
                new Ingredient("Salt", "BASE"), new Ingredient("Pineapple", "FRUIT")));

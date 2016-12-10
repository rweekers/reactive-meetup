const Rx = require('rxjs/Rx');
const sample = require('./utils').sample;
const Ingredient = require('../domain/cooking/ingredient');
const Circle = require('../domain/geometry/circle');
const Rectangle = require('../domain/geometry/rectangle');
const Square = require('../domain/geometry/square');
const Triangle = require('../domain/geometry/triangle');

const number$ = sample(Rx.Observable.of(1, 9, 4, 7, 6, 2, 2, 7, 3, 4, 8));

exports.number$ = number$;

exports.numbersWithErrors$ = number$.take(4).concat(Rx.Observable.of(new Error("uh oh! an error!")));

exports.word$ = sample(Rx.Observable.of("Jirble:", "spill", "a", "liquid", "by", "shaking", "or", "unsteady",
				"moving", "of", "the", "vessel"));

exports.shape$ = sample(Rx.Observable.of(new Circle("Circle 30", 30), new Circle("Circle 40", 40), new Square("Square 20", 20),
				new Rectangle("Rectangle 50x20", 50, 20), new Triangle("Triangle 40x10", 40, 10), new Rectangle("Rectangle 45x40", 45, 40),
				new Square("Square 30", 30), new Triangle("Triangle 40x45", 40, 45), new Rectangle("Rectangle 10x70", 10, 70)));

exports.ingredient$ = sample(Rx.Observable.of(new Ingredient("Flour", "BASE"),
				new Ingredient("Sugar", "SWEET"), new Ingredient("Strawberry", "FRUIT"),
				new Ingredient("Salt", "BASE"), new Ingredient("Pineapple", "FRUIT")));

exports.point$ = sample(Rx.Observable.of(0, 3, 0, 3, 1, 3, 0, 0, 3, 0));
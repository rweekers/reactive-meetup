import Rx from 'rxjs/Rx';
import { sample, Ingredient } from './utils.js';

export const number$ = sample(Rx.Observable.of(1, 9, 4, 7, 6, 2, 2, 7, 3, 4, 8));

export const numbersWithErrors$ = number$.take(4).concat(Observable.of(new Error("uh oh! an error!")));

export const word$ = sample(Rx.Observable.of("Jirble:", "spill", "a", "liquid", "by", "shaking", "or", "unsteady",
                "moving", "of", "the", "vessel"));

export const ingredient$ = sample(Rx.Observable.of(new Ingredient("Flour", "BASE"),
                new Ingredient("Sugar", "SWEET"), new Ingredient("Strawberry", "FRUIT"),
                new Ingredient("Salt", "BASE"), new Ingredient("Pineapple", "FRUIT")));

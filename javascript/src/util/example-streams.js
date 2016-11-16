import Rx from 'rxjs/Rx';
import { sample } from './utils.js';

export const number$ = sample(Rx.Observable.of(1, 9, 4, 7, 6, 2, 2, 7, 3, 4, 8));

export const numbersWithErrors$ = number$.take(4).concat(Observable.of(new Error("uh oh! an error!")));

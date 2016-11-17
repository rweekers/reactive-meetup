import Rx from 'rxjs/Rx';
import { ingredient$ } from '../../util/example-streams.js';
import { Cake } from '../../util/utils.js';

const cake$ = ingredient$
    .filter((ingredient) => ingredient.type === 'FRUIT')
    .scan((acc, cur) => acc.addIngredient(cur), new Cake("Fruitcake"));

cake$.subscribe((x) => console.log(x.getName()));


import Rx from 'rxjs/Rx';
import { number$ } from '../../util/example-streams.js';

number$
    .map((i) => i * i)
    .subscribe((x) => console.log(x));
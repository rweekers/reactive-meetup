import Rx from 'rxjs/Rx';
import { number$ } from '../../util/example-streams.js';

number$
    .filter((i) => i % 2 == 0)
    .subscribe((x) => console.log(x));
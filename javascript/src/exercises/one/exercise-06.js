import Rx from 'rxjs/Rx';
import { number$ } from '../../util/example-streams.js';

number$
    .scan((sum, item) => sum + item)
    .subscribe((x) => console.log(x));


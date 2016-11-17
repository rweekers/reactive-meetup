import Rx from 'rxjs/Rx';
import { word$ } from '../../util/example-streams.js';

word$
    .scan((sum, item) => sum + ' ' + item)
    .subscribe((x) => console.log(x));


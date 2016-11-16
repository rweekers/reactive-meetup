import Rx from 'rxjs/Rx';
import { number$ } from '../../util/example-streams.js';

number$.subscribe((x) => console.log(x));
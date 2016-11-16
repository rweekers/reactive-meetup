import Rx from 'rxjs/Rx';
import { number$ } from '../../util/example-streams.js';
import { isPrime } from '../../util/utils.js';

number$
    .filter((i) => isPrime(i))
    .subscribe((x) => console.log(x));
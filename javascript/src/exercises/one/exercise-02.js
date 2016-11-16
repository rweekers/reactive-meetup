import Rx from 'rxjs/Rx';
import { numbersWithErrors$ } from '../../util/example-streams.js';

numbersWithErrors$.subscribe(
    (value) => console.log("next: " + value),
    (error) => console.log("error: " + error),
    ()      => console.log("completed")
);
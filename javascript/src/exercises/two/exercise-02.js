const Rx = require('rxjs/Rx');

// ASSIGNMENT: Create a Subject and make sure that the program prints the following output:
//  - after 1 second: RxJava is cool :)
//  - after 2 seconds: So reactive!
//  - after 3 seconds: Much stream!
//  - after 4 seconds: Goodbye!
// You are only allowed to modify the lines with the ??? comment. Do not change the order the statements.
//
// HINT: Think of which kind of Subject you need to produce the desired output.

const subject = new Rx.ReplaySubject();

setTimeout(() => {
	subject.next('RxJava is cool :)');
}, 1000);

setTimeout(() => {
	subject.next('So reactive!');
}, 2000);

setTimeout(() => {
	subject.next('Much stream!');
}, 3000);

setTimeout(() => {
	subject.complete();
}, 4000);

subject.subscribe(console.log, () => {}, () => console.log('Goodbye!'));
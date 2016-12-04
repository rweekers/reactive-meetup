const Rx = require('rxjs/Rx');

// The word$ stream will emit a sequence words. Each word is just a string.
const word$ = require('../../util/example-streams.js').word$;

// ASSIGNMENT: Concatenate all the words from the word$ stream to form a sentence. Store the final and intermediate
// results (the partial sentences) in the sentence$ stream.

const sentence$ = word$
    .scan((sum, item) => sum + ' ' + item);

// If implemented correctly you should now know what "jirble" means.

sentence$.subscribe((x) => console.log(x));
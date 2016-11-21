var Rx = require('rxjs/Rx');
var streams = require('../../util/example-streams.js');
var Ingredient = require('../../domain/cooking/ingredient');
var Cake = require('../../domain/cooking/cake');

streams.shape$
    .filter((i) => i.calculateArea() > 500)
    .map((i) => i.calculateCircumference())
    .subscribe((i) => console.log(i.toFixed(2)));
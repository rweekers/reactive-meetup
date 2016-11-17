import Rx from 'rxjs/Rx';

function sample(stream) {
    return Rx.Observable.interval(500).zip(stream, (a, b) => b);
}

function isPrime(n) {
    if (n < 1) {
        return false;
    }
    if (n < 4) {
        return true;
    }
    if (n % 2 == 0) {
        return false;
    }
    const sqrt = Math.sqrt(n);
    const sqrtCeiling = Math.ceil(sqrt);

    for (let i = 3; i <= sqrtCeiling; i += 2) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

export { sample, isPrime };

export class Ingredient {
    constructor(name, type) {
        this.name = name;
        this.type = type;
    }
}

export class Cake {
    constructor(name) {
        this.name = name;
    }

    addIngredient(ingredient) {
        // TODO
    }

    getName() {
        return 'TODO';
    }
}
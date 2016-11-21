module.exports = class Subtotal {

    constructor(count, sum) {
        this.count = count;
        this.sum = sum;
    }

    add(value) {
        this.sum = this.sum + value;
        this.count++;
        return this;
    }

    getCount() {
        return this.count;
    }

    getSum() {
        return this.sum;
    }
}

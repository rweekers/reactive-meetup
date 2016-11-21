module.exports = class Circle {

    constructor(name, base, height) {
        this.name = name;
        this.base = base;
        this.height = height;
    }

    calculateArea() {
        return this.base * this.height / 2;
    }

    calculateCircumference() {
        return this.base + this.height + Math.sqrt(this.base * this.base + this.height * this.height);
    }

    getName() {
        return this.name;
    }
}

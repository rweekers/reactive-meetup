module.exports = class Circle {

    constructor(name, radius) {
        this.name = name;
        this.radius = radius;
    }

    calculateArea() {
        return Math.PI * this.radius * this.radius;
    }

    calculateCircumference() {
        return 2 * Math.PI * this.radius;
    }

    getName() {
        return this.name;
    }
}

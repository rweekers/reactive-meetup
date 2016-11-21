module.exports = class Circle {

    constructor(name, width) {
        this.name = name;
        this.width = width;
    }

    calculateArea() {
        return this.width * this.width;
    }

    calculateCircumference() {
        return 4 * this.width;
    }

    getName() {
        return this.name;
    }
}

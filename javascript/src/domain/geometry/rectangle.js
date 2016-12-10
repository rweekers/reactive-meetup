module.exports = class Circle {

	constructor(name, width, height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}

	calculateArea() {
		return this.width * this.height;
	}

	calculateCircumference() {
		return 2 * this.width * this.height;
	}

	getName() {
		return this.name;
	}
}

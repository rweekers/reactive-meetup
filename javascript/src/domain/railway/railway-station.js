const LatLong = require('./lat-long');

class RailwayStation {

	constructor(id, name, location) {
		this.id = id;
		this.name = name;
		this.location = location;
	}

	static closestTo(position) {
		return RailwayStation.values.reduce((a, b) => a.getLocation().distanceTo(position) < b.getLocation().distanceTo(position) ? a : b);
	}

	getLocation() {
		return this.location;
	}

	toString() {
		return this.name + " (" + this.id + ")";
	}

}

RailwayStation.values = [
	RailwayStation.AMS = new RailwayStation('AMS', 'Amsterdam Centraal', new LatLong(52.3791283, 4.8980833)),
	RailwayStation.DB = new RailwayStation('DB',   'Den Bosch', new LatLong(51.6905476, 5.2913696)),
	RailwayStation.DH = new RailwayStation('DH',   'Den Haag', new LatLong(52.0809271, 4.3222312)),
	RailwayStation.AMR = new RailwayStation('AMR', 'Amersfoort', new LatLong(52.1530195, 5.3711025)),
	RailwayStation.UTR = new RailwayStation('UTR', 'Utrecht Centraal', new LatLong(52.0893224, 5.1079804))
];

module.exports = RailwayStation;
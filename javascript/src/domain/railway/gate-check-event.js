module.exports = class GateCheckEvent {
	
    constructor(checkIn, timestamp, railwayStation) {
        this.checkIn = checkIn;
        this.timestamp = new Date();
        this.railwayStation = railwayStation;
    }
	
	isCheckIn() {
		return this.checkIn;
	}

	isCheckOut() {
		return !this.checkIn;
	}

	getTimestamp() {
		return this.timestamp;
	}
	
	getRailwayStation() {
		return this.railwayStation;
	}	
}
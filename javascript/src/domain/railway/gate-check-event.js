module.exports = class GateCheckEvent {
	
    constructor(isCheckIn, timestamp, railwayStation) {
        this.isCheckIn = isCheckIn;
        this.timestamp = new Date();
        this.railwayStation = railwayStation;
    }
	
	getTimestamp() {
		return timestamp;
	}
	
	getRailwayStation() {
		return railwayStation;
	}	
}
const moment = require('moment');

module.exports = class TrainMetrics {

	constructor(trainId, timestamp, position) {
		this.trainId = trainId;
		this.timestamp = timestamp;
		this.position = position;
	}

	getTrainId() {
		return trainId;
	}

	getTimestamp() {
		return this.timestamp;
	}

	getPosition() {
		return this.position;
	}

	toString() {
		return "( trainId = " + this.trainId + ", time = " + 
		moment(timestamp).format('HH:mm:ss.SSS dd-MM-YYYY') + ", position = " + this.position + " )";
	}
}	


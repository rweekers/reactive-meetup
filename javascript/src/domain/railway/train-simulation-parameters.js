module.exports = class TrainSimulationParameters {

    constructor(tickFrequency, maxVelocity, acceleration, trainId, timeDilation) {
        this.tickFrequency = tickFrequency;
        this.maxVelocity = maxVelocity;
        this.acceleration = acceleration;
        this.trainId = trainId;
        this.timeDilation = timeDilation;
    }

    getTrainId() {
        return this.trainId;
    }

    getMaxVelocity() {
        return this.maxVelocity;
    }

    getAcceleration() {
        return this.acceleration;
    }

    getTimeDilation() {
        return this.timeDilation;
    }

    getTickFrequence() {
        return this.tickFrequency;
    }

}

let costMatrix = new Map();

module.exports = class TravelCostMatrix {

    constructor() {
    }

    addCostEntry(from, to, cost) {
        var entries = costMatrix.get(from);
			if (entries == null) {
				entries = new Map();
				costMatrix.set(from, entries);
			}
			entries.set(to, cost);
    }

    getTravelCost(from, to) {
        if (from === to) {
            return 0.0;
        }

        if (!costsMatrix[from]) {
            throw new Error('No entry in travel expense matrix for starting railway station ' + from);
        }

        var destinationCosts = costsMatrix[from];

        if (!destinationCosts[to]) {
            throw new Error('No entry in travel expense matrix for arrival railway station ' + to);
        }

        return destinationCosts[to];
    }
}
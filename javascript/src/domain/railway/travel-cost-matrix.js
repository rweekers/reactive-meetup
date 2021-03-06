module.exports = class TravelCostMatrix {

	constructor() {
		this.costMatrix = new Map();
	}

	addCostEntry(from, to, cost) {
		this.addOneWayCostEntry(from, to, cost);
		this.addOneWayCostEntry(to, from, cost);
	}
	
	addOneWayCostEntry(from, to, cost) {
		let entries = this.costMatrix.get(from);
		if (entries == null) {
			entries = new Map();
			this.costMatrix.set(from, entries);
		}
		entries.set(to, cost);
	}

	getTravelCost(from, to) {
		if (from === to) {
			return 0.0;
		}

		if (!this.costMatrix.get(from)) {
			throw new Error('No entry in travel expense matrix for starting railway station ' + from);
		}

		const destinationCosts = this.costMatrix.get(from);

		if (!destinationCosts.get(to)) {
			throw new Error('No entry in travel expense matrix for arrival railway station ' + to);
		}

		return destinationCosts.get(to);
	}
}
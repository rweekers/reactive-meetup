package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class TravelCostMatrix {
	
	private final Map<ERailwayStation, Map<ERailwayStation, Double>> costMatrix;
	
	public TravelCostMatrix(Map<ERailwayStation, Map<ERailwayStation, Double>> costMatrix) {
		this.costMatrix = costMatrix;
	}
	
	public double getTravelCost(ERailwayStation from, ERailwayStation to) {
		if (from == to) {
			return 0.0;
		}
		
		if (!costMatrix.containsKey(from)) {
			throw new NoSuchElementException("No entry in travel expense matrix for starting railway station " + from);
		}
		
		 Map<ERailwayStation, Double> destinationCosts = costMatrix.get(from);
		
		if (!destinationCosts.containsKey(to)) {
			throw new NoSuchElementException("No entry in travel expense matrix for arrival railway station " + to);
		}		
		
		return destinationCosts.get(to);
	}
	
	public static TravelCostMatrixBuilder builder() {
		return new TravelCostMatrixBuilder();
	}
	
	public static class TravelCostMatrixBuilder {
		
		private final Map<ERailwayStation, Map<ERailwayStation, Double>> costMatrix = new HashMap<>();
		
		public TravelCostMatrixBuilder define(ERailwayStation a, ERailwayStation b, double cost) {
			addCostEntry(a, b, cost);
			addCostEntry(b, a, cost);
			return this;
		}
		
		private void addCostEntry(ERailwayStation a, ERailwayStation b, double cost) {
			Map<ERailwayStation, Double> entries = costMatrix.get(a);
			if (entries == null) {
				entries = new HashMap<>();
				costMatrix.put(a, entries);
			}
			entries.put(b, cost);
		}
		
		public TravelCostMatrix build() {
			return new TravelCostMatrix(costMatrix);
		}
		
	}

}

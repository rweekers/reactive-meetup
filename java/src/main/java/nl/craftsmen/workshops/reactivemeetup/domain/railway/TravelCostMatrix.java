package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class TravelCostMatrix {
	
	private final Map<RailwayStation, Map<RailwayStation, Double>> costMatrix;
	
	public TravelCostMatrix(Map<RailwayStation, Map<RailwayStation, Double>> costMatrix) {
		this.costMatrix = costMatrix;
	}
	
	public double getTravelCost(RailwayStation from, RailwayStation to) {
		if (from == to) {
			return 0.0;
		}
		
		if (!costMatrix.containsKey(from)) {
			throw new NoSuchElementException("No entry in travel expense matrix for starting railway station " + from);
		}
		
		 Map<RailwayStation, Double> destinationCosts = costMatrix.get(from);
		
		if (!destinationCosts.containsKey(to)) {
			throw new NoSuchElementException("No entry in travel expense matrix for arrival railway station " + to);
		}		
		
		return destinationCosts.get(to);
	}
	
	public static TravelCostMatrixBuilder builder() {
		return new TravelCostMatrixBuilder();
	}
	
	public static class TravelCostMatrixBuilder {
		
		private final Map<RailwayStation, Map<RailwayStation, Double>> costMatrix = new HashMap<>();
		
		public TravelCostMatrixBuilder define(RailwayStation a, RailwayStation b, double cost) {
			addCostEntry(a, b, cost);
			addCostEntry(b, a, cost);
			return this;
		}
		
		private void addCostEntry(RailwayStation a, RailwayStation b, double cost) {
			Map<RailwayStation, Double> entries = costMatrix.get(a);
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

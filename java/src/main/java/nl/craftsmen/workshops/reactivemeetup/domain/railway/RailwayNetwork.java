package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import java.util.HashSet;
import java.util.Set;

public class RailwayNetwork {

	public RailwayNetwork(Set<RailwayEdge> edges) {
		
	}
	
	public static RailwayNetworkBuilder builder() {
		return new RailwayNetworkBuilder();
	}
	
	public static class RailwayNetworkBuilder {
		
		private final Set<RailwayEdge> edges = new HashSet<>();
		
		public RailwayNetworkBuilder withEdge(RailwayStation first, RailwayStation second) {
			return withEdge(new RailwayEdge(first, second));
		}
		
		public RailwayNetworkBuilder withEdge(RailwayEdge edge) {
			edges.add(edge);
			return this;
		}
		
		public RailwayNetwork build() {
			return new RailwayNetwork(edges);
		}
		
	}
	
}

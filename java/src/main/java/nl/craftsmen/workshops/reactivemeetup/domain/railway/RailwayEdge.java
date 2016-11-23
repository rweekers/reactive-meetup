package nl.craftsmen.workshops.reactivemeetup.domain.railway;

public class RailwayEdge {

	private final RailwayStation firstStation;
	
	private final RailwayStation secondStation;
	
	public RailwayEdge(RailwayStation firstStation, RailwayStation secondStation) {
		this.firstStation = firstStation;
		this.secondStation = secondStation;
	}
	
	public RailwayStation getFirstStation() {
		return firstStation;
	}
	
	public RailwayStation getSecondStation() {
		return secondStation;
	}
	
}

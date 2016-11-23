package nl.craftsmen.workshops.reactivemeetup.domain.railway;

public class RailwayEdge {

	private final ERailwayStation firstStation;
	
	private final ERailwayStation secondStation;
	
	public RailwayEdge(ERailwayStation firstStation, ERailwayStation secondStation) {
		this.firstStation = firstStation;
		this.secondStation = secondStation;
	}
	
	public ERailwayStation getFirstStation() {
		return firstStation;
	}
	
	public ERailwayStation getSecondStation() {
		return secondStation;
	}
	
}

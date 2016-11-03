package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import java.time.Duration;
import java.time.ZonedDateTime;

public class Departure {

    private final ZonedDateTime departureTime;
    private final RailwayStation destination;
    private Duration delay = Duration.ZERO;

    public Departure(ZonedDateTime time, RailwayStation destination) {
        this.departureTime = time;
        this.destination = destination;
    }

    public Departure(ZonedDateTime time, RailwayStation destination, Duration delay) {
        this(time, destination);
        this.delay = delay;
    }

    public RailwayStation getDestination() {
        return destination;
    }

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public Duration getDelay() {
        return delay;
    }
}

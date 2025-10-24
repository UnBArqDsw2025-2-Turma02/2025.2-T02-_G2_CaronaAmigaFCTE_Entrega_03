package mediator;

import mediator.model.Report;

import java.util.ArrayList;
import java.util.List;

public class Driver extends Component {
    private double rating = 5.0;
    private int totalTrips = 0;
    private final List<Passenger> passengersRated = new ArrayList<>();

    public Driver(String id, String name, String email) { super(id, name, email); }

    public void createReport(Driver driver, String reason) {
        // opcional, se motoristas também puderem reportar
        if (mediator != null) mediator.notify(this, "reportCreated");
    }

    public void ratePassenger(Passenger passenger, int stars) {
        passengersRated.add(passenger);
        if (mediator != null) mediator.notify(this, "passengerRated:" + stars);
    }

    public void incrementTrips() { totalTrips++; }

    public void updateRating(double newAvg) { this.rating = newAvg; }

    @Override public String toString() {
        return super.toString()+"{rating="+rating+", trips="+totalTrips+"}";
    }
}

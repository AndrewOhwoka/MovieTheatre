package ArrayList;
import java.util.ArrayList;
import java.util.List;

public class MovieTheater {
    private final String theaterName;
    private final List<Seat> seats;

    // Constructor for MovieTheater
    public MovieTheater(String theaterName, int numRows, int seatsPerRow) {
        this.theaterName = theaterName;
        seats = new ArrayList<>();
        int lastRow = 'A' + (numRows - 1);
        // Creating the seating chart
        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                String seatNumber = row + String.format("%02d", seatNum);  
                seats.add(new Seat(seatNumber));
            }
        }
        System.out.println("Seating chart created for " + theaterName);
    }

    // Retrieve the theater name
    public String getTheaterName() {
        return theaterName;
    }

    // Reserve a seat given its number
    public boolean reserveSeat(String seatNumber) {
        Seat requestedSeat = findSeat(seatNumber);
        if (requestedSeat == null) {
            System.out.println("Seat " + seatNumber + " does not exist.");
            return false;
        }
        return requestedSeat.reserve();
    }

    // Cancel a seat reservation given its number
    public boolean cancelSeat(String seatNumber) {
        Seat requestedSeat = findSeat(seatNumber);
        if (requestedSeat == null) {
            System.out.println("Seat " + seatNumber + " does not exist.");
            return false;
        }
        return requestedSeat.cancel();
    }

    // Print the seating chart with reservation statuses
    public void printSeatingChart() {
        System.out.println("Seating chart for " + theaterName + ":");
        for (Seat seat : seats) {
            System.out.print(seat.getSeatNumber() + (seat.isReserved() ? " [Reserved]" : " [Available]") + " ");
            if (seat.getSeatNumber().endsWith("10")) {  
                System.out.println();  
            }
        }
        System.out.println();
    }

    // Retrieve the initial seating chart
    public List<String> getInitialSeatingChart() {
        List<String> initialSeatingChart = new ArrayList<>();
        for (Seat seat : seats) {
            initialSeatingChart.add(seat.getSeatNumber());
        }
        return initialSeatingChart;
    }

    // Find a seat by its number
    private Seat findSeat(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                return seat;
            }
        }
        return null;
    }

    // Inner class Seat representing a single seat
    private class Seat {
        private final String seatNumber;
        private boolean reserved = false;

        // Constructor for Seat
        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        // Reserve the seat
        public boolean reserve() {
            if (!reserved) {
                reserved = true;
                System.out.println("Seat " + seatNumber + " successfully reserved.");
                return true;
            } else {
                System.out.println("Seat " + seatNumber + " is already reserved.");
                return false;
            }
        }

        // Cancel the seat reservation
        public boolean cancel() {
            if (reserved) {
                reserved = false;
                System.out.println("Reservation for seat " + seatNumber + " successfully canceled.");
                return true;
            } else {
                System.out.println("Seat " + seatNumber + " was not reserved.");
                return false;
            }
        }

        // Get the seat number
        public String getSeatNumber() {
            return seatNumber;
        }

        // Check if the seat is reserved
        public boolean isReserved() {
            return reserved;
        }
    }

    // Driver code to test the MovieTheater class
    public static void main(String[] args) {
        MovieTheater theater = new MovieTheater("Grand Cinema", 3, 10);  
        theater.printSeatingChart(); 

        // Test seat reservations and cancellations
        theater.reserveSeat("A04");
        theater.reserveSeat("B05");
        theater.printSeatingChart();  // Show the seating chart after some reservations

        theater.cancelSeat("A04");
        theater.cancelSeat("B05");
        theater.printSeatingChart();  // Show the seating chart after canceling a reservation

        // Retrieve and print the initial seating chart
        List<String> initialSeatingChart = theater.getInitialSeatingChart();
        System.out.println("Initial seating chart: " + initialSeatingChart);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1_to_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class to test out whether the MovieSession class is working.
 * @author lyleb
 */
public class Driver
{

    public static void main(String[] args)
    {
        // Initializing Movie Sessions
        MovieSession[] myMovieSession = new MovieSession[6];
        myMovieSession[0] = new MovieSession("My Movie", 'M', new Time(23));
        myMovieSession[1] = new MovieSession("My Movie 2: The Sequel", 'M', new Time(4));
        myMovieSession[2] = new MovieSession("My Movie: The Prequel", 'M', new Time(18));
        myMovieSession[3] = new MovieSession("1 - The Origins", 'R', new Time(23));
        myMovieSession[4] = new MovieSession("My Movie 3: The Sequel", 'R', new Time(4));
        myMovieSession[5] = new MovieSession("My Movie 4: The Sequel-Sequel", 'R', new Time(4));
        
        // Movie Sessions
        ArrayList<MovieSession> movies = new ArrayList(Arrays.asList(myMovieSession));
        System.out.printf("Unsorted Movies: \n" + movies);
        Collections.sort(movies);
        System.out.printf("\nSorted Movies: \n" + movies);
        
        // Seat Reservation with a child
        float ticketSum = 0;
        List<SeatReservation> reservations = new ArrayList();
        AdultReservation a = new AdultReservation('A', 1);
        ChildReservation c = new ChildReservation('B', 1);
        ElderlyReservation e = new ElderlyReservation('D', 0);

        // All Three Reservations (Should be approved)
        System.out.printf("\n\nAll Three Reservations (Should be approved):\n");
        System.out.println(myMovieSession[0]);
        reservations.add(a);
        reservations.add(c);
        reservations.add(e);
        System.out.println("Reservations: Adult(A,1), Elderly(D,0), Child(B,1):");
        myMovieSession[0].applyBookings(reservations);
        myMovieSession[0].printSeats();
        for (SeatReservation reservation : reservations)
        {
            ticketSum += reservation.getTicketPrice();
        }
        System.out.println("Total Price: " + ticketSum);
        
        // Child Reservation without any adult companion in an M-Rated Film (Should be rejected)
        System.out.printf("\nChild Reservation without any adult companion in an M-Rated Film (Should be rejected):\n");
        System.out.println(myMovieSession[1]);
        reservations.clear();
        reservations.add(c);
        System.out.println("Reservations: Child(B,1):");
        myMovieSession[1].applyBookings(reservations);
        myMovieSession[1].printSeats();

        // Child Reservation with an adult companion in an M-Rated Film (Should be approved)
        System.out.printf("\nChild Reservation with an adult companion in an M-Rated Film (Should be approved):\n");
        System.out.println(myMovieSession[2]);
        reservations.clear();
        a.setComplementary(true);
        reservations.add(a);
        reservations.add(c);
        System.out.println("Reservations: Adult(A,1), Child(B,1):");
        myMovieSession[2].applyBookings(reservations);
        myMovieSession[2].printSeats();
        ticketSum = 0;
        for (SeatReservation reservation : reservations)
        {
            ticketSum += reservation.getTicketPrice();
        }
        System.out.println("Total Price: " + ticketSum);
        
        // Child Reservation with an adult companion in an R-Rated Film (Should be rejected)
        System.out.printf("\nChild Reservation with an adult companion in an R-Rated Film (Should be rejected):\n");
        System.out.println(myMovieSession[3]);
        reservations.clear();
        reservations.add(a);
        reservations.add(c);
        System.out.println("Reservations: Adult(A,1), Child(B,1):");
        myMovieSession[3].applyBookings(reservations);
        myMovieSession[3].printSeats();
        
        // Child Reservation without any adult companion in an R-Rated Film (Should be rejected)
        System.out.printf("\nChild Reservation without any adult companion in an R-Rated Film (Should be rejected):\n");
        System.out.println(myMovieSession[4]);
        reservations.clear();
        reservations.add(c);
        System.out.println("Reservations: Child(B,1):");
        myMovieSession[4].applyBookings(reservations);
        myMovieSession[4].printSeats();
        
        // Adult/Elderly in an R-Rated FiLm (Should be approved)
        System.out.printf("\nAdult/Elderly Reservationin an R-Rated FiLm (Should be approved):\n");
        System.out.println(myMovieSession[5]);
        reservations.clear();
        reservations.add(a);
        reservations.add(e);
        System.out.println("Reservations: Adult(A,1), Elderly(D,0):");
        myMovieSession[5].applyBookings(reservations);
        myMovieSession[5].printSeats();
        ticketSum = 0;
        for (SeatReservation reservation : reservations)
        {
            ticketSum += reservation.getTicketPrice();
        }
        System.out.println("Total Price: " + ticketSum);
        
        // Adult/Elderly in an already booked seats above in the same R-Rated Film (Should be rejected)
        System.out.printf("\nAdult/Elderly Reservation in an already booked seats above in the same R-Rated Film (Should be rejected):\n");
        System.out.println(myMovieSession[5]);
        reservations.clear();
        reservations.add(new ElderlyReservation('A', 1));
        reservations.add(new AdultReservation('B', 2));
        reservations.add(new ElderlyReservation('C', 3));
        System.out.println("Reservations: Elderly(A,1), Adult(B,2), Elderly(C,3):");
        myMovieSession[5].applyBookings(reservations);
        myMovieSession[5].printSeats();
        
        // Adult/Elderly Reservation in a non-booked seats above in the same R-Rated Film (Should be approved):
        System.out.printf("\nAdult/Elderly Reservation in a non-booked seats above in the same R-Rated Film (Should be approved):\n");
        System.out.println(myMovieSession[5]);
        reservations.clear();
        reservations.add(new ElderlyReservation('A', 2)); // Changed from 1 to 2
        reservations.add(new AdultReservation('B', 2));
        reservations.add(new ElderlyReservation('C', 3));
        System.out.println("Reservations: Elderly(A,2), Adult(B,2), Elderly(C,3):");
        myMovieSession[5].applyBookings(reservations);
        myMovieSession[5].printSeats();
        ticketSum = 0;
        for (SeatReservation reservation : reservations)
        {
            ticketSum += reservation.getTicketPrice();
        }
        System.out.println("Total Price: " + ticketSum);
        
        // Adult/Elderly Reservation in a non-booked seats above in the same R-Rated Film, but has a compelementary ticket (Should be approved):
        System.out.printf("\nAdult/Elderly Reservation in a non-booked seats above in the same R-Rated Film, but has a compelementary ticket (Should be approved):\n");
        System.out.println(myMovieSession[5]);
        reservations.clear();
        ElderlyReservation complementaryReservation = new ElderlyReservation('A', 3);
        complementaryReservation.setComplementary(true); // Ticket is Free
        reservations.add(complementaryReservation); 
        reservations.add(new AdultReservation('B', 3));
        reservations.add(new ElderlyReservation('C', 4));
        System.out.println("Reservations: Elderly(A,3), Adult(B,3), Elderly(C,4):");
        myMovieSession[5].applyBookings(reservations);
        myMovieSession[5].printSeats();
        ticketSum = 0;
        for (SeatReservation reservation : reservations)
        {
            ticketSum += reservation.getTicketPrice();
        }
        // Should be less a elderly ticket price less than the previous one, means $8.75 less
        System.out.println("Total Price(Should be less a elderly ticket price less than the previous one): " + ticketSum);
    }
}

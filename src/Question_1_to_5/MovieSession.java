/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1_to_5;

import java.util.List;

/**
 * A class that represents the a movie's name, rating, screening time, and seats.
 * @author lyleb
 */
public class MovieSession implements Comparable<MovieSession>
{
    private String movieName;
    private char rating;
    private Time sessionTime;
    private SeatReservation[][] sessionSeats;
    public static int NUM_ROWS = 8; //How many Rows of Seats
    public static int NUM_COLS = 6; //How many Columns of Seats

    /**
     * Constructor to set the movie's name, rating, how many seats, and session time.
     * @param movieName Name of the movie.
     * @param rating Rating of the movie (G/M/R).
     * @param sessionTime Time the movie starts (HOURS:MINUTES:SECONDS).
     */
    public MovieSession(String movieName, char rating, Time sessionTime)
    {
        this.movieName = movieName;
        this.rating = rating;
        this.sessionTime = sessionTime;
        this.sessionSeats = new SeatReservation[MovieSession.NUM_ROWS][MovieSession.NUM_COLS];
    }

    /**
     * A method that converts an char of row to a integer of index to be used in an array.
     * @param rowLetter the character of the row.
     * @return the index of the character of the row based on the characters.
     */
    public static int convertRowToIndex(char rowLetter)
    {
        return (rowLetter - '0') - 17;
    }

    /**
     * A method that converts an integer of index to a char of row to be used in a seat reservation.
     * @param rowIndex the index of the character of the row.
     * @return the character of the row based on the index.
     */
    public static char convertIndexToRow(int rowIndex)
    {
        return (char) ((rowIndex + '0') + 17);
    }

    /**
     * Method to get the MovieSession class movie rating.
     * @return the Movie Session's session rating.
     */
    public char getRating()
    {
        return this.rating;
    }

    /**
     * Method to get the MovieSession class movie name.
     * @return the Movie Session's session name.
     */
    public String getMovieName()
    {
        return this.movieName;
    }

    /**
     * Method to get the MovieSession class session time.
     * @return the Movie Session's session time.
     */
    public Time getSessionTime()
    {
        return this.sessionTime;
    }

    /**
     * A method to get the seat specified by the caller.
     * @param row which seat row.
     * @param col which seat column. 
     * @return the seat based on its row and column parameters.
     */
    public SeatReservation getSeat(char row, int col)
    {
        return sessionSeats[convertRowToIndex(row)][col];
    }

    /**
     * A method to check if the seat is available, true is if it's available, false if it's not.
     * @param row which seat row.
     * @param col which seat column.
     * @return a boolean indicating if it's available or not.
     */
    public boolean isSeatAvailable(char row, int col)
    {
        return (this.sessionSeats[convertRowToIndex(row)][col] == null);
    }

    /**
     * Applies the booking set, and if it's true, then apply them in the MovieSession.
     * If it's false, then deny the set reservation that was tried to be booked.
     * @param reservations The seat reservations requested to be booked by the caller.
     * @return a boolean to determine if the reservation is successful or not.
     */
    public boolean applyBookings(List<SeatReservation> reservations)
    {
        for (int z = 0; z < reservations.size(); z++)
        {
            if (this.rating == 'R' && reservations.get(z) instanceof ChildReservation)
            {
                // Children are restricted to watch R-rated movies
                System.out.println("CHILDREN CANNOT WATCH R-RATED FILMS !!");
                return false;
            }
        }
        
        // Loop through the Reservation List for checking
        for (int x = 0; x < reservations.size(); x++)
        {
            if (this.rating == 'R' && reservations.get(x) instanceof ChildReservation)
            {
                // Children are restricted to watch R-rated movies
                System.out.println("CHILDREN CANNOT WATCH R-RATED FILMS !!");
                return false;
            }
            else if (this.rating == 'M' && reservations.get(x) instanceof ChildReservation)
            {
                boolean adultFound = false;
                for (int y = 0; y < reservations.size(); y++)
                {
                    // Check if it's anything other than a child reservation (Adult and Elderly)
                    if (!(reservations.get(y) instanceof ChildReservation))
                    {
                        // Found a Child with an Adult
                        System.out.println("Found a Child with an Adult !!");
                        adultFound = true;
                        break;
                    }
                }
                if (!adultFound)
                {
                    // No Child Found with an Adult
                    System.out.println("THERE'S NO ADULTS FOUND TO ACCOMPANY THE CHILD !!");
                    return false;
                }
            }
            if (isSeatAvailable(reservations.get(x).getRow(), reservations.get(x).getCol()))
            {
                // Saves the reservation into the 2D Array sessionSeats
                this.sessionSeats[convertRowToIndex(reservations.get(x).getRow())][reservations.get(x).getCol()] = reservations.get(x);
            }
            else
            {
                // Seat is not Available (Not null)
                System.out.println("ONE OR MORE OF THE SEATS SET IS NOT AVAILABLE !!");
                return false;
            }
        }
        // If every seat available check or rating check went fine, then return true
        return true;
    }

    /**
     * Prints the seats with its column and rows, and also what kind of reservation is in it.
     */
    public void printSeats()
    {
        for (int row = 0; row < MovieSession.NUM_ROWS; row++)
        {
            for (int col = 0; col < MovieSession.NUM_COLS; col++)
            {
                System.out.printf("|");
                if (this.sessionSeats[row][col] instanceof ElderlyReservation)
                {
                    System.out.printf("E");
                }
                else if (this.sessionSeats[row][col] instanceof AdultReservation)
                {
                    System.out.printf("A");
                }
                else if (this.sessionSeats[row][col] instanceof ChildReservation)
                {
                    System.out.printf("C");
                }
                else
                {
                    System.out.printf("_");
                }
                System.out.printf("|");
            }
            System.out.printf("\n");
        }
    }

    /**
     * Converts the data of the current movie session into a string that shows all its data.
     * @return a string of the movie session with all its information.
     */
    @Override
    public String toString()
    {
        String movieInfo = String.format("(" + this.rating + ") " + this.movieName +  " - [" + this.sessionTime + "]");
        return movieInfo;
    }

    /**
     * Overridden method used to compare different movie sessions.
     * @param otherMovieSession
     * @return
     */
    @Override
    public int compareTo(MovieSession otherMovieSession)
    {
        // If both times are equal
        if (this.sessionTime.compareTo(otherMovieSession.sessionTime) == 0)
        {
            // Compare their movie names
            return this.movieName.compareTo(otherMovieSession.getMovieName());
        }
        else
        {
            // If not, then compare their time
            return this.sessionTime.compareTo(otherMovieSession.getSessionTime());
        }
    }
}

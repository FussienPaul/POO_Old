/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1_to_5;

/**
 * Abstract class to describe an abstract seat booking such as Adult, Elderly, and Child bookings.
 * @author lyleb
 */
public abstract class SeatReservation
{

    private char row;
    private int col;
    protected boolean complementary; // Is the seat reservation free?

    /**
     * Constructor to determine where the reservation is specified, and to set the complementary to its default false.
     * @param row which row the seat is in.
     * @param col which column the seat is in.
     */
    public SeatReservation(char row, int col)
    {
        this.row = row;
        this.col = col;
        this.complementary = false;
    }

    /**
     * An abstract method to make sure there's a way to obtain the ticket price of all seat reservation extension.
     * @return the ticket price of the Seat Reservation.
     */
    public abstract float getTicketPrice();

    /**
     * A method to set the complementary of the seat reservation.
     * @param complementary set if it's complementary or not.
     */
    public void setComplementary(boolean complementary)
    {
        this.complementary = complementary;
    }

    /**
     * Method to call to get the seat row
     * @return the row of the Seat Reservation.
     */
    public char getRow()
    {
        return this.row;
    }

    /**
     * Method to call to get the seat row
     * @return the column of the Seat Reservation.
     */
    public int getCol()
    {
        return this.col;
    }

}

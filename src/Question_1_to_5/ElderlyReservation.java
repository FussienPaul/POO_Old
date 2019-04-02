/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1_to_5;

/**
 * Elderly Reservation type of a Seat Reservation for elderly people.
 * @author lyleb
 */
public class ElderlyReservation extends SeatReservation
{

    /**
     * Constructor that sends in the row and column information back to the Seat Reservation parent.
     * @param row which row the seat is in.
     * @param col which column the seat is in.
     */
    public ElderlyReservation(char row, int col)
    {
        super(row, col);
    }

    /**
     * A Method that returns the ticket price, and checks if the reservation is complementary or not for an Elderly Reservation.
     * @return the ticket price of the Elderly Seat Reservation.
     */
    @Override
    public float getTicketPrice()
    {
        if (complementary)
        {
            return 0.0f;
        }
        else
        {
            AdultReservation adultPrice = new AdultReservation('A', 0);
            float discount = adultPrice.getTicketPrice() - (adultPrice.getTicketPrice() * 0.30f);

            return discount;
        }
    }

}

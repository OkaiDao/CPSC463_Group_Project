package com.softwaretesting.project1;
import java.util.Scanner;

import com.softwaretesting.project1.Hotel;
import com.softwaretesting.project1.RoomStatus;

/*Jonathan Dao
 * 
 * 
 * NOTE: This screen shall show guests who have reservations or have already checked in.
If a room is selected that has a guest currently checked-in to a room, the screen for Capability 6  display with all the information of the guest. 
If a room is selected that does not have a guest checked-in but is reserved for a guest, then the Screen for Capability 6  display, 
pulling as much information from Capability 3 to fill in the fields.
If the room is selected that is empty with no reservation, and the date is for the current date, then the screen for Capability 6  display with fields blank for possible check-in.
If the room is selected that is empty with no reservation and is for a future date, a screen  display asking for information in Capability 3.  
Once the information is input and saved, a new reservation is added to the list of reservations.
 */
public class Capability2 
{
    private static void printReservation_List(Scanner scanner, Hotel hotel) 
  	{
    	printResPrompt();
    	//Display all reservations
    	printAllRes(scanner, hotel);
    	int choice = scanner.nextInt();
		scanner.nextLine();
		
		while (choice != 0)
		{
			if (choice < 0 || choice > 100)
			{
				System.out.println("Invalid Room" + "\n");
			}
			else
			{
				System.out.flush();
				Hotel.Room room = hotel.getRoomsMap().get(choice);
				if (room.getReservations().isEmpty())
				{
					//Capability 3 function call
					//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Needs to be checked for Integration @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@					
				}
				else
				{
	        		for (Hotel.Reservation reservation : room.getReservations())
	        		{
	        			System.out.println("Room Number: " + room.getRoomNumber());
	        			System.out.println("Check In Date: " + reservation.getDateCheckIn());
	        			System.out.println("Check Out Date: " + reservation.getDateCheckout());
	        			System.out.println("Date Made: " + reservation.getDateMade());
	        			System.out.println("Rate: " + reservation.getRate());
	        			System.out.println("Payments Made: " + reservation.getPaymentsMade());
	        		}
	        		System.out.println("Would you like to create a new reservation? Yes or No");
	        		String prompt = scanner.nextLine();
	        		scanner.nextLine();
	        		if (prompt == "Yes")
	        		{
	        			//Capability 6 function call
						//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Needs to be checked for Integration @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
						currentGuestInfo_noguest(hotel, room);
	        		}	
				}//EO else
			}//EO else
			printAllRes(scanner, hotel);
			printResPrompt();
			choice = scanner.nextInt();
			
		}//EO while
		System.out.flush();
  		return;
  	}//EO printReservation_List
    
    private static void printAllRes(Scanner scanner, Hotel hotel)
    {
    	for (Hotel.Room room: hotel.getRooms())
    	{
    		if (room.getReservations().isEmpty())
    		{
    			System.out.println("Room Number: " + room.getRoomNumber());	
    			System.out.println("Has no reservations.");
    		}
    		else
    		{
        		for (Hotel.Reservation reservation : room.getReservations())
        		{
        			System.out.println("Room Number: " + room.getRoomNumber());
        			System.out.println("Check In Date: " + reservation.getDateCheckIn());
        			System.out.println("Check Out Date: " + reservation.getDateCheckout());
        		}
    		}//EO else	
    	}//EO for (Hotel.Room room: hotel.getRooms())
    }
    private static void printResPrompt()
    {
    	System.out.flush();
    	System.out.println("\n" + "Reservation List" + "\n");
    	System.out.println("Enter 0 to return to the main menu" + "\n");
    }

}

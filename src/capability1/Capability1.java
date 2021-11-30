package capability1;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.softwaretesting.project1.Hotel;
import com.softwaretesting.project1.RoomStatus;
import static cap6.Capability6.currentGuestInfo_guest;
import static cap6.Capability6.currentGuestInfo_noguest;
import static capability3.Capability3.*;

/*
 * Jonathan Dao
 * 
 * Prompt:
 * If the room is Available, the screen for Capability 6 display and the User can enter in information to check in a guest.  
 * Once the information is complete and if the guest is checked in, the room status  change to Unavailable/Occupied
If the room is Occupied, the Capability 6 display and the User can make any modifications to the information.  
If the User is checked out in Capability 6, this  turn the room to Unavailable/Dirty.
If the room is Dirty, the system warn the User that the room is Dirty and ask if they want to turn the status to Available.  
If they say yes, it  turn the status to Available. If the User chooses “No”, no action is taken.
If the room is in Maintenance, the system warn the User that the room is Maintenance and ask if they want to turn the status to Available. 
If they say yes, it  turn the status to Available. If the User chooses “No”, no action is taken.
 */
public class Capability1 
{
	public static void printRoom_Status(Hotel hotel, Scanner scanner) throws ParseException {
		System.out.println("Please Enter today's date. mm-dd-yyyy");
		String dateIn = scanner.nextLine();
		Date dateInputed = getDateFromString(dateIn);
		printRoomStatPrompt();
		String prompt = scanner.nextLine();

		if (prompt.equals("rooms"))
		{
			//Display Rooms
			printRoomStats(hotel);
			//Input Choice
			System.out.println("If a room is available, to check in a guest, enter the room number" + "\n");
			System.out.println("Enter 0 to return to the main menu" + "\n");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			while(choice != 0)
			{
				if (choice < 0 || choice > 20)
				{
					System.out.println("Invalid Room" + "\n");
				}
				else
				{
					//Check If Occupied
					Hotel.Room room = hotel.getRoomsMap().get(choice);
					if (room.getRoomStatus() != RoomStatus.AVAILABLE)
					{
						if (room.getRoomStatus() == RoomStatus.UNAVAILABLE_OCCUPIED)
						{
							System.out.println("Room is occupied");
							Hotel.Guest guest = null;
							for (Hotel.Reservation r: room.getReservations())
							{
								String DateCheckInReservation = getStringFromDate(r.getDateCheckIn());
								String DateCheckOutReservation = getStringFromDate(r.getDateCheckout());
								String todaysDate = getStringFromDate(dateInputed);
								if (DateCheckInReservation.equals(todaysDate)||
										dateInputed.after(r.getDateCheckIn())&&dateInputed.before(r.getDateCheckout())||
										DateCheckOutReservation.equals(todaysDate) )
								{
									//Capability 6 function call
									//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Needs to be checked for Integration @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
									guest = r.getGuest();
									currentGuestInfo_guest(hotel, guest, room);
								}
							}
						}
						if (room.getRoomStatus() == RoomStatus.UNAVAILABLE_DIRTY)// If Dirty
						{
							System.out.println("Room is dirty, would you like to turn the status to Available? \"Yes\" or \"No\" ");
							prompt = scanner.nextLine();
							if (prompt == "Yes")
								room.setRoomStatus(RoomStatus.AVAILABLE);
						}//EO if Dirty
						else if (room.getRoomStatus() == RoomStatus.UNAVAILABLE_MAINTENANCE)// Maint
						{
							System.out.println("Room is under maintenance, would you like to turn the status to Available? \"Yes\" or \"No\" ");
							prompt = scanner.nextLine();
							if (prompt == "Yes")
								room.setRoomStatus(RoomStatus.AVAILABLE);
						}//EO Maint
					}
					else
					{
						//Capability 6 function call
						currentGuestInfo_noguest(hotel, room);
					}
				}//EO else
				System.out.flush();
				printRoomStats(hotel);
				System.out.println("If a room is available, to check in a guest, enter the room number" + "\n");
				System.out.println("Enter 0 to return to the main menu" + "\n");
				choice = scanner.nextInt();
				scanner.nextLine();

			}//EO While
			System.out.flush();
			return;
			
		}
		else if (prompt.equals("return"))
		{
			System.out.flush();
			return;
		}
		else
		{
			System.out.println("Invalid input, returning to menu." + "\n");
			System.out.flush();
			return;
		}		
	}
	
	public static void printRoomStatPrompt()
	{
		System.out.println("\n" + "Rooms" + "\n");
		System.out.println("Enter \"rooms\" to display rooms" + "\n");
		System.out.println("Enter \"return\" to return to the main menu." + "\n");		
	}
	
	public static void printRoomStats(Hotel hotel)
	{
    	List<Hotel.Room> Rooms = hotel.getRooms();
    	for (Hotel.Room element: Rooms)
    	{
    		System.out.println("Room: " + element.getRoomNumber() + "\n");
    		System.out.println("Room Type: " + element.getRoomType() + "\n");
    		System.out.println("Availability: " + element.getRoomStatus() + "\n");
    	}
	}

}

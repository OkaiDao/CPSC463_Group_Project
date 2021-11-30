package cap7;

import com.softwaretesting.project1.Hotel;
import com.softwaretesting.project1.RoomStatus;
import com.softwaretesting.project1.Hotel.Room;
import com.softwaretesting.project1.Hotel.Reservation;
import com.softwaretesting.project1.Hotel.Guest;
import com.softwaretesting.project1.Main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
public class cap7 {

    public static void searchScreen(Hotel hotel) {
    	System.out.println(
    		"1. Search for guest\n" +
    		"2. Main menu\n"
    	);
    	Scanner scanner_S= new Scanner(System.in);
    	int searScreenOption = scanner_S.nextInt();
    	if( searScreenOption == 1) {
    		System.out.println("Enter Guest's First Name");
    		Scanner scanner_searchScreen = new Scanner(System.in);
    		String fName = scanner_searchScreen.next();
    		System.out.println("Enter Guest's Last Name");
    		String lName = scanner_searchScreen.next();
    		ArrayList<Reservation> guestsHistory = hotel.getGuestHistory();
    		int count = 0;
    		ArrayList<Reservation> tmpReservation = new ArrayList<Reservation>();
    		for (Reservation element : guestsHistory) {
    			if(element != null) {
    			if(element.getGuest().getFirstName().contentEquals(fName)  && element.getGuest().getLastName().contentEquals(lName)) {
    				System.out.println( ++count + ".");
    				System.out.println(
    						"First Name : " + element.getGuest().getFirstName() + "\n" + 
    						"Last Name :" + element.getGuest().getLastName() + "\n" +
    						"Room Number : " + element.getRoom().getRoomNumber() + "\n" + 
    						"Check-In Date : " + element.getDateCheckIn() + "\n" +
    						"Check-Out Date : " + element.getDateCheckout() + "\n"
    				);
    				tmpReservation.add(element);
    			}
    			}
    		}
    		if(count ==0) {
    			System.out.println("Never been here before \n");
    			System.out.println("Back to Main Menu");
    			Main.printCapabilitiesOptions();
    		}
    		else {
    			System.out.println(++count + ". Main Menu");
    		
    			System.out.print(
    				"Enter guest's number to guest's profile or enter " + count +" to back Main Menu \n" 
    			);
    			searScreenOption = scanner_S.nextInt();
    			while(searScreenOption <= 0 || searScreenOption > count) {
    				System.out.println("Invalid choice. Please enter again: ");
    				searScreenOption = scanner_S.nextInt();
    			}
    			if(searScreenOption == count) {
    				Main.printCapabilitiesOptions();
    			}
    			else {
    				//Cap 5 function passed variables with guest name to access full profile.
    			}
    		}
    	}
    	else {
    		System.out.println();
    		Main.printCapabilitiesOptions();
    	}
    	
    }
}

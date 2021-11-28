package com.softwaretesting.project1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import com.softwaretesting.project1.Hotel;
import com.softwaretesting.project1.Hotel.Guest;
import com.softwaretesting.project1.Hotel.Reservation;
import com.softwaretesting.project1.Hotel.Room;

public class Main {

    private static final int TOTAL_CAPABILITIES = 8;
    private static final Scanner scanner = new Scanner(System.in);
    private static Hotel hotel = new Hotel();
    public static void main(String[] args) {
    	Room CreateRoom = hotel.new Room(RoomType.KING,RoomStatus.AVAILABLE,101);
    	hotel.addRoom(CreateRoom);
    	Guest GuestInfo = hotel.new Guest("fname","lname","123456","address","email","id123","license 123");
    	Date date = new Date();
    	Reservation AddReservation = hotel.new Reservation(GuestInfo,"01-01-2001 23:20:11","01-02-2001 08:00:00".toString(),1.0,CreateRoom,false,0); 
        hotel.getRooms().get(0).addToReservation(AddReservation);
    	printCapabilitiesOptions();
    }

    private static void printCapabilitiesOptions() {
        int selectedOption = 0;
        while (selectedOption != -1) {
            for (int i = 1; i <= TOTAL_CAPABILITIES; i++) {
                System.out.println("Enter " + i + " to " + getCapabilityOption(i));
            }
            System.out.println("Enter " + -1 + " " + getCapabilityOption(-1));
            try {
                selectedOption = scanner.nextInt();
                if (selectedOption!=-1 && (selectedOption < 1 || selectedOption > TOTAL_CAPABILITIES)){
                    printErrorMessage();
                } else {
                    performOperation(selectedOption);
                }
            } catch (InputMismatchException exception) {
                printErrorMessage();
            }
            scanner.nextLine();
        }
    }
    
    //Capbility 1 Room List
    //Jonathan Dao
    //Note: List is vertical from 1-100 due to consle app implementation
    private static void printRoom_Status()
    {
    	int x = 0;
    	int roomNum = 0;
    	while (x < 100)
    	{
    		    			
    		System.out.println("Room " + (x + 1) + "\n"); 
    		System.out.println("Room Type: " + "\n"); //Add function to pull room type and add to output.
    		System.out.println("Availability: " + "\n"); //Add function to pull room type and add to output.
    		x = x+1;
    	}
    		
    }
    
    //Capability 2 Reservation List
    //Jonathan Dao
    private static void printReservation_List() 
  	{
  		int counter = 1;
  		while (counter < 100)
  		{
  			System.out.println("Room " + counter + "			");
  			int day = 1;
  			while (day < 7)
  			{
  				System.out.println("Day " + day );
  				//Logic for finding if there is an existing guest
  			//If there is a guest pull name and output
  				day = day + 1;

  			}
  			counter = counter + 1;
  		}
  		System.out.println("\n");
  	
  	}

    private static void performOperation(int selectedOption) {
        switch (selectedOption) {
        	case 1: 
        		printRoom_Status();
        		break;
        	case 2:
        		printReservation_List();
        		break;
            case 3:
                displayReservations();
                break;
            case 4:
                displayHousekeepingInformation();
                break;
            case 5:
                showGuestInfo();
                break;
            case 6:
            	currentGuestInfo_noguest(hotel.getRooms().get(0));
            	break;
            case 7:
            	searchScreen();
            	break;
            case 8:
                showDailyReport();
        }
    }

    //Capability 3 Reservation list
    //Sijan Rijal
    private static void displayReservations() {
        System.out.println("Guest First Name: \n" +
                "Guest Last Name: \n" +
                "Date Made:\n" +
                "Date Check-in:\n" +
                "Date Checkout:\n" +
                "Room Type:\n" +
                "Website Reservation Made:\n" +
                "Rate ($/Day):\n" +
                "Total Charge:\n");
    }

    //Capability 4 Housekeeping information
    //Sijan Rijal
    private static void displayHousekeepingInformation() {
        System.out.println("Housekeep Name:\n" +
                "Room number:\n" +
                "Type:\n" +
                "Status:\n" +
                "Bathroom:\n" +
                "Towels:\n" +
                "Bed Sheets:\n" +
                "Vacuum:\n" +
                "Dusting:\n" +
                "Electronics:\n");
    }
    
    //Capability 5 - A guest profile screen to show guest information
    //Christopher Ordinario
    private static void showGuestInfo() {
        System.out.println("First Name: \n" +
            "Last Name: \n" +
            "Phone: \n" +
            "Address: \n" + 
            "E-mail: \n" +
            "State: \n" +
            "ID#: \n" +
            "Vehicle License Plate: \n");
    }
    
    
    //Capability 6 - Current stay screen showing a guest’s information for their current stay.
    //Tuan Ngo
    
    
    
    //If this screen is reached from Capability 1 or Capability 2 with no guest checked-in
    // the fields  be blank and can be filled in by the User.
    //The guests can then be checked-in when the information is filled out.
    //If a guest checks  in, the room status (See Capability 1) turns to Unavailable/Occupied.
    
    private static void currentGuestInfo_noguest(Room room) {
    	String fName;
    	String lName;
    	String phoneNumber;
    	String email;
    	String address;
    	String idNumber;
    	String licensePlate;
    	String dateCheckin;
    	String dateCheckout;
    	double rate;
    	boolean websiteReservation;
    	double paymentsMade;
    	Scanner scanner_C= new Scanner(System.in);
    	System.out.println("\n Enter Fisrt Name: ");
    	fName = scanner_C.next();
    	System.out.println("\n Enter Last Name: ");
    	lName = scanner_C.next();
    	System.out.println("\n Enter Phone Number: ");
    	phoneNumber = scanner_C.next();
    	System.out.println("\n Enter Email : ");
    	email = scanner_C.next();
    	System.out.println("\n Enter Address : ");
    	address = scanner_C.next();
    	System.out.println("\n Enter ID Number : ");
    	idNumber = scanner_C.next();
    	System.out.println("\n Enter License Plate: ");
    	licensePlate = scanner_C.next();
    	Guest guest_tmp =   hotel.new Guest(fName,lName,phoneNumber,address,email,idNumber,licensePlate);
    	System.out.println("\n Enter date Check In (MM-dd-yyyy) : ");
    	dateCheckin = scanner_C.next();
    	System.out.println("\n Enter date Check Out (MM-dd-yyyy) : ");
    	dateCheckout = scanner_C.next();
    	System.out.println("\n Enter rate : ");
    	rate = scanner_C.nextDouble(); 
    	System.out.println("\n Website Reservation (true/false): ");
    	websiteReservation = scanner_C.nextBoolean();
    	System.out.println("\n Enter Payments Made : ");
    	paymentsMade = scanner_C.nextDouble();
    	Reservation reservation_tmp = hotel.new Reservation(guest_tmp,dateCheckin,dateCheckout,rate,room,websiteReservation,paymentsMade);
    	List<Room> tmp_room = hotel.getRooms();
    	for( Room element : tmp_room) {
    		if (element !=null) {
    			if(element == room) {
    				element.addToReservation(reservation_tmp);
    			}
    		}
    	}
//    	hotel.getRooms().get(room.getRoomNumber()).addToReservation(reservation_tmp);
    	hotel.updateRoomInfo(room);
    	System.out.println(
    			"1. Check in\n" +
    			"2. Guest's Profile\n" +
    			"3. Back to Main Menu\n"
    	);
    	scanner_C = new Scanner(System.in);
    	int option = scanner_C.nextInt();
    	while(option > 3 || option <1 ) {
    		System.out.println(
        			"1. Check in\n" +
        			"2. Guest's Profile\n" +
        			"3. Back to Main Menu\n"
        	);
    	}
    	switch(option) {
		case 1:
			System.out.println("Checked in");
	    	printCurrentGuestInfo(guest_tmp,room,reservation_tmp);
			room.setRoomStatus(RoomStatus.UNAVAILABLE_OCCUPIED);
			hotel.updateRoomInfo(room);
			System.out.println("1. Enter 1 to back to Main Menu");
	    	option = scanner_C.nextInt();
	    	while(option !=1) {
	    		System.out.println("1. Enter 1 to back to Main Menu");
		    	option = scanner_C.nextInt();
	    	}
			printCapabilitiesOptions();
			break;
		case 2:
			//call function cap 5
			showGuestInfo();
			break;
		case 3:
			System.out.println("Enter invalid back to main menu");
			printCapabilitiesOptions();
			break;
    	}

    }
    
    // If this screen is reached from Capability 1 or Capability 2 with a guest checked-in,
    // the fields  contain the appropriate information and an option to check the guest out
    // be available.  If the guest checks out, the room status (See Capability 1) turns to
    // Unavailable/Dirty.


    private static void currentGuestInfo_guest(Guest guest,Room room) {
    	List<Reservation> reservation_tmp = room.getReservation();
    	Reservation currentReservation_tmp = null;
    	for ( Reservation element : reservation_tmp) {
    		if(element.getGuest() == guest) {
    			printCurrentGuestInfo(guest,room,element);
    			currentReservation_tmp = element;
    		}
    	}
    	System.out.println(
    			"1. Check out\n" +
    			"2. Guest's Profile \n" +
    			"3. Back to Main Menu\n"
    	);
    	Scanner scanner_C= new Scanner(System.in);
    	int option = scanner_C.nextInt();
    	while(option > 3 || option <1 ) {
    		System.out.println(
        			"1. Check out\n" +
        			"2. Guest's Profile\n" +
        			"3. Back to Main Menu\n"
        	);
    	}
    	switch(option) {
    		case 1:
    			room.removeFromReservation(currentReservation_tmp);
    			room.setRoomStatus(RoomStatus.UNAVAILABLE_DIRTY);
    			hotel.updateRoomInfo(room);
    			System.out.println("Back to Main Menu");
    			printCapabilitiesOptions();
    			break;
    		case 2:
    			//call function cap 5
    			showGuestInfo();
    			break;
    		case 3:
    			printCapabilitiesOptions();
    			break;
    	}
    }
    
    // If this screen is reached from Capability 2 or Capability 3 with a reservation for a
    // guest, the information from the reservation should be transferred over to this screen.
    // Once the remaining information is filled out, the guests can then be checked-in.
    // If a guest checks  in, the room status (See Capability 1) turns to Unavailable/Occupied.


    private static void currentGuestInfo_reservation(Reservation reservation) {
    	String fName;
    	String lName;
    	String phoneNumber;
    	String email;
    	String address;
    	String idNumber;
    	String licensePlate;
    	Scanner scanner_C= new Scanner(System.in);
    	System.out.println("\n Enter Fisrt Name: ");
    	fName = scanner_C.next();
    	System.out.println("\n Enter Last Name: ");
    	lName = scanner_C.next();
    	System.out.println("\n Enter Phone Number: ");
    	phoneNumber = scanner_C.next();
    	System.out.println("\n Enter Email : ");
    	email = scanner_C.next();
    	System.out.println("\n Enter Address : ");
    	address = scanner_C.next();
    	System.out.println("\n Enter ID Number : ");
    	idNumber = scanner_C.next();
    	System.out.println("\n Enter License Plate: ");
    	licensePlate = scanner_C.next();
    	Room room_tmp = hotel.getRooms().get(reservation.getRoom().getRoomNumber());
    	Guest guest_tmp =   hotel.new Guest(fName,lName,phoneNumber,address,email,idNumber,licensePlate);
    	System.out.println(
    			"1. Check in\n" +
            	"2. Guest's Profile\n" +
            	"3. Back to Main Menu\n"
    	);
    	scanner_C = new Scanner(System.in);
    	int option = scanner_C.nextInt();
    	while(option > 3 || option <1 ) {
    		System.out.println(
        			"1. Check in\n" +
        			"2. Guest's Profile\n" +
        			"3. Back to Main Menu\n"
        	);
    	}
    	switch(option) {
		case 1:
			System.out.println("Checked in");
	    	printCurrentGuestInfo(guest_tmp,room_tmp,reservation);
			room_tmp.setRoomStatus(RoomStatus.UNAVAILABLE_OCCUPIED);
			hotel.updateRoomInfo(room_tmp);
			System.out.println("1. Enter 1 to back to Main Menu");
	    	option = scanner_C.nextInt();
	    	while(option !=1) {
	    		System.out.println("1. Enter 1 to back to Main Menu");
		    	option = scanner_C.nextInt();
	    	}
			printCapabilitiesOptions();
			break;
		case 2:
			//call function cap 5
			showGuestInfo();
			break;
		case 3:
			printCapabilitiesOptions();
			break;
    	}
    }
    private static void printCurrentGuestInfo(Guest guest,Room room, Reservation reservation) {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	System.out.println(
    			"Guest First Name: " + guest.getFirstName() + "\n" +
    			"Guest Last Name: " + guest.getLastName()  + "\n" + 
                "Date Check-in: " +  reservation.getDateCheckIn() + "\n" +
                "Time Check In: " +  formatter.format(reservation.getDateMade()) + "\n" +
                "Expected Checkout Date: " +   reservation.getDateCheckout() + "\n" + 
                "Exptected Check Out Time: 11:00 A.M\n" +
                "Room Type: " + room.getRoomType() + "\n" +
                "Room Number: " + room.getRoomNumber() + "\n" +
                "Rate ($/Day): " + reservation.getRate() + "\n" + 
                "Payment Made: " + reservation.getPaymentsMade() + "\n" +
                "Balance: " +		reservation.getBalance() + "\n" +
                "Total Charge: " + reservation.getTotalCharge() + "\n"
        );
    	
    }
    private static void currentGuestInfo() {
    	System.out.println("Enter room number:");
        Scanner scanner_r= new Scanner(System.in);
    	int tmp = scanner_r.nextInt();
//    	hotel.getRooms()
    	System.out.println(
    			"Guest Name: \n" +
                "Date Check-in:\n" +
                "Time Check In:\n" +
                "Expected Checkout Date:\n" +
                "Exptected Check Out Time:\n" +
                "Room Type:\n" +
                "Room Number:\n" +
                "Rate ($/Day):\n" +
                "Payment Made:\n" +
                "Balance:\n" +
                "Total Charge:\n");
    }
    //Capability 7: A search screen to search for guests
    //Tuan Ngo
    private static void searchScreen() {
//    	System.out.println(
//    			"1. Search By Guest First Name\n" +
//                "2. Search By Guest Last Name:\n" +
//                "3. Search By Room Number:\n" +
//                "4. Search By Phone Number:\n" +
//                "5. Search By Street Address:\n" +
//                "6. Search By Date Check In :\n" +
//                "7. Search by Date Check Out:\n"
//    	);
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
    			printCapabilitiesOptions();
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
    				printCapabilitiesOptions();
    			}
    			else {
    				//Cap 5 function passed variables with guest name to access full profile.
    			}
    		}
    	}
    	else {
    		System.out.println();
    		printCapabilitiesOptions();
    	}
    	
    }
    	
    //Capability 8 - A daily report screen
    //Christopher Ordinario
    private static void showDailyReport(){
        System.out.println("Rooms rented today: \n" +
            "Room Number: \n" +
            "Guest Name: \n" +
            "Date In: \n" +
            "Date Out: \n" +
            "Amount Paid: \n" +
            "Total Amount Paid For All Rooms: \n");
    }

    private static String getCapabilityOption(int option) {
        switch (option) {
        	case 1:
        		return "Show list of all rooms.";
        	case 2:
        		return "Show list of all rooms with their reserveations for the next 7 days.";
            case 3:
                return "Display a list of all reservations currently in the system";
            case 4:
                return "Display housekeeping information";
            case 5:
                return "show guest information";
            case 6:
            	return "Show a Guest's information";
            case 7:
            	return "Search for guests";
            case 8:
                return "show the daily report screen";
            default:
                return "exit";
        }
    }

    private static void printErrorMessage() {
        System.out.println("Please enter a valid number\n");
    }
}

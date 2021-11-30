package cap6;

import com.softwaretesting.project1.Hotel;
import com.softwaretesting.project1.RoomStatus;
import com.softwaretesting.project1.Hotel.Room;
import com.softwaretesting.project1.Hotel.Reservation;
import com.softwaretesting.project1.Hotel.Guest;
import com.softwaretesting.project1.Main;

import java.text.SimpleDateFormat;
import java.util.*;
public class Capability6 {

    //If this screen is reached from Capability 1 or Capability 2 with no guest checked-in
    // the fields  be blank and can be filled in by the User.
    //The guests can then be checked-in when the information is filled out.
    //If a guest checks  in, the room status (See Capability 1) turns to Unavailable/Occupied.
    
	public static void currentGuestInfo_noguest(Hotel hotel,Room room) {
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
			Main.printCapabilitiesOptions();
			break;
		case 2:
			//call function cap 5
//			showGuestInfo();
			break;
		case 3:
			System.out.println("Enter invalid back to main menu");
			Main.printCapabilitiesOptions();
			break;
    	}

    }
	// If this screen is reached from Capability 1 or Capability 2 with a guest checked-in,
    // the fields  contain the appropriate information and an option to check the guest out
    // be available.  If the guest checks out, the room status (See Capability 1) turns to
    // Unavailable/Dirty.


    public static void currentGuestInfo_guest(Hotel hotel,Guest guest,Room room) {
    	List<Reservation> reservation_tmp = room.getReservations();
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
    			Main.printCapabilitiesOptions();
    			break;
    		case 2:
    			//call function cap 5
    			//showGuestInfo();
    			break;
    		case 3:
    			Main.printCapabilitiesOptions();
    			break;
    	}
    }
    
    // If this screen is reached from Capability 2 or Capability 3 with a reservation for a
    // guest, the information from the reservation should be transferred over to this screen.
    // Once the remaining information is filled out, the guests can then be checked-in.
    // If a guest checks  in, the room status (See Capability 1) turns to Unavailable/Occupied.


    public static void currentGuestInfo_reservation(Hotel hotel,Reservation reservation) {
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
    	List<Room> Rooms = hotel.getRooms();
    	Room room_tmp = null;
    	for(Room element : Rooms) {
    		if (element != null) {
    			if(element == reservation.getRoom()) {
    				room_tmp = element;
    			}
    		}
    	}
		if (room_tmp!=null) {
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
					Main.printCapabilitiesOptions();
					break;
				case 2:
					//call function cap 5
					//showGuestInfo();
					break;
				case 3:
					Main.printCapabilitiesOptions();
					break;
			}
		}
    }
	
	
	public static void printCurrentGuestInfo(Guest guest,Room room, Reservation reservation) {
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
}

package com.softwaretesting.project1;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;
import com.softwaretesting.project1.Hotel.Guest;
import com.softwaretesting.project1.Hotel.Reservation;
import com.softwaretesting.project1.Hotel.Room;
import cap7.cap7;

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
    	AddReservation = hotel.new Reservation(GuestInfo,"01-08-2001 23:20:11","01-10-2001 08:00:00".toString(),1.0,CreateRoom,false,0); 
    	hotel.getRooms().get(0).addToReservation(AddReservation);
    	printCapabilitiesOptions();
    }

    public static void printCapabilitiesOptions() {
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
                searchScreen();
                break;
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

    //Capability 7: A search screen to search for guests
    //Tuan Ngo
    private static void searchScreen() {
		cap7.searchScreen(hotel);
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
//            case 6:
//            	return "Show a Guest's information";
            case 6:
            	return "Search for guests";
            case 7:
                return "show the daily report screen";
            default:
                return "exit";
        }
    }

    private static void printErrorMessage() {
        System.out.println("Please enter a valid number\n");
    }
}

package com.softwaretesting.project1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final int TOTAL_CAPABILITIES = 8;
    private static final Scanner scanner = new Scanner(System.in);
    private static final Hotel hotel = new Hotel();

    public static void main(String[] args) {
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
            	currentGuestInfo();
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
    //Capability 6 - Current stay screen showing a guest�s information for their current stay.
    //Tuan Ngo
    private static void currentGuestInfo() {
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
    	System.out.println(
    			"1. Search By Guest First Name\n" +
                "2. Search By Guest Last Name:\n" +
                "3. Search By Room Number:\n" +
                "4. Search By Phone Number:\n" +
                "5. Search By Street Address:\n" +
                "6. Search By Date Check In :\n" +
                "7. Search by Date Check Out:\n"
    	);
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

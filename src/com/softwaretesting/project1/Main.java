package com.softwaretesting.project1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final int TOTAL_CAPABILITIES = 8;
    private static final Scanner scanner = new Scanner(System.in);

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
  	
  	}

    private static void performOperation(int selectedOption) {
        switch (selectedOption) {
            case 3:
                thirdCapabilityOperation();
                break;
            case 4:
                fourthCapabilityOperation();
        }
    }

    private static void fourthCapabilityOperation() {
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

    private static void thirdCapabilityOperation() {
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

    private static String getCapabilityOption(int option) {
        switch (option) {
            case 3:
                return "Display a list of all reservations currently in the system";
            case 4:
                return "Display housekeeping information";
            default:
                return "exit";
        }
    }

    private static void printErrorMessage() {
        System.out.println("Please enter a valid number\n");
    }
}

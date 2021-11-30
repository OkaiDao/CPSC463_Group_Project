package com.softwaretesting.project1;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import capability1.Capability1;
import capability2.Capability2;
import capability3.Capability3;
import capability4.Capability4;
import cap7.cap7;

public class Main {

    private static final int TOTAL_CAPABILITIES = 5;
    private static final Scanner scanner = new Scanner(System.in);
    private static Hotel hotel = new Hotel();
    public static void main(String[] args) {
        createRooms(hotel);
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
                scanner.nextLine();
                if (selectedOption!=-1 && (selectedOption < 1 || selectedOption > TOTAL_CAPABILITIES)){
                    printErrorMessage();
                } else {
                    performOperation(selectedOption);
                }
            } catch (InputMismatchException exception) {
                printErrorMessage();
            }
        }
    }

    private static void performOperation(int selectedOption) {
        switch (selectedOption) {
        	case 1:
                try {
                    Capability1.printRoom_Status(hotel, scanner);
                } catch (ParseException e) {
                    System.out.println("Error formatting the date from the input");
                }
        		break;
        	case 2:
        		try {
                    Capability2.printReservation_List(scanner, hotel);
                } catch (ParseException e) {
                    System.out.println("Error formatting the date from the input");
                }
        		break;
            case 3:
                Capability3.modifyReservation(scanner, hotel);
                break;
            case 4:
                Capability4.manageHouseKeeping(hotel, scanner);
                break;
            case 5:
                cap7.searchScreen(hotel);
                break;
        }
    }

    private static String getCapabilityOption(int option) {
        switch (option) {
        	case 1:
        		return "Show list of all rooms and their status";
        	case 2:
        		return "Show list of all rooms with their reservations for the next 7 days.";
            case 3:
                return "Select to add or update or delete a reservation";
            case 4:
                return "Update housekeeping information for a room";
            case 5:
            	return "Search for guests";
            default:
                return "exit";
        }
    }

    private static void printErrorMessage() {
        System.out.println("Please enter a valid number\n");
    }
    
    private static void createRooms(Hotel hotel) {
    	for(int i = 0 ; i < 21 ; i++) {
            Hotel.Room fakeroom = hotel.new Room(RoomType.values()[i%4], RoomStatus.values()[i%4],i+100);
            hotel.addRoom(fakeroom);
        }
    }
}

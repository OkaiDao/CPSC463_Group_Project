package com.softwaretesting.project1;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;
import capability1.Capability1;
import capability2.Capability2;
import capability3.Capability3;
import capability4.Capability4;
import com.softwaretesting.project1.Hotel.Guest;
import com.softwaretesting.project1.Hotel.Reservation;
import com.softwaretesting.project1.Hotel.Room;
import cap7.cap7;

public class Main {

    private static final int TOTAL_CAPABILITIES = 5;
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
                Capability1.printRoom_Status(hotel, scanner);
        		break;
        	case 2:
        		Capability2.printReservation_List(scanner, hotel);
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
}

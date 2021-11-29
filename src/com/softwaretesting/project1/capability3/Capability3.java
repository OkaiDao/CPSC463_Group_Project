package com.softwaretesting.project1.capability3;

import com.softwaretesting.project1.Hotel;
import com.softwaretesting.project1.RoomStatus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Capability3 {

    /**
     *  Make updates on a reservation - Add, Delete or Update
     * */
    public static void modifyReservation(Scanner scanner, Hotel hotel) {
        System.out.println("Enter 1 to add a reservation");
        System.out.println("Enter 2 to delete a reservation");
        System.out.println("Enter 3 to update a reservation");
        System.out.println("Enter 4 to display all reservations");
        int selectedOption = scanner.nextInt();
        scanner.nextLine();
        try {
            if (selectedOption == 1) {
                Hotel.Guest guest = getGuestFromInfo(scanner, hotel);
                addReservation(scanner, hotel, guest);
            } else if (selectedOption == 2) {
                deleteReservation(scanner, hotel);
            } else if (selectedOption == 3) {
                updateReservation(scanner, hotel);
            } else if (selectedOption == 4) {
                displayAllReservation(hotel);
            }
        } catch (ParseException e) {
            System.out.println("Error parsing date from the input");
        }
    }

    /**
     * Add a reservation if there's any availability
     * */
    private static void addReservation(Scanner scanner, Hotel hotel, Hotel.Guest guest) throws ParseException {
        System.out.println("Enter the date for check in format(mm-dd-yyyy): ");
        String checkinDate = scanner.nextLine();
        System.out.println("Enter the date for check out in format(mm-dd-yyyy): ");
        String checkoutDate = scanner.nextLine();
        Date dateCheckIn = getDateFromString(checkinDate);
        Date dateCheckout = getDateFromString(checkoutDate);
        boolean isRoomFound = false;
        Hotel.Room updateRoom = null;
        for (Hotel.Room room : hotel.getRooms()) {
            if (room.getReservations().isEmpty() || canReservationBeAdded(dateCheckIn, dateCheckout, room.getReservations())) {
                isRoomFound = true;
                double rate = getRate();
                double total = getRate() * getNumberOfDaysBetweenTwoDates(dateCheckIn, dateCheckout);
                Hotel.Reservation newReservation = hotel.new Reservation(guest, checkinDate, checkoutDate, rate, room, false, total);
                updateRoom = room;
                updateRoom.addToReservation(newReservation);
            }
            if (isRoomFound) {
                break;
            }
        }
        if (isRoomFound) {
            hotel.updateRoomInfo(updateRoom);
            System.out.println("Reservation added");
        } else {
            System.out.println("Failed to add reservation. Please pick a different check in and check out date");
        }
    }

    /**
     * Delete a reservation
     * */
    private static void deleteReservation(Scanner scanner, Hotel hotel) throws ParseException {
        System.out.println("Enter your id number: ");
        String idNumber = scanner.nextLine();
        System.out.println("Enter the date when the reservation was made in the format mm-dd-yyyy: ");
        String reservationMadeDate = scanner.nextLine();
        Hotel.Reservation findReservation = findReservation(hotel, idNumber, reservationMadeDate);
        if (findReservation != null) {
            Hotel.Room room = findReservation.getRoom();
            room.removeFromReservation(findReservation);
            hotel.updateRoomInfo(room);
            System.out.println("Reservation successfully removed");
        } else {
            System.out.println("Couldn't find the reservation");
        }
    }

    /**
     * Make some updates on existing reservation
     * */
    private static void updateReservation(Scanner scanner, Hotel hotel) throws ParseException {
        System.out.println("Enter your id number: ");
        String idNumber = scanner.nextLine();
        System.out.println("Enter the date you made made the reservation in the format mm-dd-yyyy: ");
        String reservationDate = scanner.nextLine();
        Hotel.Reservation reservation = findReservation(hotel, idNumber, reservationDate);
        if (reservation != null) {
            int selectedOption = -1;
            Hotel.Room room = reservation.getRoom();
            while (selectedOption != 0) {
                if (room.getRoomStatus() == RoomStatus.UNAVAILABLE_MAINTENANCE) {
                    System.out.println("Your room is currently in maintainence mode");
                }
                //TODO move to capability 6 when check in is selected
                System.out.println("How would you like to update your reservation? ");
                System.out.println("Select 1 to update your information");
                System.out.println("Select 2 to update your check-in date");
                System.out.println("Select 3 to update your check out date");
                System.out.println("Select 0 to exit out of this menu");
                selectedOption = scanner.nextInt();
                scanner.nextLine();
                if (selectedOption == 1) {
                    Hotel.Guest guest = getGuestFromInfo(scanner, hotel);
                    Hotel.Reservation newReservation = hotel.new Reservation(guest, reservation.getDateCheckIn(), reservation.getDateCheckout(), reservation.getRate(), room, reservation.isWebsiteReservation(), reservation.getPaymentsMade());
                    room.updateReservation(reservation, newReservation);
                    hotel.updateRoomInfo(room);
                    reservation = newReservation;
                    System.out.println("Update the information successfully");
                } else if (selectedOption == 2) {
                    System.out.println("Enter new check-in date: ");
                    Date newReservationCheckinDate = getDateFromString(scanner.nextLine());
                    room.removeFromReservation(reservation);
                    hotel.updateRoomInfo(room);
                    boolean isReservationMade = false;
                    for (Hotel.Room roomI : hotel.getRooms()) {
                        if (canReservationBeAdded(newReservationCheckinDate, reservation.getDateCheckout(), roomI.getReservations())) {
                            double totalCharge = getNumberOfDaysBetweenTwoDates(newReservationCheckinDate, reservation.getDateCheckout()) * reservation.getRate();
                            Hotel.Reservation newReservation = hotel.new Reservation(reservation.getGuest(), newReservationCheckinDate, reservation.getDateCheckout(), reservation.getRate(), roomI, reservation.isWebsiteReservation(), totalCharge);
                            roomI.addToReservation(newReservation);
                            hotel.updateRoomInfo(roomI);
                            room.removeFromReservation(reservation);
                            hotel.updateRoomInfo(room);
                            room = roomI;
                            isReservationMade = true;
                            reservation = newReservation;
                            break;
                        }
                    }
                    if (!isReservationMade) {
                        room.addToReservation(reservation);
                        hotel.updateRoomInfo(room);
                        System.out.println("Couldn't find any available reservation slot. Try changing the check in or check out date");
                    } else {
                        System.out.println("Updated");
                    }
                } else if (selectedOption == 3) {
                    System.out.println("Enter new check out date: ");
                    Date newReservationCheckoutDate = getDateFromString(scanner.nextLine());
                    boolean isReservationMade = false;
                    room.removeFromReservation(reservation);
                    hotel.updateRoomInfo(room);
                    for (Hotel.Room roomI : hotel.getRooms()) {
                        if (canReservationBeAdded(reservation.getDateCheckIn(), newReservationCheckoutDate, roomI.getReservations())) {
                            double totalCharge = getNumberOfDaysBetweenTwoDates(reservation.getDateCheckIn(), newReservationCheckoutDate) * reservation.getRate();
                            Hotel.Reservation newReservation = hotel.new Reservation(reservation.getGuest(), reservation.getDateCheckIn(), newReservationCheckoutDate, reservation.getRate(), roomI, reservation.isWebsiteReservation(), totalCharge);
                            roomI.addToReservation(newReservation);
                            hotel.updateRoomInfo(roomI);
                            room.removeFromReservation(reservation);
                            hotel.updateRoomInfo(room);
                            room = roomI;
                            isReservationMade = true;
                            reservation = newReservation;
                            break;
                        }
                    }
                    if (!isReservationMade) {
                        room.addToReservation(reservation);
                        hotel.updateRoomInfo(room);
                        System.out.println("Couldn't find any available reservation slot. Try changing the check in or check out date");
                    } else {
                        System.out.println("Updated");
                    }
                } else if (selectedOption != 0) {
                    System.out.println("Please select a valid option");
                }
            }
        } else {
            System.out.println("Sorry, couldn't find the reservation");
        }

    }

    public static void displayAllReservation(Hotel hotel) {
        for (Hotel.Room room : hotel.getRooms()) {
            room.getReservations().forEach(System.out::println);
        }
    }

    /**
     * Get a guest object from user's input
     * */
    public static Hotel.Guest getGuestFromInfo(Scanner scanner, Hotel hotel) {
        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter your address: ");
        String address = scanner.nextLine();
        System.out.println("Enter your email address: ");
        String email = scanner.nextLine();
        System.out.println("Enter your identification card number: ");
        String idNumber = scanner.nextLine();
        System.out.println("Enter your license plate: ");
        String licensePlate = scanner.nextLine();
        return hotel.new Guest(firstName, lastName, phoneNumber, address, email, idNumber, licensePlate);
    }

    /***
     * Check if a reservation can be added
     * */
    public static boolean canReservationBeAdded(Date dateCheckIn, Date dateCheckout, List<Hotel.Reservation> reservations) {
        return reservations
                .stream()
                .noneMatch(reservation ->
                        //check in date before the reservation check out, but checkout date after the reservation checkout
                        dateCheckIn.before(reservation.getDateCheckout()) && dateCheckout.after(reservation.getDateCheckout())
                        //check in and checkout date between reservation's check in and checkout
                        || dateCheckIn.after(reservation.getDateCheckIn()) && dateCheckout.before(reservation.getDateCheckout())
                        // checkin and checkout date is the same as reservation's check in and checkout date
                        || dateCheckIn.equals(reservation.getDateCheckIn()) && dateCheckout.equals(reservation.getDateCheckout())
                        // checkout date is in between reservation's checkin and checkout date
                        || dateCheckout.after(reservation.getDateCheckIn()) && dateCheckout.before(reservation.getDateCheckout())
                );
    }

    /**
     * Get the number of days between any two dates
     * */
    public static int getNumberOfDaysBetweenTwoDates(Date date1, Date date2) {
        Instant date1Instant = date1.toInstant();
        Instant date2Instant = date2.toInstant();
        return (int) ChronoUnit.DAYS.between(date1Instant, date2Instant);
    }

    /**
     * Get date object from a string. The date in the string should be in the format mm-dd-yyyy
     * */
    public static Date getDateFromString(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        return simpleDateFormat.parse(date);
    }

    /**
     * Get date string in the format mm-dd-yyyy
     * */
    public static String getStringFromDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        return simpleDateFormat.format(date);
    }

    /**
     * Find a reservation using guest id number and the date the reservation was made
     * */
    public static Hotel.Reservation findReservation(Hotel hotel, String idNumber, String reservationMadeDate) {
        for (Hotel.Room room : hotel.getRooms()) {
            for (Hotel.Reservation reservation : room.getReservations()) {
                if (reservation.getGuest().getIdNumber().equalsIgnoreCase(idNumber) && getStringFromDate(reservation.getDateMade()).equals(reservationMadeDate)) {
                    return reservation;
                }
            }
        }
        return null;
    }

    /**
     * Get a random rate for reservation between 45 and 129
     * */
    public static double getRate() {
        Random random = new Random();
        return random.nextInt((90-5))+45;
    }
}

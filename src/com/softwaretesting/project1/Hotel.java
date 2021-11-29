package com.softwaretesting.project1;

import com.softwaretesting.project1.capability3.Capability3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Hotel {

    private Map<Integer, Room> rooms = new HashMap<>();

    public void addRoom(Room room) {
        rooms.putIfAbsent(room.roomNumber, room);
    }

    public void updateRoomInfo(Room room) {
        rooms.replace(room.roomNumber, room);
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms.values());
    }

    public class Room {
        private RoomType roomType;
        private RoomStatus roomStatus;
        private int roomNumber;
        private List<Reservation> reservation;

        public Room(RoomType roomType, RoomStatus roomStatus, int roomNumber) {
            this.roomType = roomType;
            this.roomStatus = roomStatus;
            this.roomNumber = roomNumber;
            this.reservation = new ArrayList<>();
        }

        public void addToReservation(Reservation reservation) {
            this.reservation.add(reservation);
        }

        public void removeFromReservation(Reservation reservation) {
            this.reservation.remove(reservation);
        }

        public void updateReservation(Reservation oldReservation, Reservation newReservation) {
            reservation.remove(oldReservation);
            reservation.add(newReservation);
        }

        public List<Reservation> getReservations() {
            return reservation;
        }

        public RoomStatus getRoomStatus() {
            return roomStatus;
        }
    }

    public class Reservation {
        private Guest guest;
        private Date dateMade;
        private Date dateCheckIn;
        private Date dateCheckout;
        private double rate;
        private Room room;
        private boolean websiteReservation;
        private double paymentsMade;
        private boolean isCheckedIn;

        //Date check in and check out should be a string in the form "MM-dd-yyyy"
        public Reservation(Guest guest, String dateCheckIn, String dateCheckout, double rate, Room room, boolean websiteReservation, double paymentsMade) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
                this.dateCheckIn = simpleDateFormat.parse(dateCheckIn);
                this.dateCheckout = simpleDateFormat.parse(dateCheckout);
                this.guest = guest;
                this.dateMade = new Date();
                this.rate = rate;
                this.room = room;
                this.websiteReservation = websiteReservation;
                this.paymentsMade = paymentsMade;
                isCheckedIn = false;
            } catch (ParseException e) {
                System.out.println("Failed to create reservation. Please enter date in the correct format");
            }
        }

        //Date check in and check out should be a string in the form "MM-dd-yyyy"
        public Reservation(Guest guest, Date dateCheckIn, Date dateCheckout, double rate, Room room, boolean websiteReservation, double paymentsMade) {
            this.dateCheckIn = dateCheckIn;
            this.dateCheckout = dateCheckout;
            this.guest = guest;
            this.dateMade = new Date();
            this.rate = rate;
            this.room = room;
            this.websiteReservation = websiteReservation;
            this.paymentsMade = paymentsMade;
            isCheckedIn = false;
        }

        public Date getDateCheckIn() {
            return dateCheckIn;
        }

        public Date getDateCheckout() {
            return dateCheckout;
        }

        public Guest getGuest() {
            return guest;
        }

        public void setGuest(Guest guest) {
            this.guest = guest;
        }

        public Date getDateMade() {
            return dateMade;
        }

        public void setDateMade(Date dateMade) {
            this.dateMade = dateMade;
        }

        public boolean isCheckedIn() {
            return isCheckedIn;
        }

        public void setCheckedIn(boolean checkedIn) {
            isCheckedIn = checkedIn;
        }

        public Room getRoom() {
            return room;
        }

        public double getRate() {
            return rate;
        }

        public boolean isWebsiteReservation() {
            return websiteReservation;
        }

        public double getPaymentsMade() {
            return paymentsMade;
        }

        public double getTotalCharge() {
            return 0;
        }

        public double getBalance() {
            return getTotalCharge() - paymentsMade;
        }

        @Override
        public String toString() {
            return guest + ", Check in date: " + Capability3.getStringFromDate(dateCheckIn) + ", Check out date: " + Capability3.getStringFromDate(dateCheckout) + ", Room number: " + room.roomNumber;
        }
    }

    class HouseKeeping {
        private String HouseKeeper;
        private Room room;
        private String bathroom;
        private boolean towels;
        private boolean vacuum;
        private boolean dusting;
        private boolean electronics;
    }

    public class Guest {
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String address;
        private String email;
        private String idNumber;
        private String licensePlate;

        public Guest(String firstName, String lastName, String phoneNumber, String address, String email, String idNumber, String licensePlate) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.email = email;
            this.idNumber = idNumber;
            this.licensePlate = licensePlate;
        }

        public String getIdNumber() {
            return idNumber;
        }

        @Override
        public String toString() {
            return "First name: " + firstName + " Last name: " + lastName;
        }

        @Override
        public boolean equals(Object obj) {
            Guest guest = (Guest) obj;
            return guest.idNumber.equalsIgnoreCase(idNumber);
        }
    }
}

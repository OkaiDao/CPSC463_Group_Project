package com.softwaretesting.project1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Hotel {

    private Map<Integer,Room> rooms = new HashMap<>();

    public void addRoom(Room room) {
        rooms.putIfAbsent(room.roomNumber, room);
    }

    public void updateRoomInfo(Room room) {
        rooms.replace(room.roomNumber, room);
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms.values());
    }

    class Room {
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
    }

    class Reservation {
        private Guest guest;
        private Date dateMade;
        private Date dateCheckIn;
        private Date dateCheckout;
        private double rate;
        private Room room;
        private boolean websiteReservation;
        private double paymentsMade;

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
            } catch (ParseException e) {
                System.out.println("Failed to create reservation. Please enter date in the correct format");
            }
        }

        public double getTotalCharge() {
            return 0;
        }

        public double getBalance() {
            return getTotalCharge() - paymentsMade;
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

    class Guest {
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String address;
        private String email;
        private String idNumber;
        private String licensePlate;
    }
}

enum  RoomType {
    KING,
    DOUBLE_QUEEN,
    DOUBLE_QUEEN_WITH_KITCHEN,
    SUITE
}

enum RoomStatus {
    AVAILABLE,
    UNAVAILABLE_OCCUPIED,
    UNAVAILABLE_DIRTY,
    UNAVAILABLE_MAINTENANCE
}

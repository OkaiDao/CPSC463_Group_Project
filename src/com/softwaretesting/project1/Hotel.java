package com.softwaretesting.project1;

import java.util.ArrayList;

class Hotel {

    private ArrayList<Room> rooms;

    class Room {
        private RoomType roomType;
        private RoomStatus roomStatus;
        private int roomNumber;
    }

    class Reservation {
        private Guest guest;
        //TODO Work with the Date class
        private String dateMade;
        private String dateCheckIn;
        private String dateCheckout;
        private double rate;
        private Room room;
        private boolean websiteReservation;
        private double paymentsMade;

        public double getTotalCharge() {
            //TODO Calculate total charge
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

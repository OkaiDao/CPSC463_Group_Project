package com.softwaretesting.project1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Hotel {

    private Map<Integer,Room> rooms = new HashMap<>();
    private ArrayList<Reservation> guesthistory = new ArrayList<Reservation>();
    public void addRoom(Room room) {
        rooms.putIfAbsent(room.roomNumber, room);
    }

    public void updateRoomInfo(Room room) {
        rooms.replace(room.roomNumber, room);
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms.values());
    }
    public ArrayList<Reservation> getGuestHistory(){
    	return new ArrayList<>(guesthistory);
    }
 
    
    class Room {
        private RoomType roomType;
        public RoomType getRoomType() {
			return roomType;
		}

		public void setRoomType(RoomType roomType) {
			this.roomType = roomType;
		}

		private RoomStatus roomStatus;
        public RoomStatus getRoomStatus() {
			return roomStatus;
		}

		public void setRoomStatus(RoomStatus roomStatus) {
			this.roomStatus = roomStatus;
		}

		private int roomNumber;
        public int getRoomNumber() {
			return roomNumber;
		}

		public void setRoomNumber(int roomNumber) {
			this.roomNumber = roomNumber;
		}

		private List<Reservation> reservation = new ArrayList<Reservation>();

        public List<Reservation> getReservation() {
			return reservation;
		}

		public void setReservation(List<Reservation> reservation) {
			this.reservation = reservation;
		}

		public Room(RoomType roomType, RoomStatus roomStatus, int roomNumber) {
            this.roomType = roomType;
            this.roomStatus = roomStatus;
            this.roomNumber = roomNumber;
            this.reservation = new ArrayList<>();
        }

        public void addToReservation(Reservation reservation) {
            this.reservation.add(reservation);
            guesthistory.add(reservation);
        }

        public void removeFromReservation(Reservation reservation) {
            this.reservation.remove(reservation);
        }
    }

    class Reservation {
        public Guest getGuest() {
			return guest;
		}

		public void setGuest(Guest guest) {
			this.guest = guest;
		}

		public Date getDateCheckIn() {
			return dateCheckIn;
		}

		public void setDateCheckIn(Date dateCheckIn) {
			this.dateCheckIn = dateCheckIn;
		}

		public Date getDateCheckout() {
			return dateCheckout;
		}

		public void setDateCheckout(Date dateCheckout) {
			this.dateCheckout = dateCheckout;
		}

		public Room getRoom() {
			return room;
		}

		public void setRoom(Room room) {
			this.room = room;
		}

		private Guest guest;
        private Date dateMade;
        public Date getDateMade() {
			return dateMade;
		}

		public void setDateMade(Date dateMade) {
			this.dateMade = dateMade;
		}

		public double getRate() {
			return rate;
		}

		public void setRate(double rate) {
			this.rate = rate;
		}

		public boolean isWebsiteReservation() {
			return websiteReservation;
		}

		public void setWebsiteReservation(boolean websiteReservation) {
			this.websiteReservation = websiteReservation;
		}

		public double getPaymentsMade() {
			return paymentsMade;
		}

		public void setPaymentsMade(double paymentsMade) {
			this.paymentsMade = paymentsMade;
		}

		private Date dateCheckIn;
        private Date dateCheckout;
        private Date timeCheckIn;
        public Date getTimeCheckIn() {
			return timeCheckIn;
		}

		public void setTimeCheckIn(Date timeCheckIn) {
			this.timeCheckIn = timeCheckIn;
		}

		private double rate;
        private Room room;
        private boolean websiteReservation;
        private double paymentsMade;

        //Date check in and check out should be a string in the form "MM-dd-yyyy hh:mm:ss"
        public Reservation(Guest guest, String dateCheckIn, String dateCheckout, double rate, Room room, boolean websiteReservation, double paymentsMade) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
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
        public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		
		private String firstName;
        private String lastName;
        private String phoneNumber;
        public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getIdNumber() {
			return idNumber;
		}
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}
		public String getLicensePlate() {
			return licensePlate;
		}
		public void setLicensePlate(String licensePlate) {
			this.licensePlate = licensePlate;
		}

		private String address;
        private String email;
        private String idNumber;
        private String licensePlate;
        public Guest(String firstName, String lastName, String phoneNumber , String address, String email, String idNumber, String licensePlate) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.phoneNumber = phoneNumber;
			this.address = address;
			this.email = email;
			this.idNumber = idNumber;
			this.licensePlate = licensePlate;
		}
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

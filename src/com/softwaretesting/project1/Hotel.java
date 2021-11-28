package com.softwaretesting.project1;

import java.util.ArrayList;

class Hotel {

    private ArrayList<Room> rooms;
    //Assumes that Type1 + 2 + 3 are < 100
    private Hotel(int Type1, int Type2, int Type3)
    {
    	int roomIt = 0;
    	RoomType roomType;
    	RoomStatus roomStat;
    	
    	if (Type1 + Type2 + Type3 > 100)
    	{
    		System.out.println("Failed to create rooms list." + "\n");
    		return;
    	}
    	
    	while (roomIt < 100)
    	{
    		if(roomIt<Type1)
    		{
    			roomType = RoomType.KING;
    			roomStat = RoomStatus.AVAILABLE;
    		}
    		else if(roomIt<(Type1+Type2))
    		{
    			roomType = RoomType.DOUBLE_QUEEN;
    			roomStat = RoomStatus.AVAILABLE;
    		}
    		else if(roomIt<(Type1+Type2+Type3))
    		{
    			roomType = RoomType.DOUBLE_QUEEN_WITH_KITCHEN;
    			roomStat = RoomStatus.AVAILABLE;
    		}
    		else
    		{
    			roomType = RoomType.SUITE;
    			roomStat = RoomStatus.AVAILABLE;
    		}
    		Room tempRoom = new Room(roomType, roomStat, roomIt);
    		rooms.add(tempRoom);
    		//this.rooms[roomIt].Room(roomType, roomStat, roomIt); 
    	}
    }
    
    class Room {
        private RoomType roomType;
        private RoomStatus roomStatus;
        private int roomNumber;
        
        public Room()
        {
        	this.roomType = RoomType.KING;
        	this.roomStatus = roomStatus.UNAVAILABLE_DIRTY;
        	this.roomNumber = 0;
        }
        public Room(RoomType roomType, RoomStatus roomStat, int roomNum)
        {
        	this.roomType = roomType;
        	this.roomStatus = roomStat;
        	this.roomNumber = roomNum;
        }
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

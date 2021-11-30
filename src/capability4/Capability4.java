package capability4;

import com.softwaretesting.project1.Hotel;
import com.softwaretesting.project1.RoomStatus;

import java.util.Scanner;

public class Capability4 {

    public static void manageHouseKeeping(Hotel hotel, Scanner scanner) {
        System.out.println("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();
        Hotel.Room room = hotel.getRoomsMap().get(roomNumber);
        if (room != null) {
            int selectedOption = -1;
            while(selectedOption != 0) {
                System.out.println("What options would you like to check off? ");
                System.out.println("Enter 1 to check off bathroom");
                System.out.println("Enter 2 to check off towels");
                System.out.println("Enter 3 to check off vacuum");
                System.out.println("Enter 4 to check off dusting");
                System.out.println("Enter 5 to check off electronics");
                System.out.println("Enter 6 to check of all the options");
                System.out.println("Enter 7 to change the status of room to maintenance");
                System.out.println("Enter 0 to exit out of the menu");
                selectedOption = scanner.nextInt();
                scanner.nextLine();
                if (selectedOption==6) {
                    room.setHouseKeeping(hotel.new HouseKeeping(room.getHouseKeeping().getHouseKeeper(), room, true, true, true, true, true));
                    if(checkIfRoomAvailable(room)) {
                        room.setRoomStatus(RoomStatus.AVAILABLE);
                    } else {
                        room.setRoomStatus(RoomStatus.UNAVAILABLE_OCCUPIED);
                    }
                    hotel.updateRoomInfo(room);
                } else if (selectedOption > 0 && selectedOption <= 6 ){
                    if (selectedOption==1) {
                        room.setHouseKeeping(hotel.new HouseKeeping(room.getHouseKeeping().getHouseKeeper(), room, true, room.getHouseKeeping().isTowels(), room.getHouseKeeping().isVacuum(), room.getHouseKeeping().isDusting(), room.getHouseKeeping().isElectronics()));
                    } else if (selectedOption==2) {
                        room.setHouseKeeping(hotel.new HouseKeeping(room.getHouseKeeping().getHouseKeeper(), room, room.getHouseKeeping().isBathroom(), true, room.getHouseKeeping().isVacuum(), room.getHouseKeeping().isDusting(), room.getHouseKeeping().isElectronics()));
                    } else if (selectedOption==3) {
                        room.setHouseKeeping(hotel.new HouseKeeping(room.getHouseKeeping().getHouseKeeper(), room, room.getHouseKeeping().isBathroom(), room.getHouseKeeping().isTowels(), true, room.getHouseKeeping().isDusting(), room.getHouseKeeping().isElectronics()));
                    } else if (selectedOption==4) {
                        room.setHouseKeeping(hotel.new HouseKeeping(room.getHouseKeeping().getHouseKeeper(), room, room.getHouseKeeping().isBathroom(), room.getHouseKeeping().isTowels(), room.getHouseKeeping().isVacuum(), true, room.getHouseKeeping().isElectronics()));
                    } else {
                        room.setHouseKeeping(hotel.new HouseKeeping(room.getHouseKeeping().getHouseKeeper(), room, true, room.getHouseKeeping().isTowels(), room.getHouseKeeping().isVacuum(), room.getHouseKeeping().isDusting(), true));
                    }
                    if (checkIfRoomAvailable(room)) {
                        if (checkIfRoomIsClean(room)) {
                            room.setRoomStatus(RoomStatus.AVAILABLE);
                        } else {
                            room.setRoomStatus(RoomStatus.UNAVAILABLE_DIRTY);
                        }
                    } else {
                        room.setRoomStatus(RoomStatus.UNAVAILABLE_OCCUPIED);
                    }
                    hotel.updateRoomInfo(room);
                } else if (selectedOption==7) {
                    room.setRoomStatus(RoomStatus.UNAVAILABLE_MAINTENANCE);
                    hotel.updateRoomInfo(room);
                }
            }
        } else {
            System.out.println("Couldn't find the room");
        }
    }

    private static boolean checkIfRoomAvailable(Hotel.Room room) {
        return room.getReservations().stream().anyMatch(Hotel.Reservation::isCheckedIn);
    }

    private static boolean checkIfRoomIsClean(Hotel.Room room) {
        Hotel.HouseKeeping houseKeeping = room.getHouseKeeping();
        return houseKeeping.isBathroom() && houseKeeping.isDusting() && houseKeeping.isTowels() && houseKeeping.isVacuum() && houseKeeping.isElectronics();
    }


}
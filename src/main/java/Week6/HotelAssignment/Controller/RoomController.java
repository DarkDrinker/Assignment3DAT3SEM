package Week6.HotelAssignment.Controller;

import HotelAssignment.DAO.HotelDAO;
import HotelAssignment.DAO.RoomDAO;
import HotelAssignment.DTO.RoomDTO;
import HotelAssignment.model.Hotel;
import HotelAssignment.model.Room;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;

public class RoomController {
    private static RoomDAO roomDAO;
    public static Handler getRooms() {
        return ctx -> {
            List<Room> roomList = roomDAO.getAll();
            List<RoomDTO> roomDTOList = roomList.stream().map(y -> new RoomDTO(y.getId(), y.getHotelId(), y.getNumber(), y.getPrice())).toList();
            ctx.json(roomDTOList);
        };
    }

    public static void getRoomById(Context ctx) {
        int id =Integer.parseInt(ctx.pathParam("id"));
        Room room = roomDAO.getById(id);
        RoomDTO roomDTO = new RoomDTO(room.getId(),room.getHotelId(),room.getNumber(),room.getPrice());
        ctx.json(roomDTO);
    }

    public static void createRoom(Context ctx) {
        RoomDTO roomDTO = ctx.bodyAsClass(RoomDTO.class);
        if(roomDTO != null) {
            Room room = new Room(roomDTO.getHotelId(), roomDTO.getNumber(), roomDTO.getPrice());
            Hotel hotel = HotelDAO.getInstance(false).getById(room.getHotelId());
            room.setHotel(hotel);
            roomDAO.create(room);
            ctx.json(room);
        }
    }

    public static void deleteRoom(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Room room = roomDAO.getById(id);
        roomDAO.delete(id);
        ctx.json(room);
    }

    public static void updateRoom(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        //to be filled
    }


}

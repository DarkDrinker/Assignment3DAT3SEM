package Week6.HotelAssignment.Controller;


import Week6.HotelAssignment.DAO.HotelDAO;
import Week6.HotelAssignment.DAO.RoomDAO;
import Week6.HotelAssignment.DTO.RoomDTO;
import Week6.HotelAssignment.model.Hotel;
import Week6.HotelAssignment.model.Room;
import io.javalin.http.Handler;

import java.util.List;

public class RoomController {
    private static RoomDAO roomDAO;
    private static RoomController instance;


    public static RoomController getInstance(boolean isTesting){
        if(instance == null){
            instance = new RoomController();
            roomDAO = RoomDAO.getInstance(isTesting);
        }
        return instance;
    }



    public Handler getRooms() {
        return ctx -> {
            List<Room> roomList = roomDAO.getAll();
            List<RoomDTO> roomDTOList = roomList.stream().map(y -> new RoomDTO(y.getId(), y.getHotelId(), y.getNumber(), y.getPrice())).toList();
            ctx.json(roomDTOList);
        };
    }

    public Handler getRoomById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Room room = roomDAO.getById(id);
            RoomDTO roomDTO = new RoomDTO(room.getId(), room.getHotelId(), room.getNumber(), room.getPrice());
            ctx.json(roomDTO);
        };
    }

    public Handler createRoom() {
        return ctx -> {
            RoomDTO roomDTO = ctx.bodyAsClass(RoomDTO.class);
            if (roomDTO != null) {
                Room room = new Room(roomDTO.getHotelId(), roomDTO.getNumber(), roomDTO.getPrice());
                Hotel hotel = HotelDAO.getInstance(false).getById(room.getHotelId());
                room.setHotel(hotel);
                roomDAO.create(room);
                ctx.json(room);
            }
        };
    }

    public Handler deleteRoom() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Room room = roomDAO.getById(id);
            roomDAO.delete(id);
            ctx.json(room);
        };
    }

    public Handler updateRoom() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            RoomDTO roomDTO = ctx.bodyAsClass(RoomDTO.class);
            Room room = roomDAO.getById(id);
            room.setHotelId(roomDTO.getHotelId());
            room.setNumber(roomDTO.getNumber());
            room.setPrice(roomDTO.getPrice());
            roomDAO.update(room, id);
            ctx.json(room);
        };
    }

}

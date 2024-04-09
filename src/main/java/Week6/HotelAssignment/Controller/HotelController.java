package Week6.HotelAssignment.Controller;


import Week6.HotelAssignment.DAO.HotelDAO;
import Week6.HotelAssignment.DTO.HotelDTO;
import Week6.HotelAssignment.DTO.RoomDTO;
import Week6.HotelAssignment.model.Hotel;
import io.javalin.http.Handler;

import java.util.List;

public class HotelController {
    private static HotelDAO hotelDAO;
    private static HotelController instance;

    public static HotelController getInstance(boolean isTesting){
        if(instance == null){
            instance = new HotelController();
            hotelDAO = hotelDAO.getInstance(isTesting);
        }
        return instance;
    }

    public Handler getHotels() {
        return ctx -> {
            List<Hotel> hotelList = hotelDAO.getAll();
            List<HotelDTO> hotelDTOList = hotelList.stream()
                    .map(HotelDTO::new)
                    .toList();
            ctx.json(hotelDTOList);
        };
    }

    public Handler getHotelById() {
        return ctx -> {
            int id =Integer.parseInt(ctx.pathParam("id"));
            Hotel hotel = hotelDAO.getById(id);
            HotelDTO hotelDTO = new HotelDTO(hotel.getId(), hotel.getName(), hotel.getAddress(), hotel.getRooms());
            ctx.json(hotelDTO);
        };
    }

    public Handler getHotelRoomsByHotelId() {
        return ctx -> {
            int id =Integer.parseInt(ctx.pathParam("id"));
            Hotel hotel = hotelDAO.getById(id);
            List<RoomDTO> rooms = hotel.getRooms().stream().map(x -> new RoomDTO(x.getId(),x.getHotelId(),x.getNumber(),x.getPrice())).toList();
            ctx.json(rooms);
        };
    }

    public Handler createHotel() {
        return ctx -> {
            HotelDTO hotelDTO = ctx.bodyAsClass(HotelDTO.class);
            Hotel hotel = new Hotel(hotelDTO.getName(), hotelDTO.getAddress());
            if(hotelDTO.getRooms() != null) {
                hotelDTO.getRooms().forEach(hotel::addRoom);
            }
            hotelDAO.create(hotel);
            ctx.json(hotel);
        };
    }

    public Handler updateHotel() {
        return ctx -> {
            int id =Integer.parseInt(ctx.pathParam("id"));
            HotelDTO hotelDTO = ctx.bodyAsClass(HotelDTO.class);
            Hotel hotel = hotelDAO.getById(id);
            hotel.setName(hotelDTO.getName());
            hotel.setAddress(hotelDTO.getAddress());
            hotelDAO.update(hotel, id);
            ctx.json(hotel);
        };
    }

    public Handler deleteHotel() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Hotel hotel = hotelDAO.getById(id);
            hotelDAO.delete(id);
            ctx.json(hotel);
        };
    }


}

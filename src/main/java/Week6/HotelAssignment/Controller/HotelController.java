package Week6.HotelAssignment.Controller;

import HotelAssignment.DAO.HotelDAO;
import HotelAssignment.DTO.HotelDTO;
import HotelAssignment.DTO.RoomDTO;
import HotelAssignment.model.Hotel;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;

public class HotelController {
    private static HotelDAO hotelDAO;


    public static Handler getHotels() {
        return ctx -> {
            List<Hotel> hotelList = hotelDAO.getAll();
            List<HotelDTO> hotelDTOList = hotelList.stream().map(y -> new HotelDTO(y.getId(),y.getName(),y.getAddress(),y.getRooms())).toList();
            hotelDTOList.forEach(System.out::println);
            ctx.json(hotelDTOList);
        };
    }

    public static void getHotelById(Context ctx) {
        int id =Integer.parseInt(ctx.pathParam("id"));
        Hotel hotel = hotelDAO.getById(id);
        HotelDTO hotelDTO = new HotelDTO(hotel.getId(), hotel.getName(), hotel.getAddress(), hotel.getRooms());
        ctx.json(hotelDTO);
    }

    public static void getHotelRoomsByHotelId(Context ctx) {
        int id =Integer.parseInt(ctx.pathParam("id"));
        Hotel hotel = hotelDAO.getById(id);
        List<RoomDTO> rooms = hotel.getRooms().stream().map(x -> new RoomDTO(x.getId(),x.getHotelId(),x.getNumber(),x.getPrice())).toList();
        ctx.json(rooms);
    }

    public static void createHotel(Context ctx) {
        HotelDTO hotelDTO = ctx.bodyAsClass(HotelDTO.class);
        Hotel hotel = new Hotel(hotelDTO.getName(), hotelDTO.getAddress());
        if(hotelDTO.getRooms() != null) {
            hotelDTO.getRooms().forEach(hotel::addRoom);
        }
        hotelDAO.create(hotel);
        ctx.json(hotel);
    }

    public static void updateHotel(Context ctx) {
        int id =Integer.parseInt(ctx.pathParam("id"));
        // missing
    }

    public static void deleteHotel(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Hotel hotel = hotelDAO.getById(id);
        hotelDAO.delete(id);
        ctx.json(hotel);
    }

}

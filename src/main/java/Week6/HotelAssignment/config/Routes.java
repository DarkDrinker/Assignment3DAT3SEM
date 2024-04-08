package Week6.HotelAssignment.config;

import Week6.HotelAssignment.Controller.HotelController;
import Week6.HotelAssignment.Controller.RoomController;
import io.javalin.apibuilder.EndpointGroup;
import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.path;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {

    public static EndpointGroup setRoutes() {
        return () -> {
            path("/hotels", getRoutes());
        };
    }

    private static EndpointGroup getRoutes() {
        return () -> {
            path("/rooms", () -> {
                get("/", RoomController.getRooms());
                get("/room/{id}", RoomController::getRoomById);
                post("/room", RoomController::createRoom);
                put("/room/{id}", RoomController::updateRoom);
                delete("/room/{id}", RoomController::deleteRoom);
            });
            get("/", HotelController.getHotels());
            get("/hotel/{id}", HotelController::getHotelById);
            get("/hotel/{id}/rooms", HotelController::getHotelRoomsByHotelId);
            post("/hotel", HotelController::createHotel);
            put("/hotel/{id}", HotelController::updateHotel);
            delete("/hotel/{id}", HotelController::deleteHotel);

        };
    }
}

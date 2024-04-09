package Week6.HotelAssignment.config;

import Week6.HotelAssignment.Controller.HotelController;
import Week6.HotelAssignment.Controller.RoomController;
import Week6.HotelAssignment.Controller.SecurityController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.security.RouteRole;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {
    private static SecurityController sc;
    private static RoomController rm;
    private static HotelController hm;
    private static final ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).registerModule(new JavaTimeModule());
    public static EndpointGroup getRoutes(Boolean isTesting){
        rm = RoomController.getInstance(isTesting);
        hm = HotelController.getInstance(isTesting);
        sc = SecurityController.getInstance(isTesting);
        return () -> {
            before(sc.authenticate());
            path("", () -> {
                get("/", ctx -> ctx.json(objectMapper.createObjectNode().put("Message", "Connected Successfully")), Role.ANYONE);
            });
            path("/rooms", () -> {
                get("/", rm.getRooms(), Role.MANAGER);
                get("/room/{id}", rm.getRoomById(), Role.MANAGER);
                post("/room", rm.createRoom(), Role.ADMIN);
                put("/room/{id}", rm.updateRoom(), Role.ADMIN);
                delete("/room/{id}", rm.deleteRoom(), Role.ADMIN);
            });
            path("/hotels", () -> {
                get("/", hm.getHotels(), Role.MANAGER, Role.ADMIN);
                get("/{id}", hm.getHotelById(), Role.ADMIN);
                get("/{id}/rooms", hm.getHotelRoomsByHotelId(), Role.ADMIN);
                post("/", hm.createHotel(), Role.ADMIN);
                put("/{id}", hm.updateHotel(), Role.ADMIN);
                delete("/{id}", hm.deleteHotel(), Role.ADMIN);
            });
            path("/auth", () -> {
                post("/login", sc.login(), Role.ANYONE);
                post("/register", sc.register(), Role.ANYONE);
            });
        };
    }

    enum Role implements RouteRole {
        ADMIN,
        MANAGER,
        ANYONE
    }
}

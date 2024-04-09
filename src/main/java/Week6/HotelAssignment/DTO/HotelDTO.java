package Week6.HotelAssignment.DTO;

import Week6.HotelAssignment.model.Hotel;
import Week6.HotelAssignment.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    private int id;
    private String name;
    private String address;
    private List<Room> rooms;


    public HotelDTO(Hotel hotel) {
        setId(hotel.getId());
        setName(hotel.getName());
        setAddress(hotel.getAddress());
        setRooms(hotel.getRooms());
    }
}


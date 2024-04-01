package Week6.HotelAssignment.DTO;

import HotelAssignment.model.Room;
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
}


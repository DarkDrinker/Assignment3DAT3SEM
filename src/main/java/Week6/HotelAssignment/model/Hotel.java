package Week6.HotelAssignment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Room> rooms = new ArrayList<>();

    public Hotel(String name,String address){
        this.name = name;
        this.address = address;
    }
    public Hotel(String name,String address,List<Room> rooms){
        this.name = name;
        this.address = address;
        this.rooms = rooms;
    }


    public void addRoom(Room room){
        rooms.add(room);
        room.setHotel(this);
    }

}
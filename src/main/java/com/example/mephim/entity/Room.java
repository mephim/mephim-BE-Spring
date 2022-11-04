package com.example.mephim.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomId;
    private String roomName;

    @OneToMany(mappedBy = "roomSeatId")
    @JsonManagedReference
    private List<RoomSeat> roomSeatList;

    public Room(Integer roomId) {
        this.roomId = roomId;
    }

    public Room(String roomName) {
        this.roomName = roomName;
    }
}

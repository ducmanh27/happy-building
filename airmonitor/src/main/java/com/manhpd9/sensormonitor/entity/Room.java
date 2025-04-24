package com.manhpd9.sensormonitor.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString(exclude = "building")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room")
public class Room extends  BaseEntity {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "floor_number")
    private int floorNumber;

    @Column(name = "room_number")
    private int roomNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "building_id")
    private Building building;


}

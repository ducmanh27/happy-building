package com.manhpd9.sensormonitor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "building")
@ToString(exclude = "rooms")
public class Building extends  BaseEntity {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "address")
    private String address;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @OneToMany(mappedBy = "building",
            cascade = {CascadeType.REFRESH, CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    private List<Room> rooms;

}

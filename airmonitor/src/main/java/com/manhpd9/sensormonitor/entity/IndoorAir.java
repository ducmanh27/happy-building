package com.manhpd9.sensormonitor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "indoor_air")
public class IndoorAir extends  BaseEntity {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "humidity")
    private double humidity;

    @Column(name = "co2")
    private double co2;

    @Column(name = "dust")
    private double dust;

    @Column(name = "light")
    private double light;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(cascade={CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;
}

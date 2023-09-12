package com.irrigation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "slots_of_time")
public class SlotsOfTime {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name="irrigation_duration")
    private Integer irrigationDuration;

    @Column(name = "start_time")
    private Integer startIrrigation;

    @Column(name = "end_time")
    private Integer  EndIrrigation;

    @Column(name="slot_status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plots_of_land_id")
    private PlotsOfLand plotsOfLand;


}

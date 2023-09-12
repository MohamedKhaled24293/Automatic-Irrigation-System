package com.irrigation.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plots_of_land")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlotsOfLand {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "id")
        private Long id;

        @Column(name = "plot_name",unique = true,nullable = false)
        @NotEmpty(message = "plot_name cannot be empty or null")
        private String name;

         @Column(name = "water_required", nullable = false)
         private Integer waterRequired;
         @Column(name = "type_of_agriculture_crop",nullable = false)
          private String typeOfAgricultureCrop;
         @Column(name="area",nullable = false)
         private Double area;
         @OneToMany(mappedBy = "plotsOfLand" ,cascade = CascadeType.ALL,orphanRemoval = true)
         Set<SlotsOfTime> slotsOfTimes =new HashSet<>();







}

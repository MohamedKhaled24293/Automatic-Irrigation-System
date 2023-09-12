package com.irrigation.dto;

import com.irrigation.model.SlotsOfTime;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class PlotsOfLandDto {

    private Long id;

    private String name;

    Set<SlotsOfTime> slotsOfTimes =new HashSet<>();

    private Integer waterRequired;

    private String typeOfAgricultureCrop;

    private Double area;

}

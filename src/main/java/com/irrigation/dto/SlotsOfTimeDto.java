package com.irrigation.dto;

import lombok.Data;

@Data
public class SlotsOfTimeDto {

    private Long id;

    private Integer irrigationDuration;

    private Integer startIrrigation;

    private Integer  EndIrrigation;

    private String status;

    private PlotsOfLandDto plotsOfLandDto;
}

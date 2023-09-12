package com.irrigation.service;

import com.irrigation.dto.PlotsOfLandDto;
import com.irrigation.dto.SlotsOfTimeDto;
import com.irrigation.model.PlotsOfLand;

import java.util.List;

public interface PlotsOfLandService {
    public List<PlotsOfLandDto> PlotsOfLandList();


    public PlotsOfLandDto updatePlotOfLandById(PlotsOfLandDto plotsOfLandDto, Long plotOfLandId);


    public PlotsOfLandDto addPlotLand(PlotsOfLandDto plotsOfLandDto);

    public String configPlotOfLand(Long plotOfLandId, Long slotOfTimeId);
}

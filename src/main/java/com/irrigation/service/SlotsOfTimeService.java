package com.irrigation.service;

import com.irrigation.dto.SlotsOfTimeDto;

import java.util.List;

public interface SlotsOfTimeService {

    public List<SlotsOfTimeDto> slotsOfTimeList();


    public SlotsOfTimeDto updateSlotsOfTimeById(SlotsOfTimeDto slotsOfTimeDto, Long slotOfTimeId);


    public SlotsOfTimeDto addSlotsOfTime(SlotsOfTimeDto SlotsOfTimeDto);
}

package com.irrigation.service.impl;

import com.irrigation.dto.PlotsOfLandDto;
import com.irrigation.dto.SlotsOfTimeDto;
import com.irrigation.exception.ResourceNotFoundException;
import com.irrigation.model.PlotsOfLand;
import com.irrigation.model.SlotsOfTime;
import com.irrigation.repository.PlotsOfLandRepo;
import com.irrigation.repository.SlotsOfTimeRepo;
import com.irrigation.service.SlotsOfTimeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SlotsOfTimeServiceImpl implements SlotsOfTimeService {

    private SlotsOfTimeRepo slotsOfTimeRepo;
    private PlotsOfLandRepo plotsOfLandRepo;
    private ModelMapper modelMapper;

    public SlotsOfTimeServiceImpl(SlotsOfTimeRepo slotsOfTimeRepo,ModelMapper modelMapper,PlotsOfLandRepo plotsOfLandRepo) {
        this.slotsOfTimeRepo = slotsOfTimeRepo;
        this.modelMapper = modelMapper;
        this.plotsOfLandRepo=plotsOfLandRepo;
    }

    @Override
    public List<SlotsOfTimeDto> slotsOfTimeList() {
        log.info("Listing PlotOfLands ..");
        List <SlotsOfTime> SlotsOfTimeList =slotsOfTimeRepo.findAll();
        return SlotsOfTimeList.stream().map(slots -> mapToDto(slots)).collect(Collectors.toList());

    }

    @Override
    public SlotsOfTimeDto updateSlotsOfTimeById(SlotsOfTimeDto slotsOfTimeDto, Long slotOfTimeId) {
        log.info("Updating a PlotOfLand Id : {}", slotOfTimeId);
        SlotsOfTime slotsOfTime = slotsOfTimeRepo.findById(slotOfTimeId).orElseThrow(() -> new ResourceNotFoundException("slotOfTime", "id", slotOfTimeId));
        slotsOfTime.setStartIrrigation(slotsOfTimeDto.getStartIrrigation());
        slotsOfTime.setEndIrrigation(slotsOfTimeDto.getEndIrrigation());
        slotsOfTime.setStatus(slotsOfTimeDto.getStatus());
        slotsOfTime.setIrrigationDuration(slotsOfTimeDto.getIrrigationDuration());
         Optional<PlotsOfLand> plotsOfLandId=plotsOfLandRepo.findById(slotsOfTimeDto.getPlotsOfLandDto().getId());
        slotsOfTime.setPlotsOfLand(plotsOfLandId.get());

        SlotsOfTime slotsOfTimeUpdated = slotsOfTimeRepo.save(slotsOfTime);

        return mapToDto(slotsOfTimeUpdated);
    }

    @Override
    public SlotsOfTimeDto addSlotsOfTime(SlotsOfTimeDto slotsOfTimeDto) {
        log.info("Saving new PlotOfLand : {}",slotsOfTimeDto.getId());

        SlotsOfTime slotsOfTime=mapToEntity(slotsOfTimeDto);
        return mapToDto(slotsOfTimeRepo.save(slotsOfTime));
    }
    private SlotsOfTimeDto mapToDto(SlotsOfTime slotsOfTime)
    {
        log.info("Mapping SlotsOfTime Entity to  SlotsOfTime Dto ");

        SlotsOfTimeDto SlotsOfTimeDtoResponse=modelMapper.map(slotsOfTime,SlotsOfTimeDto.class);

        return SlotsOfTimeDtoResponse;
    }
    private SlotsOfTime mapToEntity(SlotsOfTimeDto slotsOfTimeDto)
    {
        log.info("Mapping SlotsOfTime Dto to SlotsOfTime Entity ");

        SlotsOfTime SlotsOfTimeResponse=modelMapper.map(slotsOfTimeDto,SlotsOfTime.class);
        return SlotsOfTimeResponse;
    }
}

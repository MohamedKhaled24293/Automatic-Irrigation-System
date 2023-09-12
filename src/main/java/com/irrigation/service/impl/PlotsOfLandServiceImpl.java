package com.irrigation.service.impl;

import com.irrigation.dto.PlotsOfLandDto;
import com.irrigation.dto.SlotsOfTimeDto;
import com.irrigation.exception.ResourceNotFoundException;
import com.irrigation.model.PlotsOfLand;
import com.irrigation.model.SlotsOfTime;
import com.irrigation.repository.PlotsOfLandRepo;
import com.irrigation.repository.SlotsOfTimeRepo;
import com.irrigation.service.PlotsOfLandService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PlotsOfLandServiceImpl implements PlotsOfLandService {


  private PlotsOfLandRepo plotsOfLandRepo;

    private SlotsOfTimeRepo slotsOfTimeRepo;


    private ModelMapper modelMapper;

        public PlotsOfLandServiceImpl(PlotsOfLandRepo plotsOfLandRepo, ModelMapper modelMapper,SlotsOfTimeRepo slotsOfTimeRepo){
            this.plotsOfLandRepo = plotsOfLandRepo;
        this.modelMapper = modelMapper;
            this.slotsOfTimeRepo = slotsOfTimeRepo;
        }



    @Override
    public PlotsOfLandDto addPlotLand(PlotsOfLandDto plotsOfLandDto) {
        log.info("Saving new PlotOfLand : {}",plotsOfLandDto.getName());

        PlotsOfLand plotsOfLand=mapToEntity(plotsOfLandDto);
        return mapToDto(plotsOfLandRepo.save(plotsOfLand));
    }

    @Override
    public String configPlotOfLand(Long plotOfLandId, Long slotOfTimeId) {

        List<SlotsOfTime> slotsOfTimeList=slotsOfTimeRepo.findByPlotsOfLandId(plotOfLandId);
         Long[]listOfIds=new Long[slotsOfTimeList.size()];
         Long currentSlotOfTimeId=0l;
         String slotOfTimeStatus="";
        log.info("slotsOfTimeList.size() : {}",slotsOfTimeList.size());

        for(int i=0;i<slotsOfTimeList.size();i++) {
            log.info("slotsOfTimeList.get(i).getStartIrrigation()  : {}",slotsOfTimeList.get(i).getStartIrrigation() );

            listOfIds[i]=slotsOfTimeList.get(i).getId();

            if(slotOfTimeId >= slotsOfTimeList.get(i).getStartIrrigation() && slotOfTimeId <= slotsOfTimeList.get(i).getEndIrrigation() ) {
                currentSlotOfTimeId = listOfIds[i];

            }
            log.info("currentSlotOfTimeId2 : {}",currentSlotOfTimeId);

        }
        if(currentSlotOfTimeId >0){
            SlotsOfTime slotsOfTime =slotsOfTimeRepo.findById(currentSlotOfTimeId).orElseThrow(()->new ResourceNotFoundException("currentSlotTime","id",1));
            if(slotsOfTime.getStatus().equals("UP"))
            {
                slotOfTimeStatus="already irrigated Before you can check after : "+slotsOfTime.getEndIrrigation();
            }
            else {
                slotsOfTime.setStatus("UP");
                slotsOfTimeRepo.save(slotsOfTime);
                slotOfTimeStatus = "Irrigated";
            }

        }
        else {

            slotOfTimeStatus="can't be Irrigated";

        }


        return slotOfTimeStatus;
    }

    @Override
        public List<PlotsOfLandDto> PlotsOfLandList() {
            log.info("Listing PlotOfLands ..");
        List <PlotsOfLand> plotsOfLandList =plotsOfLandRepo.findAll();
        return plotsOfLandList.stream().map(plots -> mapToDto(plots)).collect(Collectors.toList());
        }

        @Override
        public PlotsOfLandDto updatePlotOfLandById(PlotsOfLandDto plotsOfLandDto, Long plotsOfLandId) {
            log.info("Updating a PlotOfLand Id : {}",plotsOfLandId);
            PlotsOfLand plotsOfLand=plotsOfLandRepo.findById(plotsOfLandId).orElseThrow(()->new ResourceNotFoundException("plotLand","id",plotsOfLandId));
            plotsOfLand.setName(plotsOfLandDto.getName());
            plotsOfLand.setArea(plotsOfLandDto.getArea());
            plotsOfLand.setTypeOfAgricultureCrop(plotsOfLandDto.getTypeOfAgricultureCrop());
            PlotsOfLand PlotsOfLandUpdated=plotsOfLandRepo.save(plotsOfLand);

            return mapToDto(PlotsOfLandUpdated);
        }


    private PlotsOfLandDto mapToDto(PlotsOfLand plotsOfLand)
    {
        PlotsOfLandDto PlotsOfLandDtoResponse=modelMapper.map(plotsOfLand,PlotsOfLandDto.class);

        return PlotsOfLandDtoResponse;
    }
    private PlotsOfLand mapToEntity(PlotsOfLandDto plotsOfLandDto)
    {
        PlotsOfLand PlotsOfLandResponse=modelMapper.map(plotsOfLandDto,PlotsOfLand.class);
        return PlotsOfLandResponse;
    }



    }



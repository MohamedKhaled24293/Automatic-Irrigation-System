package com.irrigation.controller;
import com.irrigation.dto.PlotsOfLandDto;
import com.irrigation.dto.Response;
import com.irrigation.model.PlotsOfLand;
import com.irrigation.service.impl.PlotsOfLandServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/irrigation")
@RequiredArgsConstructor
public class PlotsOfLandController {

    @Autowired
    PlotsOfLandServiceImpl plotsOfLandServiceImpl;


    //The URI indicates the API path to save a PlotsOfLand
    @PostMapping("/save")
    public ResponseEntity<Response> save(@RequestBody PlotsOfLandDto plotsOfLandDto) throws IOException {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("plotsOfLand",plotsOfLandServiceImpl.addPlotLand(plotsOfLandDto)))
                        .message("plotsOfLand Created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }
    //The URI indicates the API path to update a PlotsOfLand
    @PutMapping("/update/{id}")
    public ResponseEntity<Response> update(@RequestBody PlotsOfLandDto plotsOfLandDto, @PathVariable(name = "id") Long id) throws IOException {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("plotsOfLand",plotsOfLandServiceImpl.updatePlotOfLandById(plotsOfLandDto,id)))
                        .message("plotsOfLand Updated")
                        .status(HttpStatus.ACCEPTED)
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .build()
        );
    }

    //The URI indicates the API path to list a PlotsOfLands
    @GetMapping("/plotLandList")
    public ResponseEntity<Response> List() throws IOException {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("plotsOfLand",plotsOfLandServiceImpl.PlotsOfLandList()))
                        .message("plotsOfLand Created")
                        .status(HttpStatus.ACCEPTED)
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .build()
        );
    }
    //The URI indicates the API path to config a PlotsOfLand
    @PutMapping("/configure/{plotOfLandId}/{slotOfTimeId}")
    public ResponseEntity<Response> configPlotOfLand(@PathVariable(name ="plotOfLandId") Long plotOfLandId , @PathVariable(name = "slotOfTimeId")  Long slotOfTimeId){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("plotsOfLand",plotsOfLandServiceImpl.configPlotOfLand(plotOfLandId,slotOfTimeId)))
                        .message("plotsOfLand Configuration")
                        .status(HttpStatus.ACCEPTED)
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .build()
        );
    }

}

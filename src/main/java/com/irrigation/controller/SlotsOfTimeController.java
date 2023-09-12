package com.irrigation.controller;

import com.irrigation.dto.PlotsOfLandDto;
import com.irrigation.dto.Response;
import com.irrigation.dto.SlotsOfTimeDto;
import com.irrigation.service.impl.PlotsOfLandServiceImpl;
import com.irrigation.service.impl.SlotsOfTimeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

import static java.time.LocalDateTime.now;
@RestController
@RequestMapping("/api/irrigation/slots")
@RequiredArgsConstructor
public class SlotsOfTimeController {
        @Autowired
        SlotsOfTimeServiceImpl slotsOfTimeServiceImpl;

        @PostMapping("/save")
        public ResponseEntity<Response> save(@RequestBody SlotsOfTimeDto slotsOfTimeDto) throws IOException {

            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("slotsOfTime",slotsOfTimeServiceImpl.addSlotsOfTime(slotsOfTimeDto)))
                            .message("slotsOfTime Created")
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .build()
            );
        }
        @PutMapping("/update/{id}")
        public ResponseEntity<Response> update(@RequestBody SlotsOfTimeDto slotsOfTimeDto, @PathVariable(name = "id") Long id) throws IOException {

            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("plotsOfLand",slotsOfTimeServiceImpl.updateSlotsOfTimeById(slotsOfTimeDto,id)))
                            .message("plotsOfLand Updated")
                            .status(HttpStatus.ACCEPTED)
                            .statusCode(HttpStatus.ACCEPTED.value())
                            .build()
            );
        }

        @GetMapping("/plotLandList")
        public ResponseEntity<Response> List() throws IOException {

            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("plotsOfLand",slotsOfTimeServiceImpl.slotsOfTimeList()))
                            .message("plotsOfLand Created")
                            .status(HttpStatus.ACCEPTED)
                            .statusCode(HttpStatus.ACCEPTED.value())
                            .build()
            );
        }
}

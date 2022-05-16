package ro.tuc.ds2020.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.SensorRecordDTO;
import ro.tuc.ds2020.entities.SensorRecord;
import ro.tuc.ds2020.services.SensorRecordService;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/sensorRecord")
public class SensorRecordController {

    private final SensorRecordService sensorRecordService;

    public SensorRecordController(SensorRecordService sensorRecordService) {
        this.sensorRecordService = sensorRecordService;
    }

    @PostMapping(value = "/{sensorRecordId}")
    public ResponseEntity<?> insertSensorRecord(@PathVariable UUID sensorRecordId,
                                                @RequestBody SensorRecordDTO sensorRecordDTO) {
        SensorRecordDTO sensorRecordIns;
        try {
            sensorRecordIns = sensorRecordService.insertSensorRecord(sensorRecordDTO, sensorRecordId);
            return new ResponseEntity<>(sensorRecordIns, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping()
    public ResponseEntity<List<SensorRecordDTO>> getSensorRecords() {
        List<SensorRecordDTO> dtos = sensorRecordService.findSensorRecordList();

        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }
}

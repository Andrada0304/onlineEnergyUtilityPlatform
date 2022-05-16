package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.services.SensorService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/sensor")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping(value="/{deviceId}")
    public ResponseEntity<?> insertSensor(@Valid @PathVariable UUID deviceId,
                                          @Valid @RequestBody SensorDTO sensorDTO){
        //AccountDTO accountDTO = new AccountDTO(username,password,role);
        SensorDTO sensorDTO2 = sensorService.insertSensor(sensorDTO,deviceId);
        try{
            // System.out.println(clientDTO2.getId()+" "+clientDTO2.getName());
            return new ResponseEntity<>(sensorDTO2, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
 /*   @PostMapping()
    public ResponseEntity<?> insertSensor(@Valid @RequestBody SensorDTO sensorDTO){
        //AccountDTO accountDTO = new AccountDTO(username,password,role);
        SensorDTO sensorDTO2 = sensorService.insertSensor(sensorDTO);
        try{
           // System.out.println(clientDTO2.getId()+" "+clientDTO2.getName());
            return new ResponseEntity<>(sensorDTO2, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
*/
    @GetMapping()
    public ResponseEntity<List<SensorDTO>> getSensors() {
        List<SensorDTO> dtos = sensorService.findSensors();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{sensorId}")
    public ResponseEntity<?> findSensorById(@PathVariable("sensorId") UUID sensorId)
    {
        try
        {
            SensorDTO sensorDTO = sensorService.findSensorById(sensorId);
            return new ResponseEntity<>(sensorDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{sensorId}")
    public ResponseEntity<?> deleteSensor(@PathVariable("sensorId") UUID sensorId)
    {
        try {
            sensorService.deleteSensor(sensorId);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{sensorId}")
    public ResponseEntity<?> updateSensor(@Valid @PathVariable("sensorId") UUID sensorId,
                                          @Valid @RequestBody SensorDTO sensorDTO)
    {
        try {
            SensorDTO sensorDTO2 = sensorService.updateSensor(sensorId, sensorDTO);
            return new ResponseEntity<>(sensorDTO2, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}

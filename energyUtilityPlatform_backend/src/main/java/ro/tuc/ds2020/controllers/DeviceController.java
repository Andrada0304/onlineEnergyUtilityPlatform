package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.services.ClientService;
import ro.tuc.ds2020.services.DeviceService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/device")
public class DeviceController {
    private final DeviceService deviceService;
    private final ClientService clientService;

    @Autowired
    public DeviceController(DeviceService deviceService, ClientService clientService) {
        this.deviceService = deviceService;
        this.clientService = clientService;
    }
  /*  @PostMapping()
    public ResponseEntity<?> insertDevice(@Valid @RequestBody DeviceDTO deviceDTO){
        //System.out.println(clientDTO.getName());
        //SimpleClientDTO simpleClientDTO = new SimpleClientDTO(name,birthDate,address);
        //AccountDTO accountDTO = new AccountDTO(username,password,role);
     //   ClientDTO clientDTO = deviceDTO.getClientDTO();
        System.out.println("iiidddd:"+deviceDTO.getClientDTO().getId());
        DeviceDTO deviceDTO2 = deviceService.insertDevice(deviceDTO);

        try{
            //System.out.println(deviceDTO2.getId()+" "+deviceDTO2.getName());
            return new ResponseEntity<>(deviceDTO2, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }*/

    @PostMapping(value="/{clientId}")
    public ResponseEntity<?> insertDevice(@Valid @PathVariable UUID clientId,
                                          @Valid @RequestBody DeviceDTO deviceDTO)
    {
        DeviceDTO deviceIns;
        try {
            deviceIns = deviceService.insertDevice(deviceDTO, clientId);
            return new ResponseEntity<>(deviceIns, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping()
    public ResponseEntity<?> insertDevice(
                                          @RequestBody DeviceDTO deviceDTO)
    {
        DeviceDTO deviceIns;
        try {
            deviceIns = deviceService.insertDevice(deviceDTO, null);
            return new ResponseEntity<>(deviceIns, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping()
    public ResponseEntity<List<DeviceDTO>> getDevices() {
        List<DeviceDTO> dtos = deviceService.findDevices();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @GetMapping(value = "/{deviceId}")
    public ResponseEntity<?> findDeviceById(@PathVariable("deviceId") UUID deviceId)
    {
        try
        {
            DeviceDTO deviceDTO = deviceService.findDeviceById(deviceId);
            return new ResponseEntity<>(deviceDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{deviceId}")
    public ResponseEntity<?> deleteDevice(@PathVariable("deviceId") UUID deviceId)
    {
        try {
            deviceService.deleteDevice(deviceId);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{deviceId}")
    public ResponseEntity<?> updateDevice(@Valid @PathVariable("deviceId") UUID deviceId,
                                          @Valid @RequestBody DeviceDTO deviceDTO)
    {
        try {
            DeviceDTO deviceDTO2 = deviceService.updateDevice(deviceId, deviceDTO);
            return new ResponseEntity<>(deviceDTO2, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




}

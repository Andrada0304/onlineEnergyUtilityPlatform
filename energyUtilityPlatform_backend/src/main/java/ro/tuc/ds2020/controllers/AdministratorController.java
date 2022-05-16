package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.entities.Roles;
import ro.tuc.ds2020.services.AdministratorService;
import ro.tuc.ds2020.services.ClientService;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/administrator")
public class AdministratorController {
    private final AdministratorService administratorService;


    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }
    @PostMapping()
    public ResponseEntity<?> insertAdministrator(@Valid @RequestBody AdministratorDTO administratorDTO){
        //System.out.println(clientDTO.getName());
        //SimpleClientDTO simpleClientDTO = new SimpleClientDTO(name,birthDate,address);
        //AccountDTO accountDTO = new AccountDTO(username,password,role);
        AdministratorDTO administratorDTO2 = administratorService.insert(administratorDTO);
        try{
            //System.out.println(clientDTO2.getId()+" "+clientDTO2.getName());
            return new ResponseEntity<>(administratorDTO2, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping()
    public ResponseEntity<List<AdministratorDTO>> getAdministrators() {
        List<AdministratorDTO> dtos = administratorService.findAdministrators();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @GetMapping(value = "/{administratorId}")
    public ResponseEntity<?> findAdministratorById(@PathVariable("administratorId") UUID administratorId)
    {
        try
        {
            AdministratorDTO administratorDTO =  administratorService.findAdministratorById(administratorId);
            return new ResponseEntity<>(administratorDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}

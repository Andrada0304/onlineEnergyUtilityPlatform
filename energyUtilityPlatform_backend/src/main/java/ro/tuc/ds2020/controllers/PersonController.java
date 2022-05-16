package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.services.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public ResponseEntity<List<PersonDetailsDTO>> getPersons() {
        List<PersonDetailsDTO> dtos = personService.findPersons();
        for (PersonDetailsDTO dto : dtos) {
            Link personLink = linkTo(methodOn(PersonController.class)
                    .getPerson(dto.getId())).withRel("personDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody PersonDetailsDTO personDTO) {
        UUID personID = personService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDetailsDTO> getPerson(@PathVariable("id") UUID personId) {
        PersonDetailsDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updatePerson(@Valid @PathVariable("id") UUID clientId,
                                          @Valid @RequestBody PersonDetailsDTO clientDTO)
    {
        try {
            PersonDetailsDTO personDTO2 = personService.update(clientId, clientDTO);
            return new ResponseEntity<>(personDTO2, HttpStatus.OK);
        }catch (Exception e)
        {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
          //  return new ResponseEntity<PersonDetailsDTO>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{personId}")
    public ResponseEntity<?> deleteClient(@PathVariable("personId") UUID clientId)
    {
        try {
            personService.delete(clientId);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    //TODO: UPDATE, DELETE per resource

}

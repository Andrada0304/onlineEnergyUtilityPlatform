package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.entities.Roles;
import ro.tuc.ds2020.services.ClientService;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/client")
public class ClientController {
    private final ClientService clientService;


    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping()
    public ResponseEntity<?> insertClient(@Valid @RequestBody ClientDTO clientDTO){
       //AccountDTO accountDTO = new AccountDTO(username,password,role);
        ClientDTO clientDTO2 = clientService.insert(clientDTO);
        try{
            System.out.println(clientDTO2.getId()+" "+clientDTO2.getName());
            return new ResponseEntity<>(clientDTO2, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping()
    public ResponseEntity<List<ClientDTO>> getClients() {
        List<ClientDTO> dtos = clientService.findClients();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @GetMapping(value = "/{clientId}")
    public ResponseEntity<?> findClientById(@PathVariable("clientId") UUID clientId)
    {
        try
        {
            ClientDTO clientDTO = clientService.findClientById(clientId);
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(value = "/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable("clientId") UUID clientId)
    {
        try {
            clientService.delete(clientId);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{clientId}")
    public ResponseEntity<?> updateClient(@Valid @PathVariable("clientId") UUID clientId,
                                          @Valid @RequestBody ClientDTO clientDTO)
    {
        try {
            ClientDTO clientDTO2 = clientService.update(clientId, clientDTO);
            return new ResponseEntity<>(clientDTO2, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/username/{username}")
    public ResponseEntity<?> findClientByUsername(@PathVariable("username")String username)
    {
        try
        {
            ClientDTO clientDTO = clientService.findClientByUsername(username);
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}

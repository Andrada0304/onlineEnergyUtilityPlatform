package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.services.AccountService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/account")
public class AccountController {
    private final AccountService accountService;


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping()
    public ResponseEntity<?> insert(@Valid @RequestBody AccountDTO accountDTO) {
        System.out.println(accountDTO.getUsername());
        AccountDTO accountDTO2 = accountService.insert(accountDTO);
        try{
            return new ResponseEntity<>(accountDTO2, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping()
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        List<AccountDTO> dtos = accountService.findAccounts();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


}

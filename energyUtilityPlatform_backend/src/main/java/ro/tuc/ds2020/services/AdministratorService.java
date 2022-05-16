package ro.tuc.ds2020.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.dtos.builders.AdministratorBuilder;
import ro.tuc.ds2020.dtos.builders.ClientBuilder;
import ro.tuc.ds2020.entities.*;
import ro.tuc.ds2020.repositories.AccountRepository;
import ro.tuc.ds2020.repositories.AdministratorRepository;
import ro.tuc.ds2020.repositories.ClientRepository;
import ro.tuc.ds2020.repositories.DeviceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdministratorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final AdministratorRepository administratorRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository, AccountRepository accountRepository, DeviceRepository deviceRepository) {
        this.administratorRepository = administratorRepository;
        this.accountRepository = accountRepository;
    }

    public List<AdministratorDTO> findAdministrators()
    {
        List<Administrator> Administrators = administratorRepository.findAll();
        return Administrators.stream()
                .map(x -> AdministratorBuilder.toAdministratorDTO(x))
                .collect(Collectors.toList());
    }
    public AdministratorDTO findAdministratorById (UUID administratorId)
    {
        Optional<Administrator> administrator = administratorRepository.findById(administratorId);
        if(!administrator.isPresent())
        {
            LOGGER.error("Person with id {} was not found in db", administratorId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + administratorId);
        }
        return AdministratorBuilder.toAdministratorDTO(administrator.get());
    }
    public AdministratorDTO insert(AdministratorDTO administratorDTO) {
        Account account = new Account(administratorDTO.getUsername(),administratorDTO.getPassword(),administratorDTO.getUserRole());
        account = accountRepository.save(account);

        Administrator administrator = new Administrator(administratorDTO.getName(), administratorDTO.getBirthDate(),administratorDTO.getAddress(),administratorDTO.getUsername(),administratorDTO.getPassword());
        Administrator insertedAdministrator = administratorRepository.save(administrator);

        return AdministratorBuilder.toAdministratorDTO(insertedAdministrator);

    }

    public AdministratorDTO update (UUID administratorId, AdministratorDTO administratorDTO) throws Exception {
        Administrator administrator = administratorRepository.findById(administratorId).orElse(null);

        if(administrator == null)
        {
            LOGGER.error("Person with id {} was not found in db", administratorId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + administratorId);
        }

        Administrator administratorToUpdate = new Administrator(administrator.getId(), administratorDTO.getName(),
                administrator.getBirthDate(), administratorDTO.getAddress(),
                administratorDTO.getUsername(),administratorDTO.getPassword());

        return AdministratorBuilder.toAdministratorDTO(administratorRepository.save(administratorToUpdate));
    }



}

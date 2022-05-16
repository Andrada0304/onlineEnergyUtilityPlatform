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
import ro.tuc.ds2020.repositories.ClientRepository;
import ro.tuc.ds2020.repositories.DeviceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, AccountRepository accountRepository, DeviceRepository deviceRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.deviceRepository = deviceRepository;
    }

    public List<ClientDTO> findClients()
    {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(x -> ClientBuilder.toClientDTO(x))
                .collect(Collectors.toList());
    }
    public ClientDTO findClientById (UUID clientId)
    {
        Optional<Client> client = clientRepository.findById(clientId);
        if(!client.isPresent())
        {
            LOGGER.error("Person with id {} was not found in db", clientId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + clientId);
        }
        return ClientBuilder.toClientDTO(client.get());
    }
    public ClientDTO insert(ClientDTO clientDTO) {
        Account account = new Account(clientDTO.getUsername(),clientDTO.getPassword(),clientDTO.getUserRole());
        account = accountRepository.save(account);

        Client client = new Client(clientDTO.getName(), clientDTO.getBirthDate(),clientDTO.getAddress(),clientDTO.getUsername(),clientDTO.getPassword(),null);
        Client insertedClient = clientRepository.save(client);

        return ClientBuilder.toClientDTO(insertedClient);

    }

    public ClientDTO update (UUID clientId, ClientDTO clientDTO) throws Exception {
        Client client = clientRepository.findById(clientId).orElse(null);

        if(client == null)
        {
            LOGGER.error("Person with id {} was not found in db", clientId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + clientId);
        }

        Client clientToUpdate = new Client(client.getId(), clientDTO.getName(),
                client.getBirthDate(), clientDTO.getAddress(),
                clientDTO.getUsername(),clientDTO.getPassword(),client.getDevices());

        return ClientBuilder.toClientDTO2(clientRepository.save(clientToUpdate));
    }
    public void delete (UUID clientId)
    {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            LOGGER.error("Person with id {} was not found in db", clientId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + clientId);
        }

        List<Device> devices = client.getDevices();
        if(devices != null) {
            for (Device device: devices) {
                deviceRepository.delete(device);
            }
        }
        clientRepository.deleteById(clientId);
        accountRepository.deleteById(client.getUsername());
    }

    public ClientDTO findClientByUsername (String username)
    {
        Optional<Account> account = accountRepository.findById(username);

        List<ClientDTO> allClients = this.findClients();

        for(ClientDTO clientDTO: allClients)
        {
            if(clientDTO.getUsername() == username)
            {
                return clientDTO;
            }
        }
        return null;
    }


}

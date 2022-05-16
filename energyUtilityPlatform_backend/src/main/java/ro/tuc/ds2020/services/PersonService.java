package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDetailsDTO> findPersons() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(PersonBuilder::toPersonDetailsDTO)
                .collect(Collectors.toList());
    }

    public PersonDetailsDTO findPersonById(UUID id) {
        Optional<Person> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return PersonBuilder.toPersonDetailsDTO(prosumerOptional.get());
    }

    public UUID insert(PersonDetailsDTO personDTO) {
        Person person = PersonBuilder.toEntity(personDTO);
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
        return person.getId();
    }


    public PersonDetailsDTO update (UUID personId, PersonDetailsDTO personDTO) throws Exception {
        Person person = personRepository.findById(personId).orElse(null);

        if(person == null)
        {
            LOGGER.error("Person with id {} was not found in db", personId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + personId);
        }

        Person personToUpdate = new Person(person.getId(), personDTO.getName(),
                personDTO.getAddress(),personDTO.getAge());

        return PersonBuilder.toPersonDetailsDTO(personRepository.save(personToUpdate));
    }

    public void delete (UUID clientId)
    {
        Person person = personRepository.findById(clientId).orElse(null);
        if (person == null) {
            LOGGER.error("Person with id {} was not found in db", clientId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + clientId);
        }

        personRepository.deleteById(clientId);

    }


}

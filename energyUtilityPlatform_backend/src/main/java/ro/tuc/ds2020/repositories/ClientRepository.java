package ro.tuc.ds2020.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Administrator;
import ro.tuc.ds2020.entities.Client;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Administrator findByName(String name);
    Boolean existsByName(String name);
}

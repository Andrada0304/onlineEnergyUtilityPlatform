package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Administrator;

import java.util.Optional;
import java.util.UUID;


public interface AdministratorRepository extends JpaRepository<Administrator, UUID> {

    Administrator findByName(String name);
    Boolean existsByName(String name);

    Optional<Administrator> findById(UUID administratorId);
}

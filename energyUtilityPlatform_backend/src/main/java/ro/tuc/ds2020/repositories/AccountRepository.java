package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Account;


public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsername(String username);
    Boolean existsByUsername(String username);

}

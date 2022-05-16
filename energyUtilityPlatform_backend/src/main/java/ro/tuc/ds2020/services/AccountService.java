package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.AccountDTO;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.builders.AccountBuilder;
import ro.tuc.ds2020.dtos.builders.ClientBuilder;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public List<AccountDTO> findAccounts() {
        List<Account> accountList = accountRepository.findAll();
        return accountList.stream()
                .map(AccountBuilder::toAccountDTO)
                .collect(Collectors.toList());
    }
    public AccountDTO insert(AccountDTO accountDTO) {
        Account account = new Account(accountDTO.getUsername(),accountDTO.getPassword(),accountDTO.getRole());
        Account account2 = accountRepository.save(account);

        return AccountBuilder.toAccountDTO(account2);

    }
}

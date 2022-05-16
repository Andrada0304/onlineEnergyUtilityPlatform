package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.AccountDTO;
import ro.tuc.ds2020.entities.Account;
public class AccountBuilder {

    private AccountBuilder() {
    }

    public static AccountDTO toAccountDTO (Account account)
    {
        return new AccountDTO(
                account.getUsername(),
                account.getPassword(),
                account.getRole());
    }

    public static Account doDTOToEntity (AccountDTO accountDTO)
    {
        return new Account(
                accountDTO.getUsername(),
                accountDTO.getPassword(),
                //encodePassword(accountDTO.getPassword()),
                accountDTO.getRole());
    }

   /* public static String encodePassword(String password)
    {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }*/
}

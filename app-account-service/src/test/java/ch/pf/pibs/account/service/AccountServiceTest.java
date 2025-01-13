package ch.pf.pibs.account.service;


import ch.pf.pibs.account.configuration.PostgresTestConfiguration;
import ch.pf.pibs.account.exception.AccountNotFoundException;
import ch.pf.pibs.account.jooq.tables.pojos.AccountDto;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Import({PostgresTestConfiguration.class})
public class AccountServiceTest {

    @Autowired
    private DSLContext dslContext;

    @Autowired
    private AccountService accountService;

    @Test
    @Transactional
    public void testCreateAccount() {
        AccountDto expectedAccountDto = new AccountDto(1L, "foo", "bar");
        AccountDto createdAccountDto = accountService.createAccount(new AccountDto(null, "foo", "bar"));
        Assertions.assertEquals(expectedAccountDto, createdAccountDto);
    }

    @Test
    @Transactional
    public void testFindAccount() throws AccountNotFoundException {
        AccountDto createdAccountDto = accountService.createAccount(new AccountDto(null, "foo", "bar"));
        AccountDto foundAccountDto = accountService.findAccount(createdAccountDto.getId());
        Assertions.assertEquals(createdAccountDto, foundAccountDto);
    }

    @Test
    @Transactional
    public void testFindAllAccounts() {

        AccountDto createdAccountDto1 = accountService.createAccount(new AccountDto(null, "foo", "bar"));
        AccountDto createdAccountDto2 = accountService.createAccount(new AccountDto(null, "bar", "foo"));

        List<AccountDto> allAccounts = accountService.findAllAccounts();
        Assertions.assertTrue(allAccounts.contains(createdAccountDto1));
        Assertions.assertTrue(allAccounts.contains(createdAccountDto2));
        Assertions.assertEquals(allAccounts.size(), 2);
    }

    @Test
    public void testUpdateAccount() throws AccountNotFoundException {
        AccountDto createdAccountDto = accountService.createAccount(new AccountDto(null, "foo", "bar"));
        createdAccountDto.setAccountOwner("baz");
        createdAccountDto.setAccountType("baz");
        AccountDto updatedAccount = accountService.updateAccount(createdAccountDto);
        Assertions.assertEquals(new AccountDto(1L, "baz", "baz"), updatedAccount);
    }

}
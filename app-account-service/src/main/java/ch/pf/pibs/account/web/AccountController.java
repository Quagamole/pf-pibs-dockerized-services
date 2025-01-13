package ch.pf.pibs.account.web;

import ch.pf.pibs.account.exception.AccountNotFoundException;
import ch.pf.pibs.account.jooq.tables.pojos.AccountDto;
import ch.pf.pibs.account.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService peopleService) {
        this.accountService = peopleService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto registerAccount(@RequestBody AccountDto account) throws IllegalArgumentException {
        return accountService.createAccount(account);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AccountDto findAccountRecord(@PathVariable Long id) throws AccountNotFoundException {
        return accountService.findAccount(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> findAllAccountRecords() {
        return accountService.findAllAccounts();
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AccountDto updateAccountRecord(@PathVariable @Positive Long id, @RequestBody @Valid AccountDto account) throws IllegalArgumentException, AccountNotFoundException {
        if (!id.equals(account.getId())) {
            throw new IllegalArgumentException("id and account.id not matching");
        }
        return accountService.updateAccount(account);
    }

}

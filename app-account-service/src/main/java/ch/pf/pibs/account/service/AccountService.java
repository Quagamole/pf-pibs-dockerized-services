package ch.pf.pibs.account.service;

import ch.pf.pibs.account.exception.AccountNotFoundException;
import ch.pf.pibs.account.jooq.tables.pojos.AccountDto;
import ch.pf.pibs.account.jooq.tables.records.AccountRecord;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    private final DSLContext dslContext;

    public AccountService(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Transactional
    public AccountDto createAccount(AccountDto account) throws IllegalArgumentException {
        if (account.getId() != null) {
            throw new IllegalArgumentException("identifier=id must be null");
        }
        // TODO: Create and insert Account
        AccountRecord newAccountRecord = null;

        // TODO: get AccountDto for created Account from db
        AccountDto createdAccount = null;
        log.info("successfully registered person with id={}", createdAccount.getId());
        return createdAccount;
    }

    public AccountDto findAccount(long id) throws AccountNotFoundException {
        // TODO: Find Account or throw if Account does not exist
        return null;
    }

    public List<AccountDto> findAllAccounts() {
        // TODO: Find all Accounts
        return null;
    }

    @Transactional
    public AccountDto updateAccount(AccountDto account) throws IllegalArgumentException, AccountNotFoundException {
        if (account.getId() != null) {
            // TODO: Check if Account exists
            boolean accountExists = false;
            if (accountExists) {
                // TODO: Update Account and return updated Version
                return null;
            }
            throw new AccountNotFoundException("account with id=%s not found".formatted(account.getId()));
        }
        throw new IllegalArgumentException("account.id must not be null");
    }
}

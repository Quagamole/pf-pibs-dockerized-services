package ch.pf.pibs.account.service;

import ch.pf.pibs.account.exception.AccountNotFoundException;
import ch.pf.pibs.account.jooq.tables.pojos.AccountDto;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static ch.pf.pibs.account.jooq.Sequences.ACCOUNT_SEQ;
import static ch.pf.pibs.account.jooq.tables.Account.ACCOUNT;

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
        AccountDto createdAccount = dslContext
                .insertInto(ACCOUNT, ACCOUNT.ID, ACCOUNT.ACCOUNT_TYPE, ACCOUNT.ACCOUNT_OWNER)
                .values(dslContext.nextval(ACCOUNT_SEQ), account.getAccountType(), account.getAccountOwner())
                .returning()
                .fetchOneInto(AccountDto.class);

        log.info("successfully registered person with id={}", createdAccount.getId());
        return createdAccount;
    }

    public AccountDto findAccount(long id) throws AccountNotFoundException {
        AccountDto account = dslContext
                .selectFrom(ACCOUNT)
                .where(ACCOUNT.ID.eq(id))
                .fetchOneInto(AccountDto.class);

        if (account == null) {
            throw new AccountNotFoundException("identifier=id does not exist");
        }
        return account;
    }

    public List<AccountDto> findAllAccounts() {
        return dslContext
                .selectFrom(ACCOUNT)
                .fetchInto(AccountDto.class);
    }

    @Transactional
    public AccountDto updateAccount(AccountDto account) throws IllegalArgumentException, AccountNotFoundException {
        if (account.getId() != null) {
            try {
                findAccount(account.getId());
            } catch (AccountNotFoundException e) {
                throw new AccountNotFoundException("identifier=id for update does not exist");
            }
            AccountDto updatedAccount = dslContext
                    .update(ACCOUNT)
                    .set(ACCOUNT, account)
                    .where(ACCOUNT.ID.eq(account.getId()))



            log.info("successfully updated person with id={}", account.getId());
            return null;
        }
        throw new IllegalArgumentException("account.id must not be null");
    }
}

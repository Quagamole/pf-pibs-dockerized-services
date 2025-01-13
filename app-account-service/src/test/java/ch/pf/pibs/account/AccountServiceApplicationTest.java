package ch.pf.pibs.account;

import ch.pf.pibs.account.configuration.PostgresTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({PostgresTestConfiguration.class, TestAccountServiceApplication.class})
public class AccountServiceApplicationTest {

    @Test
    void contextLoads() {
    }
}

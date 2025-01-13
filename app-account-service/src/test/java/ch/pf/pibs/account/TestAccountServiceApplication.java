package ch.pf.pibs.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(TestAccountServiceApplication::main).with(TestAccountServiceApplication.class).run(args);
    }
}

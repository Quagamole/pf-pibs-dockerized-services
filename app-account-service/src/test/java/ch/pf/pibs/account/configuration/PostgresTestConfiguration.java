package ch.pf.pibs.account.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class PostgresTestConfiguration {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        @SuppressWarnings("UnnecessaryLocalVariable")
        PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-bullseye"));
        // falls man beim debuggen von Tests auf die DB schauen möchte
        // postgreSQLContainer.setPortBindings(List.of("24241:5432"));
        return postgreSQLContainer;
    }

}
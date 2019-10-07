package com.gaievskyi.paradisehotel.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.gaievskyi.paradisehotel.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}

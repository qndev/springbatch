package io.github.qndev.springbatch.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:batch-mysql.properties")
public class BatchDataSourceConfiguration {

    @Value("${batch.schema.script}")
    private String batchSchemaScript;

    private final DataSource dataSource;

    private final ResourceLoader resourceLoader;

    public BatchDataSourceConfiguration(DataSource dataSource, ResourceLoader resourceLoader) {
        this.dataSource = dataSource;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    protected void initialize() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(this.resourceLoader.getResource(this.batchSchemaScript));
        populator.setContinueOnError(true);
        DatabasePopulatorUtils.execute(populator, this.dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(this.dataSource);
    }

}

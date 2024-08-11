package io.github.qndev.springbatch.configuration;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableAsync
public class BatchConfiguration {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final PlatformTransactionManager transactionManager;

    public BatchConfiguration(DataSource dataSource,
                              JdbcTemplate jdbcTemplate,
                              PlatformTransactionManager transactionManager) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.transactionManager = transactionManager;
    }

    @Bean
    @Order(1)
    public BatchConfigurer defaultBatchConfigurer() {
        return new DefaultBatchConfigurer(dataSource, jdbcTemplate, transactionManager);
    }

    @Bean
    @Order(2)
    public JobRepository jobRepository(BatchConfigurer batchConfigurer) throws Exception {
        return batchConfigurer.getJobRepository();
    }

    @Bean
    @Order(3)
    public JobExplorer jobExplorer(BatchConfigurer batchConfigurer) throws Exception {
        return batchConfigurer.getJobExplorer();
    }

    @Bean
    @Order(4)
    public JobLauncher jobLauncher(BatchConfigurer batchConfigurer) throws Exception {
        return batchConfigurer.getJobLauncher();
    }

    @Bean
    @Order(5)
    public JobBuilderFactory jobBuilderFactory(JobRepository jobRepository) {
        return new JobBuilderFactory(jobRepository);
    }

    @Bean
    @Order(6)
    public StepBuilderFactory stepBuilderFactory(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilderFactory(jobRepository, transactionManager);
    }

}

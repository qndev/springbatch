package io.github.qndev.springbatch.configuration;

import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

public class DefaultBatchConfigurer implements BatchConfigurer {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    private JobRepository jobRepository;
    private JobLauncher jobLauncher;
    private JobExplorer jobExplorer;

    public DefaultBatchConfigurer(DataSource dataSource, JdbcTemplate jdbcTemplate, PlatformTransactionManager transactionManager) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.transactionManager = transactionManager;
    }

    @PostConstruct
    public void initialize() {
        try {
            this.jobRepository = this.createJobRepository();
            this.jobExplorer = this.createJobExplorer();
            this.jobLauncher = this.createJobLauncher();
        } catch (Exception exception) {
            throw new BatchConfigurationException(exception);
        }
    }

    @Override
    public JobRepository getJobRepository() {
        return this.jobRepository;
    }

    @Override
    public PlatformTransactionManager getTransactionManager() {
        return this.transactionManager;
    }

    @Override
    public JobLauncher getJobLauncher() {
        return this.jobLauncher;
    }

    @Override
    public JobExplorer getJobExplorer() {
        return this.jobExplorer;
    }

    protected JobLauncher createJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(this.jobRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    protected JobRepository createJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(this.dataSource);
        factory.setDatabaseType("MYSQL");
        factory.setTransactionManager(this.transactionManager);
        factory.setJdbcOperations(this.jdbcTemplate);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    protected JobExplorer createJobExplorer() throws Exception {
        JobExplorerFactoryBean factoryBean = new JobExplorerFactoryBean();
        factoryBean.setDataSource(this.dataSource);
        factoryBean.setJdbcOperations(this.jdbcTemplate);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

}

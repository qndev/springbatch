package io.github.qndev.springbatch.configuration;

import io.github.qndev.springbatch.entity.Qndev;
import io.github.qndev.springbatch.entity.QndevWriter;
import io.github.qndev.springbatch.repository.QndevRepository;
import io.github.qndev.springbatch.repository.QndevWriterRepository;
import io.github.qndev.springbatch.service.QndevItemProcessor;
import io.github.qndev.springbatch.service.QndevItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public JobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job qndevJob(@Qualifier("qndevStep") Step qndevStep) {
        return jobBuilderFactory.get("qndevJob")
                .start(qndevStep)
                .build();
    }

    @Bean
    public Step qndevStep(ItemReader<Qndev> qndevItemReader,
                          ItemProcessor<Qndev, QndevWriter> qndevItemProcessor,
                          ItemWriter<QndevWriter> qndevItemWriter) {
        return stepBuilderFactory.get("qndevStep")
                .<Qndev, QndevWriter>chunk(10)
                .reader(qndevItemReader)
                .processor(qndevItemProcessor)
                .writer(qndevItemWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public ItemReader<Qndev> qndevItemReader(QndevRepository qndevRepository) {
        return new RepositoryItemReaderBuilder<Qndev>()
                .name("qndevReader")
                .pageSize(10)
                .methodName("findAll")
                .repository(qndevRepository)
                .sorts(new HashMap<String, Sort.Direction>() {{
                    put("id", Sort.Direction.ASC);
                }})
                .build();
    }

    @Bean
    public ItemProcessor<Qndev, QndevWriter> qndevItemProcessor() {
        return new QndevItemProcessor();
    }

    @Bean
    public ItemWriter<QndevWriter> qndevItemWriter(QndevWriterRepository qndevWriterRepository) {
        return new QndevItemWriter(qndevWriterRepository);
    }

}

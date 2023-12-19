package com.muneebcode.job.email;

import com.muneebcode.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BulkEmailJobConfig {
    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private final CustomerMapper customerMapper;
    private final EmailSendingWriter emailSendingWriter;
    private final EmailSendingProcessor emailSendingProcessor;

    @Bean
    public Job bulkEmailSendingJob() {
        return jobBuilderFactory.get("bulkEmailSendingJob").incrementer(new RunIdIncrementer()).start(emailSendingStep()).build();
    }

    @Bean
    public Step emailSendingStep() {
        return stepBuilderFactory.get("emailSendingStep").<Customer, Customer>chunk(20).reader(emailSendingReader()).processor(emailSendingProcessor).writer(emailSendingWriter).build();

    }



    @Bean
    public ItemReader<Customer> emailSendingReader() {
        String sql = "select * from customer";
        log.info("Read Customer from db");
        return new JdbcCursorItemReaderBuilder<Customer>().name("emailSendingReader").dataSource(dataSource).sql(sql).rowMapper(customerMapper).build();
    }

}


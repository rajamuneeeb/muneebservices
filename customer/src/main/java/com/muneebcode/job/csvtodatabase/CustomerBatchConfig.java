package com.muneebcode.job.csvtodatabase;

import com.muneebcode.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
@Slf4j
@AllArgsConstructor
public class CustomerBatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final CustomerSkipListener customerSkipListener;
    private final CustomerItemProcessor customerItemProcessor;
    private final CustomerRepositoryItemWriter customerRepositoryItemWriter;

    @Bean
    public Job csvToDatabase(JobCompletionNotificationListener jobCompletionNotificationListener) {
        return jobBuilderFactory.get("CsvToDatabaseJob").incrementer(new RunIdIncrementer()).listener(jobCompletionNotificationListener).flow(customerStep()).end().build();


    }

    @Bean
    public Step customerStep() {
        return stepBuilderFactory.get("customerStep").<Customer, Customer>chunk(10).reader(customerItemReader()).processor(customerItemProcessor).writer(customerRepositoryItemWriter).faultTolerant().skipLimit(5).skip(CsvProcessingException.class).listener(customerSkipListener).build();

    }

    @Bean
    public FlatFileItemReader<Customer> customerItemReader() {
        return new FlatFileItemReaderBuilder<Customer>().name("cutomerItemWriter").resource(new ClassPathResource("customer.csv")).delimited().delimiter(",").names(new String[]{"firstName", "lastName", "email"}).linesToSkip(1).fieldSetMapper(mapper()).build();

    }

    @Bean
    public FieldSetMapper<Customer> mapper() {
        BeanWrapperFieldSetMapper<Customer> customerBeanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        customerBeanWrapperFieldSetMapper.setTargetType(Customer.class);
        return customerBeanWrapperFieldSetMapper;


    }


}

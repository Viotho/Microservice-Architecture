package com.jackyzeng.batch.config;

import com.example.demo.mapper.SampleFieldSetMapper;
import com.example.demo.model.DBModel;
import com.example.demo.processor.DataItemProcessor;
import com.example.demo.reader.DataItemReader;
import com.example.demo.tasklet.SampleTasklet;
import com.example.demo.writer.DataItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.JsonLineMapper;
import org.springframework.batch.item.file.separator.JsonRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BatchTaskConfig {

    @Value("${chunk.size}")
    private String chunkSize;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private SampleTasklet sampleTasklet;

    @Bean
    public Job sampleJob() {
        return jobBuilderFactory.get("sampleJob")
                .repository(jobRepository)
                .start(stepA())
                .next(stepB()).on("FAILED").fail()
                .end()
                .build();

    }

    @Bean
    public Job conditionalFlowJob() {
        return jobBuilderFactory.get("conditionalFlowJob")
                .repository(jobRepository)
                .start(stepA())
                .on("*").to(stepB())
                .from(stepA()).on("FAILED").to(stepC())
                .end()
                .build();
    }

    @Bean
    public Job jobWithFlow() {
        return new JobBuilder("jobWithFlow")
                .repository(jobRepository)
                .start(sampleFlow())
                .next(stepC())
                .end()
                .build();
    }

    @Bean
    public Step stepA() {
        return stepBuilderFactory.get("stepA")
                .transactionManager(transactionManager)
                .<List<DBModel>, List<DBModel>>chunk(Integer.parseInt(chunkSize))
                .reader(dataItemReader())
                .processor(dataItemProcessor())
                .writer(dataItemWriter())
                .build();
    }

    @Bean
    public Step stepB() {
        return stepBuilderFactory.get("stepB")
                .tasklet(sampleTasklet)
                .build();
    }

    @Bean
    public Step stepC() {
        return stepBuilderFactory.get("stepC")
        .tasklet(sampleTasklet)
        .build();
    }

    @Bean
    public Flow sampleFlow() {
        return new FlowBuilder<SimpleFlow>("sampleFlow")
                .start(stepA())
                .next(stepB())
                .build();
    }

    @Bean
    public ItemReader<List<DBModel>> dataItemReader() {
        return new DataItemReader();
    }

    @Bean
    public ItemProcessor<List<DBModel>, List<DBModel>> dataItemProcessor() {
        return new DataItemProcessor();
    }

    @Bean
    public ItemWriter<List<DBModel>> dataItemWriter() {
        return new DataItemWriter();
    }

    @Bean
    public ItemReader<DBModel> jsonItemReader() {
        return new JsonItemReaderBuilder<DBModel>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(DBModel.class))
                .resource(new ClassPathResource("path/to/json/resource"))
                .name("jsonItemReader")
                .build();
    }

    @Bean
    public ItemWriter<DBModel> jsonItemWriter() {
        return new JsonFileItemWriterBuilder<DBModel>()
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .resource(new ClassPathResource("path/to/json/resource"))
                .name("jsonItemWriter")
                .build();
    }

    @Bean
    public ItemReader<Map<String, Object>> awsFileItemReader() {
        FlatFileItemReader<Map<String, Object>> awsFileItemReader = new FlatFileItemReader<>();
        awsFileItemReader.setResource(resourceLoader.getResource("s3://"));
        awsFileItemReader.setLineMapper(new JsonLineMapper());
        awsFileItemReader.setRecordSeparatorPolicy(new JsonRecordSeparatorPolicy());
        return awsFileItemReader;
    }

    @Bean
    public ItemReader flatFileItemReader() {
        FlatFileItemReader<DBModel> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("path/to/resource"));
        DefaultLineMapper<DBModel> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
        lineMapper.setFieldSetMapper(new SampleFieldSetMapper());
        flatFileItemReader.setLineMapper(lineMapper);
        return flatFileItemReader;
    }

    // Also could use ItemReaderAdaptor/ItemWriterAdaptor to reusing existing services.
}

package com.ipddashboard.dashboard.config;

import com.ipddashboard.dashboard.data.FootballMatchData;
import com.ipddashboard.dashboard.model.FootBallMatch;
import com.ipddashboard.dashboard.processor.FootBallMatchProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

   @Autowired
   private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("classPath:/results.csv")
    private Resource inputResource;

    @Bean
    public Job readCSVFileJob(Step step) {
        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step step(JdbcBatchItemWriter<FootBallMatch> writer) {
        return stepBuilderFactory
                .get("step")
                .<FootballMatchData, FootBallMatch>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public ItemProcessor<FootballMatchData, FootBallMatch> processor() {
        return new FootBallMatchProcessor();
    }

    @Bean
    public FlatFileItemReader<FootballMatchData> reader() {
        FlatFileItemReader<FootballMatchData> itemReader = new FlatFileItemReader<FootballMatchData>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }

    @Bean
    public LineMapper<FootballMatchData> lineMapper() {
        DefaultLineMapper<FootballMatchData> lineMapper = new DefaultLineMapper<FootballMatchData>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("date","home_team","away_team","home_score","away_score","tournament","city","country","neutral");

        BeanWrapperFieldSetMapper<FootballMatchData> fieldSetMapper = new BeanWrapperFieldSetMapper<FootballMatchData>();
        fieldSetMapper.setTargetType(FootballMatchData.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public JdbcBatchItemWriter<FootBallMatch> writer(DataSource dataSource) {
        JdbcBatchItemWriter<FootBallMatch> itemWriter = new JdbcBatchItemWriter<FootBallMatch>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql("INSERT INTO foot_ball_match (date,home_team,away_team,home_score,away_score,tournament,city,country,neutral) "
                + " VALUES (:date,:homeTeam,:awayTeam,:homeScore,:awayScore,:tournament,:city,:country,:neutral)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<FootBallMatch>());
        return itemWriter;
    }
}
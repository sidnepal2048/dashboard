package com.ipddashboard.dashboard.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/*@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

   // private final EntityManager em;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Map<String, Team> stringTeamMap = new HashMap<>();
            *//*em.createQuery("select m.team1, count (*) from match m group by m.team1", Object[].class)
                    .getResultList().stream()
                    .map(mp -> new Team((String)mp[0], (long)mp[1]))
                    .forEach(team ->stringTeamMap.put(team.getTeamName(),team));*//*
            jdbcTemplate.query("SELECT homeTeam, awayTeam, date FROM FootBallMatch",
                    (rs, row) -> "Home Team " + rs.getString(1) + " Away Team " + rs.getString(2)
            + "Date" + rs.getDate(3)
            ).forEach(str -> log.info("Found <" + str + "> in the database."));

            *//*em.createQuery("select m.team2, count (*) from match m group by m.team2", Object[].class)
                    .getResultList().stream()
                    .forEach(e -> {
                            Team team = stringTeamMap.get((String) e[0]);
                            team.setTotalMatch(team.getTotalMatch() + (long)e[1]);
                    });

            em.createQuery("select m.matchWinner, count (*) from match m group by m.matchWinner", Object[].class)
                    .getResultList().stream()
                    .forEach(e -> {
                        Team team = stringTeamMap.get((String) e[0]);
                        if(team!=null) team.setTotalWin((long)e[1]);
                    });*//*

            //stringTeamMap.values().forEach(team -> em.persist(team));
        }
    }

}*/

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.STARTED) {
            log.info("started");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

           /* jdbcTemplate.query("SELECT firstName, lastName FROM person",
                    (rs, row) -> new Person(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3))
            ).forEach(person -> log.info("Found <" + person + "> in the database."));*/
        }
    }
}
package com.ipddashboard.dashboard.processor;

import com.ipddashboard.dashboard.data.FootballMatchData;
import com.ipddashboard.dashboard.model.FootBallMatch;
import org.springframework.batch.item.ItemProcessor;
import java.time.LocalDate;

public class FootBallMatchProcessor implements ItemProcessor<FootballMatchData, FootBallMatch> {

    @Override
    public FootBallMatch process(FootballMatchData footballMatchData) throws Exception {

        FootBallMatch footBallMatch = new FootBallMatch();
        footBallMatch.setDate(LocalDate.parse(footballMatchData.getDate()));
        footBallMatch.setHomeTeam(footballMatchData.getHomeTeam());
        footBallMatch.setAwayTeam(footballMatchData.getAwayTeam());
        footBallMatch.setHomeScore(footballMatchData.getHomeScore());
        footBallMatch.setAwayScore(footballMatchData.getAwayScore());
        footBallMatch.setTournament(footballMatchData.getTournament());

        footBallMatch.setCity(footballMatchData.getCity());
        footBallMatch.setCountry(footballMatchData.getCountry());
        footBallMatch.setNeutral(footballMatchData.getNeutral());

        return footBallMatch;
    }
}

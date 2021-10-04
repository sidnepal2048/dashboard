package com.ipddashboard.dashboard.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "foot_ball_match")
public class FootBallMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private LocalDate date;
    @Column(name="home_team")
    private String homeTeam;
    @Column(name="away_team")
    private String awayTeam;
    @Column(name="home_score")
    private String homeScore;
    @Column(name="away_score")
    private String awayScore;
    @Column(name="tournament")
    private String tournament;
    @Column(name="city")
    private String city;
    @Column(name="country")
    private String country;
    @Column(name="neutral")
    private String neutral;
    @Column(name="winner")
    private String winner;

    public FootBallMatch() {
    }

    public FootBallMatch(Long id, String homeTeam, String awayTeam, String homeScore, String awayScore, String tournament, String city, String country, String neutral, String winner, LocalDate date) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.tournament = tournament;
        this.city = city;
        this.country = country;
        this.neutral = neutral;
        this.winner = winner;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNeutral() {
        return neutral;
    }

    public void setNeutral(String neutral) {
        this.neutral = neutral;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}

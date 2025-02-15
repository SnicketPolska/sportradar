package core.domain;

import java.util.Objects;

public class Match implements Comparable<Match> {
	private final String homeTeam;
	private final String awayTeam;
	private int homeScore;

	private int awayScore;

	public Match(String homeTeam, String awayTeam) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScore = 0;
		this.awayScore = 0;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void updateScore(int homeScore, int awayScore) {
		if (homeScore < 0 || awayScore < 0) {
			throw new IllegalArgumentException("homeScore and awayScore need to be non-negative integers. Were: %s, %s"
					.formatted(homeScore, awayScore));
		}
		this.homeScore = homeScore;
		this.awayScore = awayScore;
	}

	private int getSumOfScores() {
		return homeScore + awayScore;
	}

	@Override
	public String toString() {
		return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
	}

	@Override
	public int compareTo(Match o) {
		return this.getSumOfScores() - o.getSumOfScores();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		Match match = (Match) o;
		return Objects.equals(homeTeam, match.homeTeam) && Objects.equals(awayTeam, match.awayTeam);
	}

	@Override
	public int hashCode() {
		return Objects.hash(homeTeam, awayTeam);
	}
}
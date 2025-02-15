package core.domain;

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
}
package core;

import core.domain.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scoreboard {
	List<Match> matchList = new ArrayList<>();

	public void startGame(String homeTeam, String awayTeam) {
		Match match = new Match(homeTeam, awayTeam);
		matchList.add(match);
	}

	public void finishGame(String homeTeam, String awayTeam) {
		matchList.removeIf(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam));
	}

	public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
		matchList.stream().filter(m -> m.getHomeTeam().equals(homeTeam) && m.getAwayTeam().equals(awayTeam)).findFirst()
				.ifPresent(value -> value.updateScore(homeScore, awayScore));
	}

	public String showSummary() {
		String s = matchList.stream().sorted((match1, match2) -> match2.getSumOfScores() - match1.getSumOfScores())
				.map(Match::toString).collect(Collectors.joining("\n"));
		System.out.println(s);
		return s;
	}
}
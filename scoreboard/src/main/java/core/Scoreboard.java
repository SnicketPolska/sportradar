package core;

import core.domain.Match;

import java.util.*;
import java.util.stream.Collectors;

public class Scoreboard {
	Queue<Match> matchList = new PriorityQueue<>();
	Map<String, Boolean> teams = new HashMap<>();

	public void startGame(String homeTeam, String awayTeam) {
		if (teams.containsKey(homeTeam) || teams.containsKey(awayTeam)) {
			throw new IllegalArgumentException("There is already an ongoing match played by one of the teams.");
		}
		Match match = new Match(homeTeam, awayTeam);
		teams.put(homeTeam, true);
		teams.put(awayTeam, true);
		matchList.add(match);
	}

	public void finishGame(String homeTeam, String awayTeam) {
		boolean removed = matchList
				.removeIf(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam));
		if (removed) {
			teams.remove(homeTeam);
			teams.remove(awayTeam);
		}
	}

	public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
		if (homeScore < 0 || awayScore < 0) {
			throw new IllegalArgumentException("homeScore and awayScore need to be non-negative integers. Were: %s, %s"
					.formatted(homeScore, awayScore));
		}
		matchList.stream().filter(m -> m.getHomeTeam().equals(homeTeam) && m.getAwayTeam().equals(awayTeam)).findFirst()
				.ifPresent(value -> value.updateScore(homeScore, awayScore));
	}

	public String showSummary() {
		String s = matchList.stream().map(Match::toString).collect(Collectors.joining("\n"));
		System.out.println(s);
		return s;
	}
}
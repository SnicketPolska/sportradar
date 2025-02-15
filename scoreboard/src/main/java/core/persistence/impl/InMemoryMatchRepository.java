package core.persistence.impl;

import core.domain.Match;
import core.persistence.MatchRepository;

import java.util.*;

public class InMemoryMatchRepository implements MatchRepository {

	Queue<Match> matchList = new PriorityQueue<>();
	Map<String, Boolean> teams = new HashMap<>();

	@Override
	public void addMatch(Match match) {
		if (teams.containsKey(match.getHomeTeam()) || teams.containsKey(match.getAwayTeam())) {
			throw new IllegalArgumentException("There is already an ongoing match played by one of the teams.");
		}
		teams.put(match.getHomeTeam(), true);
		teams.put(match.getAwayTeam(), true);
		matchList.add(match);
	}

	@Override
	public void removeMatch(Match match) {
		boolean removed = matchList.remove(match);
		if (removed) {
			teams.remove(match.getHomeTeam());
			teams.remove(match.getAwayTeam());
		}
	}

	@Override
	public void updateMatch(Match match) {
		// No Implementation. In memory list stores references.
		// Update handled by Scoreboard.
	}

	@Override
	public Match getMatch(String homeTeam, String awayTeam) {
		return matchList.stream()
				.filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
				.findFirst().orElse(null);
	}

	@Override
	public List<Match> getMatches() {
		return new ArrayList<>(matchList);
	}
}

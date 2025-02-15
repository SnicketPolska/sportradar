package core.persistence;

import core.domain.Match;

import java.util.List;

public interface MatchRepository {
	void addMatch(Match match);
	void removeMatch(Match match);
	void updateMatch(Match match);
	Match getMatch(String homeTeam, String awayTeam);
	List<Match> getMatches();
}

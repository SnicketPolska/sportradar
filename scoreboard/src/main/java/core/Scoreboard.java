package core;

import core.domain.Match;
import core.persistence.MatchRepository;
import core.service.OutputService;

import java.util.stream.Collectors;

public class Scoreboard {

	MatchRepository matchRepository;
	OutputService outputService;

	public Scoreboard(MatchRepository matchRepository, OutputService outputService) {
		this.matchRepository = matchRepository;
		this.outputService = outputService;
	}

	public void startGame(String homeTeam, String awayTeam) {
		Match match = new Match(homeTeam, awayTeam);
		matchRepository.addMatch(match);
	}

	public void finishGame(String homeTeam, String awayTeam) {
		Match match = matchRepository.getMatch(homeTeam, awayTeam);
		if (match != null) {
			matchRepository.removeMatch(match);
		}
	}

	public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
		Match match = matchRepository.getMatch(homeTeam, awayTeam);
		match.updateScore(homeScore, awayScore);
		matchRepository.updateMatch(match);
	}

	public void showSummary() {
		outputService
				.showSummary(matchRepository.getMatches().stream().map(Match::toString).collect(Collectors.toList()));
	}
}
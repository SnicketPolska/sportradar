package core;

import core.persistence.MatchRepository;
import core.persistence.impl.InMemoryMatchRepository;
import core.service.OutputService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {

	MatchRepository matchRepository = new InMemoryMatchRepository();
	MockOutputService mockOutputService = new MockOutputService();
	Scoreboard scoreboard;

	@BeforeEach
	void setUp() {
		scoreboard = new Scoreboard(matchRepository, mockOutputService);
	}

	@Test
	void shouldStartGame() {
		// given
		scoreboard.startGame("TeamA", "TeamB");
		// when
		scoreboard.showSummary();
		// then
		List<String> summary = mockOutputService.getLastOutput();
		assertEquals("TeamA 0 - TeamB 0", summary.get(0));
	}

	@Test
	void shouldEndGame() {
		// given
		scoreboard.startGame("TeamA", "TeamB");
		scoreboard.finishGame("TeamA", "TeamB");
		// when
		scoreboard.showSummary();
		// then
		List<String> summary = mockOutputService.getLastOutput();
		assertTrue(summary.isEmpty());
	}

	@Test
	void shouldUpdateScore() {
		// given
		scoreboard.startGame("TeamA", "TeamB");
		scoreboard.updateScore("TeamA", "TeamB", 3, 2);
		// when
		scoreboard.showSummary();
		// then
		List<String> summary = mockOutputService.getLastOutput();
		assertEquals("TeamA 3 - TeamB 2", summary.get(0));
	}

	@Test
	void shouldDisplayScoresByOrderOfTotalScores() {
		// given
		scoreboard.startGame("TeamA", "TeamB");
		scoreboard.updateScore("TeamA", "TeamB", 3, 2);
		scoreboard.startGame("TeamC", "TeamD");
		scoreboard.updateScore("TeamC", "TeamD", 4, 2);
		// when
		scoreboard.showSummary();
		// then
		List<String> summary = mockOutputService.getLastOutput();
		assertEquals("TeamC 4 - TeamD 2", summary.get(0));
	}

	@Test
	void shouldDisplayScoresByOrderOfSubmition() {
		// given
		scoreboard.startGame("TeamA", "TeamB");
		scoreboard.updateScore("TeamA", "TeamB", 3, 2);
		scoreboard.startGame("TeamC", "TeamD");
		scoreboard.updateScore("TeamC", "TeamD", 3, 2);
		scoreboard.startGame("TeamE", "TeamF");
		scoreboard.updateScore("TeamE", "TeamF", 2, 3);
		// when
		scoreboard.showSummary();
		// then
		List<String> summary = mockOutputService.getLastOutput();
		assertEquals("TeamE 2 - TeamF 3", summary.get(0));
	}

	@Test
	void shouldNotAllowToUpdateScoreWithNegativeValue() {
		// given
		scoreboard.startGame("TeamA", "TeamB");
		// when, then
		Throwable t = assertThrows(IllegalArgumentException.class,
				() -> scoreboard.updateScore("TeamA", "TeamB", -1, 2));
		assertEquals("homeScore and awayScore need to be non-negative integers. Were: -1, 2", t.getMessage());
	}

	@Test
	void shouldNotAllowToAddMatchForAnExistingTeam() {
		scoreboard.startGame("TeamA", "TeamB");
		Throwable t = assertThrows(IllegalArgumentException.class, () -> scoreboard.startGame("TeamA", "TeamC"));
		assertEquals("There is already an ongoing match played by one of the teams.", t.getMessage());
	}

	static class MockOutputService implements OutputService {

		private List<String> output;

		@Override
		public void showSummary(List<String> matchSummaries) {
			this.output = matchSummaries;
		}

		public List<String> getLastOutput() {
			return this.output;
		}
	}
}
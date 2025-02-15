package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {

	Scoreboard scoreboard;

	@BeforeEach
	void setUp() {
		scoreboard = new Scoreboard();
	}

	@Test
	void shouldStartGame() {
		// given
		scoreboard.startGame("TeamA", "TeamB");
		// when
		String summary = scoreboard.showSummary();
		// then
		assertEquals("TeamA 0 - TeamB 0", summary);
	}

	@Test
	void shouldEndGame() {
		// given
		scoreboard.startGame("TeamA", "TeamB");
		// when
		scoreboard.finishGame("TeamA", "TeamB");
		// then
		String summary = scoreboard.showSummary();
		assertTrue(summary.isEmpty());
	}

	@Test
	void shouldUpdateScore() {
		// given
		scoreboard.startGame("TeamA", "TeamB");
		// when
		scoreboard.updateScore("TeamA", "TeamB", 3, 2);
		// then
		String summary = scoreboard.showSummary();
		assertEquals("TeamA 3 - TeamB 2", summary);
	}

	@Test
	void shouldDisplayScoresByOrderOfTotalScores() {
		// given
		scoreboard.startGame("TeamA", "TeamB");
		scoreboard.updateScore("TeamA", "TeamB", 3, 2);
		scoreboard.startGame("TeamC", "TeamD");
		scoreboard.updateScore("TeamC", "TeamD", 4, 2);
		// when
		String[] summary = scoreboard.showSummary().split("\n");
		// then
		assertEquals("TeamC 4 - TeamD 2", summary[0]);
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
		String[] summary = scoreboard.showSummary().split("\n");
		// then
		assertEquals("TeamE 2 - TeamF 3", summary[0]);
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
}
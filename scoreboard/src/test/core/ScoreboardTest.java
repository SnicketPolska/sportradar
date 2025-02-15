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
}
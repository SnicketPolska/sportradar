import core.Scoreboard;

public class Main {
	public static void main(String[] args) {
		var scoreboard = new Scoreboard();

		scoreboard.startGame("Mexico", "Canada");
		scoreboard.updateScore("Mexico", "Canada", 0, 3);

		scoreboard.startGame("Spain", "Brazil");
		scoreboard.updateScore("Spain", "Brazil", 3, 2);

		scoreboard.showSummary();
	}
}
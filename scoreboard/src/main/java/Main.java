import core.Scoreboard;
import core.persistence.MatchRepository;
import core.persistence.impl.InMemoryMatchRepository;
import core.service.OutputService;
import core.service.impl.ConsoleOutputService;

public class Main {
	public static void main(String[] args) {
		MatchRepository matchRepository = new InMemoryMatchRepository();
		OutputService outputService = new ConsoleOutputService();
		Scoreboard scoreboard = new Scoreboard(matchRepository, outputService);

		scoreboard.startGame("Mexico", "Canada");
		scoreboard.updateScore("Mexico", "Canada", 2, 3);

		scoreboard.startGame("Spain", "Brazil");
		scoreboard.updateScore("Spain", "Brazil", 3, 2);

		scoreboard.showSummary();
	}
}
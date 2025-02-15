package core.service.impl;

import core.service.OutputService;

import java.util.List;

public class ConsoleOutputService implements OutputService {

	@Override
	public void showSummary(List<String> matchSummaries) {
		System.out.println(String.join("\n", matchSummaries));
	}
}

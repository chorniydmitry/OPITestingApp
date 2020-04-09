package ru.fssprus.r82.utils.testingTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.Test;
import ru.fssprus.r82.entity.TestSet;
import ru.fssprus.r82.entity.User;
import ru.fssprus.r82.service.QuestionService;

/**
 * @author Chernyj Dmitry
 *
 */
public class TestProcessBuilder {
	private Test test;
	private List<Question> questions;
	private User user;

	public TestProcessBuilder(Test test, User user) {
		this.test = test;
		this.user = user;
	}

	private void loadQuestions() {
		QuestionService questService = new QuestionService();
		Set<TestSet> testSets = test.getTestSets();

		if (questions == null)
			questions = new ArrayList<>();

		for (TestSet testSet : testSets) {
			List<Question> allQuestionsList = questService.getAllByQuestionSet(testSet.getQuestionSet());

			Random rnd = new Random();
			Set<Integer> randomIndexesSet = new HashSet<>();

			do {
				randomIndexesSet.add(rnd.nextInt(testSet.getQuestionsAmount()));
			} while (randomIndexesSet.size() < testSet.getQuestionsAmount());

			randomIndexesSet.forEach(n -> questions.add(allQuestionsList.get(n)));
			Collections.shuffle(questions);
		}

	}

	public TestingProcess buildTest() {
		loadQuestions();

		return new TestingProcess(user, questions, test);

	}

}

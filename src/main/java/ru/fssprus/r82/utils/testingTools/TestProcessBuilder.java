package ru.fssprus.r82.utils.testingTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.entity.User;
import ru.fssprus.r82.service.QuestionService;
import ru.fssprus.r82.utils.Utils;

/**
 * @author Chernyj Dmitry
 *
 */
public class TestProcessBuilder {
	private List<QuestionSet> questionSets;
	private List<Question> questions;
	private List<Integer> amountQuestionsForSets;
	private User user;

	public TestProcessBuilder(List<QuestionSet> questionSets, User user) {
		this.questionSets = questionSets;
		this.user = user;
	}

	private void countAmountsOfQuestionsForQuestionSets() {
		int amountOfQuestions = 0;
		int commonPercent = 0;

		amountQuestionsForSets = initAmountsOfQuestionsForSetList(amountOfQuestions, commonPercent);
	}

	private List<Integer> initAmountsOfQuestionsForSetList(int amountOfQuestions, int commonPercent) {
		int commonQuestsAmount = Utils.countCommonQuestsAmount(amountOfQuestions, commonPercent);

		int setQuestsAmount = Utils.countSetQuestsAmount(amountOfQuestions, commonQuestsAmount);

		List<Integer> amountQuestionsForSets = new ArrayList<Integer>();
		
		amountQuestionsForSets.add(setQuestsAmount);
		amountQuestionsForSets.add(commonQuestsAmount);
		
		return amountQuestionsForSets;
	}

	private void loadQuestions() {
		QuestionService questService = new QuestionService();

		if (questions == null)
			questions = new ArrayList<Question>();

		for (int i = 0; i < questionSets.size(); i++) {
			List<Question> allQuestionsList = questService.getAllByQuestionSet(questionSets.get(i));

			Random rnd = new Random();
			Set<Integer> randomIndexesSet = new HashSet<Integer>();

			do {
				randomIndexesSet.add(rnd.nextInt(allQuestionsList.size()));

			} while (randomIndexesSet.size() < amountQuestionsForSets.get(i));

			randomIndexesSet.forEach((n) -> questions.add(allQuestionsList.get(n)));

			Collections.shuffle(questions);
			
			
		}
	}

	public TestingProcess buildTest() {
		countAmountsOfQuestionsForQuestionSets();
		loadQuestions();

		return new TestingProcess(user, questions, questionSets.get(0));

	}

}

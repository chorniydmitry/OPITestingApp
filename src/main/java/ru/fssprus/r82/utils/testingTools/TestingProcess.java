package ru.fssprus.r82.utils.testingTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.fssprus.r82.entity.Answer;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.Test;
import ru.fssprus.r82.entity.User;
import ru.fssprus.r82.swing.utils.MessageBox;

/**
 * @author Chernyj Dmitry
 *
 */
public class TestingProcess {
	private User testedUser;
	private Test test;
	private List<Question> questionsToAskList;
	private Map<Question, List<Answer>> questionsAndAnswersGiven;
	private boolean isQuizzFinished = false;

	public TestingProcess(User user, List<Question> questionsToAsk, Test test) {
		this.testedUser = user;
		this.questionsToAskList = questionsToAsk;
		this.test = test;
		
		initQuestionsAndAnswersGiven();
	}
	
	private void initQuestionsAndAnswersGiven() {
		questionsAndAnswersGiven = new HashMap<>();
		
		questionsToAskList.forEach(question -> 
			questionsAndAnswersGiven.put(question, new ArrayList<>()));
	}

	public void makeAnswer(Question question, List<Answer> answersGiven) {
		if(answersGiven == null || answersGiven.size() == 0) {
			questionsAndAnswersGiven.put(question, new ArrayList<>());
			return;
		}
		
		if(!checkQuestion(question)) {
			MessageBox.showWrongQuestionToAnswerErrorMessage(null);
			return;
		}
		if((!checkAnswers(question, answersGiven)) && answersGiven.size() > 0) {
			MessageBox.showWrongAnswerListForQuestionErrorMessage(null);
			return;
		}

		questionsAndAnswersGiven.put(question, answersGiven);
	}
	
	public void finishTest() {
		isQuizzFinished = true;
	}

	private boolean checkQuestion(Question q) {
		return (questionsToAskList.contains(q)) ? true : false;
	}

	private boolean checkAnswers(Question q, List<Answer> answers) {
		int answersFound = 0;

		List<Answer> answersToCompare = new ArrayList<>(q.getAnswers());

		for (Answer answer : answers)
			answersFound = answersToCompare.contains(answer) ? (answersFound + 1) : answersFound;
		
			
		return answersFound == answers.size();
	}

	public User getTestedUser() {
		return testedUser;
	}

	public void setTestedUser(User testedUser) {
		this.testedUser = testedUser;
	}

	public void setQuestionsToAskList(List<Question> questionsToAskList) {
		this.questionsToAskList = questionsToAskList;
	}

	public Map<Question, List<Answer>> getQuestionsAndAnswersGiven() {
		return questionsAndAnswersGiven;
	}

	public void setQuestionsAndAnswersGiven(Map<Question, List<Answer>> questionsAndAnswersGiven) {
		this.questionsAndAnswersGiven = questionsAndAnswersGiven;
	}

	public boolean isQuizzFinished() {
		return isQuizzFinished;
	}

	public void setQuizzFinished(boolean isQuizzFinished) {
		this.isQuizzFinished = isQuizzFinished;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
	public int getTimeSec() {
		return test.getTestTimeSec();
	}
	

//	public void setTimeForQuest(int time) {
//		this.totalTimeForQuest = time;
//	}
//	
//	public int getTimeForQuest() {
//		return totalTimeForQuest;
//	}

}

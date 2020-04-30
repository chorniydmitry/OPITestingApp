package ru.fssprus.r82.utils.testingTools;

import java.util.Date;

import ru.fssprus.r82.entity.Result;
import ru.fssprus.r82.entity.User;
import ru.fssprus.r82.service.ResultService;
import ru.fssprus.r82.service.UserService;

/**
 * @author Chernyj Dmitry
 *
 */
public class TestingResultsSaver {
	public void saveResultsToDB(int timeLeft, TestingProcessAnaliser analiser) {
		
		int correctAnswers = analiser.getCorrectAnswersAmount();
		
		Result result = new Result();
		result.setCorrectAnswers(correctAnswers);
		result.setDate(new Date());
		
		TestingProcess testingProcess = analiser.getTestingProcess();
		
		result.setScore(analiser.getMarkPercent());
		result.setResult(analiser.getMarkText());
		result.setUser(getDBUser(testingProcess.getTestedUser()));

		result.setQuestionSet(testingProcess.getTest());
		result.setTestingTime(testingProcess.getTimeSec() - timeLeft);
		result.setTotalQuestions(analiser.getTotalAmount());

		ResultService service = new ResultService();
		service.add(result);
	}
	
	public User getDBUser(User user) {
		UserService userService = new UserService();

		User usersFromDB = userService.getByNameSurnameSecondNameSingle(
				user.getName(), 
				user.getSurname(),
				user.getSecondName());

		if (usersFromDB == null)
			userService.add(user);
		else
			user = usersFromDB;
		
		return user;
	}

}

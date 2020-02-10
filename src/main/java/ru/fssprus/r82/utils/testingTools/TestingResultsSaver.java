package ru.fssprus.r82.utils.testingTools;

import java.util.Date;

import ru.fssprus.r82.entity.Result;
import ru.fssprus.r82.entity.User;
import ru.fssprus.r82.service.ResultService;
import ru.fssprus.r82.service.UserService;
import ru.fssprus.r82.utils.TimeUtils;

/**
 * @author Chernyj Dmitry
 *
 */
public class TestingResultsSaver {
	public void saveResultsToDB(int timeLeft, TestingProcessAnaliser analiser) {
		
		int correctAnswers = analiser.getCorrectAnswersAmount();
		
		Result test = new Result();
		test.setCorrectAnswers(correctAnswers);
		test.setDate(new Date());
		
		TestingProcess testingProcess = analiser.getTestingProcess();
		
		test.setScore(analiser.getMarkPercent());
		test.setResult(analiser.getMarkText());
		test.setUser(getDBUser(testingProcess.getTestedUser()));

		test.setSpecification(testingProcess.getSpecification());
		test.setLevel(testingProcess.getTestLevel());
		test.setTestingTime(TimeUtils.getQuizzTimeSecByLevel(testingProcess.getTestLevel()) - timeLeft);
		test.setTotalQuestions(analiser.getTotalAmount());

		ResultService service = new ResultService();
		service.add(test);
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

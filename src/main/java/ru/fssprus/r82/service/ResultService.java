package ru.fssprus.r82.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import ru.fssprus.r82.dao.ResultDao;
import ru.fssprus.r82.dao.impl.ResultDatabaseDao;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.entity.Result;
import ru.fssprus.r82.entity.User;

/**
 * @author Chernyj Dmitry
 *
 */
public class ResultService {
	// TODO: autowired
	private ResultDao resultDao = new ResultDatabaseDao();
	
	public void add(Result result) {
		resultDao.add(result);
	}
	
	public List<Result> getAll(int fromId, int toId) {
		return resultDao.getAll(fromId, toId);
	}
	
	public int countAll() {
		return resultDao.getAmountOfItems();
	}
	
	
	public void delete(Result test) {
		resultDao.remove(test);
	}

	public List<Result> getByUserQuestionSetAndDate(Set<User> users, Set<QuestionSet> sets,
			Date dateMore, Date dateLess, String result, int scoreMore, int scoreLess) {
		return resultDao.getByUserQuestionSetAndDate(-1, -1, users, sets, dateMore, dateLess, result, scoreMore, scoreLess);
		
	}

	public List<Result> getByUserQuestionSetAndDate(int startX, int endX, Set<User> users,
			Set<QuestionSet> sets, Date dateMore, Date dateLess, String result, int scoreMore,
			int scoreLess) {
		return resultDao.getByUserQuestionSetAndDate(startX, endX, users, sets, dateMore, dateLess, result, scoreMore, scoreLess);
	}
	
	public int countByUserQuestionSetAndDate(Set<User> users,
			Set<QuestionSet> sets, Date dateMore, Date dateLess, String result, int scoreMore,
			int scoreLess) {
		return resultDao.countByUserQuestionSetAndDate(users, sets, dateMore, dateLess, result, scoreMore, scoreLess);
		
	}
	
}

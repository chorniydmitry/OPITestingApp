package ru.fssprus.r82.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import ru.fssprus.r82.dao.ResultDao;
import ru.fssprus.r82.dao.impl.ResultDatabaseDao;
import ru.fssprus.r82.entity.Result;
import ru.fssprus.r82.entity.Test;
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

	public List<Result> getByUserTestAndDate(Set<User> users, Set<Test> tests,
			Date dateMore, Date dateLess, String result, int scoreMore, int scoreLess) {
		return resultDao.getByUserTestAndDate(-1, -1, users, tests, dateMore, dateLess, result, scoreMore, scoreLess);
		
	}

	public List<Result> getByUserTestAndDate(int startX, int endX, Set<User> users,
			Set<Test> tests, Date dateMore, Date dateLess, String result, int scoreMore,
			int scoreLess) {
		return resultDao.getByUserTestAndDate(startX, endX, users, tests, dateMore, dateLess, result, scoreMore, scoreLess);
	}
	
	public int countByUserTestAndDate(Set<User> users,
			Set<Test> tests, Date dateMore, Date dateLess, String result, int scoreMore,
			int scoreLess) {
		return resultDao.countByUserTestAndDate(users, tests, dateMore, dateLess, result, scoreMore, scoreLess);
		
	}
	
}

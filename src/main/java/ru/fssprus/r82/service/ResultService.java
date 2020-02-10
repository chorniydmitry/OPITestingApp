package ru.fssprus.r82.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import ru.fssprus.r82.dao.ResultDao;
import ru.fssprus.r82.dao.impl.ResultDatabaseDao;
import ru.fssprus.r82.entity.QuestionLevel;
import ru.fssprus.r82.entity.Specification;
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

	public List<Result> getByUserSpecifiactionLevelAndDate(Set<User> users, Set<Specification> specs, QuestionLevel level,
			Date dateMore, Date dateLess, String result, int scoreMore, int scoreLess) {
		return resultDao.getByUserSpecifiactionLevelAndDate(-1, -1, users, specs, level, dateMore, dateLess, result, scoreMore, scoreLess);
		
	}

	public List<Result> getByUserSpecifiactionLevelAndDate(int startX, int endX, Set<User> users,
			Set<Specification> specs, QuestionLevel level, Date dateMore, Date dateLess, String result, int scoreMore,
			int scoreLess) {
		return resultDao.getByUserSpecifiactionLevelAndDate(startX, endX, users, specs, level, dateMore, dateLess, result, scoreMore, scoreLess);
	}
	
	public int countByUserSpecifiactionLevelAndDate(Set<User> users,
			Set<Specification> specs, QuestionLevel level, Date dateMore, Date dateLess, String result, int scoreMore,
			int scoreLess) {
		return resultDao.countByUserSpecifiactionLevelAndDate(users, specs, level, dateMore, dateLess, result, scoreMore, scoreLess);
		
	}
	
}

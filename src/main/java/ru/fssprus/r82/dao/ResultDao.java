package ru.fssprus.r82.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import ru.fssprus.r82.entity.Result;
import ru.fssprus.r82.entity.Test;
import ru.fssprus.r82.entity.User;

/**
 * @author Chernyj Dmitry
 *
 */
public interface ResultDao extends ItemDao<Result> {

	int getAmountOfItems();

	List<Result> getByUserTestAndDate(int startPos, int endPos, Set<User> user, Set<Test> tests,
			Date dateMore, Date dateLess, String result, int scoreMore, int scoreLess);

	int countByUserTestAndDate(Set<User> users, Set<Test> tests, Date dateMore, Date dateLess,
			String result, int scoreMore, int scoreLess);

}
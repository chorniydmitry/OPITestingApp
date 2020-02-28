package ru.fssprus.r82.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.entity.Result;
import ru.fssprus.r82.entity.User;

/**
 * @author Chernyj Dmitry
 *
 */
public interface ResultDao extends ItemDao<Result> {

	int getAmountOfItems();

	List<Result> getByUserQuestionSetAndDate(int startPos, int endPos, Set<User> user, Set<QuestionSet> questionSets,
			Date dateMore, Date dateLess, String result, int scoreMore, int scoreLess);

	int countByUserQuestionSetAndDate(Set<User> users, Set<QuestionSet> sets, Date dateMore, Date dateLess,
			String result, int scoreMore, int scoreLess);

}
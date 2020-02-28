package ru.fssprus.r82.dao;

import java.util.List;

import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;

/**
 * @author Chernyj Dmitry
 *
 */
public interface QuestionSetDao extends ItemDao<QuestionSet> {
	
	public List<QuestionSet> getByTitle(int startPos, int endPos, String title);
	
	public List<QuestionSet> getByQuestion(int startPos, int endPos, Question questions);
	
}

package ru.fssprus.r82.dao;

import java.util.List;
import java.util.Set;

import ru.fssprus.r82.entity.Answer;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;

/**
 * @author Chernyj Dmitry
 *
 */
public interface QuestionDao extends ItemDao<Question> {
	
	public List<Question> getByTitle(int startPos, int endPos, String title);
	
	public List<Question> getByAnswer(int startPos, int endPos, Answer answer);
	
	public List<Question> getByQuestionSet(int startPos, int endPos, QuestionSet set);
	
	public List<Question> getByIds(Set<Long> ids);
	
	public List<Question> getByNameAndQuestionSet(String name, QuestionSet set);
	
	public Set<Question> getByNameAnswersAndQuestionSet(String name, Set<Answer> answers, QuestionSet set);
	
	public int countItemsByQuestionSet(QuestionSet set);
	
	public int getAmountOfItems();

	public List<Question> getByNameAndQuestionSetList(String name, Set<QuestionSet> sets);

	public int countByQuestionSet(QuestionSet set);
	
	public List<Question> getByNameSetListAndID(int startPos, int endPos, String name, Set<QuestionSet> sets, Long id);
	
	public int countByNameSetListAndID(String name, Set<QuestionSet> sets, Long id);
	
	

}

package ru.fssprus.r82.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.fssprus.r82.dao.QuestionDao;
import ru.fssprus.r82.dao.impl.QuestionDatabaseDao;
import ru.fssprus.r82.entity.Answer;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;

/**
 * @author Chernyj Dmitry
 *
 */
public class QuestionService {
	// TODO: autowired
	private QuestionDao questionDao = new QuestionDatabaseDao();

	public Question getById(Long id) {
		return questionDao.getById(id);
	}

	public List<Question> getByName(int startPos, int endPos, String name) {
		return questionDao.getByTitle(startPos, endPos, name);
	}

	public List<Question> getByName(String title) {
		return questionDao.getByTitle(-1, -1, title);
	}

	public List<Question> getByAnswer(int startPos, int endPos, Answer answer) {
		return questionDao.getByAnswer(startPos, endPos, answer);
	}

	public List<Question> getByQuestionSet(int startPos, int endPos, QuestionSet set) {
		return questionDao.getByQuestionSet(startPos, endPos, set);
	}

	public List<Question> getByIDsList(Set<Long> list) {
		return questionDao.getByIds(list);
	}

	public int getAmountByQuestionSet(QuestionSet set) {
		return questionDao.countByQuestionSet(set);
	}

	public int getAmountOfItems() {
		return questionDao.getAmountOfItems();
	}

	public List<Question> getAllByQuestionSet(QuestionSet questionSet) {
		return questionDao.getByQuestionSet(-1, -1, questionSet);
	}

	public List<Question> getByNameAndQuestionSet(String name, QuestionSet set) {
		return questionDao.getByNameAndQuestionSet(name, set);
	}

	public List<Question> getAll(int startPos, int endPos) {
		return questionDao.getAll(startPos, endPos);
	}
	
	public List<Question> getByNameAnswersAndQuestionSet(String name, Set<Answer> answers, QuestionSet set) {
		return questionDao.getByNameAnswersAndQuestionSet(name, answers, set);
	}
	
	public void addAllNoDuplicates(HashSet<Question> questions) {
		for (Question question : questions) {
			System.out.println(":::::"+question);
			if(!questionDao.hasDuplicates(question))
				questionDao.add(question);
		}
	}

	public void save(Question questionToSave) {
		questionDao.add(questionToSave);
	}
	
	public void update(Question questionModified) {
		Question question = questionDao.getById(questionModified.getId());
		AnswerService ansService = new AnswerService();
		question.getAnswers().forEach((n)-> ansService.delete(n));
		question.setAnswers(new HashSet<Answer>(questionModified.getAnswers()));
		
		questionDao.update(questionModified);
	}

	public int countAll() {
		return questionDao.getAmountOfItems();
	}

	public List<Question> getAllByNameSpecListAndId(String name, Set<QuestionSet> sets, Long id) {
		return questionDao.getByNameSetListAndID(-1, -1, name, sets, id);

	}

	public List<Question> getByNameSpecListAndId(int startPos, int endPos, String name, Set<QuestionSet> sets,
			Long id) {
		return questionDao.getByNameSetListAndID(startPos, endPos, name, sets, id);

	}

	public int countByNameSpecListAndId(String name, Set<QuestionSet> sets, Long id) {
		return questionDao.countByNameSetListAndID(name, sets, id);
	}
	
	public int countByQuestionSet(QuestionSet questionSet) {
		return questionDao.countByQuestionSet(questionSet);
	}

	public void delete(Question question) {
		questionDao.remove(question);
	}

}

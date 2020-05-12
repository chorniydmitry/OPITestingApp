package ru.fssprus.r82.service;

import java.util.List;

import ru.fssprus.r82.dao.QuestionSetDao;
import ru.fssprus.r82.dao.impl.QuestionSetDatabaseDao;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;

/**
 * @author Chernyj Dmitry
 *
 */
public class QuestionSetService {
	
	//TODO: autowired
	private QuestionSetDao questionSetDao = new QuestionSetDatabaseDao();
	
	public List<QuestionSet> getByName(int startPos, int endPos, String title) {
		return questionSetDao.getByTitle(startPos, endPos, title);
	}

	public List<QuestionSet> getByQuestion(int startPos, int endPos, Question question) {
		return questionSetDao.getByQuestion(startPos, endPos, question);
	}

	public List<QuestionSet> getAll() {
		return questionSetDao.getAll();
	}
	
	public QuestionSet getByID(Long id) {
		return questionSetDao.getById(id);
	}
	public void update(QuestionSet questionSetToUpdate) {
		questionSetDao.update(questionSetToUpdate);
	}

	public List<QuestionSet> getByName(String specName) {
		return questionSetDao.getByTitle(-1, -1, specName);
	}
	
	public QuestionSet getUniqueByName(String title) {
		List<QuestionSet> specsByName = questionSetDao.getByTitle(-1, -1, title);
		if(specsByName.size() == 0 || title.isEmpty())
			return null;
		return specsByName.get(0);
	}
	
	public void save(QuestionSet spec) {
		questionSetDao.add(spec);
	}

	public void delete(QuestionSet setToDelete) {
		questionSetDao.remove(setToDelete);
	}
	
}
	

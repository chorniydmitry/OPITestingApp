package ru.fssprus.r82.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jopendocument.dom.template.statements.ForEach;

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
	
	public Set<Question> getByNameAnswersAndQuestionSet(String name, Set<Answer> answers, QuestionSet set) {
		return questionDao.getByNameAnswersAndQuestionSet(name, answers, set);
	}
	
	//TODO DODODO
	public void addAll(HashSet<Question> questions) {
		for (Question question : questions) {
			questionDao.getByNameAnswersAndQuestionSet(question.getTitle(), question.getAnswers(), question.getQuestionSet());
			
		}
	}

//	public void addFilteringExistant(HashSet<Question> questions) {
//		for (Question question : questions)
//			addFilteringExistant(question);
//	}

	public void save(Question questionToSave) {
		QuestionSetService service = new QuestionSetService();

		if (service.getByName(questionToSave.getQuestionSet().getName()) == null)
			service.save(questionToSave.getQuestionSet());

		questionDao.add(questionToSave);
	}
	
//FIXME
	public void update(Long id, Question questionModified) {
		Question question = questionDao.getById(id);
		AnswerService ansService = new AnswerService();
		if (question == null)
			question = new Question();

		question.getAnswers().forEach((n) -> ansService.delete(n));
		question.setAnswers(new HashSet<Answer>(questionModified.getAnswers()));
		question.setQuestionSet(questionModified.getQuestionSet());
		question.setTitle(questionModified.getTitle());

		questionDao.update(question);
	}

//FIXME вынести
//	public void addFilteringExistant(Question question) {
//		List<Question> questionsFound = questionDao.getByTitle(-1, -1, question.getTitle());
//
//		int ansOverlaps = 0;
//		// Если в БД уже есть вопрос с такой формулировкой
//		if (questionsFound.size() > 0) {
//			// проверяем, совпадают ли ответы
//			for (Answer answerFound : questionsFound.get(0).getAnswers()) {
//				for (Answer answerQuest : question.getAnswers()) {
//					if (answerQuest.getTitle().equals(answerFound.getTitle()))
//						ansOverlaps++;
//				}
//			}
//		}
//
//		if (ansOverlaps != question.getAnswers().size()) {
//			QuestionSetService sService = new QuestionSetService();
//			String setName = question.getQuestionSet().getName();
//
//			QuestionSet set = null;
//			if (sService.getByName(setName) != null)
//				set = sService.getByName(setName).get(0);
//			else {
//				set = new QuestionSet();
//				set.setName(setName);
//			}
//
//			question.setQuestionSet(set);
//			save(question);
//		}
//	}

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

package ru.fssprus.r82.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import ru.fssprus.r82.dao.QuestionDao;
import ru.fssprus.r82.entity.Answer;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.utils.HibernateUtil;

/**
 * @author Chernyj Dmitry
 *
 */
public class QuestionDatabaseDao extends AbstractHibernateDao<Question> implements QuestionDao {

	public QuestionDatabaseDao() {
		super();
	}

	@Override
	public List<Question> getByIds(Set<Long> ids) {
		List<Question> questionList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Question> criteriaQuery = builder.createQuery(Question.class);

			Root<Question> root = criteriaQuery.from(Question.class);
			criteriaQuery.select(root).where(builder.in(root.get("id")));

			Expression<String> parentExpression = root.get("id");
			Predicate parentPredicate = parentExpression.in(ids);
			criteriaQuery.where(parentPredicate);

			Query<Question> query = session.createQuery(criteriaQuery);

			questionList = query.getResultList();

			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return questionList;
	}

	@Override
	public List<Question> getByTitle(int startPos, int endPos, String title) {
		List<Question> questionList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Question> criteriaQuery = builder.createQuery(Question.class);

			Root<Question> root = criteriaQuery.from(Question.class);
			criteriaQuery.select(root).where(builder.like(root.get("title"), "%" + title + "%"));

			Query<Question> query = session.createQuery(criteriaQuery);

			if (!(endPos == -1 || startPos == -1)) {
				query.setFirstResult(startPos);
				query.setMaxResults(endPos);
			}

			questionList = query.getResultList();

			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return questionList;
	}

	@Override
	public List<Question> getByAnswer(int startPos, int endPos, Answer answer) {
		List<Question> questionList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Question> criteriaQuery = builder.createQuery(Question.class);

			Root<Question> root = criteriaQuery.from(Question.class);
			criteriaQuery.select(root).where(builder.equal(root.get("answer"), answer));

			Query<Question> query = session.createQuery(criteriaQuery);

			if (!(endPos == -1 || startPos == -1)) {
				query.setFirstResult(startPos);
				query.setMaxResults(endPos);
			}

			questionList = query.getResultList();

			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return questionList;
	}

	@Override
	public List<Question> getByQuestionSet(int startPos, int endPos, QuestionSet set) {
		List<Question> questionList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Question> criteriaQuery = builder.createQuery(Question.class);

			Root<Question> root = criteriaQuery.from(Question.class);
			criteriaQuery.where(root.join("questionset").in(set));

			Query<Question> query = session.createQuery(criteriaQuery);

			if (!(endPos == -1 || startPos == -1)) {
				query.setFirstResult(startPos);
				query.setMaxResults(endPos);
			}

			questionList = query.getResultList();

			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return questionList;
	}

	@Override
	public int countItemsByQuestionSet(QuestionSet set) {
		int returnValue = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Object> criteriaQuery = builder.createQuery();
			Root<Question> root = criteriaQuery.from(Question.class);

			criteriaQuery.select(builder.count(root));
			criteriaQuery.where(root.join("questionset").in(set));

			TypedQuery<Object> q = session.createQuery(criteriaQuery);

			returnValue = Integer.parseInt(q.getSingleResult().toString());

			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return returnValue;
	}

	@Override
	public int getAmountOfItems() {
		int returnValue = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Object> criteriaQuery = builder.createQuery();
			Root<Question> root = criteriaQuery.from(Question.class);

			criteriaQuery.select(builder.count(root));

			TypedQuery<Object> q = session.createQuery(criteriaQuery);

			returnValue = Integer.parseInt(q.getSingleResult().toString());

			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return returnValue;
	}

	@Override
	public List<Question> getByNameAndQuestionSet(String name, QuestionSet set) {
		List<Question> questionList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Question> criteriaQuery = builder.createQuery(Question.class);

			Root<Question> root = criteriaQuery.from(Question.class);
			criteriaQuery.select(root).where((builder.like(root.get("title"), "%" + name + "%")),
					(root.join("questionset").in(set)));

			Query<Question> query = session.createQuery(criteriaQuery);

			questionList = query.getResultList();

			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return questionList;
	}

	@Override
	public List<Question> getByNameAnswersAndQuestionSet(String name, Set<Answer> answers, QuestionSet set) {
		Set<Question> questionList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Question> criteriaQuery = builder.createQuery(Question.class);

			Root<Question> root = criteriaQuery.from(Question.class);

			List<Predicate> predicates = new ArrayList<Predicate>();

			predicates.add(builder.equal(root.get("title"), name));

			predicates.add(builder.equal(root.get("questionset"), set));

			predicates.add(root.joinSet("answers").in(answers));

			criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));

			Query<Question> query = session.createQuery(criteriaQuery);

			questionList = new HashSet<>(query.getResultList());

			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return new ArrayList<>(questionList);
	}

	@Override
	public boolean hasDuplicates(Question question) {
		return getByNameAnswersAndQuestionSet(question.getTitle(), question.getAnswers(), question.getQuestionSet())
				.size() > 0 ? true : false;
	}

	@Override
	public List<Question> getByNameAndQuestionSetList(String name, Set<QuestionSet> sets) {
		List<Question> questionList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Question> criteriaQuery = builder.createQuery(Question.class);

			Root<Question> root = criteriaQuery.from(Question.class);
			criteriaQuery.select(root).where((builder.like(root.get("title"), "%" + name + "%")),
					(root.join("questionset").in(sets)));

			Query<Question> query = session.createQuery(criteriaQuery);

			questionList = query.getResultList();

			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return questionList;
	}

	@Override
	public List<Question> getByNameSetListAndID(int startPos, int endPos, String name, Set<QuestionSet> sets, Long id) {
		List<Question> questionList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Question> criteriaQuery = builder.createQuery(Question.class);

			Root<Question> root = criteriaQuery.from(Question.class);

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (id != 0) {
				predicates.add(builder.equal(root.get("id"), id));
			}

			if (!name.isEmpty()) {
				predicates.add(builder.like(root.get("title"), "%" + name + "%"));
			}
			if (sets != null) {
				predicates.add(root.join("questionset").in(sets));
			}

			criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));

			Query<Question> query = session.createQuery(criteriaQuery);

			if (!(endPos == -1) || !(startPos == -1)) {

				query.setFirstResult(startPos);
				query.setMaxResults(endPos);
			}

			questionList = query.getResultList();

			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return questionList;
	}

	@Override
	public int countByQuestionSet(QuestionSet set) {
		int returnValue = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Object> criteriaQuery = builder.createQuery();
			Root<Question> root = criteriaQuery.from(Question.class);

			criteriaQuery.select(builder.count(root));
			criteriaQuery.where(root.join("questionset").in(set));

			TypedQuery<Object> q = session.createQuery(criteriaQuery);

			returnValue = Integer.parseInt(q.getSingleResult().toString());

			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return returnValue;
	}

	@Override
	public int countByNameSetListAndID(String name, Set<QuestionSet> sets, Long id) {
		int returnValue = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Object> criteriaQuery = builder.createQuery();

			Root<Question> root = criteriaQuery.from(Question.class);

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (id != 0 && id != null) {
				predicates.add(builder.equal(root.get("id"), id));
			}

			if (!name.isEmpty()) {
				predicates.add(builder.like(root.get("title"), "%" + name + "%"));
			}
			if (sets != null) {
				predicates.add(root.join("questionset").in(sets));
			}

			criteriaQuery.select(builder.count(root));
			criteriaQuery.where(predicates.toArray(new Predicate[] {}));

			TypedQuery<Object> q = session.createQuery(criteriaQuery);

			returnValue = Integer.parseInt(q.getSingleResult().toString());

			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return returnValue;

	}

}

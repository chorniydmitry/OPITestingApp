package ru.fssprus.r82.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import ru.fssprus.r82.dao.QuestionSetDao;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.utils.HibernateUtil;

/**
 * @author Chernyj Dmitry
 *
 */
public class QuestionSetDatabaseDao extends AbstractHibernateDao<QuestionSet> implements QuestionSetDao {

	public QuestionSetDatabaseDao() {
		super();
	}

	@Override
	public List<QuestionSet> getByTitle(int startPos, int endPos, String title) {
		List<QuestionSet> questionSetList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<QuestionSet> criteriaQuery = builder.createQuery(QuestionSet.class);

			Root<QuestionSet> root = criteriaQuery.from(QuestionSet.class);
			criteriaQuery.select(root).where(builder.like(root.get("name"), "%" + title + "%"));

			Query<QuestionSet> query = session.createQuery(criteriaQuery);
			if (!(endPos == -1 || startPos == -1)) {
				query.setFirstResult(startPos);
				query.setMaxResults(endPos);
			}

			questionSetList = query.getResultList();
			
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return questionSetList;
	}

	@Override
	public List<QuestionSet> getByQuestion(int startPos, int endPos, Question question) {
		List<QuestionSet> specList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<QuestionSet> criteriaQuery = builder.createQuery(QuestionSet.class);

			Root<QuestionSet> root = criteriaQuery.from(QuestionSet.class);
			criteriaQuery.where(root.join("questionset").in(question));

			Query<QuestionSet> query = session.createQuery(criteriaQuery);
			if (!(endPos == -1 || startPos == -1)) {
				query.setFirstResult(startPos);
				query.setMaxResults(endPos);
			}

			specList = query.getResultList();
			
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return specList;
	}

}

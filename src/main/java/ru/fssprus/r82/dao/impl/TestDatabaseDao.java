package ru.fssprus.r82.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import ru.fssprus.r82.dao.TestDao;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.entity.Test;
import ru.fssprus.r82.entity.TestSet;
import ru.fssprus.r82.utils.HibernateUtil;

public class TestDatabaseDao extends AbstractHibernateDao<Test> implements TestDao {

	@Override
	public int countAll() {
		int returnValue = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Object> criteriaQuery = builder.createQuery();
			Root<Test> root = criteriaQuery.from(Test.class);

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
	public Test getByName(String name) {
		Test test = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Test> criteriaQuery = builder.createQuery(Test.class);

			Root<Test> root = criteriaQuery.from(Test.class);
			criteriaQuery.select(root).where(builder.equal(root.get("name"), name));
			Query<Test> query = session.createQuery(criteriaQuery);

			test = query.getSingleResult();

			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

		return test;
	}

	@Override
	public List<Test> getByQuestionSet(QuestionSet set) {
		List<Test> custList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Test> query = builder.createQuery(Test.class);

			Root<Test> test = query.from(Test.class);
			Root<TestSet> testSet = query.from(TestSet.class);

			TypedQuery<Test> typedQuery = session
					.createQuery(query.select(test)
							.where(builder.equal(testSet.get("questionset"), set),
									builder.equal(testSet.get("test"), test)));

			custList = typedQuery.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return custList;
	}

	@Override
	public int countByQuestionSet(QuestionSet questionSet) {
		int returnValue = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Object> criteriaQuery = builder.createQuery();

			Root<Test> test = criteriaQuery.from(Test.class);
			Root<TestSet> testSet = criteriaQuery.from(TestSet.class);


			List<Predicate> predicates = new ArrayList<Predicate>();

			predicates.add(builder.equal(testSet.get("questionset"), questionSet));
			predicates.add(builder.equal(testSet.get("test"), test));

			criteriaQuery.select(builder.count(test));
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

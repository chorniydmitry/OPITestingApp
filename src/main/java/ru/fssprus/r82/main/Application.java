package ru.fssprus.r82.main;

/**
 * @author Chernyj Dmitry
 *
 */
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.SwingUtilities;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import ru.fssprus.r82.entity.Answer;
import ru.fssprus.r82.entity.Password;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.entity.Test;
import ru.fssprus.r82.entity.TestSet;
import ru.fssprus.r82.service.AnswerService;
import ru.fssprus.r82.service.QuestionService;
import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.service.TestService;
import ru.fssprus.r82.swing.dialogs.DialogBuilder;
import ru.fssprus.r82.swing.main.mainFrame.MainFrame;
import ru.fssprus.r82.utils.CheatingStopper;
import ru.fssprus.r82.utils.CryptWithMD5;
import ru.fssprus.r82.utils.HibernateUtil;

public class Application {

	private static void appStart() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame mainFrame = new MainFrame();
				CheatingStopper.create(mainFrame);
				DialogBuilder.setParent(mainFrame);
			}

		});
	}

	public static void getKeyCodes() {
		Field[] fields = java.awt.event.KeyEvent.class.getDeclaredFields();
		for (Field f : fields) {
			if (Modifier.isStatic(f.getModifiers())) {
				System.out.println(f.getName());
			}
		}
	}

	public static void test() {
		QuestionService qs = new QuestionService();

		List<Question> qlist = qs.getByName("частота");
		System.out.println(qlist.size());
		qlist.forEach((n) -> System.out.println(n.getTitle()));

		System.out.println("-----------------------");
		AnswerService as = new AnswerService();
		List<Answer> aList = as.getAllByQuestion(0, 50, qlist.get(0));
		System.out.println(aList.size());
		aList.forEach((n) -> System.out.println(n.getTitle()));
		aList.forEach((n) -> System.out.println(n.getIsCorrect()));

		System.out.println("-----------------------");
		AnswerService as2 = new AnswerService();
		List<Answer> aList2 = as2.getAllByQuestion(0, 50, qlist.get(1));
		System.out.println(aList2.size());
		aList2.forEach((n) -> System.out.println(n.getTitle()));
		aList2.forEach((n) -> System.out.println(n.getIsCorrect()));

	}

	public static void newPassForConfigure() {
		String section = "SETTINGS";
		String password = CryptWithMD5.cryptWithMD5("settingsv2");

		Password pass = new Password();
		pass.setSectionName(section);
		pass.setPasswordMD5(password);

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.save(pass);
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}

	}

	public static void testValidation() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Question question = new Question();
		// question.setTitle("12");

		Set<ConstraintViolation<Question>> constrs = validator.validate(question);
		System.out.println(constrs.size());
		for (ConstraintViolation<Question> constr : constrs) {

			StringBuilder stringBuilder = new StringBuilder("property: ");
			stringBuilder.append(constr.getPropertyPath());
			stringBuilder.append(" value: ");
			stringBuilder.append(constr.getInvalidValue());
			stringBuilder.append(" message: ");
			stringBuilder.append(constr.getMessage());

			System.out.println(stringBuilder.toString());
		}
	}
	
	
	public static void testTestCreation() {
		QuestionSetService qsServ = new QuestionSetService();
		
		Test test1 = new Test();
		test1.setTestTimeSec(300);
		test1.setAmountOfQuestions(30);
		test1.setName("ПЕРВЫЙ ТЕСТ");
		test1.setActive(true);
		
		Test test2 = new Test();
		test2.setName("ВТОРОЙ ТЕСТ");
		
		
		TestSet testSet1 = new TestSet();
		testSet1.setQuestionsAmount(new Integer(10));
		testSet1.setQuestionSet(qsServ.getByID(1L));
		testSet1.setTest(test1);
		
		
		TestSet testSet2 = new TestSet();
		testSet2.setQuestionsAmount(new Integer(20));
		testSet2.setQuestionSet(qsServ.getByID(2L));
		testSet2.setTest(test1);
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.save(testSet1);
			session.save(testSet2);
			
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		
		//testServ.add(test1);
	}
	
	public static void testTestExtractionFromDB() {
		TestService testServ = new TestService();
		
		Test test = testServ.getById(1L);
		
		Set<TestSet> testSets = test.getTestSets();
		
		for (TestSet testSet : testSets) {
			System.out.println(testSet.getTest().getName() + " " + testSet.getQuestionsAmount() + " " + testSet.getQuestionSet().getName());
		}
		
		System.out.println(test.getName());
	}
	

	public static void main(String[] args) throws IOException {

		//Configuration configuration = new Configuration();
		
		//configuration.configure(Application.class.getResource("hibernate.cfg.xml"));

//		Flyway flyway = Flyway.configure().dataSource(configuration.getProperty("connection.url"), 
//				configuration.getProperty("connection.username"), 
//				configuration.getProperty("connection.password")).load();
//		flyway.migrate();

		// try (Session session = HibernateUtil.getSessionFactory().openSession()) {

		// test();

		// newPassForConfigure();

		// testValidation();
		//testCreation2();
		//testTestCreation();
		//testTestExtractionFromDB();
		
		appStart();

		// } catch (HibernateException e) {
		// e.printStackTrace();
		// HibernateUtil.getSessionFactory().getCurrentSession().close();
		// } finally {
		// HibernateUtil.getSessionFactory().getCurrentSession().close();
		// }
	}
}

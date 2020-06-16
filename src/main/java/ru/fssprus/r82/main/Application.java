package ru.fssprus.r82.main;

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SwingUtilities;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import ru.fssprus.r82.entity.Answer;
import ru.fssprus.r82.entity.Password;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.Test;
import ru.fssprus.r82.entity.TestSet;
import ru.fssprus.r82.service.AnswerService;
import ru.fssprus.r82.service.QuestionService;
import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.service.TestService;
import ru.fssprus.r82.ui.dialogs.DialogBuilder;
import ru.fssprus.r82.ui.main.mainFrame.MainFrame;
import ru.fssprus.r82.ui.utils.UIManagerConfigurator;
import ru.fssprus.r82.utils.CryptWithMD5;
import ru.fssprus.r82.utils.HibernateUtil;

/**
 * @author Chernyj Dmitry
 *
 */

public class Application {

	private static void appStart() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame mainFrame = new MainFrame();
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

	public static void testTestCreation2() {

		QuestionSetService qsServ = new QuestionSetService();
		TestService tServ = new TestService();

		Test test = new Test();

		test.setActive(true);
		test.setAmountOfQuestions(20);
		test.setName("ПЕРВЫЙ ТЕСТОВЫЙ ТЕСТ");
		test.setTestTimeSec(300);

		Set<TestSet> testSets = new HashSet<>();
		TestSet testSet1 = new TestSet();
		testSet1.setQuestionsAmount(10);
		testSet1.setQuestionSet(qsServ.getByID(7L));
		testSet1.setTest(test);
		testSets.add(testSet1);

		TestSet testSet2 = new TestSet();
		testSet2.setQuestionsAmount(5);
		testSet2.setQuestionSet(qsServ.getByID(8L));
		testSet2.setTest(test);
		testSets.add(testSet2);

		TestSet testSet3 = new TestSet();
		testSet3.setQuestionsAmount(5);
		testSet3.setQuestionSet(qsServ.getByID(9L));
		testSet3.setTest(test);
		testSets.add(testSet3);

		test.setTestSets(testSets);

		tServ.add(test);

	}

	public static void testTestUpdationg() {
		TestService testServ = new TestService();
		Test test = testServ.getById(4L);
		System.out.println(test.getName());
		test.setName("ПЕРВЫЙ ТЕСТ ИЗМЕНЕННЫЙ");
		System.out.println(test.getName());
		testServ.update(test);

		test = testServ.getById(4L);
		System.out.println(test.getName());

		// testServ.delete(test);

	}

	public static void testTestCreation() {
		QuestionSetService qsServ = new QuestionSetService();

		Test test1 = new Test();
		test1.setTestTimeSec(400);
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

		// testServ.add(test1);
	}

	public static void testTestExtractionFromDB() {
		TestService testServ = new TestService();

		Test test = testServ.getById(1L);

		Set<TestSet> testSets = test.getTestSets();

		for (TestSet testSet : testSets) {
			System.out.println(testSet.getTest().getName() + " " + testSet.getQuestionsAmount() + " "
					+ testSet.getQuestionSet().getName());
		}

		System.out.println(test.getName());
	}

	public static void main(String[] args) throws IOException {

		// Configuration configuration = new Configuration();

		// configuration.configure(Application.class.getResource("hibernate.cfg.xml"));

//		Flyway flyway = Flyway.configure().dataSource(configuration.getProperty("connection.url"), 
//				configuration.getProperty("connection.username"), 
//				configuration.getProperty("connection.password")).load();
//		flyway.migrate();

		// try (Session session = HibernateUtil.getSessionFactory().openSession()) {

		// test();

		// newPassForConfigure();

		// testValidation();
		// testCreation2();
		// testTestCreation2();
		// testTestExtractionFromDB();
		// testTestUpdationg();
//		System.out.println("start");
//		Enumeration<Object> a = UIManager.getDefaults().keys();
//		
//		
//		for(Entry<Object, Object> entry :UIManager.getDefaults().entrySet()) {
//			System.out.println(entry.getKey() + " > " + entry.getValue());
//		}
//		
//
//		System.out.println("end");
		
		
//		String fonts[] = 
//			      GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
//
//			    for ( int i = 0; i < fonts.length; i++ )
//			    {
//			      System.out.println(fonts[i]);
//			    }
		
		
		
		
		

		UIManagerConfigurator.configure();
		appStart();

		// testTestCreation2();

		// } catch (HibernateException e) {
		// e.printStackTrace();
		// HibernateUtil.getSessionFactory().getCurrentSession().close();
		// } finally {
		// HibernateUtil.getSessionFactory().getCurrentSession().close();
		// }
	}

}

import java.util.Set;

import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.service.QuestionService;

public class Test {
	
	public static void main(String[] args) {
		QuestionService qService = new QuestionService();
		Question q1 = qService.getById(1L);
		Question q2 = qService.getById(228L);
		
		Set<Question> res1 = qService.getByNameAnswersAndQuestionSet(q1.getTitle(), q1.getAnswers(), q1.getQuestionSet());
		
		//q2.setTitle("NewTitle");
		
		Set<Question> res2 = qService.getByNameAnswersAndQuestionSet(q2.getTitle(), q2.getAnswers(), q2.getQuestionSet());
		
		System.out.println("res1");
		for (Question question : res1) {
			System.out.println(question);
		}
		
		System.out.println("res2");
		for (Question question : res2) {
			System.out.println(question);
		}
		
	}

}

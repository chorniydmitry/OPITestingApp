import java.util.List;

import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.service.QuestionService;
import ru.fssprus.r82.service.QuestionSetService;

public class Temp {
	
	public static void main(String[] args) {
		QuestionService qService = new QuestionService();
		
		List<Question> all = qService.getByQuestionSet(0, 999999, new QuestionSetService().getByID(1L));
		
		for (Question question : all) {
			if(question.getId() > 55)
				qService.delete(question);
		}
	}

}

import java.util.List;

import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.service.QuestionService;

public class Temp {
	
	public static void main(String[] args) {
		QuestionService qService = new QuestionService();
		List<Question> qestions = qService.getAll(0, 9999999);
		
		for (Question question : qestions) {
			if(question.getId() > 2025)
				qService.delete(question);
		}
	}

}

package ru.fssprus.r82.utils.spreadsheet;

import java.io.File;
import java.util.HashSet;

import ru.fssprus.r82.entity.Answer;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;

/**
 * @author Chernyj Dmitry
 *
 */
public class SpreadSheetParser {
	
	public HashSet<Question> parse(File f, QuestionSet set) {
		SpreadSheetAdapter adapter = new SpreadSheetAdapter(f);
		int columns = adapter.getColumnsCount();
		int rows = adapter.getRowsCount();
		
		HashSet<Question> questions = new HashSet<Question>();
		for(int i = 1; i < rows; i++) {
			Question question = new Question();
			HashSet<Answer> answers = new HashSet<Answer>();
			for(int j = 0; j < columns; j++) {
				// 2 - формулировка вопроса
				if(j == 2) {
					question.setTitle(adapter.getCellValue(i, j));
					question.setQuestionSet(set);
				}
				// 3 ... - формулировка ответа, его правильность
				if(j >= 3) {
					Answer answer = new Answer();
					// 3, 5, 7 ... - формулировки ответа
					if ((j % 2) == 1) {
						String currAnswer = adapter.getCellValue(i, j);
						if (currAnswer != null && !currAnswer.isEmpty()) {
							answer = new Answer();
							answer.setTitle(currAnswer);
							answer.setQuestion(question);
						} else {
							break;
						}
					}
					
					// 4, 6, 8 ... - правильность ответа (1 - верный, 0 - не верный)
					j++;
					if (!(answer == null)) {
						boolean isAnswerCorrect = "1".equals(adapter.getCellValue(i, j));
						answer.setIsCorrect(isAnswerCorrect);
					}
					answers.add(answer);
				}
			}
			question.setAnswers(answers);
			questions.add(question);
		}
		
		return questions;
		
	}

}

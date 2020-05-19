package ru.fssprus.r82.utils;

/**
 * @author Chernyj Dmitry
 *
 */
public class Utils {

	private static final double MAX_PERCENT = 100.0;

	public static int countTestDialogTaQuestionHeight(int height) {
		return (2 * (height - 60) / 3) - 160;
	}

	public static int countTestDialogPnlAnswersHeight(int height) {
		return ((height - 60) / 3) + 40;
	}
	
	public static int countCommonQuestsAmount(int amountOfQuestions, int commonPercent) {
		return (int) Math.round((double) (amountOfQuestions * (commonPercent / MAX_PERCENT)));
	}
	
	public static int countSetQuestsAmount(int amountOfQuestions, int commonQuestsAmount) { 
		return amountOfQuestions - commonQuestsAmount;
	}

	public static boolean isNumeric(Object num) {
		String strNum = String.valueOf(num);
		try {
			Double.parseDouble(strNum);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}

	public static int parseInt(String text) {
		int returnVal = 0;
		try {
			returnVal = Integer.parseInt(text);
		} catch(NumberFormatException | NullPointerException nfe) {
			return 0;
		}
		return returnVal;
	}

}

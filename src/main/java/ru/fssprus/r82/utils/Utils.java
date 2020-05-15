package ru.fssprus.r82.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.ImageIcon;

import ru.fssprus.r82.swing.utils.MessageBox;

/**
 * @author Chernyj Dmitry
 *
 */
public class Utils {

	private static final double MAX_PERCENT = 100.0;

	public static int countMinimumCommonQuestionsForLevel(String level) {
		try {
		int amount = Integer.parseInt(ApplicationConfiguration.getItem(level + ".num"));
		int commons = Integer.parseInt(ApplicationConfiguration.getItem(level + ".common.percent"));
		
		return (int) (amount * (commons / MAX_PERCENT));
		
		} catch(Exception e) {
			MessageBox.showAppConfigFileNotFoundOrCorrupted(null);
		}
		return 0;
	}
	
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

	//TODO REMOVE THIS
	public static int countMinimumCommonQuestionsForLevel() {
		return 5;
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

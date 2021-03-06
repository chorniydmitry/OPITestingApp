package ru.fssprus.r82.ui.utils;

/**
 * @author Chernyj Dmitry
 *
 */
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import ru.fssprus.r82.utils.AppConstants;

public class MessageBox extends JOptionPane {
	private static final long serialVersionUID = -2426219006577522299L;

	private static final String READY = "Готово!";
	private static final String ERROR_VALIDATION_HEADER = "ПРОБЛЕМЫ С ВАЛИДАЦИЕЙ ФОРМЫ";
	private static final String ERROR_COMMON_HEADER = "ОШИБКА";
	
	private static final String ERROR_FILE_NOT_LOAD = "Ошибка при открытии файла! Если файл выбран верно и содержит\n"
			+ "набор вопросов, возможно этот файл не соответствует шаблону! Загрузить шаблон можно по нажатию на\n"
			+ "соответствующую кнопку внизу экрана!";
	private static final String ERROR_WRONG_SPEC_SPECIFIED = "Не верно указано название набора вопросов!";
	private static final String PASSWORD_INPUT_TITLE = "Ввод пароля";
	private static final String PASSWORD_INPUT_MESSAGE = "Введите пароль:";
	private static final String CONFIRM_QUESTION_DELETE = "Будет удалена выбранная запись из базы данных. Продолжить?";
	private static final String CONFIRM_SET_DELETE = "Будет удален выбранный набор, а так же связанные с ним вопросы \nи ссылки вхождения данного набора в тесты(если такие имеются). Продолжить?";
	private static final String TABLE_EDITING_ENTRY_NOT_SAVED = "Редактируемая запись в таблице не была сохранена, сохранить запись"
			+ "перед продолжением?";
	private static final String ERROR_WRONG_QUESTION_SPECIFIED = "Не верно заполнен вопрос для добавления в базу данных!\n\n"
			+ "ТЕКСТ ВОПРОСА: " + "Длина текста вопроса должна быть больше " + AppConstants.QUESTION_TEXT_MIN_LENGTH
			+ " символов.\n\n" + "СПИСОК ОТВЕТОВ:\n" + " - минимальное количество ответов должно быть больше или равно "
			+ AppConstants.MIN_ANSWERS_AMOUNT + ";\n"
			+ " - как минимум один из ответов должен быть помечен как верный; \n"
			+ " - не заполненный вопрос не должен быть помечен как верный.\n\n" + "СЛОЖНОСТЬ ВОПРОСА: "
			+ "Для вопроса должна быть выбрана как минимум одна сложность.\n\n" + "СПЕЦИАЛИЗАЦИЯ ВОПРОСА: "
			+ "Специализация должна быть выбрана из списка или заполнена.\n\n"
			+ "---------------------------------------\n"
			+ "Отредактируйте введенные данные, чтобы они удовлетворяли условиям, описанным выше"
			+ " и повторите попытку!";

	private static final Object ERROR_APPCONFIG_FILE_PROBLEMS = "ОПЕРАЦИЯ ПРЕРВАНА!\nФайл application.configuration "
			+ "не найден, поврежден или имеет не правильный формат. Для корректной работы приложения "
			+ "проверьте файл application.configurationь и приведите его к корректному формату или "
			+ "загрузите стандартный файл application.configuration с настройками\"по-умолчанию\" "
			+ "в корневую папку приложения, заменив существующий";

	private static final String ERROR_WRONG_QUESTION = "Не правильный вопрос для ответа!";

	private static final String ERROR_WRONG_ANSWERS_SELECTED = "Выбраны не правильные ответы или ответы, не принадлежащие этому вопросу!";

	public static void showReadyDialog(Component component) {
		MessageBox.showMessageDialog(component, READY, null, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showFileNotLoadedErrorDialog(Component component) {
		MessageBox.showMessageDialog(component, ERROR_FILE_NOT_LOAD, null, JOptionPane.ERROR_MESSAGE);

	}

	public static void showWrongSpecSpecifiedErrorDialog(Component component) {
		MessageBox.showMessageDialog(component, ERROR_WRONG_SPEC_SPECIFIED, null, JOptionPane.ERROR_MESSAGE);
	}

	public static String showInputPasswordDialog(Component component) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(PASSWORD_INPUT_MESSAGE);
		JPasswordField pass = new JPasswordField(20);
		panel.add(label);
		panel.add(pass);
		String[] options = new String[] { "OK", "Отменить" };

		int option = JOptionPane.showOptionDialog(component, panel, PASSWORD_INPUT_TITLE, JOptionPane.NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (option == 0) {
			char[] password = pass.getPassword();
			return new String(password);
		}
		return "";
	}

	public static int showEntryEditingNotSavedDialog(Component component) {
		return JOptionPane.showOptionDialog(component, TABLE_EDITING_ENTRY_NOT_SAVED, null, JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Да", "Нет", "Отмена" }, "Да");
	}
	
	public static boolean showConfirmDialog(Component component, String message) {
		int result = JOptionPane.showOptionDialog(component, message, null, JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Да", "Нет" }, "Да");

		if (result == JOptionPane.YES_OPTION)
			return true;
		return false;
	}

	public static boolean showConfirmQuestionDelete(Component component) {
		return showConfirmDialog(component, CONFIRM_QUESTION_DELETE);
	}
	
	public static boolean showConfirmSetDelete(Component component) {
		return showConfirmDialog(component, CONFIRM_SET_DELETE);
	}

	public static void showWrongQuestionSpecifiedErrorDialog(Component component) {
		MessageBox.showMessageDialog(component, ERROR_WRONG_QUESTION_SPECIFIED, null, JOptionPane.ERROR_MESSAGE);
	}

	public static void showAppConfigFileNotFoundOrCorrupted(Component component) {
		MessageBox.showMessageDialog(component, ERROR_APPCONFIG_FILE_PROBLEMS, null, JOptionPane.ERROR_MESSAGE);
	}

	public static void showWrongQuestionToAnswerErrorMessage(Component component) {
		MessageBox.showMessageDialog(component, ERROR_WRONG_QUESTION, null, JOptionPane.ERROR_MESSAGE);
	}

	public static void showWrongAnswerListForQuestionErrorMessage(Component component) {
		MessageBox.showMessageDialog(component, ERROR_WRONG_ANSWERS_SELECTED, null, JOptionPane.ERROR_MESSAGE);
	}

	public static void showValidationFaildMessage(Component component, String errorMessage) {
		MessageBox.showMessageDialog(component, errorMessage, ERROR_VALIDATION_HEADER, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showErrorMessage(Component component, String errorMessage) {
		MessageBox.showMessageDialog(component, errorMessage, ERROR_COMMON_HEADER, JOptionPane.ERROR_MESSAGE);

	}
	
	public static void showDefaultValidationFailedErrorMessage(Component component, ArrayList<String> violations) {
		String errorMessage = "";
		for(String viol: violations)
			errorMessage += viol + "\n";
		
		MessageBox.showMessageDialog(component, errorMessage, null, JOptionPane.ERROR_MESSAGE);

	}
}

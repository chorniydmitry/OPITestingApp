package ru.fssprus.r82.utils;

/**
 * @author Chernyj Dmitry
 *
 */
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AppConstants {
	// Константы разделов приложения
	public static final int SECTIONS_AMOUNT = 8;

	public static final int DEFAULT_OFFSET = 10;

	public static final String QUESTION_LOAD_ICON = "/questionload.png";
	public static final String ADMIN_ICON = "/admin.png";
	public static final String CONFIG_ICON = "/config.png";
	public static final String TESTCONSTRUCTOR_ICON = "/config.png";
	public static final String NEW_TEST_ICON = "/newtest.png";
	public static final String MANAGE_PASSWORDS_ICON = "/password.png";
	public static final String QUESTION_EDIT_ICON = "/questionedit.png";
	public static final String RESULTING_ICON = "/result.png";
	public static final String STATISTICS_ICON = "/statistics.png";
	public static final String TEST_ICON = "/quizz.png";
	public static final String WRONG_ANSWERS_ICON = "/wronganswers.png";
	public static final String EXIT_ICON = "/exit.png";

	public static final String QUESTION_LOAD_DIALOG = "LoadingQuestionSetDialog";
	public static final String ADMIN_DIALOG = "AdminDialog";
	public static final String CONFIG_DIALOG = "ConfigDialog";
	public static final String TESTCONSTRUCTOR_DIALOG = "TestConstructorDialog";
	public static final String NEW_TEST_DIALOG = "NewTestDialog";
	public static final String MANAGE_PASSWORDS_DIALOG = "PasswordManageDialog";
	public static final String QUESTION_EDIT_DIALOG = "QuestionListDialog";
	public static final String RESULTING_DIALOG = "ResultingDialog";
	public static final String STATISTICS_DIALOG = "StatisticsDialog";
	public static final String TEST_DIALOG = "TestDialog";
	public static final String WRONG_ANSWERS_DIALOG = "WrongAnswersDialog";

	public static final String[] DIALOG_WITH_PASSWORDS_ARR = { NEW_TEST_DIALOG, STATISTICS_DIALOG, ADMIN_DIALOG,
			CONFIG_DIALOG, QUESTION_EDIT_DIALOG, QUESTION_LOAD_DIALOG, TESTCONSTRUCTOR_DIALOG,
			MANAGE_PASSWORDS_DIALOG };

	public static final String QUESTION_LOAD_TEXT = "Импорт вопросов";
	public static final String ADMIN_TEXT = "Администрирование";
	public static final String CONFIG_TEXT = "Конфигурация";
	public static final String TESTCONSTRUCTOR_TEXT = "Конструктор тестов";
	public static final String NEW_TEST_TEXT = "Новый тест";
	public static final String MANAGE_PASSWORDS_TEXT = "Редактор паролей";
	public static final String QUESTION_EDIT_TEXT = "Редактор вопросов";
	public static final String RESULTING_TEXT = "Результаты тестирования";
	public static final String STATISTICS_TEXT = "Статистика";
	public static final String TEST_TEXT = "Тестирование";
	public static final String WRONG_ANSWERS_TEXT = "Не правильные ответы";

	public static final String[] DIALOG_WITH_PASSWORDS_TEXT_ARR = { TEST_TEXT, STATISTICS_TEXT, ADMIN_TEXT, CONFIG_TEXT,
			QUESTION_EDIT_TEXT, QUESTION_LOAD_TEXT, TESTCONSTRUCTOR_TEXT, MANAGE_PASSWORDS_TEXT };

	// Размеры окон
	public static final int DIALOG_CONFIG_WIDTH = 800;
	public static final int DIALOG_CONFIG_HEIGHT = 600;

	public static final int DIALOG_LOADING_QUESTION_SET_WIDTH = 800;
	public static final int DIALOG_LOADING_QUESTION_SET_HEIGHT = 600;

	public static final int DIALOG_QUESTION_LIST_WIDTH = 1000;
	public static final int DIALOG_QUESTION_LIST_HEIGHT = 700;

	public static final int DIALOG_QUESTION_EDIT_WIDTH = 800;
	public static final int DIALOG_QUESTION_EDIT_HEIGHT = 768;

	public static final int DIALOG_MANAGE_PASSWORDS_WIDTH = 600;
	public static final int DIALOG_MANAGE_PASSWORDS_HEIGHT = 400;

	public static final int DIALOG_TEST_WIDTH = 1000;
	public static final int DIALOG_TEST_HEIGHT = 700;

	public static final int DIALOG_WRONG_ANSWERS_WIDTH = 800;
	public static final int DIALOG_WRONG_ANSWERS_HEIGHT = 700;

	public static final int DIALOG_NEW_TEST_WIDTH = 800;
	public static final int DIALOG_NEW_TEST_HEIGHT = 300;

	public static final int DIALOG_ADMIN_WIDTH = 500;
	public static final int DIALOG_ADMIN_HEIGHT = 300;

	public static final int DIALOG_STATISTICS_WIDTH = 800;
	public static final int DIALOG_STATISTICS_HEIGHT = 600;

	public static final int DIALOG_RESULTING_WIDTH = 600;
	public static final int DIALOG_RESULTING_HEIGHT = 300;

	public static final int DIALOG_TEST_CONSTRUCTOR_WIDTH = 900;
	public static final int DIALOG_TEST_CONSTRUCTOR_HEIGHT = 700;

	// Константы шрифтов
	public static final Font RESULTDIALOG_TEXT_FONT = new Font("Courier New", Font.BOLD, 18);
	public static final Font TESTDIALOG_HEADER_FONT = new Font("Courier New", Font.PLAIN, 20);
	public static final Font TESTDIALOG_QUESTION_FONT = new Font("Courier New", Font.PLAIN, 20);
	public static final Font TESTDIALOG_ITEMS_FONT = new Font("Courier New", Font.PLAIN, 16);
	public static final Font TOP_PANELS_TEXT_FONT = new Font("Courier New", Font.PLAIN, 22);

	// public static final Font RESULTDIALOG_TEXT_FONT = new Font("Times New Roman",
	// Font.BOLD, 18);
	// public static final Font TESTDIALOG_HEADER_FONT = new Font("Tahoma",
	// Font.PLAIN, 20);
	// public static final Font TESTDIALOG_QUESTION_FONT = new Font("Times New
	// Roman", Font.PLAIN, 20);
	// public static final Font TESTDIALOG_ITEMS_FONT = new Font("Times New Roman",
	// Font.PLAIN, 16);
	// public static final Font TOP_PANELS_TEXT_FONT = new Font("Tahoma",
	// Font.PLAIN, 22);

	// Размеры компонентов
	public static final int MAINFRAME_BTN_WIDTHS = 250;
	public static final int MAINFRAME_BTN_HEIGHTS = 60;

	public static final int TESTDIALOG_LBL_QUESTION_INFO_HEIGHT = 30;
	public static final int TESTDIALOG_PNLQNA_RIGID_AREA_WIDTH = 0;
	public static final int TESTDIALOG_PNLQNA_RIGID_AREA_HEIGHT = 30;
	public static final int WADIALOG_TAWRONGS_HEIGHT_PADDING = 60;

	public static final int TOP_PANEL_HEIGHT = 60;

	// QuestionListDialog константы
	public static final int QLDIALOG_TF_SIZE = 25;

	public static final int[] QLDIALOG_TABLE_COL_WIDTHS_ARR = { 40, 750, 200 };
	public static final String[] QLDIALOG_TABLE_COL_CAPTIONS_ARR = { "id", "Формулировка", "Набор вопросов" };

	public static final String ACCP_SPEC_NAMES_PROTOTYPE_DISPLAY_VALUE = "XXXXXXXXXXXXXXXXXXXX";

	public static final String QLDIALOG_PNL_QEDIT_BORDER_TITLE_RU = "Просмотр и редактирование вопроса";

	// StatisticsDialog константы
	public static final int[] STATDIALOG_TABLE_COL_WIDTHS_ARR = { 20, 250, 125, 125, 50, 50, 50, 125 };
	public static final String[] STATDIALOG_TABLE_COL_CAPTIONS_ARR = { "#", "ФИО пользователя", "Набор вопросов",
			"Дата теста", "Время теста", "Верных ответов", "%", "Результат" };
	public static final SimpleDateFormat STATDIALOG_TABLE_DATE_FORMAT = new SimpleDateFormat("dd MMMMM yyyy HH:mm",
			new Locale("ru", "RU"));

	// TestConstructorDialog константы
	public static final int[] TESTCONSTRUCTOR_TABLELIST_COL_WIDTHS_ARR = { 20, 450, 150, 150, 100 };
	public static final String[] TESTCONSTRUCTOR_TABLELIST_COL_CAPTIONS_ARR = { "#", "Название теста",
			"Время на тест(сек)", "Всего вопросов", "Тест активен?" };

	public static final int[] TESTCONSTRUCTOR_TABLECURRENT_COL_WIDTHS_ARR = { 450, 150 };
	public static final String[] TESTCONSTRUCTOR_TABLECURRENT_COL_CAPTIONS_ARR = { "Название набора",
			"Количество вопросов" };

	// Прочие константы
	public static final String CONTENT_TYPE_HTML = "text/html";
	public static final String PASSWORD_IS_SET_DEFAULT_MASK = "******";
	public static final int TABLE_ROWS_LIMIT = 20;
	public static final int MAX_ANSWERS_AMOUNT = 5;
	public static final int MIN_ANSWERS_AMOUNT = 2;
	public static final int QUESTION_TEXT_MIN_LENGTH = 5;
	public static final int MINIMUM_QUESTIONS_TO_INIT_TEST = 30;
	public static final Color TABLE_SELECTION_COLOR = new Color(0x03a06c);
	// new Color(0x0000ff);
	public static final Color FSSP_COLOR = new Color(0x03a06c);
	public static final Color TOP_PANELS_TEXT_FONT_COLOR = new Color(0xffffff);
	public static final int NO_ROW_SELECTED = -1;
	public static final int NO_INDEX_SELECTED = -1;

	public static final int NO_SQL_LIMIT_START_SPECIFIED = -1;
	public static final int NO_SQL_LIMIT_MAX_SPECIFIED = -1;

	public static final String VALIDATION_ANSWER_NOT_EMPTY = "Не заполнен ответ!";
	public static final String VALIDATION_ANSWER_WRONG_SIZE = "Не верная длина ответа (должна быть от 2 до 2048 символов)";

	public static final String VALIDATION_QUESTION_TITLE_EMPTY = "Не заполнена Формулировка вопроса!";
	public static final String VALIDATION_QUESTION_TITLE_SIZE = "Не верная длина вопроса (должна быть от 2 до 2048 символов)";

	public static final String VALIDATION_SPECIFICATION_TITLE_EMPTY = "Не заполнено название специализации!";
	public static final String VALIDATION_SPECIFICATION_TITLE_SIZE = "Не верная длина названия специализации (должна быть от 2 до 2048 символов)";

	public static final String DIALOG_LOADING_QUEST_SET_ABOUT_INFO = "<html>" + "<style> body { margin: 10; "
			+ "				  text-align: justify}" + "   	   i, b { color : green}" + "</style>"
			+ "<body>Данная форма предназначена для " + "импорта наборов вопросов в базу данных приложения." + "<br />"
			+ "Для импорта вопросов необходимо создать набор вопросов в базе данных, с помошью панели "
			+ "<i>Создание нового набора вопросов</i>, после чего выгрузить набор вопросов из файла, соответствующего "
			+ "шаблону, с помощью панели <i>Добавление вопросов в набор из файла</i>. Можно загружать вопросы в набор "
			+ "дополнительно(из другого или дополненного файла), при этом дубликаты вопросов добавляться не будут."
			+ "<br />" + "<br />"
			+ "Также в нижней части экрана расположена кнопка для выгрузки шаблона файла списка вопросов, которые "
			+ "принимает программа." + "<br />" + "Файл содержит следующие столбцы:" + "<ul>"
			+ "<li>в столбце <b>id</b> указывается уникальный id вопроса, поле можно оставить пустым;</li>"
			+ "<li>в столбце <b>Номер</b> указывается порядковый номер вопроса, значение может быть любым, поле можно "
			+ "оставить пустым;</li>" + "<li>в столбце <b>Формулировка вопроса</b> указывается текст вопроса."
			+ "<br />" + "<br />"
			+ "Далее чередуются столбцы <b>Ответ1</b>..<b>Ответ5</b>, <b>Правильный1?</b>..<b>Правильный5?</b>:"
			+ "<li>в полях <b>Ответ1</b>..<b>Ответ5</b> указываются возможные варианты ответа на данный вопрос</li>"
			+ "<li>в полях <b>Правильный1?</b>..<b>Правильный5?</b> указывается правильный ли это ответ на поставленный "
			+ "вопрос, необходимо указывать только значения 1 или 0, где 1 - правильный, 0 - не правильный.</li>"
			+ "</ul></body><html>";

	public static final String DIALOG_PASSOWRD_MANAGE_ABOUT_INFO = "<html>Назначение паролей для следующих разделов "
			+ "программы" + "<br/>" + "оставьте пустым, если пароль для доступа к разделу не требуется:</html>";

	/*
	 * VALIDATION CONSTANTS
	 */

	/*
	 * FOR TEST CONSTRUCTOR FORM
	 */

	public static final String VALID_TEST_NAME_NOT_UNIQUE = "Тест с таким названием уже зарегистрирован в базе данных, поле ИМЯ ТЕСТА должно содержать уникальное значение!";
	public static final String VALID_QUESTIONSET_HAS_DUPLICATES = "В списке наборов вопросов содержатся дубликаты!";
	public static final String VALID_TOTALAMOUNTOFQUESTIONS_WRONG = "Общее количество вопросов не совпадает с суммой вопросов по наборам!";

	/*
	 * FOR TEST ENTITY
	 */
	public static final int VALID_TEST_QUESTIONS_AMOUNT_MIN = 10;
	public static final int VALID_TEST_QUESTIONS_AMOUNT_MAX = 1000;
	public static final int VALID_TEST_TIME_MIN = 300;
	public static final int VALID_TEST_TIME_MAX = 18000;

	/*
	 * FOR TESTSET ENTITY
	 */

	public static final int VALID_TESTSET_NAME_SIZE_MIN = 5;

	/*
	 * VALIDATION MESSAGES CONSTANTS
	 */

	/*
	 * FOR QUESTION ENTITY
	 */
	public static final String VALID_QUEST_NAME_NOTNULL = "Поле ФОРМУЛИРОВКА ВОПРОСА НЕ должно быть пустым!";
	public static final String VALID_QUEST_NAME_SIZE = "Поле ФОРМУЛИРОВКА ВОПРОСА должно быть более 5 символов длиной!";
	public static final String VALID_QUEST_ANS_AMOUNT = "Количество заполненных ответов на вопросы должно быть в пределах ["
			+ MIN_ANSWERS_AMOUNT + " ... " + MAX_ANSWERS_AMOUNT + "]";
	public static final String VALID_QUEST_QUESTIONSET_NOTNULL = "Для вопроса не выбран набор вопросов, новый набор вопросов можно предворительно создать в соответствующей форме!";
	public static final String VALID_QUEST_ANSWERS_AMOUNT = "Для вопроса должно быть как минимум " + MIN_ANSWERS_AMOUNT + ", но не более " + MAX_ANSWERS_AMOUNT + " заполненных ответов!";
	public static final String VALID_QUEST_NO_CORRECT_ANSWER = "Для вопроса должен быть выбран по крайней мере один правильный ответ!";
	
	/*
	 * FOR ANSWER ENTITY
	 */
	public static final String VALID_ANSWER_NAME_NOTNULL = "Поле ФОРМУЛИРОВКА ВОПРОСА НЕ должно быть пустым!";
	public static final String VALID_ANSWER_QUEST_NOT_SET = "Для ответа не выбрано вопроса!";

	/*
	 * FOR TEST ENTITY
	 */

	public static final String VALID_TEST_NAME_NOTNULL = "Поле НАЗВАНИЕ ТЕСТА НЕ должно быть пустым!";
	public static final String VALID_TEST_NAME_SIZE = "Поле НАЗВАНИЕ ТЕСТА должно быть более 5 символов длиной!";

	public static final String VALID_TEST_TIME_NOTNULL = "Поле ВРЕМЯ НА ТЕСТ(СЕК.) НЕ должно быть пустым!";
	public static final String VALID_TEST_TIME_RANGE = "Поле ВРЕМЯ НА ТЕСТ(СЕК.), должно содержать значение в пределах"
			+ "[" + AppConstants.VALID_TEST_TIME_MIN + ";" + AppConstants.VALID_TEST_TIME_MAX + "]";

	public static final String VALID_TEST_AMOUNT_NOTNULL = "Поле КОЛИЧЕСТВО ВОПРОСОВ(ОБЩЕЕ) НЕ должно быть пустым!";
	public static final String VALID_TEST_AMOUNT_RANGE = "Поле КОЛИЧЕСТВО ВОПРОСОВ(ОБЩЕЕ), должно содержать значение в пределах"
			+ "[" + AppConstants.VALID_TEST_QUESTIONS_AMOUNT_MIN + ";" + AppConstants.VALID_TEST_QUESTIONS_AMOUNT_MAX
			+ "]";
	public static final String VALID_TEST_TESTSET_SIZE_MIN = "Должен быть выбран хотябы 1 набор вопросов для теста!";

	/*
	 * FOR TESTSET ENTITY
	 */

	public static final String VALID_TESTSET_NAME_NOTNULL = "Поле НАЗВАНИЕ НАБОРА ВОПРОСОВ НЕ должно быть пустым!";
	public static final String VALID_TESTSET_NAME_SIZE = "Поле НАЗВАНИЕ НАБОРА ВОПРОСОВ должно быть более 5 символов длиной!";

	/*
	 * FOR QUESTION SET
	 */
	public static final String VALID_QUESTIONSET_NAME_NOTNULL = "Поле НАЗВАНИЕ НАБОРА НЕ должно быть пустым!";
	public static final String VALID_QUESTIONSET_NAME_SIZE = "длина поля НАЗВАНИЕ НАБОРА не может быть меньше! "
			+ "5 и больше 2048 символов.";
	public static final String VALID_QUESTIONSET_NAME_UNIQUE = "Поле НАЗВАНИЕ НАБОРА должно быть уникальным"
			+ "(в базе данных не должно содержаться записи с таким же значением)!";

}

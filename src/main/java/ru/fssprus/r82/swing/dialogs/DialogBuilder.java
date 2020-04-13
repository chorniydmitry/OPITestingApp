package ru.fssprus.r82.swing.dialogs;

/**
 * @author Chernyj Dmitry
 *
 */
import javax.swing.JFrame;

import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.swing.dialogs.admin.AdminController;
import ru.fssprus.r82.swing.dialogs.admin.AdminDialog;
import ru.fssprus.r82.swing.dialogs.importSet.ImportQuestionSetController;
import ru.fssprus.r82.swing.dialogs.importSet.ImportQuestionSetDialog;
import ru.fssprus.r82.swing.dialogs.newTest.NewTestController;
import ru.fssprus.r82.swing.dialogs.newTest.NewTestDialog;
import ru.fssprus.r82.swing.dialogs.passwordManage.PasswordManageController;
import ru.fssprus.r82.swing.dialogs.passwordManage.PasswordManageDialog;
import ru.fssprus.r82.swing.dialogs.questionEdit.QuestionEditController;
import ru.fssprus.r82.swing.dialogs.questionEdit.QuestionEditDialog;
import ru.fssprus.r82.swing.dialogs.questionList.QuestionListController;
import ru.fssprus.r82.swing.dialogs.questionList.QuestionListDialog;
import ru.fssprus.r82.swing.dialogs.resulting.ResultingController;
import ru.fssprus.r82.swing.dialogs.resulting.ResultingDialog;
import ru.fssprus.r82.swing.dialogs.statistics.StatisticsController;
import ru.fssprus.r82.swing.dialogs.statistics.StatisticsDialog;
import ru.fssprus.r82.swing.dialogs.test.TestController;
import ru.fssprus.r82.swing.dialogs.test.TestDialog;
import ru.fssprus.r82.swing.dialogs.testConstructor.TestConstructorController;
import ru.fssprus.r82.swing.dialogs.testConstructor.TestConstructorDiaolg;
import ru.fssprus.r82.swing.dialogs.wrongAnswers.WrongAnswersController;
import ru.fssprus.r82.swing.dialogs.wrongAnswers.WrongAnswersDialog;
import ru.fssprus.r82.utils.AppConstants;
import ru.fssprus.r82.utils.testingTools.TestingProcess;
import ru.fssprus.r82.utils.testingTools.TestingProcessAnaliser;

public class DialogBuilder {
	private static JFrame parent;

	public static final void setParent(JFrame newParent) {
		if (parent == null)
			parent = newParent;
	}

	public static void showAdminDialog() {
		new AdminController(new AdminDialog(AppConstants.DIALOG_ADMIN_WIDTH, AppConstants.DIALOG_ADMIN_HEIGHT, parent));
	}

	public static void showStatisticsDialog() {
		new StatisticsController(new StatisticsDialog(AppConstants.DIALOG_STATISTICS_WIDTH,
				AppConstants.DIALOG_STATISTICS_HEIGHT, parent));
	}

	public static void showNewTestDialog() {
		new NewTestController(
				new NewTestDialog(AppConstants.DIALOG_NEW_TEST_WIDTH, AppConstants.DIALOG_NEW_TEST_HEIGHT, parent));
	}

	public static void showTestDialog(TestingProcess testingProcess) {
		new TestController(new TestDialog(AppConstants.DIALOG_TEST_WIDTH, AppConstants.DIALOG_TEST_HEIGHT, parent),
				testingProcess);
	}

	public static void showQuestionLoadingSetDialog() {
		new ImportQuestionSetController(new ImportQuestionSetDialog(AppConstants.DIALOG_LOADING_QUESTION_SET_WIDTH,
				AppConstants.DIALOG_LOADING_QUESTION_SET_HEIGHT, parent));
	}

	public static void showQuestionListDialog() {
		new QuestionListController(new QuestionListDialog(AppConstants.DIALOG_QUESTION_LIST_WIDTH,
				AppConstants.DIALOG_QUESTION_LIST_HEIGHT, parent));
	}
	
	public static void showQuestionEditDialog(Question questionToEdit) {
		new QuestionEditController(new QuestionEditDialog(AppConstants.DIALOG_QUESTION_EDIT_WIDTH,
				AppConstants.DIALOG_QUESTION_EDIT_HEIGHT, null),questionToEdit);
	}

	public static void showPasswordManageDialog() {
		new PasswordManageController(new PasswordManageDialog(AppConstants.DIALOG_MANAGE_PASSWORDS_WIDTH,
				AppConstants.DIALOG_MANAGE_PASSWORDS_HEIGHT, parent));
	}

	public static void showWrongAnswersDialog(int wrongAnswersAmount, String text) {
		new WrongAnswersController(new WrongAnswersDialog(AppConstants.DIALOG_WRONG_ANSWERS_WIDTH,
				AppConstants.DIALOG_WRONG_ANSWERS_HEIGHT, parent), wrongAnswersAmount, text);
	}

	public static void showResultingDialog(TestingProcessAnaliser tpAnaliser) {
		new ResultingController(
				new ResultingDialog(AppConstants.DIALOG_RESULTING_WIDTH, AppConstants.DIALOG_RESULTING_HEIGHT, parent),
				tpAnaliser);
	}

	public static void showTestConstructorDialog() {
		new TestConstructorController(new TestConstructorDiaolg(AppConstants.DIALOG_TEST_CONSTRUCTOR_WIDTH,
				AppConstants.DIALOG_TEST_CONSTRUCTOR_HEIGHT, parent));
	}

}

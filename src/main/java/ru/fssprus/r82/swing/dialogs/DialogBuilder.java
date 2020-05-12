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
		new AdminController(new AdminDialog(AppConstants.ADMIN_DIALOG_WIDTH, AppConstants.ADMIN_DIALOG_HEIGHT, parent));
	}

	public static void showImportQuestionSetDialog() {
		new ImportQuestionSetController(new ImportQuestionSetDialog(AppConstants.IMPORTQUESTIONSET_DIALOG_WIDTH,
				AppConstants.IMPORTQUESTIONSET_DIALOG_HEIGHT, parent));
	}

	public static void showNewTestDialog() {
		new NewTestController(
				new NewTestDialog(AppConstants.NEWTEST_DIALOG_WIDTH, AppConstants.NEWTEST_DIALOG_HEIGHT, parent));
	}

	public static void showPasswordManageDialog() {
		new PasswordManageController(new PasswordManageDialog(AppConstants.PASSWORDMANAGE_DIALOG_WIDTH,
				AppConstants.PASSWORDMANAGE_DIALOG_HEIGHT, parent));
	}

	public static void showQuestionEditDialog(Question questionToEdit) {
		new QuestionEditController(new QuestionEditDialog(AppConstants.QUESTIONEDIT_DIALOG_WIDTH,
				AppConstants.QUESTIONEDIT_DIALOG_HEIGHT, parent), questionToEdit);
	}

	public static void showQuestionListDialog() {
		new QuestionListController(new QuestionListDialog(AppConstants.QUESTIONLIST_DIALOG_WIDTH,
				AppConstants.QUESTIONLIST_DIALOG_HEIGHT, parent));
	}

	public static void showStatisticsDialog() {
		new StatisticsController(new StatisticsDialog(AppConstants.STATISTICS_DIALOG_WIDTH,
				AppConstants.STATISTICS_DIALOG_HEIGHT, parent));
	}

	public static void showTestDialog(TestingProcess testingProcess) {
		new TestController(new TestDialog(AppConstants.TEST_DIALOG_WIDTH, AppConstants.TEST_DIALOG_HEIGHT, parent),
				testingProcess);
	}

	public static void showWrongAnswersDialog(int wrongAnswersAmount, String text) {
		new WrongAnswersController(new WrongAnswersDialog(AppConstants.WRONGANSWERS_DIALOG_WIDTH,
				AppConstants.WRONGANSWERS_DIALOG_HEIGHT, parent), wrongAnswersAmount, text);
	}

	public static void showResultingDialog(TestingProcessAnaliser tpAnaliser) {
		new ResultingController(
				new ResultingDialog(AppConstants.RESULTING_DIALOG_WIDTH, AppConstants.RESULTING_DIALOG_HEIGHT, parent),
				tpAnaliser);
	}

	public static void showTestConstructorDialog() {
		new TestConstructorController(new TestConstructorDiaolg(AppConstants.TESTCONSTRUCTOR_DIALOG_WIDTH,
				AppConstants.TESTCONSTRUCTOR_DIALOG_HEIGHT, parent));
	}

}

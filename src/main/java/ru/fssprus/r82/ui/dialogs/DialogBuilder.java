package ru.fssprus.r82.ui.dialogs;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.ADMIN_DIALOG_HEIGHT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.ADMIN_DIALOG_WIDTH;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.ADMIN_ICON;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.ADMIN_TEXT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.IMPORTQUESTIONSET_DIALOG_HEIGHT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.IMPORTQUESTIONSET_DIALOG_WIDTH;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.IMPORTQUESTIONSET_ICON;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.IMPORTQUESTIONSET_TEXT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.NEWTEST_DIALOG_HEIGHT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.NEWTEST_DIALOG_WIDTH;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.NEWTEST_ICON;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.NEWTEST_TEXT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.PASSWORDMANAGE_DIALOG_HEIGHT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.PASSWORDMANAGE_DIALOG_WIDTH;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.PASSWORDMANAGE_ICON;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.PASSWORDMANAGE_TEXT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.QUESTIONEDIT_DIALOG_HEIGHT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.QUESTIONEDIT_DIALOG_WIDTH;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.QUESTIONEDIT_ICON;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.QUESTIONEDIT_TEXT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.QUESTIONLIST_DIALOG_HEIGHT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.QUESTIONLIST_DIALOG_WIDTH;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.QUESTIONLIST_ICON;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.QUESTIONLIST_TEXT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.RESULTING_DIALOG_HEIGHT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.RESULTING_DIALOG_WIDTH;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.RESULTING_ICON;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.RESULTING_TEXT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.STATISTICS_DIALOG_HEIGHT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.STATISTICS_DIALOG_WIDTH;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.STATISTICS_ICON;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.STATISTICS_TEXT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.TESTCONSTRUCTOR_DIALOG_HEIGHT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.TESTCONSTRUCTOR_DIALOG_WIDTH;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.TESTCONSTRUCTOR_ICON;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.TESTCONSTRUCTOR_TEXT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.TEST_DIALOG_HEIGHT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.TEST_DIALOG_WIDTH;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.TEST_ICON;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.TEST_TEXT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.WRONGANSWERS_DIALOG_HEIGHT;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.WRONGANSWERS_DIALOG_WIDTH;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.WRONGANSWERS_ICON;
import static ru.fssprus.r82.utils.AppConstants.Dialogs.WRONGANSWERS_TEXT;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Chernyj Dmitry
 *
 */
import javax.swing.JFrame;

import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.ui.dialogs.admin.AdminController;
import ru.fssprus.r82.ui.dialogs.admin.AdminDialog;
import ru.fssprus.r82.ui.dialogs.importSet.ImportQuestionSetController;
import ru.fssprus.r82.ui.dialogs.importSet.ImportQuestionSetDialog;
import ru.fssprus.r82.ui.dialogs.newTest.NewTestController;
import ru.fssprus.r82.ui.dialogs.newTest.NewTestDialog;
import ru.fssprus.r82.ui.dialogs.passwordManage.PasswordManageController;
import ru.fssprus.r82.ui.dialogs.passwordManage.PasswordManageDialog;
import ru.fssprus.r82.ui.dialogs.questionEdit.QuestionEditController;
import ru.fssprus.r82.ui.dialogs.questionEdit.QuestionEditDialog;
import ru.fssprus.r82.ui.dialogs.questionList.QuestionListController;
import ru.fssprus.r82.ui.dialogs.questionList.QuestionListDialog;
import ru.fssprus.r82.ui.dialogs.resulting.ResultingController;
import ru.fssprus.r82.ui.dialogs.resulting.ResultingDialog;
import ru.fssprus.r82.ui.dialogs.statistics.StatisticsController;
import ru.fssprus.r82.ui.dialogs.statistics.StatisticsDialog;
import ru.fssprus.r82.ui.dialogs.test.TestController;
import ru.fssprus.r82.ui.dialogs.test.TestDialog;
import ru.fssprus.r82.ui.dialogs.testConstructor.TestConstructorController;
import ru.fssprus.r82.ui.dialogs.testConstructor.TestConstructorDiaolg;
import ru.fssprus.r82.ui.dialogs.wrongAnswers.WrongAnswersController;
import ru.fssprus.r82.ui.dialogs.wrongAnswers.WrongAnswersDialog;
import ru.fssprus.r82.utils.testingTools.TestingProcess;
import ru.fssprus.r82.utils.testingTools.TestingProcessAnaliser;

public class DialogBuilder {
	private static JFrame parent;

	public static final void setParent(JFrame newParent) {
		if (parent == null)
			parent = newParent;
	}

	public static void showAdminDialog() {
		new AdminController(
				new AdminDialog(
						ADMIN_DIALOG_WIDTH, 
						ADMIN_DIALOG_HEIGHT, 
						ADMIN_TEXT,
						Paths.get(ADMIN_ICON),
						parent));
	}

	public static void showImportQuestionSetDialog() {
		new ImportQuestionSetController(
				new ImportQuestionSetDialog(
					IMPORTQUESTIONSET_DIALOG_WIDTH,
					IMPORTQUESTIONSET_DIALOG_HEIGHT, 
					IMPORTQUESTIONSET_TEXT,
					Paths.get(IMPORTQUESTIONSET_ICON),
					parent));
	}

	public static void showNewTestDialog() {
		new NewTestController(
				new NewTestDialog(
					NEWTEST_DIALOG_WIDTH, 
					NEWTEST_DIALOG_HEIGHT, 
					NEWTEST_TEXT,
					Paths.get(NEWTEST_ICON),
					parent));
	}

	public static void showPasswordManageDialog() {
		new PasswordManageController(
				new PasswordManageDialog(
					PASSWORDMANAGE_DIALOG_WIDTH,
					PASSWORDMANAGE_DIALOG_HEIGHT,
					PASSWORDMANAGE_TEXT,
					Paths.get(PASSWORDMANAGE_ICON),
					parent));
	}

	public static void showQuestionEditDialog(Question questionToEdit, Map<String, String> filters) {
		new QuestionEditController(
				new QuestionEditDialog(
					QUESTIONEDIT_DIALOG_WIDTH,
					QUESTIONEDIT_DIALOG_HEIGHT, 
					QUESTIONEDIT_TEXT,
					Paths.get(QUESTIONEDIT_ICON),
					parent), 
				questionToEdit,
				filters);
	}

	public static void showQuestionListDialog(Map<String, String> filters) {
				new QuestionListController(new QuestionListDialog(
					QUESTIONLIST_DIALOG_WIDTH,
					QUESTIONLIST_DIALOG_HEIGHT, 
					QUESTIONLIST_TEXT,
					Paths.get(QUESTIONLIST_ICON),
					parent), filters);
	}
	

	public static void showStatisticsDialog() {
		new StatisticsController(
				new StatisticsDialog(
					STATISTICS_DIALOG_WIDTH,
					STATISTICS_DIALOG_HEIGHT, 
					STATISTICS_TEXT,
					Paths.get(STATISTICS_ICON),
					parent));
	}

	public static void showTestDialog(TestingProcess testingProcess) {
		new TestController(
				new TestDialog(
					TEST_DIALOG_WIDTH, 
					TEST_DIALOG_HEIGHT, 
					TEST_TEXT,
					Paths.get(TEST_ICON),
					parent),
					testingProcess);
	}

	public static void showWrongAnswersDialog(int wrongAnswersAmount, String text) {
		new WrongAnswersController(
				new WrongAnswersDialog(
					WRONGANSWERS_DIALOG_WIDTH,
					WRONGANSWERS_DIALOG_HEIGHT,
					WRONGANSWERS_TEXT,
					Paths.get(WRONGANSWERS_ICON),
					parent),				
				wrongAnswersAmount, text);
	}

	public static void showResultingDialog(TestingProcessAnaliser tpAnaliser) {
		new ResultingController(
				new ResultingDialog(
						RESULTING_DIALOG_WIDTH, 
						RESULTING_DIALOG_HEIGHT,
						RESULTING_TEXT,
						Paths.get(RESULTING_ICON),
						parent),
				tpAnaliser);
	}

	public static void showTestConstructorDialog() {
		new TestConstructorController(
				new TestConstructorDiaolg(
						TESTCONSTRUCTOR_DIALOG_WIDTH,
						TESTCONSTRUCTOR_DIALOG_HEIGHT, 
						TESTCONSTRUCTOR_TEXT,
						Paths.get(TESTCONSTRUCTOR_ICON),						
						parent));
	}
	
//	public static void showUIDesignerDialog() {
//		new UIDesignerController(
//				new UIDesignerDialog(
//						UIDESIGNER_DIALOG_WIDTH,
//						UIDESIGNER_DIALOG_HEIGHT,
//						UIDESIGNER_TEXT,
//						Paths.get(UIDESIGNER_ICON)
//						parent));
//	}

}

package ru.fssprus.r82.swing.dialogs.importSet;

/**
 * @author Chernyj Dmitry
 *
 */
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.service.QuestionService;
import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.service.TestService;
import ru.fssprus.r82.swing.dialogs.CommonController;
import ru.fssprus.r82.swing.utils.MessageBox;
import ru.fssprus.r82.utils.AppConstants;
import ru.fssprus.r82.utils.spreadsheet.SpreadSheetParser;
import ru.fssprus.r82.utils.spreadsheet.SpreadsheetFileChooser;

public class ImportQuestionSetController extends CommonController<ImportQuestionSetDialog> {
	private File testFile;

	public ImportQuestionSetController(ImportQuestionSetDialog dialog) {
		super(dialog);
		init();
	}
	
	private void init() {
		dialog.getBtnImportQuestionsSet().setEnabled(false);
		loadTfSpecNames();
		
		dialog.getTfNameOfNewTest().setText("");
		dialog.getTfFilePath().setText("");
	}
	
	private void loadTfSpecNames() {
		QuestionSetService setService = new QuestionSetService();

		ArrayList<String> keywords = new ArrayList<String>();
		setService.getAll().forEach((n) -> keywords.add(n.getName()));

		keywords.forEach((n) -> dialog.getCbSetName().addItem(n));
	}

	@Override
	protected void setListeners() {
		dialog.getBtnImportQuestionsSet().addActionListener(listener -> doImportQuestionSet());
		dialog.getBtnOpenTextFile().addActionListener(listener -> doOpenTestFile());
		dialog.getBtnSaveNewSet().addActionListener(listener -> doSaveNewSet());
		dialog.getBtnLoadSetFileTemplate().addActionListener(listener -> doLoadSetFileTemplate());
		dialog.getCbSetName().addActionListener(listener -> doSwitchQuestionSet());
		dialog.getBtnDeleteSet().addActionListener(listener -> doDeleteSet());
	}
	
	private void doDeleteSet() {
		MessageBox.showConfirmSetDelete(dialog);
	}

	private void doSwitchQuestionSet() {
		QuestionSet qSet = new QuestionSetService().getUniqueByName(String.valueOf(dialog.getCbSetName().getSelectedItem()));

		int questionsInSet = new QuestionService().countByQuestionSet(qSet);
		
		dialog.getLblTotalQuestionsInSetVal().setText(String.valueOf(questionsInSet));
		dialog.getLblTimesInTestVal().setText(String.valueOf(new TestService().countByQuestionSet(qSet)));
		
	}

	private void doLoadSetFileTemplate() {
		try {
			SpreadsheetFileChooser chooser = new SpreadsheetFileChooser();
			File fileToSave = chooser.selectSpreadSheetFileToSave();
			
			Path source = Paths.get(ImportQuestionSetController.class.getResource("/set_template.xlsx").toURI());
		    Path target = fileToSave.toPath();
		    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException | URISyntaxException e) {
			//TODO вывести в messagebox
			e.printStackTrace();
			
		}
	}

	private void doSaveNewSet() {
		ArrayList<String> violations = validateSetSaving();
		
		if(violations.size() > 0) {
			MessageBox.showDefaultValidationFailedErrorMessage(dialog, violations);
			return;
		}
		
		QuestionSetService qsService = new QuestionSetService();
		QuestionSet qSet = new QuestionSet();
		qSet.setName(dialog.getTfNameOfNewTest().getText());
		qsService.save(qSet);
		
		init();

	}
	
	private ArrayList<String> validateSetSaving() {
		ArrayList<String> violations = new ArrayList<>();
		
		String setName = dialog.getTfNameOfNewTest().getText();
		if(setName == null || setName.length() == 0)
			violations.add(AppConstants.VALID_QUESTIONSET_NAME_NOTNULL);
		
		if(checkIfSetExists(setName))
			violations.add(AppConstants.VALID_QUESTIONSET_NAME_UNIQUE);
			
		QuestionSet qSet = new QuestionSet();
		qSet.setName(setName);
		
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<QuestionSet>> consViolations = validator.validate(qSet);
		
		for (ConstraintViolation<QuestionSet> constraintViolation : consViolations) {
			violations.add(constraintViolation.getMessage());
		}
		
		return violations;
	}


	private boolean checkIfSetExists(String name) {
		QuestionSetService qsService = new QuestionSetService();
		return qsService.getByName(name).size() > 0 ? true : false;
	}

	private void doOpenTestFile() {
		SpreadsheetFileChooser chooser = new SpreadsheetFileChooser();
		testFile = chooser.selectSpreadSheetFileToOpen();
		if (testFile != null)
			try {
				dialog.getTfFilePath().setText(testFile.getCanonicalFile().getPath());
				dialog.getBtnImportQuestionsSet().setEnabled(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	private void doImportQuestionSet() {
		dialog.getBtnImportQuestionsSet().setEnabled(false);
		HashSet<Question> questionsParsed = getQuestionsParsed();
		if(!validateSetFileOpenning() || questionsParsed == null)
			return;

		saveQuestionSetToDB(questionsParsed);

		MessageBox.showReadyDialog(dialog);
	}

	private boolean validateSetFileOpenning() {
		if (!validateFile()) {
			MessageBox.showFileNotLoadedErrorDialog(dialog);
			dialog.getTfFilePath().requestFocus();
			return false;
		}

		if (!validateSpecTf()) {
			MessageBox.showWrongSpecSpecifiedErrorDialog(dialog);
			dialog.getCbSetName().requestFocus();
			return false;
		}
		
		return true;
	}
	
	//TODO
	private boolean validateFile() {
		if (testFile == null || dialog.getTfFilePath().getText().isEmpty()
				|| ((!dialog.getTfFilePath().getText().toUpperCase().endsWith(".ODS"))
						&& (!dialog.getTfFilePath().getText().toUpperCase().endsWith(".XLSX")))) {
			return false;

		}
		return true;
	}
	//TODO
	private boolean validateSpecTf() {
		if (dialog.getCbSetName().getSelectedItem().toString().isEmpty())
			return false;
		return true;
	}

	/**
	 * @return
	 */
	private HashSet<Question> getQuestionsParsed() {
		SpreadSheetParser parser = new SpreadSheetParser();
		HashSet<Question> questions = null;
		try {
			MessageBox.showFileNotLoadedErrorDialog(dialog);
			questions = parser.parse(testFile, getQuestionSet());
		} catch(NullPointerException e) {
			return null;
		}
		return questions;
	}

	/**
	 * @return
	 */
	private QuestionSet getQuestionSet() {
		String title = dialog.getCbSetName().getSelectedItem().toString();
		QuestionSet set = new QuestionSetService().getUniqueByName(title);
		return set;
	}

	private void saveQuestionSetToDB(HashSet<Question> questions) {
		QuestionService qService = new QuestionService();
		qService.addAllNoDuplicates(questions);
	}

}

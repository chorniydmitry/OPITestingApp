package ru.fssprus.r82.swing.dialogs.addingSet;

import java.awt.Desktop;
/**
 * @author Chernyj Dmitry
 *
 */
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.ImageIcon;

import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.service.QuestionService;
import ru.fssprus.r82.swing.dialogs.CommonController;
import ru.fssprus.r82.swing.main.mainFrame.MainFrame;
import ru.fssprus.r82.swing.utils.MessageBox;
import ru.fssprus.r82.utils.spreadsheet.SpreadSheetParser;
import ru.fssprus.r82.utils.spreadsheet.SpreadsheetFileChooser;

public class LoadingQuestionSetController extends CommonController<LoadingQuestionSetDialog> {
	private File testFile;

	public LoadingQuestionSetController(LoadingQuestionSetDialog dialog) {
		super(dialog);
		init();
	}
	
	private void init() {
		dialog.getBtnImportQuestionsSet().setEnabled(false);
	}

	@Override
	protected void setListeners() {
		dialog.getBtnImportQuestionsSet().addActionListener(listener -> doImportQuestionSet());
		dialog.getBtnOpenTextFile().addActionListener(listener -> doOpenTestFile());
		dialog.getBtnSaveNewSet().addActionListener(listener -> doSaveNewSet());
		dialog.getBtnLoadSetFileTemplate().addActionListener(listener -> doLoadSetFileTemplate());
	}
	
	private void doLoadSetFileTemplate() {
		try {
			
	        //text file, should be opening in default text editor
	        File file = new File(LoadingQuestionSetController.class.getResource("/set_template.xlsx").getFile());
	        
	        //first check if Desktop is supported by Platform or not
	        if(!Desktop.isDesktopSupported()){
	            System.out.println("Desktop is not supported");
	            return;
	        }
	        
	        Desktop desktop = Desktop.getDesktop();
	        if(file.exists()) desktop.open(file);
	        
	        //let's try to open PDF file
	        //file = new File("/Users/pankaj/java.pdf");
	        //if(file.exists()) desktop.open(file);
		} catch (IOException e) {
			//TODO вывести в messagebox
			
		}
	
	
	}

	private Object doSaveNewSet() {
		// TODO Auto-generated method stub
		return null;
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
		if(!validate() || questionsParsed == null)
			return;

		saveQuestionSetToDB(getQuestionsParsed());

		MessageBox.showReadyDialog(dialog);
	}

	private boolean validate() {
		if (!validateFile()) {
			MessageBox.showFileNotLoadedErrorDialog(dialog);
			dialog.getTfFilePath().requestFocus();
			return false;
		}

		if (!validateSpecTf()) {
			MessageBox.showWrongSpecSpecifiedErrorDialog(dialog);
			dialog.getCbSpecName().requestFocus();
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
		if (dialog.getCbSpecName().getSelectedItem().toString().isEmpty())
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
		QuestionSet set = new QuestionSet();
		set.setName(dialog.getCbSpecName().getSelectedItem().toString());
		return set;
	}

	private void saveQuestionSetToDB(HashSet<Question> questions) {
		QuestionService qService = new QuestionService();
		qService.addFilteringExistant(questions);
	}

}

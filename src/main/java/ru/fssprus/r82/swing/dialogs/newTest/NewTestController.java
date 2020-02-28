package ru.fssprus.r82.swing.dialogs.newTest;

/**
 * @author Chernyj Dmitry
 *
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.entity.User;
import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.swing.dialogs.CommonController;
import ru.fssprus.r82.swing.dialogs.DialogBuilder;
import ru.fssprus.r82.utils.testingTools.TestProcessBuilder;

public class NewTestController extends CommonController<NewTestDialog> {

	private static final String COMMON_TEXT = "Общие";

	public NewTestController(NewTestDialog dialog) {
		super(dialog);
		loadSpecificationList();
	}

	@Override
	protected void setListeners() {
		dialog.getBtnBegin().addActionListener(listener -> doBegin());
		dialog.getBtnCancel().addActionListener(listener -> doCancel());
	}

	private void doBegin() {
		dialog.resetUserInputComponents();

		if (!validateFields())
			return;

		List<QuestionSet> sets = configureSpecsList();
		
		TestProcessBuilder tpBuilder = new TestProcessBuilder(sets, getUser());

		DialogBuilder.showTestDialog(tpBuilder.buildTest());
		
		dialog.dispose();
	}
	
	private User getUser() {
		return new User(dialog.getTfName().getText(), dialog.getTfSurname().getText(), dialog.getTfSecondName().getText());
	}

	private List<QuestionSet> configureSpecsList() {
		
		String selectedSpecName = String.valueOf(dialog.getCbSpecification().getSelectedItem());
		
		QuestionSet selectedSpec = new QuestionSetService().getUniqueByName(selectedSpecName);
		QuestionSet commonSpec = new QuestionSetService().getUniqueByName(COMMON_TEXT);
		
		List<QuestionSet> sets = new ArrayList<>();
		sets.add(selectedSpec);
		sets.add(commonSpec);

		return sets;
	}
	
	private void doCancel() {
		dialog.dispose();
	}

	private boolean validateFields() {

		boolean validationPassed = true;
		
		ValidatorFactory valFact = Validation.buildDefaultValidatorFactory();
		Validator validator = valFact.getValidator();
		
		Set<ConstraintViolation<User>> nameCons = validator.validateValue(User.class, "name", dialog.getTfName().getText());
		Set<ConstraintViolation<User>> snCons = validator.validateValue(User.class, "surname", dialog.getTfSurname().getText());
		Set<ConstraintViolation<User>> fnCons = validator.validateValue(User.class, "secondName", dialog.getTfSecondName().getText());
		
		if (nameCons.size() > 0) {
			dialog.getTfName().setBackground(Color.RED);
			validationPassed = false;
		}
		if (snCons.size() > 0) {
			dialog.getTfSurname().setBackground(Color.RED);
			validationPassed = false;
		}
		if (fnCons.size() > 0) {
			dialog.getTfSecondName().setBackground(Color.RED);
			validationPassed = false;
		}
		
		Object cbValue = dialog.getCbSpecification().getSelectedItem();

		if (cbValue == null) {
			dialog.getCbSpecification().setBackground(Color.RED);
			validationPassed = false;
		}

		valFact.close();

		return validationPassed;

	}

	private void loadSpecificationList() {
		QuestionSetService service = new QuestionSetService();
		List<QuestionSet> setList = service.getAll();

		dialog.getCbSpecification().addItem(null);

		for (QuestionSet set : setList) {
			if (set.getName().toUpperCase().equals(COMMON_TEXT.toUpperCase()))
				continue;
			dialog.getCbSpecification().addItem(set.getName());
		}
	}

}

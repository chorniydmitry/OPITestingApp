package ru.fssprus.r82.ui.table;

/**
 * @author Chernyj Dmitry
 *
 */
public interface UpdatableController {
	abstract void selectionChanged(int index);
	abstract void nextPage();
	abstract void previousPage();
	abstract void delete(int index);
	abstract void goToPage(int page);
	abstract void addEntry(int index);
	abstract void edit();
	abstract void save();
	abstract void cancel();
}

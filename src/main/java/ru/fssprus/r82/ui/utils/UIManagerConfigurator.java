package ru.fssprus.r82.ui.utils;

import java.awt.Color;
import java.awt.Font;
import java.util.Enumeration;
import java.util.Map.Entry;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;

import ru.fssprus.r82.utils.ApplicationConfiguration;

public class UIManagerConfigurator {

	private static final int HEX = 16;

	private UIManagerConfigurator() {

	}

	public static final void configure() {

		colorElements();

		setTitledBorders();

		configureDefaultFont();

		localizeDialogs();

	}

	private static void setTitledBorders() {
		Color color = new Color(Integer.parseInt(ApplicationConfiguration.getItem("color.TitledBorder.border"), HEX));
		int size = Integer.parseInt(ApplicationConfiguration.getItem("size.TitledBorder"));

		UIManager.put("TitledBorder.border", new LineBorder(color, size));
		UIManager.put("TextField.border", new LineBorder(color, size));
//		UIManager.put("CheckBox.border", new LineBorder(color, size));
//		UIManager.put("Spinner.border", new LineBorder(color, size));
//		UIManager.put("CheckBoxMenuItem.border", new LineBorder(color, size));
//		UIManager.put("TextArea.border", new LineBorder(color, size));
//		UIManager.put("Button.border", new LineBorder(color, size));
//		UIManager.put("OptionPane.border", new LineBorder(color, size));
//		UIManager.put("RadioButton.border", new LineBorder(color, size));
//		UIManager.put("RadioButtonMenuItem.border", new LineBorder(color, size));
//		UIManager.put("ScrollPane.border", new LineBorder(color, size));
//		UIManager.put("TextPane.border", new LineBorder(color, size));
//		UIManager.put("EditorPane.border", new LineBorder(color, size));
		UIManager.put("PasswordField.border", new LineBorder(color, size));

	}

	private static void colorElements() {
		for (Entry<Object, Object> prop : ApplicationConfiguration.getAll()) {
			if (String.valueOf(prop.getKey()).contains("color."))
				UIManager.put(String.valueOf(prop.getKey()).split("color.")[1],
						new Color(Integer.parseInt((String) prop.getValue(), HEX)));
		}
	}

	private static void configureDefaultFont() {
		FontUIResource f = new FontUIResource(new Font("Tahoma", 0, 12));
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				FontUIResource orig = (FontUIResource) value;
				Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
				UIManager.put(key, new FontUIResource(font));
			}
		}
	}

	private static void localizeDialogs() {
		UIManager.put("OptionPane.yesButtonText", "Да");
		UIManager.put("OptionPane.noButtonText", "Нет");
		UIManager.put("OptionPane.cancelButtonText", "Отмена");
		UIManager.put("OptionPane.okButtonText", "Готово");
	}

}

package ru.fssprus.r82.ui.dialogs.UIDesginer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import ru.fssprus.r82.ui.dialogs.CommonDialog;
import ru.fssprus.r82.utils.ImageUtils;

public class UIDesignerDialog extends CommonDialog {

	private static final long serialVersionUID = 3231620504415592368L;
	
//	private static final String LBL_DEFCOLOR_CAPTION = "Основной цвет";
//	private static final String LBL_DEFFONT_CAPTION = "Основной шрифт";
//	private static final String LBL_MAINBACKGROUNDIMAGE_CAPTION = "Фоновое изображение";
	
	private JButton btnEdit = new JButton();
	

	public UIDesignerDialog(int width, int height, String title, Path icon, JFrame parent) {
		super(width, height, title, icon, parent);
		
	}

	@Override
	protected void layoutDialog() {
		
		try {
			BufferedImage image = ImageIO.read(UIDesignerController.class.getResourceAsStream("/edit.png"));
			BufferedImage coloredImage = ImageUtils.colorImage(image, Color.CYAN);
			btnEdit.setPreferredSize(new Dimension(50, 25));
			btnEdit.setIcon(new ImageIcon(coloredImage));
			getContentPanel().add(btnEdit);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		
		
	}


	@Override
	protected void layoutPanelTop() {
		// TODO Auto-generated method stub
		
	}

}

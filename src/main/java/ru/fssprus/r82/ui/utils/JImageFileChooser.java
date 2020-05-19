package ru.fssprus.r82.ui.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import ru.fssprus.r82.utils.AppConstants;

public class JImageFileChooser extends JFileChooser {
	private static final long serialVersionUID = -4271150307045347270L;
	
	private static final String FILECHOOSER_ERROR_OCCURED = "Возникла ошибка";
	public static final String FILECHOOSER_NOT_IMAGE_MESSAGE = "Не изображение";
	
	JLabel lblImagePreview = new JLabel();
	
	public JImageFileChooser() {
		this.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.setMultiSelectionEnabled(false);
		this.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG, PNG and GIF images", "jpg", "jpeg", "png",
				"gif");
		
		this.addChoosableFileFilter(filter);
		
		lblImagePreview
		.setPreferredSize(new Dimension(AppConstants.IMAGE_PREVIEW_WIDTH, AppConstants.IMAGE_PREVIEW_HEIGHT));
		
		this.setAccessory(lblImagePreview);
		
		setPropertyChangeListener(this);
		
	}
	
	public File selectImageFile(Component parent) {
		int selection = this.showOpenDialog(parent);
		if (selection == JFileChooser.APPROVE_OPTION) {
			return this.getSelectedFile();
		} else
			return null;
	}

	private void setPropertyChangeListener(JFileChooser jFC) {
		jFC.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(final PropertyChangeEvent pe) {
				SwingWorker<Image, Void> worker = new SwingWorker<Image, Void>() {

					protected Image doInBackground() {
						if (pe.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
							File f = jFC.getSelectedFile();
							try {
								FileInputStream fin = new FileInputStream(f);
								BufferedImage bim = ImageIO.read(fin);
								return bim.getScaledInstance(AppConstants.IMAGE_PREVIEW_WIDTH,
										AppConstants.IMAGE_PREVIEW_HEIGHT, BufferedImage.SCALE_FAST);

							} catch (Exception e) {
								lblImagePreview.setText(FILECHOOSER_NOT_IMAGE_MESSAGE);
							}
						}

						return null;
					}

					protected void done() {
						try {
							Image i = get(1L, TimeUnit.NANOSECONDS);

							if (i == null)
								return;

							lblImagePreview.setIcon(new ImageIcon(i));
						} catch (Exception e) {
							lblImagePreview.setText(FILECHOOSER_ERROR_OCCURED);
						}
					}
				};
				worker.execute();
			}
		});
	}

}

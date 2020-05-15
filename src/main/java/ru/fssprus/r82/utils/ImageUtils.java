package ru.fssprus.r82.utils;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ru.fssprus.r82.swing.utils.MessageBox;

public class ImageUtils {

	public static BufferedImage colorImage(BufferedImage image, Color color) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int[] pixels = raster.getPixel(x, y, (int[]) null);
                pixels[0] = color.getRed();
                pixels[1] = color.getGreen();
                pixels[2] = color.getBlue();
                raster.setPixel(x, y, pixels);
            }
            
        }
        return image;
    }
	
	public static ImageIcon colorImage(File imageFile, Color color) {
		BufferedImage buffImage;
		try {
			buffImage = ImageIO.read(imageFile);
			return new ImageIcon(colorImage(buffImage, color));
		} catch (IOException e) {
			MessageBox.showErrorMessage(null, AppConstants.ERROR_ICON_NOT_LOADED);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ImageIcon resizeImage(ImageIcon img, int newWidth) {
		Image image = img.getImage();
		
		if(img.getIconWidth() < newWidth)
			return img;
		int newHeight = newWidth * img.getIconHeight() / img.getIconWidth();

		Image newimg = image.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}
	
	
}

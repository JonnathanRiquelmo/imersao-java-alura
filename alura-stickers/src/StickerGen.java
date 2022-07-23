import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
// import java.awt.GraphicsEnvironment;

public class StickerGen {

    public void createSticker(InputStream inputStream, String fileName) throws Exception {
        // https://m.media-amazon.com/images/M/MV5BYTQ4MjA4NmYtYjRhNi00MTEwLTg0NjgtNjk3ODJlZGU4NjRkL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UY176_CR0,0,128,176_AL_.jpg
        String text = "Niiiiice!";
        // BufferedImage originalImg = ImageIO.read(new File("img/sticker.jpg"));
        BufferedImage originalImg = ImageIO.read(inputStream);

        // Creates a copy with transparency
        int width = originalImg.getWidth();
        int height = originalImg.getHeight();
        int newHeight = height + 200;
        BufferedImage newImg = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copies the new image to the memory
        Graphics2D g2d = (Graphics2D) newImg.getGraphics();
        g2d.drawImage(originalImg, null, 0, 0);

        // font settings
        g2d.setFont(new Font("Z003-MediumItalic", Font.BOLD, 72));
        g2d.setColor(Color.ORANGE);

        var horizontalPosition = centralizeTextWidth(g2d, text, newImg);
        var verticalPosition = centralizeTextHeight(g2d, text, newImg);

        // writes a text in the new image
        g2d.drawString(text, horizontalPosition, verticalPosition);

        // saves the new image on a file
        ImageIO.write(newImg, "png", new File(fileName));

        // Get all the OS installed fonts 
        // GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        // Font[] fonts = ge.getAllFonts();
        // for (Font font : fonts) {
        // System.out.print(font.getFontName() + " : ");
        // System.out.println(font.getFamily());
        // }

    }

    private int centralizeTextWidth(Graphics2D g2d, String string, BufferedImage newImg) {
        int WidthLenght = (int) g2d.getFontMetrics().getStringBounds(string, g2d).getWidth();
        int horizontalPos = newImg.getWidth() / 2 - WidthLenght / 2;

        return horizontalPos;
    }

    private int centralizeTextHeight(Graphics2D g2d, String string, BufferedImage newImg) {
        int HeightLenght = (int) g2d.getFontMetrics().getStringBounds(string, g2d).getHeight();
        int verticalPosition = newImg.getHeight() - HeightLenght;

        return verticalPosition;
    }
}

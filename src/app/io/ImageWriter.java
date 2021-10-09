package app.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageWriter {
    public static void write(String imagepath,int width,int height,int rgb[][][]){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int alpha =  rgb[i][j][3];
                int red = rgb[i][j][0];
                int green = rgb[i][j][1];
                int blue = rgb[i][j][2];
                int color = (alpha << 24) | (red << 16) | (green << 8) | blue;
                image.setRGB(j, i, color);
            }
        }

        try {
            ImageIO.write(image, "png", new File( imagepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

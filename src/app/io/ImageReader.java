package app.io;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReader {
    public static int[][][] read(String imagepath){
        int out[][][] = null;
        try {
            BufferedImage in = ImageIO.read(new File(imagepath));
            out = new int[in.getHeight()][in.getWidth()][4];
            for(int i = 0;i<in.getHeight();i++){
                for(int j = 0;j<in.getWidth();j++){
                    Color PixelColor =  new Color(in.getRGB(j,i));
                    out[i][j][0] = PixelColor.getRed();
                    out[i][j][1] = PixelColor.getGreen();
                    out[i][j][2] = PixelColor.getBlue();
                    out[i][j][3] =(in.getRGB(j,i) >> 24) & 255;;

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
}

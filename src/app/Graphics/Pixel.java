package app.Graphics;

import java.awt.Color;

public class Pixel {
    Color c;
    public void setBackground(Color c){
        this.c = new Color(c.getRGB(),true);
    }
    public Color getBackground(){
        return c;
    }
}

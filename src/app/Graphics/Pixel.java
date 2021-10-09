package app.Graphics;

import java.awt.Color;

public class Pixel {
    Color c;
    public void setBackground(Color c){
        if(c.getAlpha()<255)
            this.c = new Color(80+(c.getRed()/3),80+(c.getGreen()/3),80+(c.getBlue())/3,c.getAlpha());
        else this.c = c;
    }
    public Color getBackground(){
        return c;
    }
}

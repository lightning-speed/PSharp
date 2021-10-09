package app.ui;

import app.Graphics.DrawingArea2;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class AppView extends JPanel {
    public static DrawingArea2 drawingArea;
    public AppView(){
        this.setLayout(null);
        drawingArea = new DrawingArea2(30,30);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    drawingArea.createArea(30,30);
                    add_c(drawingArea.getArea(), 200, 75, 500, 500);
                    for(;;){
                        drawingArea.zoom(new Scanner(System.in).nextInt());
                    }
                }catch (Exception e){}
            }
            }).start();
    }
    public void add_c(Component c,int x,int y,int width,int height){
        add(c);
        c.setBounds(x,y,width,height);
    }
}

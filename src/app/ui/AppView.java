package app.ui;

import app.Graphics.DrawingArea2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class AppView extends JPanel {
    public static DrawingArea2 drawingArea;
    public JToolBar tb = new JToolBar();
    public static JButton colorChooserBtn = new JButton(" ");
    public AppView(){
        this.setLayout(null);

        tb.setBounds(0,0,800,30);
        tb.setFloatable(false);
        tb.add(colorChooserBtn);

        colorChooserBtn.setBackground(DrawingArea2.PointerColor);
        colorChooserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorChooser.open();
                DrawingArea2.PointerColor = colorChooserBtn.getBackground();
            }
        });
        this.add(tb);
        drawingArea = new DrawingArea2(30,30);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    drawingArea.createArea(30,30);
                    add_c(drawingArea.getArea(), 200, 75, 500, 500);
                }catch (Exception e){}
            }
            }).start();
    }
    public void add_c(Component c,int x,int y,int width,int height){
        add(c);
        c.setBounds(x,y,width,height);
    }
}

package app.ui;

import app.Graphics.DrawingArea2;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppView extends JPanel {
    public static DrawingArea2 drawingArea;
    public JToolBar tb = new JToolBar();
    public static JButton colorChooserBtn = new JButton(" ");
    public static JToggleButton pen = new JToggleButton(new ImageIcon("icons\\pen.png"));
    public static JToggleButton erase = new JToggleButton(new ImageIcon("icons\\erase.png"));
    public static JSlider zoom = new JSlider();
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

        tb.add(pen);
        tb.add(erase);
        pen.setSelected(true);
        pen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pen.isSelected()){
                    erase.setSelected(false);
                }else{
                    erase.setSelected(true);
                }
            }
        });
        erase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(erase.isSelected()){
                    pen.setSelected(false);
                }
                else{
                    pen.setSelected(true);
                }
            }
        });
        drawingArea = new DrawingArea2(500,500);
        zoom.setBounds(200,580,300,30);
        zoom.setMaximum(5000/drawingArea.Iwidth);
        zoom.setMinimum(1);
        zoom.setValue(5);
        zoom.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                drawingArea.zoom(zoom.getValue());
            }
        });
        add(zoom);
        this.add(tb);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    drawingArea.load_image("icon.png");
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

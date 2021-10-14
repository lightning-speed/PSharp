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
    public static JButton rotate = new JButton(new ImageIcon("icons\\rotate.png"));
    public static JSlider zoom = new JSlider();
    public AppView(){
        this.setLayout(null);
        colorChooserBtn.setToolTipText("Choose Color");
        pen.setToolTipText("Pen");
        erase.setToolTipText("Eraser");
        rotate.setToolTipText("Rotate");
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
        tb.add(rotate);
        rotate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				drawingArea.rotate();
			}});
        drawingArea = new DrawingArea2(60,57);
        zoom.setBounds(200,580,300,30);
        if(drawingArea.Iwidth>=500)
           zoom.setMaximum(2);
        if(drawingArea.Iwidth<=300)
            zoom.setMaximum(5);
        if(drawingArea.Iwidth<=200)
            zoom.setMaximum(7);
        if(drawingArea.Iwidth<=100)
           zoom.setMaximum(10);
        if(drawingArea.Iwidth<=60)
            zoom.setMaximum(15);
        zoom.setMaximum(drawingArea.pixel_size);
        zoom.setToolTipText("Zoom");
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
                    drawingArea.load_image("icons\\icon.png");
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

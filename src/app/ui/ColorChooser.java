package app.ui;

import app.Graphics.DrawingArea2;
import app.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChooser {
    static JFrame window = new JFrame();
    static JPanel MainPanel = new JPanel();
    static Color out;
    public static void open(){
        window = new JFrame("Color Chooser");
        window.setVisible(true);
        window.setSize(600,450);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setContentPane(MainPanel);
        MainPanel.setLayout(null);
        JColorChooser chooser = new JColorChooser();
        chooser.setBounds(0,0,600,350);
        MainPanel.add(chooser);
        JButton done = new JButton("Done");
        done.setBackground(new Color(100,130,240));
        done.setBounds(10,360,100,25);
        MainPanel.add(done);
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out = chooser.getColor();
                AppView.colorChooserBtn.setBackground(out);
                window.setVisible(false);
                Main.win.refresh();
                DrawingArea2.PointerColor = out;
            }
        });
        window.setSize(600,451);
    }
}

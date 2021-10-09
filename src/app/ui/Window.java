package app.ui;

import app.core.Core;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

public class Window extends JFrame {

    public static AppView view;
    public JMenuBar bar = new JMenuBar();

    public JMenu file_menu = new JMenu("File");
    public JMenu edit_menu = new JMenu("Edit");

    public JMenuItem _undo = new JMenuItem("Undo");

    public Window(){
        super("PSharp");
        this.setIconImage(new ImageIcon("a.png").getImage());
        this.setSize(900,650);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.setJMenuBar(bar);

        bar.add(file_menu);
        bar.add(edit_menu);

        edit_menu.add(_undo);

        _undo.setAccelerator(KeyStroke.getKeyStroke('Z', InputEvent.CTRL_DOWN_MASK));
        _undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.undo();
            }
        });

        view = new AppView();
        this.setContentPane(view);
    }
    public void refresh(){
        this.setSize(900,651);
        this.setSize(900,650);
    }
}

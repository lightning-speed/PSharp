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
    public JMenuItem _save = new JMenuItem("Save");

    public Window(){
        super("PSharp");
        this.setIconImage(new ImageIcon("icons\\icon.png").getImage());
        this.setSize(900,687);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.setJMenuBar(bar);

        bar.add(file_menu);
        bar.add(edit_menu);

        edit_menu.add(_undo);
        file_menu.add(_save);

        _undo.setAccelerator(KeyStroke.getKeyStroke('Z', InputEvent.CTRL_DOWN_MASK));
        _save.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));

        _undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.undo();
            }
        });
        _save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.save();
            }
        });

        view = new AppView();
        this.setContentPane(view);
    }
    public void refresh(){
        this.setSize(900,651);
        this.setSize(900,687);
    }
}

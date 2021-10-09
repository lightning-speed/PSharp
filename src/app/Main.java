package app;

import app.ui.Window;

import javax.swing.*;

public class Main {
    public static Window win;
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        win = new Window();
    }
}

package app.Graphics;

import app.Main;
import app.core.Core;
import app.io.ImageReader;
import app.io.ImageWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class DrawingArea2 {
    public static JScrollPane scroll_area;
    public static JPanel drawing_panel;
    public static int pixel_size = 15;
    public static int Iheight, Iwidth;
    public static Color PointerColor = Color.BLACK;
    public static Pixel[][] pixles;
    public JLabel img;

    public DrawingArea2(int width, int height) {
        Iheight = height;
        Iwidth = width;
        img = new JLabel();
        pixel_size = 500/((height+width)/2);
        if(pixel_size<2)pixel_size = 2;
        drawing_panel = new JPanel();
        drawing_panel.setLayout(null);
        drawing_panel.setBackground(new Color(100,100,100,200));
        img.setBounds(0,0,width*pixel_size,height*pixel_size);
        drawing_panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                 drawing_panel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                drawing_panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }
        });
        drawing_panel.add(img);
        drawing_panel.setPreferredSize(new Dimension(width * pixel_size, height * pixel_size));
        scroll_area = new JScrollPane(drawing_panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        activatePen();

    }

    public JScrollPane getArea() {
        return scroll_area;
    }

    public void load_image(String path) {
        int[][][] in = ImageReader.read(path);
        int h = in.length, w = in[0].length;
        pixles = new Pixel[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                pixles[i][j] = new Pixel();
                pixles[i][j].setBackground(new Color(in[i][j][0], in[i][j][1], in[i][j][2], in[i][j][3]));

            }
        }
        System.out.println("done");
        ImageIcon ico = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(pixel_size*Iwidth, pixel_size*Iheight, Image.SCALE_DEFAULT));
        img.setIcon(ico);
        activatUpdate();
        Main.win.refresh();

    }

    public void createArea(int width, int height) {
        int h = height, w = width;
        pixles = new Pixel[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                pixles[i][j] = new Pixel();
                Color c = new Color(0, 0,  0, 0);
                pixles[i][j].setBackground(c);
            }
        }
        ImageIcon ico = new ImageIcon(new ImageIcon(emptyImage(w, h)).getImage().getScaledInstance( Iwidth*pixel_size, Iheight*pixel_size, Image.SCALE_DEFAULT));
        img.setIcon(ico);
        activatUpdate();
        Main.win.refresh();

    }

    public void saveAsImg(String to) {
        int[][][] out = new int[Iheight][Iwidth][4];
        for (int i = 0; i < Iheight; i++) {
            for (int j = 0; j < Iwidth; j++) {
                out[i][j][0] = pixles[i][j].getBackground().getRed();
                out[i][j][1] = pixles[i][j].getBackground().getGreen();
                out[i][j][2] = pixles[i][j].getBackground().getBlue();
                out[i][j][3] = pixles[i][j].getBackground().getAlpha();

            }
        }
        ImageWriter.write(to, Iwidth, Iheight, out);
    }

    public void drawImage() {
        ImageIcon icor = (ImageIcon) img.getIcon();
        BufferedImage imgs = new BufferedImage(
                icor.getIconWidth() / pixel_size,
                icor.getIconHeight() / pixel_size,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = imgs.createGraphics();
        icor.paintIcon(null, g, 0, 0);
        g.dispose();
        for (int i = 0; i < Iheight; i++) {
            for (int j = 0; j < Iwidth; j++) {
                imgs.setRGB(j, i, pixles[i][j].getBackground().getRGB());
            }
        }
        ImageIcon ico = new ImageIcon(new ImageIcon(imgs).getImage().getScaledInstance(pixel_size*Iwidth, pixel_size*Iheight, Image.SCALE_DEFAULT));
        img.getGraphics().dispose();
        ico.getImage().flush();
        img.setIcon(ico);
    }

    public BufferedImage emptyImage(int w, int h) {
        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Color c = new Color(0, 0, 0, 0);
                out.setRGB(j, i, 0);
            }
        }
        return out;
    }

    public void activatePen() {
        drawing_panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                pixles[e.getY() / pixel_size][e.getX() / pixel_size].setBackground(PointerColor);
                drawImage();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        drawing_panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pixles[e.getY() / pixel_size][e.getX() / pixel_size].setBackground(PointerColor);
                drawImage();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Core.add_move();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }
    public void activatUpdate(){
        new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Core.move_index==0)
                    Core.add_move();
                drawImage();

            }
        }).start();
    }
    public void zoom(int pixelSize){
        pixel_size = pixelSize;
        drawImage();
    }
}
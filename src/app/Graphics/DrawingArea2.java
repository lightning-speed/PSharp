package app.Graphics;

import app.Main;
import app.core.Core;
import app.io.ImageReader;
import app.io.ImageWriter;
import app.ui.AppView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class DrawingArea2 {
    public static JScrollPane scroll_area;
    public static JPanel drawing_panel;
    public static int pixel_size = 15;
    public static int Iheight, Iwidth;
    public static String FileName;
    public static Color PointerColor = Color.WHITE;
    public static Pixel[][] pixels;
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
        FileName = path;
        int[][][] in = ImageReader.read(path);
        int h = in.length, w = in[0].length;
        pixels = new Pixel[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                pixels[i][j] = new Pixel();
                pixels[i][j].setBackground(new Color(in[i][j][0], in[i][j][1], in[i][j][2], in[i][j][3]));

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
        pixels = new Pixel[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                pixels[i][j] = new Pixel();
                Color c = new Color(0, 0,  0, 0);
                pixels[i][j].setBackground(c);
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
                out[i][j][0] = pixels[i][j].getBackground().getRed();
                out[i][j][1] = pixels[i][j].getBackground().getGreen();
                out[i][j][2] = pixels[i][j].getBackground().getBlue();
                out[i][j][3] = pixels[i][j].getBackground().getAlpha();

            }
        }
        ImageWriter.write(to, Iwidth, Iheight, out);
    }

    public void drawImage() {
        ImageIcon icor = (ImageIcon) img.getIcon();
        BufferedImage imgs = new BufferedImage(
                Iwidth,
                Iheight,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = imgs.createGraphics();
        icor.paintIcon(null, g, 0, 0);
        g.dispose();
        for (int i = 0; i < Iheight; i++) {
            for (int j = 0; j < Iwidth; j++) {
                imgs.setRGB(j, i, pixels[i][j].getBackground().getRGB());
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
                if(e.getX()>-1&&e.getX()<490&&e.getY()>-1&&e.getY()<490) {
                    if(AppView.pen.isSelected())
                        pixels[e.getY() / pixel_size][e.getX() / pixel_size].setBackground(PointerColor);
                    else
                        pixels[e.getY() / pixel_size][e.getX() / pixel_size].setBackground(new Color(0,0,0,0));

                    drawImage();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                drawing_panel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        pixels[e.getY() / pixel_size][e.getX() / pixel_size].setBackground(PointerColor);
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
        }).start();


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
    public void zoom(int pixelS){
        pixel_size = pixelS;
        img.setBounds(0,0,Iwidth*pixel_size,Iheight*pixel_size);
        drawing_panel.setPreferredSize(new Dimension(Iwidth * pixel_size, Iheight * pixel_size));
    }
    public void rotate() {
    	Core.add_move();
    	Pixel Temp[][] = pixels;
    	int temp = Iheight;
    	Iheight = Iwidth;
    	Iwidth = temp;
    	   img.setBounds(0,0,Iwidth*pixel_size,Iheight*pixel_size);
           drawing_panel.setPreferredSize(new Dimension(Iwidth * pixel_size, Iheight * pixel_size));
    	pixels = new Pixel[Iheight][Iwidth];
    	for(int i = 0;i<Iwidth;i++) {
    		for(int j = 0;j<Iheight;j++) {
    			pixels[(Iheight-1)-j][(Iwidth-1)-i] = Temp[(Iwidth-1)-i][j];
    		}
    	}
    	
    	drawImage();
    }
}
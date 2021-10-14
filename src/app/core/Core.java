package app.core;

import app.Graphics.DrawingArea2;
import app.Graphics.Pixel;
import app.io.ImageWriter;
import app.ui.AppView;

import java.awt.*;
import java.net.FileNameMap;

public class Core {
	static int pixels[][][] = new int[50][1000][1000];
	public static int move_index = 0;

	public static void add_move() {
		if (move_index >= 49) {

			move_index = 0;
		}
		move_index++;
		for (int i = 0; i < AppView.drawingArea.pixels.length; i++) {
			for (int j = 0; j < AppView.drawingArea.pixels[i].length; j++) {
				pixels[move_index][i][j] = AppView.drawingArea.pixels[i][j].getBackground().getRGB();
			}
		}
	}

	public static void undo() {
		if (move_index > 1) {
			move_index--;
			for (int i = 0; i < AppView.drawingArea.pixels.length; i++) {
				for (int j = 0; j < AppView.drawingArea.pixels[i].length; j++) {
					AppView.drawingArea.pixels[i][j].setBackground(new Color(pixels[move_index][i][j], true));
				}
			}
		}
	}

	public static void save() {
		AppView.drawingArea.saveAsImg(DrawingArea2.FileName);
	}
}

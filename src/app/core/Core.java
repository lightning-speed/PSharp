package app.core;

import app.Graphics.DrawingArea2;
import app.Graphics.Pixel;
import app.ui.AppView;

import java.awt.*;

public class Core {
  static int pixels[][][] = new int[50][1000][1000];
  public static int move_index = 0;
  public static void add_move(){
    move_index++;
    for(int i = 0;i<AppView.drawingArea.pixles.length;i++) {
      for(int j = 0;j<AppView.drawingArea.pixles[i].length;j++){
        pixels[move_index][i][j] = AppView.drawingArea.pixles[i][j].getBackground().getRGB();
      }
    }
  }
  public static void undo(){
    if(move_index>1){
    move_index--;
    for(int i = 0;i<AppView.drawingArea.pixles.length;i++) {
      for (int j = 0; j < AppView.drawingArea.pixles[i].length; j++) {
        AppView.drawingArea.pixles[i][j].setBackground(new Color(pixels[move_index][i][j],true));
      }
    }
    }
  }
}

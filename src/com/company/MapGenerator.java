package com.company;

import java.awt.*;
import java.util.Random;

public class MapGenerator {

   public int map[][];
   public int brickWidth;
   public int brickHeight;
   public MapGenerator(int row , int col){
       map = new int[row][col];
       for(int i=0;i<map.length;i++)
       {
           for(int j=0;j<map[0].length;j++)
           {
               Random random = new Random();
               int x = random.nextInt(7)+1;
               map[i][j] = x;
           }
       }

       brickWidth = 1200/col;
       brickHeight = 300/row;

   }
   public void draw(Graphics2D g)
   {
       for(int i=0;i<map.length;i++)
       {
           for(int j=0;j<map[0].length;j++)
           {
               if(map[i][j] > 0){
                   if(map[i][j] == 1) {
                       g.setColor(Color.white);
                   }
                   else
                   {
                       int x = map[i][j];
                       if(x==2)
                       g.setColor(Color.PINK);
                       if(x==3)
                       g.setColor(Color.CYAN);
                       if(x==4)
                       g.setColor(Color.GREEN);
                       if(x==5)
                           g.setColor(Color.BLUE);
                       if(x==6)
                           g.setColor(Color.ORANGE);
                       if(x==7)
                           g.setColor(Color.MAGENTA);


                   }

                   g.fillRect(j*brickWidth+170,i*brickHeight+50,brickWidth,brickHeight);

                   g.setStroke(new BasicStroke(5));
                   g.setColor(Color.black);
                   g.drawRect(j*brickWidth+170,i*brickHeight+50,brickWidth,brickHeight);
               }
           }
       }

   }

   public  void setBrickValue(int value , int row , int col)
   {
       if(map[row][col] >1)
           map[row][col] = 1;
       else
       map[row][col]=0;
   }


}

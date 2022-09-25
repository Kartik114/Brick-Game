package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
   private boolean play = false;
   private int score = 0;

   private  int totalBricks = 14*28;

   private  Timer timer;
   private int delay = 8;

   private int playerX = 540;

   private int ballposX = 540;
   private  int ballposY = 730;
    private int counter=0;

   Random random = new Random();
    int n = random.nextInt(2+1-2) - 2;
    private int ballXdir = n;
    private int ballYdir = -2;

   private MapGenerator map;

   public Gameplay()
   {
     map = new MapGenerator(14,28);
     addKeyListener(this);
     setFocusable(true);
     setFocusTraversalKeysEnabled(false);
     timer = new Timer(delay,this);
     timer.start();
   }

   public void paint(Graphics g)
   {
       // background
       g.setColor(Color.black);
       g.fillRect(1,1,1600,900);

       //drawing map
       map.draw((Graphics2D)g);

       // borders
       g.setColor(Color.yellow);
       g.fillRect(0,0,10,900);

       g.setColor(Color.BLUE);
       g.fillRect(0,0,1580,10);

       g.setColor(Color.PINK);
       g.fillRect(1530,0,10,900);

       g.setColor(Color.MAGENTA);
       g.fillRect(0,830,1580,10);



       //scores
       g.setColor(Color.GREEN);
       g.setFont(new Font("serif",Font.BOLD,30));
       g.drawString(""+score,50,50);

       // the paddle
       g.setColor(Color.green);
       g.fillRect(playerX,750,100,8);

       // the ball
       g.setColor(Color.cyan);
       g.fillOval(ballposX,ballposY,14,14);

       if(totalBricks <= 0)
       {
           play = false;
           ballXdir=0;
           ballYdir=0;
           g.setColor(Color.RED);
           g.setFont(new Font("serif",Font.BOLD,30));
           g.drawString("You Won!!" ,700,350);

           g.setFont(new Font("serif",Font.BOLD,20));
           g.drawString("Press Enter to Restart " ,700,450);


       }

       if(ballposY > 815)
       {

           play = false;
           ballXdir=0;
           ballYdir=0;
           g.setColor(Color.RED);
           g.setFont(new Font("serif",Font.BOLD,30));
           g.drawString("Game Over! ",700,400);
           g.drawString("Score : " + score,700,450);


           g.setFont(new Font("serif",Font.BOLD,20));
           g.drawString("Press Enter to Restart " ,700,500);



       }
       g.dispose();

   }

    @Override
    public void actionPerformed(ActionEvent e) {

      timer.start();
      if(play)
      {
          if(new Rectangle(ballposX, ballposY,14,14).intersects(new Rectangle(playerX,750,100,8)))
          {
            ballYdir = -ballYdir;

          }

          A: for(int i=0;i<map.map.length;i++)
          {
              for(int j=0;j<map.map[0].length;j++)
              {
                  if(map.map[i][j] > 0){
                      int brickX = j*map.brickWidth+170;
                      int brickY = i*map.brickHeight+50;
                      int brickWidth = map.brickWidth;
                      int brickHeight = map.brickHeight;
                      Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                      Rectangle ballRect = new Rectangle(ballposX,ballposY,14,14);
                      Rectangle brickRect = rect;

                      if(ballRect.intersects(brickRect))
                      {
                          music();


                          if(map.map[i][j] == 1)
                          {
                              score+=1;
                              totalBricks--;

                          }
                          else {
                               int x = map.map[i][j]-1;
                              if(x==1)
                                  score+=2;
                              if(x==2)
                                  score+=3;
                              if(x==3)
                                  score+=4;
                              if(x==4)
                                  score+=5;
                              if(x==5)
                                  score+=6;
                              if(x==6)
                                  score+=7;

                          }
                          map.setBrickValue(0,i,j);

                          if((ballposX<=brickRect.x) || (ballposX>=brickRect.x+brickRect.width))
                          {
                              ballXdir = -ballXdir;

                          }
                          else
                          {
                              ballYdir= -ballYdir;
                          }
                          break A;
                      }
                  }
              }
          }
          ballposX+=ballXdir;
          ballposY+=ballYdir;
          if(ballposX<12)
          {
              ballXdir = -ballXdir;
          }
          if(ballposY<12)
          {
              ballYdir = -ballYdir;
          }
          if(ballposX>1500)
          {
              ballXdir = -ballXdir;
          }
      }

      repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
              if(playerX >= 1430)
              {
                  playerX = 1430;
              }
              else
              {
                  moveRight();
              }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 20)
            {
                playerX = 20;
            }
            else
            {
                moveLeft();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play)
            {
                play = true;
                ballposX = (int) (Math.random()*(1500-100+1)+100);
                ballposY =  (int) (Math.random()*(700-400+1)+400);

                Random random = new Random();
                int n = random.nextInt(2+1-2) - 2;
                 ballXdir = n;
                 ballYdir = -2;
                playerX = 540;
                score =0;
                totalBricks = 14*28;


                map = new MapGenerator(14,28);
                repaint();
            }
        }


    }

    public void moveRight()
    {
        play = true;
        playerX+=20;
    }

    public void moveLeft()
    {
        play = true;
        playerX-=20;
    }
    public void music()
    {

        try {
            File file = new File("sound1.wav");
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void music2()
    {
        try {
            File file2 = new File("sound2.wav");
            AudioInputStream audio2 = AudioSystem.getAudioInputStream(file2);
            Clip clip2 = AudioSystem.getClip();
            clip2.open(audio2);
            clip2.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

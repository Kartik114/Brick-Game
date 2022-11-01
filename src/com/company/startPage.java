package com.company;


import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startPage
{
    static Toolkit t =  Toolkit.getDefaultToolkit();

    static int width = t.getScreenSize().width;
    static int height = t.getScreenSize().height;


    public  static void main(String[] args)
    {

       X obj = new X();

    }
    static class jpanel extends  JPanel
    {
        jpanel()
        {
            this.setBackground(Color.pink);
            Border border = BorderFactory.createLineBorder(Color.magenta,7,true);
            this.setBorder(border);

        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(Color.black);
            g.setFont(new Font("Arial",Font.BOLD,50));
            String s = "PLEASE GO THROUGH THESE INSTRUCTIONS";


            g.drawString(s,100,50);
            s="";
            g.drawString(s,100,100);
            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial",Font.ITALIC,28));
            s = "1. Press  \"ENTER\"  Key to start the game.";
            g.drawString(s,150,150);
            s = "2. Use Left and Right Arrow keys to move the paddle. ";
            g.drawString(s,150,200);
            s = "3. As You hit bricks with ball ,  the score increases on the board.";
            g.drawString(s,150,250);
            s = "4. Each brick has some different points with different colors.";
            g.drawString(s,150,300);
            s = "5. The Score of the coloured tiles are as follows :";
            g.drawString(s,150,350);
            s = "    White => 1  , Pink => 2  ,  Cyan => 3 , Green => 4 , Blue => 5 ";
            g.drawString(s,150,400);
            s = "    ,Orange => 6 , Magenta => 8.";
            g.drawString(s,150,450);
            s = "6. You will get larger paddle and ball with more score!";
            g.drawString(s,150,500);
            s = "7. Destroy all bricks to win it all.";
            g.drawString(s,150,550);
            s = "8. If ball falls down the window , You lost and game is over.";
            g.drawString(s,150,600);

            setBounds(100,100,1330,670);



        }

    }

    static class  jbutton extends  JButton{
        jbutton(String s)
        {
            this.setText(s);
            this.setForeground(Color.yellow);
            this.setBackground(Color.black);
            this.setFocusPainted(false);
            this.setMargin(new Insets(100, 50, 50, 50));
            Border border = BorderFactory.createLineBorder(Color.red,7,true);
            this.setBorder(border);


            this .setFont(new Font("serif",Font.BOLD,40));
        }
    }

    static class X extends JFrame
    {
        private static final int X = 100;  // location x
        private static final int Y = 650;   // location y
        private static final int heights = 100;      // size height
        private static final int widths = 150;       // size width

        public X()
        {

            jbutton b = new jbutton("PRESS HERE!");

            jpanel p1 = new jpanel();

            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JFrame obj = new JFrame();
                    Gameplay gameplay = new Gameplay();

                    obj.setBounds(-8, -4, 1600, 900);
                    obj.setTitle("Breakout Ball");
                    obj.setResizable(false);
                    obj.setVisible(true);
                    obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    obj.add(gameplay);

                    dispose();
                }
            });

             add(p1);
             p1.setLayout(null);

             add(b);


             setTitle("Welcome Page");
             setLayout(new FlowLayout());
             setVisible(true);
             setSize(width , height);

             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        }

    }

}


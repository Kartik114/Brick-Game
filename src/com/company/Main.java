package com.company;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {

        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();

        obj.setBounds(-8,-4,1600,900);
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}

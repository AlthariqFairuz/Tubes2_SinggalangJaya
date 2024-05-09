package com.singgalangjaya.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.singgalangjaya.utils.Utils1;

public class GameDisplay {
    public static void Display () {
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Akademi OOP");   
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Panel for matriks
            JPanel matrixPanel = new JPanel(new GridLayout(4,5,10,10));
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++) {
                    matrixPanel.add(new JTextField());
                }
            }

            // Panel untuk tombol
            JPanel buttonPanel = new JPanel(new GridLayout(6,3,10,10));
            JButton button1 = new JButton("Ladangku");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Utils1.tes();
                }
            });

            // Tambahkan MouseListener
            button1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button1.setBackground(Color.GREEN);  
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button1.setBackground(UIManager.getColor("control"));  
                }
            });

            buttonPanel.add(button1);

            JButton button2 = new JButton("Ladang Lawan");
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Mclaren lo warna apa bos");
                }
            });
            // Tambahkan MouseListener
            button2.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button2.setBackground(Color.GREEN);  
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button2.setBackground(UIManager.getColor("control"));  
                }
            });

            buttonPanel.add(button2);

            JButton button3 = new JButton("Lihat Ladang");
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Ladang lo warna apa bos");
                }
            });
            // Tambahkan MouseListener
            button3.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button3.setBackground(Color.GREEN);  
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button3.setBackground(UIManager.getColor("control"));  
                }
            });

            buttonPanel.add(button3);
            
            // Top Panel
            JPanel topPanel = new JPanel(new GridLayout(2,5,5,5));
            JButton button4 = new JButton("Next");
            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Next");
                }
            });
            // Tambahkan MouseListener
            button4.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button4.setBackground(Color.GREEN);  
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button4.setBackground(UIManager.getColor("control"));  
                }
            });
            topPanel.add(button4);


            // Tambahkan panel ke frame
            frame.add(matrixPanel, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.EAST);
            frame.add(topPanel, BorderLayout.NORTH);

            frame.pack();
            frame.setVisible(true);
        });
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RestClientPalocs.Gate;

import com.RestClientPalocs.Utils.CheckPanel;
import com.RestClientPalocs.Utils.EventPanel;
import com.RestClientPalocs.Utils.LogoPanel;
import com.RestClientPalocs.Utils.RoundedPanel;
import com.RestClientPalocs.Utils.VideoCap;
import com.RestClientPalocs.Utils.WebcamPanel;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author rez
 */
public class GateF extends javax.swing.JFrame {
		/**
	 * Creates new form GateF
	 */
	
//	private static final int height = 480;
//	private static final int width = 800;
	
	public GateF() {
		initComponents();
	}

    private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       // setBounds(0, 0, 1280, 720);
	        contentPane = new JPanel();
	        contentPane.setLayout(new GridLayout(2,2));
	        logoPanel = new LogoPanel();
//	        BufferedImage img = null;
//	        try {
//	            img = ImageIO.read(new File(System.getProperty("user.dir") + "/resources/img/polito-blue.png"));
//	        } catch (IOException e) {
//	        }
//	        if (img != null)	logoPanel.setIcon(new ImageIcon(resize(img,height/2,width/2)));  
//	        //jp2 = new JPanel();
	        eventPanel = new EventPanel();
	        jButton1 = new javax.swing.JButton();
	        //jp4 = new JPanel();
	        //jp4 = new WebcamPanel();
	        jl = new WebcamPanel();
	        //jp2.add(new RoundedPanel());
	        //jp2.setLayout(new FlowLayout());
	        //TextField t2 = new TextField("prova");
	        ckp = new CheckPanel();
	        //ckp.setForeground(Color.getColor("#1F2F61"));
	        contentPane.add(logoPanel);
	        contentPane.add(ckp);
	        contentPane.add(eventPanel);
	        contentPane.add(jl);
	        //contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
	        setContentPane(contentPane);

	        pack();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CheckPanel;
    private javax.swing.JPanel MessagePanel;
    private javax.swing.JButton jButton1;
    private JPanel contentPane;
    private LogoPanel logoPanel;
    //private JPanel jp2;
    private EventPanel eventPanel;
    //private JPanel jp4;
    private JPanel ckp;
    private WebcamPanel jl;
    //private VideoCap cam;
    // End of variables declaration//GEN-END:variables

	public void addButtonListener(ActionListener l) {
		// element1.addActionListener(l);
		((CheckPanel)ckp).getButton().addActionListener(l);
	}

	public void contentUpdate(Color c) {
		// TODO Auto-generated method stub
			System.out.print("Sto colorando");
			((CheckPanel) ckp).updateContent(c);
	}
	
	public WebcamPanel getWebCamPanel() {
		return (WebcamPanel) jl;
	}

	public BufferedImage getCamImage() {
		// TODO Auto-generated method stub
		return ((WebcamPanel)jl).getImage();
	}

	public void setEvent(String eventId) {
		// TODO Auto-generated method stub
		eventPanel.setEvent(eventId);
	}

	public void setTime() {
		// TODO Auto-generated method stub
		eventPanel.setTime();
	}
	
//	public BufferedImage resize(BufferedImage img, int newW, int newH) {
//		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
//		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
//
//		Graphics2D g2d = dimg.createGraphics();
//		g2d.drawImage(tmp, 0, 0, null);
//		g2d.dispose();	
//		return dimg;
//	}
	
}

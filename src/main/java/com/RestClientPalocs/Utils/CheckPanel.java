package com.RestClientPalocs.Utils;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class CheckPanel extends javax.swing.JPanel {
	private static final int INTERVAL = 1000;

	/**
	 * Creates new form CheckPanel
	 */
	public CheckPanel() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		GreenCircle = new RoundedPanel(300);
		RedCircle = new RoundedPanel(300);
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/resources/img/settings.png");
		jButton1 = new JButton(new ImageIcon(icon.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)));
		jButton1.setBackground(Color.GRAY);
		
		 setBackground(new java.awt.Color(31, 47, 97));	
		 setPreferredSize(new java.awt.Dimension(400, 240));
		 GreenCircle.setBackground(new java.awt.Color(31, 47, 97));
		 RedCircle.setBackground(new java.awt.Color(31, 47, 97));
		 javax.swing.GroupLayout GreenCircleLayout = new javax.swing.GroupLayout(GreenCircle);
	        GreenCircle.setLayout(GreenCircleLayout);
	        GreenCircleLayout.setHorizontalGroup(
	            GreenCircleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 100, Short.MAX_VALUE)
	        );
	        GreenCircleLayout.setVerticalGroup(
	            GreenCircleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 100, Short.MAX_VALUE)
	        );

	        RedCircle.setBackground(new java.awt.Color(31, 47, 97));

	        javax.swing.GroupLayout RedCircleLayout = new javax.swing.GroupLayout(RedCircle);
	        RedCircle.setLayout(RedCircleLayout);
	        RedCircleLayout.setHorizontalGroup(
	            RedCircleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 100, Short.MAX_VALUE)
	        );
	        RedCircleLayout.setVerticalGroup(
	            RedCircleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 100, Short.MAX_VALUE)
	        );


	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap(70, Short.MAX_VALUE)
	                .addComponent(GreenCircle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(58, 58, 58)
	                .addComponent(RedCircle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(72, 72, 72))
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addGap(0, 0, Short.MAX_VALUE)
	                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(jButton1)
	                .addGap(35, 35, 35)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(RedCircle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(GreenCircle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(77, Short.MAX_VALUE))
	        );
	    }// </editor-fold>   

//    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
//        // TODO add your handling code here:
//    }                       

	// Variables declaration - do not modify
	private RoundedPanel GreenCircle;
	private RoundedPanel RedCircle;
	private javax.swing.JButton jButton1;
	// End of variables declaration

	public void updateContent(Color c) {
		// TODO Auto-generated method stub
		if (c == Color.red) {
			blink(RedCircle, c);
		} else
			blink(GreenCircle, c);
	}

	private void blink(RoundedPanel circle, Color c) {
		// TODO Auto-generated method stub
		circle.setBack(c);
		circle.repaint();
		try {
			Thread.sleep(INTERVAL * 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		circle.setBack(null);
		circle.repaint();

	}
	
	public JButton getButton() {
		return jButton1;
	}
}
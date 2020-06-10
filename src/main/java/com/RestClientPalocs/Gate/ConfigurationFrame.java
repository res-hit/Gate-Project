package com.RestClientPalocs.Gate;

import java.awt.BorderLayout;
import com.RestClientPalocs.Model.Event;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ConfigurationFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboBox;
	JButton finish;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigurationFrame frame = new ConfigurationFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConfigurationFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSelectEvent = new JLabel("Select Event:");
		lblSelectEvent.setHorizontalAlignment(SwingConstants.LEFT);
		
		comboBox = new JComboBox();
		
		finish = new JButton("Finish");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(175)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(finish)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, Alignment.TRAILING, 0, 105, Short.MAX_VALUE)
								.addComponent(lblSelectEvent, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
							.addGap(160))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(lblSelectEvent, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addComponent(finish)
					.addContainerGap(77, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setList(List<Event> evs) {
		DefaultComboBoxModel<String> m = new DefaultComboBoxModel<>();
		m.addElement("-------"); //only way to fix not first element selected
		String[] es = evs.stream().map(e->e.getEventId()).toArray(String[]::new);
		for (String s : es)
		{ System.out.print(s+"\n"); m.addElement(s);}
		comboBox.setModel(m);
	}
	
	public void addDropDownMenuListener(ActionListener l) {
		comboBox.addActionListener(l);
	}
	
	public void addButtonListener(ActionListener l) {
		//element1.addActionListener(l); 
		finish.addActionListener(l);
	}
	
}

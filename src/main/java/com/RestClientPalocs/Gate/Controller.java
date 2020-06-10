package com.RestClientPalocs.Gate;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import javax.swing.JComboBox;
import javax.swing.Timer;

import com.RestClientPalocs.Client.RestClient;
import com.RestClientPalocs.Model.GateInfo;
import com.RestClientPalocs.Utils.VideoCap;
import com.RestClientPalocs.Utils.Exceptions.CannotRegisterGateException;
import com.RestClientPalocs.Utils.Exceptions.MissingMandatoryParameterException;

public class Controller /*implements ActionListener*/ {

	private GateF gate;
	private ConfigurationFrame cf;
	private RestClient cl;
	private static final String SETUP_FILE_PATH = System.getProperty("user.dir") + "/resources/files/setup.txt";
	private String eventId;
	private Login login;
	//private boolean logged = false;
	public CountDownLatch completeSignal;
	public BufferedImage img;
	
	
	public Controller(GateF gate2, ConfigurationFrame Cf, Login login) {
		// TODO Auto-generated constructor stub
		this.gate = gate2;
		this.cf = Cf;
		this.login = login;
		gate.addButtonListener(new SetupButtonListener());
		cf.addDropDownMenuListener(new SelectedEventListener());
		cf.addButtonListener(new FinishListener());
		login.addButtonListener(new LoginConfirm());
	}

	private void setUpTimer() {
		// TODO Auto-generated method stub
		int delay = 40; //milliseconds
		ActionListener taskPerformer = new TaskPerformer();
		    
		new Timer(delay, taskPerformer).start();
	}

	public void setClient(RestClient c) {
		this.cl = c;
	}
	
	class TimerTaskPerformer implements ActionListener {

		 public void actionPerformed(ActionEvent evt) {
	    	  gate.setTime();
	      }
	  		}
	
	class TaskPerformer implements ActionListener {

		 public void actionPerformed(ActionEvent evt) {
	    	  //System.out.print(gate.VidPanel.getComponent(0).toString());
	    	  gate.getWebCamPanel().repaint(); //must repaint only the WebcamPanel -> to do
	    	  img = gate.getCamImage();
	      }
	  		}


	class SetupButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			cf.setVisible(true);
			//otherwise the other thread popups elements in a not predictible order
			//can setup in a better way, for example loop of thread sleep, depending o a variable
			//as done in gatecheck
			try {Thread.sleep(500);} catch (Exception ex) {}
			login.getFrame().setVisible(true);
				// print on screen problem with connection, contact admin and close application
		}
		}


	class SelectedEventListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// cl.gateId();
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			eventId = (String) cb.getSelectedItem();
		}
	}

	class FinishListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// controlla se il file esiste, se sì, usa quello, altrimenti crealo
			cf.dispose();
			//GateCheck.setEvent(eventId); //?? non va sempre bene, serve una condizione
			
			//Se eventId è diverso da null, allora l'elemento è stato scelto, dalla combobox
				//Primo ramo: controlla che l'evento nel file, se esiste, sia diverso
				//altrimenti non fare nemmeno la richiesta ( magari aggiungi un popup)
				File f = new File(SETUP_FILE_PATH);
				String fileEId;
				if(f.exists() && !f.isDirectory()) { 
					List<List<String>> records = new ArrayList<>();
		            try{
		                Scanner scanner = new Scanner(f);
		                while(scanner.hasNextLine()){
		                    String line = scanner.nextLine();
		                    String[] values = line.split(",");
		                    if(values.length == 4) {
		                        fileEId = values[0] + "_" + values[1];
		                       //Not modify file and not send change to server
		                        //if the event selected is the same
		                        if (fileEId.equals(eventId)){
		                        	return;
		                        }
		                        //terzo ramo: il file esiste, ma si vuole cambiare id del gate
		                        else {
		        					setup(f);	
		                        }
		                       
		                    }
		                }
		                scanner.close();
				}catch (Exception exc) {
	                exc.printStackTrace();
	            }}
				//secondo ramo, il file non esiste, quindi bisogna configurare il gate
				//e risvegliare il thread lockato
				else{
				setup(f);
				completeSignal.countDown();		
				completeSignal = null;
				}
			}

		private void setup(File f) {
			
			try {
				GateInfo gi;
				gi = cl.setup(eventId, "operator", "keykey");
				gate.setEvent(eventId);
				cl.setup(eventId.split("_")[0], gi.getEventId(),gi.getGateId(),gi.getKey());
				FileWriter writer= new FileWriter(f,false);
                writer.append(eventId.split("_")[0]); //0
                writer.append(",");
                writer.append("").append(String.valueOf(gi.getEventId()));//1
                writer.append(",");
                writer.append("").append(String.valueOf(gi.getGateId()));//2
                writer.append(",");
                writer.append(gi.getKey());//3
                writer.append("\n");
                writer.flush();
                writer.close();
				} catch (CannotRegisterGateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MissingMandatoryParameterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}

	class LoginConfirm implements ActionListener {
		
		//Listener triggered when OK button pressed
		//gets text content from login window
		//checks if user and password is correct ( better request to server..)
		//if correct, setup list of events in combobox
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// Implement logic and dispose
			String[] ts = login.getOpInput();
			System.out.print(ts[0] + " " + ts[1] + "\n");
			if (!ts[0].equals("operator") || !ts[1].equals("keykey")) {
				System.out.print("Inside if\n");
				return;
			}
			try {
				cf.setList(cl.getJsonEvents(""));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			login.getFrame().dispose();
		}

	}

	/*public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}*/

	public void updateContent(Color c) {
		gate.contentUpdate(c);
	}

	public void startConfigurationFrame(CountDownLatch completeSignal/*, VideoCap cam*/) {
		cf.setVisible(true);
		try {Thread.sleep(500);} catch (Exception e) {} //see setupbuttonlistener
		login.getFrame().setVisible(true);
		//gate.setCamera(cam);
		setUpTimer();
		// cf.pack();
		// cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.completeSignal= completeSignal;
	}

	public void setEvent(String eventId) {
		// TODO Auto-generated method stub
		this.eventId = eventId;
		gate.setEvent(eventId);
		setUpTimeTimer();
		
	}

	private void setUpTimeTimer() {
		// TODO Auto-generated method stub
		int delay = 1000; //milliseconds
		ActionListener taskPerformer = new TimerTaskPerformer();
		    
		new Timer(delay, taskPerformer).start();
		
	}

	public BufferedImage getCameraImage() {
		// TODO Auto-generated method stub
		return gate.getCamImage();
	}
}

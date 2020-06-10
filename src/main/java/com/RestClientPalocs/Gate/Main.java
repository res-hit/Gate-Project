package com.RestClientPalocs.Gate;

//import java.util.Timer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.Timer;

import com.RestClientPalocs.Utils.VideoCap;

public class Main {
	
	//Todo -> do not stop execution if operator log-in is closed, or selection window is closed
	//After you change event first time, if you try to change other ones, you can skip the login
	//and try to re-register -> must be fixed
	
	//Make operator password not visible, fix window pop up 
	
	//Todo-> add real time video window/frame
	
	//Todo-> set another interval for longer times, for example if the device is not
	//shut down

	//private static int INTERVAL = 1000;
	private static GateF gate;
	private static final int height = 800;
	private static final int width = 480;
	private static ConfigurationFrame cf;
	private static Login login;
	//private static VideoCap cam;

	public static void main(String[] args) {
		CountDownLatch completeSignal = new CountDownLatch(1);

		EventQueue.invokeLater(new Runnable() {
			public void run() {		
				try {
					//cam = new VideoCap(); !!!!!!!!!!!!Per colpa di questo ho vagato inutilmente delle ore. 
					//Il riferimento alla cam esiste già dentro al panel e non può essere richiesto altrove.
					//Un modo opportuno di gestire tutto quanto sarebbe quella di usare un riferimento che abbia visibilità globale sulle classi
					//ed accedere ad esso in mutua esclusione. Una volta che il riferimento alla cam è ottenuta, per 
					//liberarlo bisogna utilizzare release ( o a quanto pare close). Problema, è inefficiente.
					//meglio utilizzare un unico riferimento per l'elemento che ha bisogno del framerate più alto
					//e rendere disponibili le immagini per gli altri dispositivi attraverso dei duplicati, ma genera problemi 
					//di sincronizzazione
					gate = new GateF();
					gate.setSize(height,width);
					gate.setVisible(true);	
					cf = new ConfigurationFrame();
					login = new Login();
					completeSignal.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//SLEEP TO GIVE GUI THREAD TIME TO SETUP, OTHERWISE CONTROLLER INSTATIATION FAILS
		//try{Thread.sleep(INTERVAL);}catch(InterruptedException e) {e.printStackTrace();}
		//System.out.print(gate);
		try {
			completeSignal.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Controller c = new Controller(gate,cf,login);
		GateCheck.start(c);
		
	}
}
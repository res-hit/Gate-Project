package com.RestClientPalocs.Gate;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import com.RestClientPalocs.Client.RestClient;
import com.RestClientPalocs.Model.ReservationRequest;
import com.RestClientPalocs.Utils.VideoCap;

public class GateCheck {

	private static int INTERVAL = 1000;
	private static int LOW_FREQ_TH = 20;
	//private static GateFrame gate;
	//private static Controller controller;
	//private static VideoCap cam;
	//private static String event;
	private static final String SETUP_FILE_PATH = System.getProperty("user.dir")+"/resources/files/setup.txt";

	public static void start(Controller controller) {
		try{
			RestClient c = new RestClient();
			BufferedImage image;
			ReservationRequest response=null;
			String msg="";
			File f;
			CountDownLatch completeSignal = new CountDownLatch(1);
			
			controller.setClient(c);
			
			f = new File(SETUP_FILE_PATH);
			
			if(f.exists() && !f.isDirectory()) { 
				//Take informations from files and pass to client
				//Not sending request to server
	                readFile(f,controller,c);
			}else {
            	//If no setup exist popup window configuration, login needed
				controller.startConfigurationFrame(completeSignal/*,cam*/);
				try {
					completeSignal.await();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
			//COUNTER USED FOR 2 MODES OF TAKING PICTURES
			int cnt=0;
			
			//CAM SNAPSHOT ITERATIONS
			//INFINITE LOOP + THREAD SLEEPS + DIFFERENT SNAPSHOT FREQ TO REDUCE
			//ENERGY CONSUMPTION AND RESOURCE USAGE
			while(true) {
				
				
				//controller.updateContent(Color.DARK_GRAY);
				image = controller.getCameraImage();
				if (image == null) //Added for synch issue. First call of update do not create an image
				{
				try{Thread.sleep(10);}catch(Exception e){} //add for second issue. The image is not ready, with rapid iterations
					continue;								//still adding sleep is not a safe operation-> try to fix with await
				}
				try {
					msg = com.RestClientPalocs.Utils.QRCodeGenerator.decodeQRCode(image);
				}catch (Exception e) {
					//e.printStackTrace();
				}
				//System.out.print(msg+"\n");

				//CASE THERE IS NO QR CODE IN THE IMAGE
				//IF FOR MORE ITERATIONS ( COUNTED BY CNT) THERE IS NO
				//QR CODE DETECTED, TAKE PICTURES WITH LESS FREQUENCY
				if (msg==null) { 
					cnt++;
					if (cnt < LOW_FREQ_TH) {
						try{Thread.sleep(INTERVAL/2);}catch(InterruptedException e) {e.printStackTrace();}
					}
					else
						try{Thread.sleep(INTERVAL*2);}catch(InterruptedException e) {e.printStackTrace();}
					continue;}
				//SET THE COUNTER TO ZERO IF A QR CODE IS DETECTED
				cnt = 0;
				//CHECK QR CODE CONTAINS NAMEEVENT_ID ID
				if (Array.getLength((msg.split(" "))) != 2)  {
					controller.updateContent(Color.RED);
					System.out.print("Invalid format\n");
					continue;
				}
				//CHECK NAMEEVENT_N HAS RIGHT FORMAT
				if (Array.getLength((msg.split(" ")[0].split("_"))) != 2){
					controller.updateContent(Color.RED);
					System.out.print("Invalid format\n");
					continue;
				} 
				//QUERY THE SERVER
				try{response = c.getJsonReservationRequest(msg.split(" ")[0],
						Integer.parseInt(msg.split(" ")[1]));
				}
				catch(Exception e) {
					e.printStackTrace();
					controller.updateContent(Color.RED);
					//System.out.print("Invalid request\n");
					
					//DO SOMETHING IF SERVER IS OFFLINE (ERROR 404)
					//DO SOMETHING IF SYSTEM DATE IS WRONG
					
					continue;
				}
				//SIGNIFICANT DATA, CHECK IF EQUALS MSG CONTENT
				//IF POSITIVE-> GREEN
				if (response.getEventId().equals(msg.split(" ")[0]) &&
						response.getId() == Integer.parseInt(msg.split(" ")[1]))
				{
						controller.updateContent(Color.GREEN);
					
				}
				//IF NO MATCH, SIGNIFICANT DATA BUT NOT THE EXPECTED
				//FROM SERVER -> RED 
				//+OTHER CASES
				else{
					
						controller.updateContent(Color.RED);
				}
			}
		}
		finally {
			//CLOSE WEBCAM ALWAYS IN CASE OF APPLICATION FAILURE
			//cam.close();
		}
	}

	private static void readFile(File f, Controller controller, RestClient c) {
		// TODO Auto-generated method stub
		try {
		String eventId=null,key;
		int gateId;
		Scanner scanner = new Scanner(f);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(",");
            if(values.length == 4) {
                eventId = values[0] + "_" + values[1];
                gateId = Integer.parseInt(values[2]);
                key = values[3];
                controller.setEvent(eventId);
                c.setup(values[0],Integer.parseInt(values[1]),gateId, key);
            }
        }
        scanner.close();
		
	}catch (Exception e) {
        e.printStackTrace();
    	}		
	}
	
}	

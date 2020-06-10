package com.RestClientPalocs.Client;

import com.RestClientPalocs.Model.AccessRecording;
import com.RestClientPalocs.Model.GateInfo;

import com.RestClientPalocs.Utils.Exceptions.*;

import com.RestClientPalocs.Model.Event;
import com.RestClientPalocs.Model.ReservationRequest;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class RestClient {

    private static final String REST_URI = "http://192.168.1.95:8080/RestPalocs/palocs";
   //private static String REST_URI = "http://37.183.22.109:1997/RestPalocs/palocs";
	//private static String REST_URI = "http://188.153.169.207:8080/RestPalocs/palocs";
    //private static final String REST_URI = "http://localhost:8080/RestPalocs/palocs/events";
    private static final String SETUP_FILE_PATH = System.getProperty("user.dir")+"\\src\\main\\java\\com\\RestClientPalocs\\Files\\setupFile.txt";
    private Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
    private WebTarget target;

    private Integer gateId ;
    private String key = "";            //todo as above
    //private String eventId = "";
    private String eventId = "Monet_1"; //todo as above

    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static void main(String[] args){
        RestClient client = new RestClient();
        /*InetAddress address = null;
         * String port = ":1997"
         * path = /RestPalocs/palocs
        try {
			 address = InetAddress.getByName("www.systemevents.group2palocs2020.it");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        REST_URI = "http://"+address.toString()+port+path;
        System.out.print(REST_URI+"\n");
        */
        try {
            client.getJsonReservationRequest(client.eventId, 1);
        } catch (WrongDateOrTimeException e) {
            e.printStackTrace();
        } catch (ServerErrorException e) {
            e.printStackTrace();
        } catch (NotExistingException e) {
            e.printStackTrace();
        } catch (MissingMandatoryParameterException e) {
            e.printStackTrace();
        } catch (NotMatchingSetupParametersException e) {
            e.printStackTrace();
        }
    }

    public void setup(String eventName, Integer id, Integer gateId, String key) throws MissingMandatoryParameterException {
        if(eventName == null || id == null || gateId == null || key == null)
            throw new MissingMandatoryParameterException("Arguments cannot be null");
        eventId = eventName+"_"+id;
        this.gateId = gateId;
        this.key = key;
    }

    public GateInfo setup(String eventId, String operator, String operatorKey) throws CannotRegisterGateException {
        GateInfo gi = new GateInfo();
        gi.setEventId(Integer.parseInt(eventId.split("_")[1]));
        //gi.setOperator("operator");
        //gi.setOperatorKey("keykey");
        gi.setOperator(operator);
        gi.setOperatorKey(operatorKey);
        try {
        if(gateId != -1 && gateId != null) {
            Response del = target.path("events").path(eventId).path("gate").path(gateId.toString())
                    .request(MediaType.APPLICATION_JSON)
                    .delete();
            //todo importa se l'operazione ha avuto successo o meno?
        }
        }catch(NullPointerException e) {
        	System.out.print("Not already registered\n");
        }

        Response res = target.path("events").path(eventId).path("gates")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(gi));

        int status = res.getStatus();
        if(status != 200 && status != 201 && status != 204){
            System.out.println("errore non recuperabile, fallirà tutto!");
            throw new CannotRegisterGateException("Error in registering the gate for the requested event");
        } else{
            gi = res.readEntity(GateInfo.class);
            /*f = new File(SETUP_FILE_PATH);
            try {
                System.out.println(SETUP_FILE_PATH);
                if(f.createNewFile()){
                    System.out.println("File created!");
                } else {
                    System.out.println("File already exists.");
                }

                FileWriter writer= new FileWriter(f,true);

                BufferedWriter out = new BufferedWriter(writer);
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
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            System.out.print(gateId+key+"\n");
            System.out.print(gi.toString());
            return gi;
        }
    }



    public RestClient(){
        target = client.target(REST_URI);
        /*File f = new File(SETUP_FILE_PATH);

        boolean askServer = true;
        if(f.exists() && !f.isDirectory()){
            List<List<String>> records = new ArrayList<>();
            try{
                Scanner scanner = new Scanner(f);
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    String[] values = line.split(",");
                    if(values.length == 4) {
                        eventId = values[0] + "_" + values[1];
                        gateId = Integer.parseInt(values[2]);
                        key = values[3];
                    }
                }
                scanner.close();
                askServer = false;
                System.out.println("NON HO LETTO DA SERVER :");
                System.out.println("eventID ---> " + eventId );
                System.out.println("gateId ---> " + gateId);
                System.out.println("key ---> " + key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(askServer){
            GateInfo gi = new GateInfo();
            gi.setEventId(Integer.parseInt(eventId.split("_")[1]));
            gi.setOperator("operator");
            gi.setOperatorKey("keykey");

            Response res = target.path(eventId).path("gates")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(gi));

            int status = res.getStatus();
            if(status != 200 && status != 201 && status != 204){
                System.out.println("errore non recuperabile, fallirà tutto!");
            } else{
                gi = res.readEntity(GateInfo.class);
                f = new File(SETUP_FILE_PATH);
                try {
                    System.out.println(SETUP_FILE_PATH);
                    if(f.createNewFile()){
                        System.out.println("File created!");
                    } else {
                        System.out.println("File already exists.");
                    }

                    FileWriter writer= new FileWriter(f,true);

                    BufferedWriter out = new BufferedWriter(writer);
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

    public boolean isServerUp(){
        Response res = target.request().head();
        int status = res.getStatus();
        return status == 200;
    }

    public List<Event> getJsonEvents(String keyword) throws ServerErrorException {
        List<Event> retVal = new ArrayList<>();
        Response res = target.path("events")
                .queryParam("keyword",keyword)
                .request(MediaType.APPLICATION_JSON)
                .get();
        int status = res.getStatus();
        if(status != 200){
            System.out.println("Errore : status code -> "+status);
            throw new ServerErrorException("Internal Server error : "+res.getStatus());
        }else {
            retVal = res.readEntity(new GenericType<List<Event>>(){});
        }
        return retVal;
    }

    public Event getJsonEvent(String event_id) throws ServerErrorException, NotExistingException {
        Event retVal = new Event();
        Response res = target.path("events").path(event_id).request(MediaType.APPLICATION_JSON).get();
        //if(res.getStatus()!= 200){
            //System.out.println("Errore : status code -> "+res.getStatus());
        //}
        int status = res.getStatus();
        if(status == 500) {
            System.out.println("Errore : status code -> "+status);
            throw new ServerErrorException("Internal Server error : "+status);
        } else if (status == 404){
            System.out.println("Errore : status code -> "+status);
            throw new NotExistingException("Event not found : "+status);
        } else {
            retVal = res.readEntity(Event.class);
        }
        return retVal;
    }

    public List<ReservationRequest> getJsonReservationRequests(String eventId,String keyword) throws ServerErrorException, NotMatchingSetupParametersException {
        List<ReservationRequest> retVal = new ArrayList<>();
        if(!this.eventId.equals(eventId))
            throw new NotMatchingSetupParametersException("Cannot request reservations for another event");
        Response res = target
                .path("events").path(eventId).path("reservations")
                .queryParam("keyword",keyword)
                .request(MediaType.APPLICATION_JSON)
                .get();
        int status = res.getStatus();
        if(res.getStatus() != 200){
            System.out.println("Errore : status code -> "+res.getStatus());
            throw new ServerErrorException("Internal server error : "+status);
        }else {
            retVal = res.readEntity(new GenericType<List<ReservationRequest>>(){});
        }
        return retVal;
    }

    public ReservationRequest createJsonReservationRequest(String eventId,
                                                           Integer numberOfPeople,
                                                           String owner,
                                                           String pricing,
                                                           String accessSlot,
                                                           String date) throws MissingMandatoryParameterException, ServerErrorException, NotMatchingSetupParametersException {
        if(!this.eventId.equals(eventId))
            throw new NotMatchingSetupParametersException("Cannot create a reservation for another event!");
        ReservationRequest r = new ReservationRequest();
        if(eventId == null || owner == null || owner.length()<=0 || numberOfPeople<= 0 || date == null || date.length() <= 0)
            throw new MissingMandatoryParameterException();
        r.setEventId(eventId);
        r.setAccessSlot(accessSlot);
        r.setNumberOfPeople(numberOfPeople);
        r.setPricingType(pricing);
        r.setReservationOwner(owner);
        r.setDate(date);

        Response res = target
                .path("events").path(eventId).path("reservations")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(r));
        int status = res.getStatus();
        System.out.println("Status code -> "+status);
        if(status == 400){
            throw new MissingMandatoryParameterException("Error in the posted object : "+status);
        }else if(status== 500){
            throw new ServerErrorException("Internal server error : "+status);
        }
        /*if(status!= 200 && status!= 201){
            System.out.println("Errore : status code -> "+status);
        }*/ else if(status == 200 || status == 201) {
            r = res.readEntity(ReservationRequest.class);
        } else {
            throw new ServerErrorException("Internal server error : "+status);
        }
        return r;
    }

    public ReservationRequest getJsonReservationRequest(String eventId, Integer id) throws ServerErrorException, NotExistingException, WrongDateOrTimeException, NotMatchingSetupParametersException, MissingMandatoryParameterException {
        if(!this.eventId.equals(eventId))
            throw new NotMatchingSetupParametersException("Cannot request reservations for another event");
        if( eventId == null || id == null)
            throw new MissingMandatoryParameterException("Parameters cannot be null");

        ReservationRequest retVal = new ReservationRequest();
        Response res = target.path("events").path(eventId).path("reservations").path(id.toString())
                .request(MediaType.APPLICATION_JSON)
                .get();

        int status = res.getStatus();
        if(status == 500){
            throw new ServerErrorException("Internal server error : "+status);
        }else if(status == 404){
            throw new NotExistingException("Reservation not found : "+status);
        }
        /*if(status != 200){
>>>>>>> branch 'gate-base' of https://github.com/VittorioDiLeo21/palocs.git
            System.out.println("Errore : status code -> "+status);
        }*/else if(status == 200) {
            retVal = res.readEntity(ReservationRequest.class);
            String date = LocalDate.now().format(dateFormatter);
            //String date = "2020-04-20";   //TODO added for dbg 
            LocalTime now = LocalTime.now();
            if(!date.equals(retVal.getDate()))
                throw new NotExistingException("Reservation not found for this date!");
            LocalTime as = LocalTime.parse(retVal.getAccessSlot(),timeFormatter);
            if(as.isAfter(now))
                throw new WrongDateOrTimeException("Reservation expired!");

            String time = now.format(timeFormatter);
            //String time = "09:50:00";       //TODO added for dbg
            AccessRecording ar = new AccessRecording();
            ar.setGateId(gateId);
            ar.setKey(key);
            ar.setDate(date);
            ar.setTime(time);
            ar.setReservationId(retVal.getId());
            String[] eid = retVal.getEventId().split("_");
            ar.setEventId(Integer.parseInt(eid[1]));
            ar.setEventName(eid[0]);

            Response res2 = target.path("events").path(eventId).path("access")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(ar));
            status = res2.getStatus();
            if(status!= 204){
                //System.out.println("Errore2 : status code -> "+status);
                throw new ServerErrorException("Errore : status code -> "+status);
            } else {
                System.out.println("Ok, access registered!" + status);
            }
        }
        return retVal;
    }
    
}

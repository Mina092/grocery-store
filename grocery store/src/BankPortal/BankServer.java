package BankPortal;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class BankServer {

    // initialize socket and input stream
    // private Socket socket = null;
    private ServerSocket server = null;
    // private DataInputStream in = null;
    // private ServerSocket mServer;
    private int serverPort = 9090;
    private ArrayList<Thread> threads = new ArrayList<Thread>();
    private HashMap<String, ClientManager> clientsMap = new HashMap<String, ClientManager>();
    
    final static String fromEmail = "rm13822831@gmail.com"; // requires valid gmail id
    final static String password = "sspnznucrbmrqdqv"; // correct password for gmail id

    // constructor with port
    public BankServer(int port) {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            while (true) {
                Socket client = server.accept();

                System.out.println("New client accepted");

                // create new thread to manage each client separately and
                // simultaneously
                Thread t = new Thread(new ClientManager(this, client));

                // add Thread to "threads" list
                threads.add(t);

                // start thread
                t.start();

            }

        } catch (IOException i) {
            // System.out.println(i);
        }
    }

    public void addClientManager(String clientName, ClientManager cm) {
        clientsMap.put(clientName, cm);
    }

    public static void send(String from, String password, String to, String sub, String msg) {
        // Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        // get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            // send message
            Transport.send(message);
            System.out.println("message sent successfully");
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        BankServer server = new BankServer(5000);
    }
}

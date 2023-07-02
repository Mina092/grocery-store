package BankPortal;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ClientManager implements Runnable{

    Socket clientHolder;
	BankServer serverHolder;
	InputStream fromClientStream;
	OutputStream toClientStream;

	DataInputStream reader;
	PrintWriter writer;

    String toEmail; // can be any email id

	public ClientManager(BankServer server,Socket client) {
		serverHolder = server;
		clientHolder = client;	
	}

	public void run() {
		try {
			// input stream (stream from client)
			fromClientStream = clientHolder.getInputStream();

			// output stream (stream to client)
			toClientStream = clientHolder.getOutputStream();

			reader = new DataInputStream(fromClientStream);
			writer = new PrintWriter(toClientStream, true);

			
			String name = "client" + hashCode();
			
			//add "this" to Server "clientsMap" HashMap
			serverHolder.addClientManager(name,this);
			
			while (true) {
				// read command from client
				String command = reader.readUTF();

				if (command.startsWith("Email:")) {
                    toEmail = command.substring(6);
                    System.out.println("Sending email to: "+toEmail);
					BankServer.send(BankServer.fromEmail, BankServer.password, toEmail,"Bank Payment","Your payment done Successfully.");
					
                    break;
				}
			}
		} catch (Exception e) {
		}
	}
    
}

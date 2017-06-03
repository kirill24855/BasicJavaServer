package TwoWayServer;

import java.io.*;
import java.net.*;

/**
 * This Two-Way server and client is based off a simple console chat service.
 * Tested it out and seems to work fine.
 * If you want to see the original code see the attached site.
 * http://way2java.com/networking/chat-program-two-way-communication/
 */

public class TwoWayServer {

	private static final int PORT = 4444;

	public static void main(String[] args) throws Exception {
		ServerSocket serSock = new ServerSocket(PORT);
		System.out.println("Server started");
		Socket sock = serSock.accept();

		//Sending to client
		OutputStream ostream = sock.getOutputStream();
		PrintWriter pwrite = new PrintWriter(ostream, true);

		//Receiving from client
		InputStream istream = sock.getInputStream();
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

		String receiveMessage;
		String sendMessage;

		/*
		 * Keeps connection active by checking constantly if the client sent anything to the server
		 * and can send messages back.
		 */
		while (true) {
			if((receiveMessage = receiveRead.readLine()) != null) {
				sendMessage = processInput(receiveMessage);
				pwrite.println(sendMessage);
				pwrite.flush(); //Flush data from memory
			}
			if (receiveMessage.equals("exit")) break;
		}
	}

	private static String processInput(String input) {
		return input;
	}
}

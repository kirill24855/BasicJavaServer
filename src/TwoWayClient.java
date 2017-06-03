import TwoWayServer.TwoWayServer;
import java.io.*;
import java.net.*;

/*
 * This Two-Way server and client is based off a simple console chat service.
 * Tested it out and seems to work fine.
 * If you want to see the original code see the attached site.
 * http://way2java.com/networking/chat-program-two-way-communication/
 */

public class TwoWayClient
{
	
	private static final int PORT = 4444;
	
	public static void main(String[] args)
	{
		// Tries to connect with server via IP address and port given
		Socket sock = null;
		try
		{
			// Might want to look into seeing how to properly close the socket if something goes wrong.
			// Never know if a program wants to use the same IP and port.
			sock = new Socket("127.0.0.1", PORT);
		}
		catch (UnknownHostException e)
		{
			System.err.println("Unknown Host");
			System.exit(1);
		}
		catch (IOException e)
		{
			System.err.println("Could not listen on port: " + PORT);
			System.exit(1);
		}
		
		
		// Read from keyboard
		BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
		
		// Sending to client
		OutputStream ostream = null;
		try 
		{
			ostream = sock.getOutputStream();
		} 
		catch (IOException e)
		{
			System.err.println("Couldn't send anything to client");
			System.exit(1);
		} 
		
		PrintWriter pwrite = new PrintWriter(ostream, true);
		
		// Recieving from server
		InputStream istream = null;
		try 
		{
			istream = sock.getInputStream();
		}
		catch (IOException e)
		{
			System.err.println("Could not recieve information from server");
			System.exit(1);
		}
		
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

		System.out.println("Start the chat, type and press Enter key");
		String receiveMessage = null, sendMessage = null;               
		/*
		 * This keeps the connection active by constantly checking to see if any messages
		 * is being sent by the server. It also sends messages to server.
		 */
		while(true)
		{
			try {
				sendMessage = keyRead.readLine(); //Keyboard reading
			} catch (IOException e) {
				System.err.println("Could not send message");
				System.exit(1);
			}
			
			pwrite.println(sendMessage);       // Sending to server
			pwrite.flush();                    // Flush data from memory
			
			try {
				if((receiveMessage = receiveRead.readLine()) != null) //Receive from server
				{
					System.out.println(receiveMessage); // Display console
				}
			} catch (IOException e) {
				System.err.println("recieveMessage wasn't initialize");
				System.exit(1);
			}         
		}
	}
	
}

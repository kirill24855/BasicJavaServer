package TwoWayServer;
import java.io.*;
import java.net.*;

/*
 * This Two-Way server and client is based off a simple console chat service.
 * Tested it out and seems to work fine.
 * If you want to see the original code see the attached site.
 * http://way2java.com/networking/chat-program-two-way-communication/
 */

public class TwoWayServer 
{
	
	private static final int PORT = 4444;
	
	public static void main(String[] args) throws Exception
	{
		ServerSocket sersock = new ServerSocket(PORT);
		System.out.println("Server  ready for chatting");
		Socket sock = sersock.accept( );                          
		
		//Reads inputs from keyboard
		BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
		
		//Sending to client
		OutputStream ostream = sock.getOutputStream(); 
		PrintWriter pwrite = new PrintWriter(ostream, true);

		//Receiving from server
		InputStream istream = sock.getInputStream();
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

		String receiveMessage, sendMessage;               
		
		/*
		 * Keeps connection active by checking constantly if the client sent anything to the server
		 * and can send messages back.
		 */
		while(true)
		{
			if((receiveMessage = receiveRead.readLine()) != null)  
			{
				System.out.println(receiveMessage);         
			}         
			sendMessage = keyRead.readLine(); 
			pwrite.println(sendMessage);             
			pwrite.flush(); //Flush data from memory
		}               
	}
}

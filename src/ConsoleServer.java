import KnockKnockProtocol.KnockKnockProtocol;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConsoleServer {

	private static final int PORT = 4444;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + PORT);
			System.exit(1);
		}

		Socket socket = null;
		try {
			socket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(
			new InputStreamReader(
				socket.getInputStream()
			)
		);
		String inputLine, outputLine;
		KnockKnockProtocol kkp = new KnockKnockProtocol();

		/*outputLine = kkp.processInput("null");
		out.println(outputLine);*/

		while (true) {
			inputLine = in.readLine();
			outputLine = kkp.processInput(inputLine);
			out.println(outputLine);
			if (outputLine.equals("break")) break;
		}

		out.close();
		in.close();
		socket.close();
		serverSocket.close();
	}
}

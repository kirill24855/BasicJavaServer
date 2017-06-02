import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private final static int PORT = 4444;

	public static void main(String[] args) throws Exception {
		Scanner systemScan = new Scanner(System.in);
		Socket clientSocket = new Socket("127.0.0.1", PORT);
		Scanner serverScan = new Scanner(clientSocket.getInputStream());

		System.out.println("Enter string");
		String number = systemScan.nextLine();

		PrintStream stream = new PrintStream(clientSocket.getOutputStream());
		stream.println(number);
		System.out.println(serverScan.nextLine());
	}
}

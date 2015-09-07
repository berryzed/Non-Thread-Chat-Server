package kr.berryz.nonthreadchatserver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Berryzed
 *
 */
public class NonThreadChatServer
{
	public static void main(String[] args)
	{
		BufferedReader in = null;
		BufferedReader stin = null;
		BufferedWriter out = null;
		ServerSocket listener = null;
		Socket socket = null;
		
		try
		{
			listener = new ServerSocket(9999); // Create server socket
			socket = listener.accept(); // Wait client request
			
			System.out.println("Client Connected!");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // BufferedReader for client
			stin = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader for Keyboard
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // BufferedWriter for Client
			String inputMessage;
			
			while (true)
			{
				inputMessage = in.readLine(); // Read line from client
				if (inputMessage.equalsIgnoreCase("bye")) // Disconnect when client sent "bye"
					break;
				System.out.println(inputMessage);
				String outputMessage = stin.readLine();
				out.write("Server>" + outputMessage + "\n");
				out.flush();
			}
		}
		catch (IOException e)
		{
			System.out.println("error:" + e.getMessage());
		}
		finally
		{
			try
			{
				socket.close(); // Close Socket
				listener.close(); // Close ServerSocket
			}
			catch (IOException e)
			{
				System.out.println("error:" + e.getMessage());
			}
		}
	}
}

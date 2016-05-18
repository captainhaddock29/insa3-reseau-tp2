package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientTCP{

	int server_port;
	InetAddress server_addr;

	
	
	public ClientTCP(int port, InetAddress addr) {
		this.server_port = port;
		this.server_addr = addr;
		try {
			Socket s = new Socket(this.server_addr, this.server_port);
			ThreadLecture thL = new ThreadLecture(s);
			ThreadEcriture thE = new ThreadEcriture(s);
			thL.start();
			thE.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public static void main (String argv[]) {
		try {
			ClientTCP client = new ClientTCP(8888,InetAddress.getByName("localhost"));
			//client.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
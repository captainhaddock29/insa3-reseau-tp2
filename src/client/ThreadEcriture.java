package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadEcriture extends Thread{

	/**
	 * Pour écrire sur le serveur
	 */
	
	private Socket socket;		
	private PrintWriter output;
	private BufferedReader input;
	private String message;
	
	public ThreadEcriture(Socket s){
		
		socket = s;
		
	}
	
	public void run() {
		//	socket = new Socket(this.server_addr, this.server_port);
			message = "Entrée sur le chat";
		while(!message.equals("QUIT")){
			try {
				
				System.out.println("A vous d'écrire : ");
				Scanner sc = new Scanner(System.in);
				message = sc.nextLine();
				output = new PrintWriter(socket.getOutputStream(), true); 
				output.println(message);
				
				
				
			} catch (IOException e) { e.printStackTrace(); }
			
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

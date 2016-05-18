package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadLecture extends Thread{

	/**
	 * Pour lire ce qu'envoie le serveur
	 */
	private Socket socket;		
	private PrintWriter output;
	private BufferedReader input;
	private String message;
	
	public ThreadLecture(Socket s){
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
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while((message = input.readLine())!=null)
				{
					System.out.println(message);
					if(message.equals("QUIT"))
					{
						break;
					}
				}
				
				
				
				
				
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

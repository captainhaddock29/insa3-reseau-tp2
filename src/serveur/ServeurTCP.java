package serveur;

import java.net.*;
import java.sql.Time;
import java.io.*;
import java.util.*;

public class ServeurTCP
{
    static String nomServeur ="-- Serveur TCP Java --";

    static int port = 8888;  // port par défaut
	static long time_spent = 0;
    static int nb_requetes = 0;
    static ServerSocket socket;
    static Socket client;
    static int nbClient = 0;
    
    protected static ArrayList<Socket> listSock;

    static void usage(){
    	message("Usage :\n java ServeurTCP [port]\n");
    }

    static void message(String msg){
	System.err.println(msg);
    }

    static void erreur(String msg){
    	message("Erreur: "+msg);
    }

    static void erreur(){
    	erreur(null);
    }

    static String date(){
    	// ou System.currentTimeMillis()
    	Date d= new Date();
	
    	return d.toString();
    }


    public static void main (String argv[]) {
	
		if (argv.length == 1 ){
		    port = Integer.parseInt(argv[0]);
		} else if ( argv.length >= 1 )
		    usage();
		
	
		listSock = new ArrayList<Socket>();
		// Création de la socket
		try {socket = new ServerSocket(port);} catch (IOException e){
		erreur("Impossible d'ouvrir le port "+port+":"+e);}	
	    System.out.println("Démarrage Serveur sur le port "+port);
	    Thread thr;
		
		while( true ) { // Attente de connexion
			
			try {
				client = socket.accept();
				System.out.println("Un nouveau client est arrivé.");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Thread th = new Thread(){
				private int nbC = nbClient;
				public void run(){
					Socket s = client;
					String requete = null;
					BufferedReader in = null;
					PrintWriter out = null;
					
					System.out.println("Serveur ok");
					while(true){
						try {
							in = new BufferedReader(new 
									InputStreamReader(s.getInputStream()));  
							
						} catch (IOException e){
							erreur("Lecture socket "+e);
						}
						try { requete = in.readLine();} catch (IOException e){
							erreur("lecture "+e);
						}
						System.out.println("Client "+nbC+" : "+requete);
						for(Socket s1 : listSock)
						{
							try {
								out = new PrintWriter(s1.getOutputStream(),true);
								out.println("Client "+nbC+" : "+requete);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						
						if(requete.equals("QUIT"))
						{	
							try {
								s.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Thread.currentThread().interrupt(); // Très important de réinterrompre
							System.out.println("Le client s'est fait tejjjjj");
							
			                break;
						}
					}
				}
			};
			nbClient++;
			listSock.add(client);
			th.start();
			
		}
    }
}

package urjc.ist.echoservice;

import java.io.*;
import java.util.Scanner;
import java.net.Socket;
import java.util.NoSuchElementException;

public class WorkerRunnable implements Runnable {

	private Socket clientSocket;	
	
	public WorkerRunnable(){
		super();
	}
	
	public WorkerRunnable(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run(){
		Scanner scanner;
        PrintWriter out;
        final String CHARSET_NAME = "UTF-8";
		
		try {
            InputStream is = clientSocket.getInputStream();
            scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Imposible crear " + clientSocket, ioe);
        }

        // nextLine() se bloquea hasta recibir una nueva línea del cliente
        String line = null;
        try {
            line = scanner.nextLine();
        }
        catch (NoSuchElementException e) {
            line = null;
        }
        out.println(line);
        out.flush();
        // Cerrar streams de E/S y después el socket de atención al cliente
        System.out.println("Cerrando recursos de comuniación...");
        System.out.flush();
        
        try{
        	out.close();
            scanner.close();
        	clientSocket.close();
        	System.out.println("¡Cliente atendido con éxito!");
            System.out.flush();
        }catch(IOException e){
        	e.printStackTrace();
        }
        
		
	}

}

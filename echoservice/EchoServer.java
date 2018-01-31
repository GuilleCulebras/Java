package urjc.ist.echoservice;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;

public class EchoServer {	
	
	private static final int NCLIENTES = 5;
	
    public static void main(String[] args) throws Exception {
    	
    	ArrayList<Thread> hilos = new ArrayList<Thread>();
        // Crear el socket del servidor
        int port = 15000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Inciado servidor en el puerto " + port);
        System.err.flush();
        
        // Esperar a las conexiones de los clientes y procesar
        // Procesa NCLIENTES conexiones entrantes antes de finalizar
        for (int count = 1; count <= NCLIENTES; count++) {
        	System.err.println("Esperando nuevo cliente...");
        	System.err.flush();
            // La llamada a accept() es bloqueante hasta que llegue un cliente
            Socket clientSocket = serverSocket.accept();
            System.err.println("Aceptada nueva conexión de un cliente, procesando...");
            System.err.flush();
            //Creamos un nuevo thread
            
            WorkerRunnable wR = new WorkerRunnable(clientSocket);
            Thread hilo = new Thread(wR);
            hilo.start();
            hilos.add(hilo);            
        }
        //Cerramos los threads
        for (Thread elem : hilos){
        	elem.join();
        }
        
        System.err.println("Ya he atendido a " + NCLIENTES + "fin del programa");
        System.err.flush();
        System.err.println("Liberando el socket del servidor...");
        System.err.flush();
        serverSocket.close();
        System.err.println("Finaliza el servidor");
        System.err.flush();
    }
}
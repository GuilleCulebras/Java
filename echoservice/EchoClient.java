package urjc.ist.echoservice;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EchoClient {
	
	private static final String CHARSET_NAME = "UTF-8";

    public static void main(String[] args) throws Exception {
        String login = "Ortega";
        String host = "localhost";
        int port = 15000;
        Scanner scanner;
        PrintWriter out;
        
        // Conectar al servidor y abrir sockets y streams
        Socket socket = new Socket(host, port);
        
        // Configurar streams de E/S
        Scanner stdin = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
        try {
            InputStream is = socket.getInputStream();
            scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
            out = new PrintWriter(socket.getOutputStream(), true);
        }
        catch (IOException ioe) {
        	socket.close(); stdin.close();
            throw new IllegalArgumentException("Imposible crear " + socket, ioe);
        }
        
        System.err.println("Connectado al servidor" + host + " en el puerto " + port);

        // Comunicación con el servidor a través del socket
        String s;
        System.out.print("Introduce el mensaje: ");
        // Leer línea desde consola
        try {
        	s = stdin.nextLine();
        }
        catch (NoSuchElementException e) {
        	s = null;
        }
        //System.out.println("Leído: " + "[" + login + "]: " + s);

        // Enviar la línea al servidor
        out.println("[" + login + "]: " + s);

        String line = null;
        try {
        	line = scanner.nextLine();
        }
        catch (NoSuchElementException e) {
        	line = null;
        }
        System.out.println(line);
        
        // Cerrar streams de E/S y después el socket
        System.err.println("Cerrando la conexión a " + host);
        out.close();
        scanner.close();
        socket.close();
        stdin.close();
        System.err.println("Recursos liberados, finaliza el programa cliente.");
    }
}
package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {

	
	private final String HOST="localhost";
	private final int PUERTO=9876;
	private Socket socket;
	Scanner teclado;
	
	public Client() throws IOException {
		
		socket=new Socket(HOST,PUERTO);
	}
	
	public void iniciarClient() throws IOException {
		
		//Instanciamos un objeto de la clase Scanner para poder introducir mensajes por consola
		teclado=new Scanner(System.in);
		
		//Iniciamos la entrada de datos desde el servidor
		DataInputStream entradaServidor= new DataInputStream(socket.getInputStream());		
		
		//Iniciamos envio de datos hacia el servidor
		DataOutputStream salidaServidor=new DataOutputStream(socket.getOutputStream());
		
		String datos="";		
		
		//En este bucle recbimos los primeros mensajes del servidor donde nos pide nuestro
		//nombre y el número de tareas a realizar. 
		for(int i=0;i<2;i++) {
			System.out.println("Recibiendo mensaje del servidor");
			System.out.println(entradaServidor.readUTF());			
			datos=teclado.nextLine();
			salidaServidor.writeUTF(datos);						
			System.out.println("Enviado mensaje al servidor: {"+datos+"}");			
		}		
		
		//Con el número de tareas mandadas al servidor lo guardamos en una variable parseando
		//el String a entero.
		int tareas=Integer.parseInt(datos);
			
		//EN este bucle recibimos los mensajes del servidor y enviamos la descripcion y el estado 
		//de las tareas. Por eso en el primer bucle for el límite será el numero de tareas totales
		//y el segundo bucle for sólo se recorre dos veces (una por la descripción y otra por el estado).
		for(int i=0;i<tareas;i++) {
			System.out.println(entradaServidor.readUTF());
			//salidaServidor.writeUTF("ok");
			for(int j=0;j<2;j++) {
				System.out.println("Leyendo mensaje del servidor");
				System.out.println(entradaServidor.readUTF());
				datos=teclado.nextLine();
				salidaServidor.writeUTF(datos);
				System.out.println("Enviado mensaje al servidor: {"+datos+"}");				
			}			
		}
		
		//Una vez enviadas todas las tareas, recibimos desde el servidor el listado de las tareas que le pedimos.
		for(int i=0;i<tareas+1;i++) {
			System.out.println("Leyendo mensaje del servidor");
			System.out.println(entradaServidor.readUTF());
		}
		
		System.out.println("Cerrando cliente");
		
		//Cerramos los flujos y el socket
		salidaServidor.close();
		entradaServidor.close();
		socket.close();
		
		
	}
	
	
}

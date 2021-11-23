package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	private final int PUERTO=9876;
	private ServerSocket serverSocket;
	private Socket socket;
	
	public Server() throws IOException {
		//Definimos la conexion pasando el puerto
		serverSocket=new ServerSocket(PUERTO);
		//Iniciamos el cliente
		socket=new Socket();
	}
	
	//Funcion que inicia la conexion del servidor
	public void iniciarServer() throws IOException {
		
		//Mensajes locales de inicialización
		System.out.println("Iniciando servidor");
		int i=1;
		
		//Si quisieramos que el servidor quedase a la espera de nuevos clientes lo haríamos con 
		//el bucle while habilitado.
		//while(true) {
		

			System.out.println("Esperando "+i+"r cliente");
			
			//Guardamos la peticion que llega al servidor en socket
			//El servidor se queda a la espera de recibir peticiones			
			socket=serverSocket.accept();				
			//Una vez que recibimos la petición, iniciamos la conexion
			
			//Creamos los flujos de entrada y salida de datos del servidor
			DataOutputStream salidaCliente=new DataOutputStream(socket.getOutputStream());
			
			DataInputStream entradaCliente=new DataInputStream(socket.getInputStream());
			
			//Enviamos mensaje bienvenida al cliente
			salidaCliente.writeUTF("Bienvenido, ¿como te llamas?");			
			
			//Recibimos el mensaje del cliente y lo guardamos en la variable mensajeDeCliente
			String mensajeDeCliente=entradaCliente.readUTF();
			
			System.out.println("Encantado de verte, "+mensajeDeCliente);
			
			salidaCliente.writeUTF("¿Cuantas tareas has de realizar?");
			
			String numero_tareas=entradaCliente.readUTF();
			
			System.out.println("Se han recibido "+numero_tareas+" tareas");
			
			//Guardamos el numero de tareas recibidas en una variable de tipo entero, parseando el string recibido
			int tareas=Integer.parseInt(numero_tareas);
			
			//Creamos un array de Tarea con el tamaño que nos pasa el cliente
			Tarea [] lista=new Tarea[tareas];
			
			String descripcion;
			String estado;
			
			//Con este bucle le mandamos el mensaje de la tarea que debe introducir y recibimos y mandamos 
			//los mensajes correspondientes con el cliente. Recibimos la descripcion y el estado y lo almacenamos en 
			//una nueva instancia de la clase Tarea y la metemos en la posición del array de tareas
			//
			for(int j=1;j<=tareas;j++) {
				salidaCliente.writeUTF("Introduccion de la tarea "+j);				
				salidaCliente.writeUTF("Introduce la descripcion:");
				descripcion=entradaCliente.readUTF();
				System.out.println("Descripcion recibida: "+descripcion);
				salidaCliente.writeUTF("Introduce el estado:");
				estado=entradaCliente.readUTF();
				System.out.println("Estado recibido: "+estado);					
				lista[j-1]=new Tarea(descripcion,estado);				
			}
			
			//Le mandamos al cliente el listado de las tareas que nos ha mandado
			System.out.println("Listado de tareas");
			salidaCliente.writeUTF("Listado de tareas");
			
			//Con un bucle for each le mandamos los mensajes al cliente con el método toString de la clase Tarea
			for(Tarea tarea:lista) {
				salidaCliente.writeUTF(tarea.toString());
			}			
			
			//Cerramos los flujos y el socket
			salidaCliente.close();
			entradaCliente.close();
            socket.close();					
					
		//}
		
	}
	
	//Finalizar conexion
	public void finaliazarServidor() throws IOException {
		serverSocket.close();
	}
	

}

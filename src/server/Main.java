package server;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//Definimos un objeto servidor
		Server servidor=new Server();
		
		//Iniciamos el servidor
		servidor.iniciarServer();
		
		//Finalizamos servidor
		servidor.finaliazarServidor();
		
	}

}

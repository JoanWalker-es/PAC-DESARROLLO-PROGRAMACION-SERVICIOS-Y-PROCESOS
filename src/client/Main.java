package client;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		//Creamos objeto cliente
		Client cliente=new Client();
		System.out.println("Iniciando cliente");
		
		//Iniciamos la conexion
		cliente.iniciarClient();
		
	}

}

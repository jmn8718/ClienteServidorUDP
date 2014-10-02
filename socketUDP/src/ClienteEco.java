import java.io.*;
import java.util.*;

public class ClienteEco extends ClienteUDP{
	
	public ClienteEco(){
		super();
	}
	
	public void pedirServidor(){
		System.out.println("Introduzca los datos del servidor");
		this.dirIP = this.getString("DIRECCION IP");
		this.puerto = this.getPuerto();
		this.establecerServidor(dirIP, puerto);
	}

	public void enviarMensaje(){
		String mensaje = this.getString("MENSAJE");
		this.enviar(mensaje);
		
		System.out.println(this.recibir());
	}
	
	private int getPuerto(){
		int resul=0;
		
		System.out.print("Puerto: ");
		Scanner sc = new Scanner(System.in);
		resul = Integer.parseInt(sc.nextLine());
		
		return resul;
	}
	
	private String getString(String mensaje){
		String resul = new String();
		
		InputStreamReader flujo = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(flujo);
		System.out.print(mensaje+": ");
		try {
			resul = teclado.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("\n----NO SE HA LEIDO "+mensaje);
		} 
		return resul;
	}
	
	public static void main(String[] args) throws IOException {

		ClienteEco cliente = new ClienteEco();
		cliente.pedirServidor();
		cliente.enviarMensaje();
		cliente.cerrarCliente();
	}

}

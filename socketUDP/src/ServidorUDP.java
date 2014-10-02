import java.net.*;
import java.io.*;

public class ServidorUDP {
	public static String LOCALHOST = "localhost";
	public static int PORT = 10001;
	public static int SIZE = 512;
	private DatagramSocket servidorUDP;
	private byte [] buffer;
	
	public ServidorUDP(){
		try {
			this.servidorUDP = new DatagramSocket(ServidorUDP.PORT);
		} catch (SocketException e) {
			System.out.println("\n----NO SE HA CREADO EL SERVIDOR");
		}
		this.buffer = new byte[ServidorUDP.SIZE];
	}
	
	public void recibir(){
		DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
		try {
			this.servidorUDP.receive(paqueteRecibido);
		} catch (IOException e) {
			System.out.println("\n----NO SE HA RECIVIDO EL MENSAJE");
			return;
		}
		System.out.print("Datagrama recibido del host: "+paqueteRecibido.getAddress());
		System.out.println(" desde el puerto remoto: " +paqueteRecibido.getPort());
		System.out.write(paqueteRecibido.getData(),0,paqueteRecibido.getLength());
		
		this.enviarRespuesta("MENSAJE RECIVIDO", paqueteRecibido);
	}
	
	public void enviarRespuesta(String mensaje, DatagramPacket paqueteRecibido){
		DatagramPacket respuesta =
		          new DatagramPacket(mensaje.getBytes(), mensaje.length(),
		        		  paqueteRecibido.getAddress(), paqueteRecibido.getPort());
		try {
			this.servidorUDP.send(respuesta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("\n----NO SE HA ENVIADO LA RESPUESTA");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServidorUDP servidorUDP = new ServidorUDP();
		while (true){
			servidorUDP.recibir();

	        System.out.println("\n---------");
		}
	}

}

import java.net.*;
import java.io.*;

public class ClienteUDP {
	protected int SIZE=512;
	protected String dirIP;
	protected int puerto;
	private DatagramSocket socketCliente;
	private InetAddress direccionHostServidor;
	
	public ClienteUDP(){
		try {
			this.socketCliente = new DatagramSocket();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
	}
	
	protected void establecerServidor(String dirIP, int puerto){
		this.dirIP = dirIP;
		this.puerto = puerto;
		try {
			this.direccionHostServidor = InetAddress.getByName(this.dirIP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	public void enviar(String mensaje){
		byte [] datosDatagrama = new byte [SIZE];
		datosDatagrama = mensaje.getBytes();
		DatagramPacket paqueteEnvio = new DatagramPacket(datosDatagrama, datosDatagrama.length, this.direccionHostServidor , this.puerto);
		try {
			this.socketCliente.send(paqueteEnvio);
		} catch (IOException e) {
			System.out.println("\n----NO SE HA PODIDO ENVIAR EL MENSAJE");
		}
	}
	
	public String recibir(){
		String mensaje = new String("NO HAY MENSAJES");
		DatagramPacket paqueteRecibido = new DatagramPacket(new byte [SIZE], SIZE);
		try {
			this.socketCliente.setSoTimeout(2000);
			this.socketCliente.receive(paqueteRecibido);
		} catch (IOException e) {
			System.out.println("\n----NO SE HA RECIBIDO EL MENSAJE EN EL TIEMPO ESTIMADO");
			return new String("ERROR");
		}
		//System.out.write(paqueteRecibido.getData(),0,paqueteRecibido.getLength());
		mensaje = new String(paqueteRecibido.getData()).substring(0,paqueteRecibido.getLength());
		//System.out.println("\n-----\n"+mensaje+"\n-----\n");
		
		return mensaje;
	}
	
	public void cerrarCliente(){
		this.socketCliente.close();
	}
	/*
	public static void main(String[] args) {
		//System.out.println("Cliente UDP");
		String dirIP = "localhost";
		int port = 10001;
		byte [] datosDatagrama = new byte [512];
		try {
			DatagramSocket socketCliente = new DatagramSocket();
			
			String mensaje = "mensaje de Jose Miguel\n";
			//System.out.println(mensaje);
			datosDatagrama = mensaje.getBytes();
			InetAddress direccionHostServidor = InetAddress.getByName(dirIP);
			//System.out.println(direccionHostServidor.toString());
			DatagramPacket paqueteEnvio = new DatagramPacket(datosDatagrama, datosDatagrama.length, direccionHostServidor , port);
			socketCliente.send(paqueteEnvio);
			DatagramPacket paqueteRecibido = new DatagramPacket(new byte [512], 512);
			socketCliente.receive(paqueteRecibido);
			
			System.out.write(paqueteRecibido.getData(),0,paqueteRecibido.getLength());
			//System.out.println("//"+new String(paqueteRecibido.getData()));
		
		} catch (SocketException e) {
			System.out.println("error en socket "+e);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}

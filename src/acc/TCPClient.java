package acc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) {
		 Socket socket = null ;
		 try {
		 socket = new Socket();
		 System.out.println( "[Client] Request connect to Server" );
		 socket.connect( new InetSocketAddress( "192.168.0.18", 10001 ) );
		 System.out.println( "[Client] Connect Success to Server" );
		
		 } catch (IOException e) {
		 e.printStackTrace();
		 } finally {
		 if( socket != null && socket.isClosed() == false ) {
		 try {
		 socket.close();
		 } catch( IOException ex ) {
		 ex.printStackTrace();
		 }
		 }
		 }
	 }
}
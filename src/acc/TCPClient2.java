package acc;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient2 {

  public static void main(String[] args) {
    Socket socket = null;
    
    try {
      
      socket = new Socket();
      System.out.println("[Client] Request connect to Server ");
      socket.connect(new InetSocketAddress("localhost", 20000));
      System.out.println("[Client] Connect Success to Server ");
      
      //데이터 쓰기
      OutputStream os = socket.getOutputStream();
      String data = "Hello Socket !!";
      os.write( data.getBytes("UTF-8") );
      os.close();
      
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

}

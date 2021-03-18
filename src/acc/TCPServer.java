package acc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

  public static void main(String[] args) {
    ServerSocket serverSocket = null;
    try {
      
      serverSocket = new ServerSocket();
      serverSocket.bind(new InetSocketAddress("192.168.0.18", 10001));
      System.out.println("[Server] socket create complete!!");
      Socket socket = serverSocket.accept();
      InetSocketAddress isAdd = (InetSocketAddress)socket.getRemoteSocketAddress();
      System.out.println("[Server] client["+isAdd.getHostName() + "](PORT:" + isAdd.getPort() + ")"
                        +" connect ... accept complete!!");
//      // 클라이언트 소켓으로 데이터를 보내기 위한 준비
//      OutputStream os = socket.getOutputStream();
//      
//      String data = "소켓 프로그래밍 Socket !!!!!!!!!!!!!!!!!!!";
//      // 인터넷으로 데이터를 보내기 위해 변환( UTF -> Byte[] )
//      os.write( data.getBytes("UTF-8") );
//      // os 가 사용하는 버퍼를 출력(네트워크)쪽으로 보내기(비움)
//      os.flush();
//      os.close();
//      
      
      // 클라이언트 소켓으로부터 데이터를 받기 위한 준비
      InputStream is = socket.getInputStream();
      while( true ) {
        byte[] buffer = new byte[128]; // 버퍼크기 128 바이트 지

        // is에서 읽을때 buffer의 크기만큼 읽어서 buffer에 넣고 결과(읽은 바이트 수)를 readByteCount로 리턴 한다
        int readByteCount = is.read ( buffer ); 
        
        if( readByteCount < 0) { // 읽은 바이트 크기가 0 미만이면... 클라이언트 연결 끊김
          System.out.println("[Server] Client disconneted !!");
          is.close();
          socket.close();
          break;
        }
        
        String data2 = new String( buffer, 0,readByteCount,"UTF-8" );
        System.out.print(data2);
      }
      
      
      
      
      
      
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if(serverSocket != null && serverSocket.isClosed() == false) {
        try { serverSocket.close(); } catch (IOException e) { e.printStackTrace(); }
      }
      
    }
    
    
  }

}

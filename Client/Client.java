import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client{

public static void main(String[] args) throws Exception 
    {
        try (var socket = new Socket("127.0.0.1", 59896)) 
        {
        	DataInputStream in = new DataInputStream(socket.getInputStream());
        	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println(in);
        }
    }
}

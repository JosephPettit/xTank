// import java.io.DataInputStream;
// import java.io.DataOutputStream;
// import java.net.Socket;

// import javax.swing.JOptionPane;

// public class Client{
// private static ClientWindow clientWindow;
// public static void main(String[] args) throws Exception 
//     {

//         clientWindow = new ClientWindow();
//         String ipAddress = getIPAddress();

//         clientWindow.run(ipAddress);
//         clientWindow.setVisible(true);
//         // try (var socket = new Socket("127.0.0.1", 59896)) 
//         // {
//         // 	DataInputStream in = new DataInputStream(socket.getInputStream());
//         // 	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//         //     System.out.println(in);
//         // }
//         // clientWindow.dispose();
//     }

//     private static String getIPAddress() {
//         return JOptionPane.showInputDialog(clientWindow, "Enter Server IP Address", "Server Address",
//                 JOptionPane.QUESTION_MESSAGE);
//     }
// }

// import java.net.InetAddress;
// import java.net.ServerSocket;
// import java.net.Socket;
// import java.net.UnknownHostException;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.IOException;
// import java.io.ObjectInputStream;
// import java.io.ObjectOutputStream;

// import javax.swing.JFrame;
// import javax.swing.JPanel;
// import javax.swing.JOptionPane;
// import javax.swing.JTextArea;

// public class ClientWindow extends JFrame {

//     // String serverAddress;

//     // JTextArea serverMessages;
//     JPanel gamePanel;
//     Socket socket;

//     public ClientWindow() {
        
//         setSize(420, 420);
//         setTitle("X-Tank Client");
//         setResizable(false);
//         gamePanel = new JPanel();
//         add(gamePanel);
//     }

//     public void run(String serverAddress) throws UnknownHostException, IOException{
        
//         socket = new Socket(serverAddress, 59896);
        
//         ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
//         ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());    

//     }

//     private int IPNotConnected() {
//         return JOptionPane.showConfirmDialog(this, "The Server IP address entered was not located. \nTry again?",
//                 "Server Address Not Found",
//                 JOptionPane.YES_NO_OPTION);
//     }
// }

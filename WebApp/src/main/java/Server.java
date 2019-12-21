import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;


public class Server {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8888, 1, InetAddress.getLocalHost())) {

            System.out.println(
                    "Server is in: "
                    + serverSocket.getInetAddress().getHostAddress()
                    + ":" + serverSocket.getLocalPort()
            );
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("NewClient");

                new ServerThread(socket).start();
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;


public class ServerThread extends Thread {
    private Socket socket;
    private LinkedList<String> logs = new LinkedList<>();

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);


            String text = "";

            while (!text.equals("exit")) {
                text = reader.readLine();
                if (text.equals("/logs")) {
                    writer.print(logs);
                }
                String result = "";
                try {
                    result = "Result: " + Calculator.process(text) + '\n';
                } catch (Exception e) {
                    result = "InvalidCommand\n";
                }
                logs.add(text + " " + result);
                writer.println(result);
            }
            writer.println("GoodBay");

            socket.close();
        } catch (IOException ex) {
            System.out.println("ServerException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
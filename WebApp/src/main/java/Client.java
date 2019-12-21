import java.net.*;
import java.io.*;


public class Client {

    public static void main(String[] args) {

        String hostname = "192.168.8.109";
        int port = 8888;

        try (Socket socket = new Socket(hostname, port)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);


            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String text;
            do {
                text = reader.readLine();
                writer.println(text);

                InputStream input = socket.getInputStream();
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(input));

                String result = reader2.readLine();

                System.out.println(result);

            } while (!text.equals("exit"));

            socket.close();

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
